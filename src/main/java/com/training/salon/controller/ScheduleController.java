package com.training.salon.controller;

import com.training.salon.entity.Master;
import com.training.salon.entity.Schedule;
import com.training.salon.entity.User;
import com.training.salon.exception.BookException;
import com.training.salon.exception.DiscrepancyException;
import com.training.salon.service.MasterService;
import com.training.salon.service.ProcedureService;
import com.training.salon.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.training.salon.controller.IConstant.DAYS_iN_SCHEDULE;


@Controller
@SessionAttributes({"schedule"})
public class ScheduleController {

    private final MasterService masterService;
    private final ScheduleService scheduleService;
    private final ProcedureService procedureService;

    public ScheduleController(MasterService masterService, ScheduleService scheduleService, ProcedureService procedureService) {
        this.masterService = masterService;
        this.scheduleService = scheduleService;
        this.procedureService = procedureService;
    }
    @Autowired
    private MessageSource messageSource;

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @ModelAttribute
    LocalDate initLocalDate() {
        return LocalDate.now();
    }

    @ModelAttribute
    LocalTime initLocalTime() {
        return LocalTime.now();
    }

    @ModelAttribute("schedule")
    public Schedule setSchedule() {
        return new Schedule();
    }

    @GetMapping("/user/booking")
    public String getMasters(Model model,
                             Locale locale,
                             @RequestParam(required = false) Long masterId,
                             @RequestParam(required = false) Long procedureId,
                             @ModelAttribute Schedule schedule,
                             @RequestHeader(required = false) String referer) {

        if(Optional.ofNullable(masterId).isEmpty())
            return "redirect:/user/booking?"+ UriComponentsBuilder.fromHttpUrl(referer).build().getQuery();
        Optional<Master> master = masterService.findById(masterId);
        if (master.isEmpty()) return "redirect:/user/masterlist";

        master.ifPresent(schedule::setMaster);
        model.addAttribute("schedule", schedule);
        model.addAttribute("busySchedule", scheduleService.getScheduleForMaster(masterId));

        model.addAttribute("scheduleDate", Stream.iterate(LocalDate.now(), curr -> curr.plusDays(1))
                .limit(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(DAYS_iN_SCHEDULE)))
                .collect(Collectors.toList()));

        model.addAttribute("availableTime", Stream.iterate(master.get().getTimeStart(), curr -> curr.plusHours(1))
                .limit(ChronoUnit.HOURS.between(master.get().getTimeStart(), master.get().getTimeEnd()))
                .collect(Collectors.toList()));

        try {
            masterService.isProcedureAccordToMaster(masterId, procedureId);
        } catch (DiscrepancyException e) {
            model.addAttribute("discrepancy", messageSource.getMessage("procedure.error", null, locale));
            return "/user/booking";
        }
        procedureService.findProcedureById(procedureId).ifPresent(schedule::setProcedure);
        return "/user/booking";
    }

    @GetMapping("/user/order")
    public String getMasters(Model model,
                             Locale locale,
                             @RequestParam(required = false) String timeOrder,
                             @RequestParam(required = false) String dateOrder,
                             @RequestHeader(required = false) String referer,
                             @ModelAttribute Schedule schedule,
                             @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        if(Optional.ofNullable(timeOrder).isEmpty())
            return "redirect:/user/order?"+ UriComponentsBuilder.fromHttpUrl(referer).build().getQuery();
        LocalDate date = LocalDate.parse(dateOrder);
        try {
            masterService.checkTimeForMaster(LocalTime.parse(timeOrder), schedule.getMaster().getId());
            if (date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().plusDays(DAYS_iN_SCHEDULE)))
                throw new DiscrepancyException();
        } catch (DiscrepancyException e) {
            model.addAttribute("timeError",  messageSource.getMessage("unavailable.time", null, locale));
            return "/user/order";
        }
        schedule.setDate(LocalDate.parse(dateOrder));
        schedule.setTime(LocalTime.parse(timeOrder));
        schedule.setUser(user);
        return "/user/order";
    }

    @GetMapping("/user/save")
    public String getMasters(Model model,
                             Locale locale,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @ModelAttribute Schedule schedule) {
        schedule.setClientFirstName(firstName);
        schedule.setClientLastName(lastName);
        schedule.setDone(false);
        try {
            scheduleService.saveToSchedule(schedule);
        } catch (BookException e) {
            model.addAttribute("alreadyBooked", messageSource.getMessage("already.booked", null, locale));
            return "/user/order";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorOrder", messageSource.getMessage("unable.to.create.record", null, locale));
            return "/user/order";
        }
        return "redirect:/user/successpage";
    }

    @GetMapping("/user/successpage")
    public String getSuccess(Model model){
        return "/user/successpage";
    }

}
