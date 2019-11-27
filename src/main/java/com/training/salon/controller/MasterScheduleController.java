package com.training.salon.controller;

import com.training.salon.entity.Master;
import com.training.salon.entity.Schedule;
import com.training.salon.entity.User;
import com.training.salon.service.MailSender;
import com.training.salon.service.MasterService;
import com.training.salon.service.ScheduleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.training.salon.controller.IConstant.DAYS_iN_SCHEDULE;

@Controller
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MASTER')")
public class MasterScheduleController {

    private final MasterService masterService;
    private ScheduleService scheduleService;
    private MailSender mailSender;


    public MasterScheduleController(MasterService masterService, ScheduleService scheduleService, MailSender mailSender) {
        this.masterService = masterService;
        this.scheduleService = scheduleService;
        this.mailSender = mailSender;
    }

    @GetMapping("/master/schedule")
    public String getMasters(Model model,
                             @AuthenticationPrincipal User user,
                             @RequestHeader(required = false) String referer,
                             @RequestParam(required = false) Long masterId) {
        Optional<Master> master;
        if(Optional.ofNullable(masterId).isEmpty() && user.isAdmin())
            return "redirect:/master/schedule?" + UriComponentsBuilder.fromHttpUrl(referer).build().getQuery();
        if (user.isAdmin()) {
            master = masterService.findById(masterId);
        } else
            master = masterService.findByUserId(user.getId());

        if (master.isEmpty()) return "redirect:/";
        model.addAttribute("schedule", scheduleService.getScheduleForMaster(master.get().getId()));
        model.addAttribute("dates", Stream.iterate(LocalDate.now(), curr -> curr.plusDays(1))
                .limit(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(DAYS_iN_SCHEDULE)))
                .collect(Collectors.toList()));

        model.addAttribute("workTime", Stream.iterate(master.get().getTimeStart(), curr -> curr.plusHours(1))
                .limit(ChronoUnit.HOURS.between(master.get().getTimeStart(), master.get().getTimeEnd()))
                .collect(Collectors.toList()));
        return "/master/schedule";
    }

    @GetMapping("/master/sendemail")
    public String makedone(@RequestParam Long scheduleId) {

        scheduleService.makeNoteDone(scheduleId);
        Optional<Schedule> scheduleNote = scheduleService.findById(scheduleId);
        scheduleNote.ifPresent(schedule -> mailSender.send(schedule.getUser().getEmail(), schedule.getMaster().getId()));
        return "redirect:/master/schedule";
    }
}
