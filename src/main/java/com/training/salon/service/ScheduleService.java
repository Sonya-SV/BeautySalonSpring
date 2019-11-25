package com.training.salon.service;


import com.training.salon.entity.Schedule;
import com.training.salon.exception.BookException;
import com.training.salon.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    public List<Schedule> getScheduleForMaster(Long masterId) {
        return scheduleRepository.findAllByMasterId(masterId);
    }

    private Optional<Schedule> checkScheduleByTimeAndMasterAndDate(Long masterId, LocalDate date, LocalTime time) {
        return scheduleRepository.findByMasterIdAndDateAndTime(masterId, date, time);
    }

    @Transactional
    public void saveToSchedule(Schedule schedule) throws BookException, DataIntegrityViolationException {
        Optional<Schedule> checkSchedule =
                this.checkScheduleByTimeAndMasterAndDate(schedule.getMaster().getId(),
                        schedule.getDate(), schedule.getTime());
        if (checkSchedule.isPresent()) {
            log.warn("Master is already busy at this time");
            throw new BookException();
        }
        scheduleRepository.save(schedule);
        log.info("Note added to the master (" + schedule.getMaster().getUser().getFirstName() + ") schedule");
    }

    public void makeNoteDone(Long scheduleId) {
        scheduleRepository.setDoneByScheduleId(scheduleId, true);
    }

    public Optional<Schedule> findById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId);
    }
}
