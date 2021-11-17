package com.example.sellers.service;

import com.example.sellers.model.view.StatsView;

public interface StatsService {

    void onRequest();

    StatsView getStats();
}
