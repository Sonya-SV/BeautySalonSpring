package com.training.salon.repository;

import com.training.salon.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {

    @Query("select p from Category c join c.masters m join c.procedures p where m.id= :masterId")
    List<Procedure> findByMaster(@Param("masterId") Long masterId);

    List<Procedure> findAllProceduresByCategoryId(Long categoryId);
}
