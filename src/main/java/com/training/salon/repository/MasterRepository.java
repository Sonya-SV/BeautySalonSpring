package com.training.salon.repository;

import com.training.salon.entity.Master;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface MasterRepository extends JpaRepository<Master,Long> {
    Page<Master> findAllMastersByCategoryId(Long categoryId, Pageable pageable);

    Optional<Master> findByUserId(Long userId);

    @Query("select m from Master m join m.category c join c.procedures p where p.id= :procedureId and m.id= :masterId")
    Optional<Master> findByProcedureIdAndMasterId(@Param("procedureId") Long procedureId, @Param("masterId") Long masterId);

    Optional<Master> findByIdAndTimeEndGreaterThanAndTimeStartLessThanEqual(Long masterId, LocalTime time, LocalTime time1);
}
