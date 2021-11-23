package com.example.sellers.service.impl;

import com.example.sellers.model.entity.StoreEntity;
import com.example.sellers.model.entity.VisitorEntity;
import com.example.sellers.repository.VisitorRepository;
import com.example.sellers.service.VisitorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }


    @Override
    public Integer countStoreVisitorsBetweenDate(String storeName, LocalDate fromDate, LocalDate toDate) {
        return visitorRepository.countVisitorsBetweenDate(storeName, fromDate, toDate);
    }

    @Override
    public void addTestVisitors(List<StoreEntity> stores) {
        //ToDo work only for November
        LocalDate dayOfMonth = LocalDate.of(2021, 11, 1);
        int countDays = LocalDate.now().getDayOfMonth();
        for (int i = 1; i <= countDays; i++) {
            for (StoreEntity store : stores) {
                int quantity = ThreadLocalRandom.current().nextInt(100, 500);
                VisitorEntity visitors =
                        new VisitorEntity()
                                .setStore(store)
                                .setDate(dayOfMonth)
                                .setQuantity(quantity);

                visitorRepository.save(visitors);
            }
            dayOfMonth = dayOfMonth.plusDays(1);
        }
    }
}
