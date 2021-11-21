package com.example.sellers.service.impl;

import com.example.sellers.repository.VisitorRepository;
import com.example.sellers.service.VisitorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }


    @Override
    public Integer countStoreVisitorsBetweenDate(String storeName, LocalDate fromDate, LocalDate toDate) {
        return visitorRepository.countVisitorsBetweenDate(storeName , fromDate , toDate);
    }
}
