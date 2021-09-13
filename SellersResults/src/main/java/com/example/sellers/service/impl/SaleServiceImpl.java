package com.example.sellers.service.impl;

import com.example.sellers.repository.SaleRepository;
import com.example.sellers.service.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
    }
}
