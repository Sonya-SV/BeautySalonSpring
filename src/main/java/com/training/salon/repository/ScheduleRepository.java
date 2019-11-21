package com.training.salon.repository;

import com.training.salon.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByMasterId(Long masterId);

    Optional<Schedule> findByMasterIdAndDateAndTime(Long masterId, LocalDate date, LocalTime time);

    @Transactional
    @Modifying
    @Query("update Schedule s set s.isDone=:done where s.id=:scheduleId")
    void setDoneByScheduleId(@Param("scheduleId") Long scheduleId, @Param("done") boolean done);
}
