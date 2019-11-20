package com.training.salon.repository;

import com.training.salon.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterRepository extends JpaRepository<Master,Long> {
    List<Master> findAllMastersByCategoryId(Long categoryId);
}
