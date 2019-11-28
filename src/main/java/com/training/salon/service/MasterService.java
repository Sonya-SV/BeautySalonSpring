package com.training.salon.service;

import com.training.salon.entity.Master;
import com.training.salon.exception.DiscrepancyException;
import com.training.salon.repository.MasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MasterService {

    private final MasterRepository masterRepository;

    public MasterService(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    public List<Master> findAll() {
        return masterRepository.findAll();
    }

    public Page<Master> findAll(Pageable pageable) {
        return masterRepository.findAll(pageable);
    }

    public Optional<Master> findById(Long masterId) {
        return masterRepository.findById(masterId);
    }

    public List<Master> getMastersByCategory(Long categoryId) {
        return masterRepository.findAllMastersByCategoryId(categoryId);
    }

    public Optional<Master> findByUserId(Long userId) {
        return masterRepository.findByUserId(userId);
    }

    public void isProcedureAccordToMaster(Long masterId, Long procedureId) throws DiscrepancyException {
        masterRepository.findByProcedureIdAndMasterId(procedureId, masterId).orElseThrow(DiscrepancyException::new);
    }

    public void checkTimeForMaster(LocalTime time, Long masterId) throws DiscrepancyException {
        Optional<Master> masterCheck = masterRepository.findByIdAndTimeEndGreaterThanAndTimeStartLessThanEqual(masterId, time, time);
        if (masterCheck.isEmpty())
            throw new DiscrepancyException();

    }
}
