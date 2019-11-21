package com.training.salon.controller;

import com.training.salon.entity.Master;
import com.training.salon.entity.Procedure;
import com.training.salon.entity.Schedule;
import com.training.salon.entity.User;
import com.training.salon.exception.BookException;
import com.training.salon.exception.DiscrepancyException;
import com.training.salon.service.MasterService;
import com.training.salon.service.ProcedureService;
import com.training.salon.service.ScheduleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.training.salon.controller.ITextConstant.*;

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

    @ModelAttribute("schedule")
    public Schedule setSchedule() {
        return new Schedule();
    }

    @GetMapping("/user/booking")
    public String getMasters(Model model,
                             @RequestParam Long masterId,
                             @RequestParam Long procedureId,
                             @ModelAttribute Schedule schedule) {
        Optional<Master> master = masterService.findById(masterId);
        if (master.isEmpty())
            return "redirect:/user/masterlist";

//        Schedule schedule = new Schedule();
        master.ifPresent(schedule::setMaster);
//        model.addAttribute("schedule", schedule);
        model.addAttribute("busySchedule", scheduleService.getScheduleForMaster(masterId));

        model.addAttribute("scheduleDate", Stream.iterate(LocalDate.now(), curr -> curr.plusDays(1))
                .limit(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(7)))
                .collect(Collectors.toList()));

        model.addAttribute("availableTime", Stream.iterate(master.get().getTimeStart(), curr -> curr.plusHours(1))
                .limit(ChronoUnit.HOURS.between(master.get().getTimeStart(), master.get().getTimeEnd()))
                .collect(Collectors.toList()));

        Optional<Procedure> procedure = procedureService.findProcedureById(procedureId);
        if (procedure.isEmpty())
            model.addAttribute("errorProcedure", PROCEDURE_ERROR);
        else
            schedule.setProcedure(procedure.get());
        return "/user/booking";
    }

    @GetMapping("/user/order")
    public String getMasters(Model model,
                             @RequestParam String time,
                             @RequestParam String dateOrder,
                             @ModelAttribute Schedule schedule,
                             @AuthenticationPrincipal User user) {
        schedule.setDate(LocalDate.parse(dateOrder));
        schedule.setTime(LocalTime.parse(time));
        schedule.setUser(user);
        return "/user/order";
    }

    @GetMapping("/user/save")
    public String getMasters(Model model,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @ModelAttribute Schedule schedule) {

        schedule.setClientFirstName(firstName);
        schedule.setClientLastName(lastName);
        schedule.setDone(false);
        try {
            scheduleService.saveToSchedule(schedule);
//            log.info("Note added to the master (" +schedule.getMaster().getUser().getFirstName() + ") Schedule");
        } catch (BookException e) {
//            log.warn("These date and time are already busy");
            model.addAttribute("alreadyBooked", ALREADY_BOOKED);
            return "/user/order";
        }
        return "/user/successpage";
    }
}
