package com.example.sellers.service.impl;

import com.example.sellers.model.entity.ProductEntity;
import com.example.sellers.model.entity.SaleEntity;
import com.example.sellers.model.entity.UserEntity;
import com.example.sellers.repository.SaleRepository;
import com.example.sellers.service.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<SaleEntity> findAllByUserAndDateBetween(UserEntity userEntity, LocalDate fromDate, LocalDate toDate) {
        return saleRepository.findAllByUserEntityAndDateOfSaleBetween(userEntity, fromDate, toDate).orElseThrow();
    }

    @Override
    public SaleEntity createSale(List<ProductEntity> productEntities , UserEntity userEntity) {
        SaleEntity saleEntity =
                new SaleEntity().setProducts(productEntities).setDateOfSale(LocalDate.now()).setUserEntity(userEntity);

        return saleRepository.save(saleEntity);
    }
}
