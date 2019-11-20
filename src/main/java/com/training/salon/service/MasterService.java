package com.training.salon.service;

import com.training.salon.entity.Master;
import com.training.salon.repository.MasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MasterService  {

    private final MasterRepository masterRepository;

    public MasterService(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    public List<Master> findAll(){
        return masterRepository.findAll();
    }
    public Optional<Master> findById(Long masterId){
        return masterRepository.findById(masterId);
    }

    public List<Master> getMastersByCategory(Long categoryId) {
        return masterRepository.findAllMastersByCategoryId(categoryId);
    }
}
