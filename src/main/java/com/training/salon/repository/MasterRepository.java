package com.training.salon.repository;

import com.training.salon.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MasterRepository extends JpaRepository<Master,Long> {
    List<Master> findAllMastersByCategoryId(Long categoryId);

    Optional<Master> findByUserId(Long userId);

    @Query("select m from Master m join m.category c join c.procedures p where p.id= :procedureId and m.id= :masterId")
    Optional<Master> findByProcedureIdAndMasterId(@Param("procedureId") Long procedureId, @Param("masterId") Long masterId);

    Optional<Master> findByIdAndTimeEndGreaterThanAndTimeStartLessThanEqual(Long masterId, LocalTime time, LocalTime time1);
}
