package com.example.sellers.service;

import java.time.LocalDate;

public interface VisitorService {

    Integer countStoreVisitorsBetweenDate(String storeName , LocalDate fromDate , LocalDate toDate);
}
