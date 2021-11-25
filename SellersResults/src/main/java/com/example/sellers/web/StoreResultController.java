package com.example.sellers.web;

import com.example.sellers.model.dto.StoreWeekResultDTO;
import com.example.sellers.service.StWRService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stores-results")
public class StoreResultController {

    private final StWRService stWRService;

    public StoreResultController(StWRService stWRService) {
        this.stWRService = stWRService;
    }

    @GetMapping("/week")
    public ResponseEntity<List<StoreWeekResultDTO>> getWeekResults() {
        List<StoreWeekResultDTO> storesWeekResults = stWRService.getAllStoresWeekResults();

        return ResponseEntity.ok(storesWeekResults);
    }

    @GetMapping("/week/{id}")
    public ResponseEntity<StoreWeekResultDTO> storeWeekResults(@PathVariable Long id) {
        Optional<StoreWeekResultDTO> results = stWRService.getById(id);

        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(results.get());
        }
    }

//    How to create object
//    @PostMapping()
//    public ResponseEntity<StoreWeekResultDTO> create(@RequestBody StoreWeekResultDTO storeWeekResultDTO,
//                                                     UriComponentsBuilder builder) {
//
//        long resultId = stWRService.create();
//
//        URI location = builder.path("/stores-results/week/{id}").buildAndExpand(resultId).toUri();
//
//        return ResponseEntity.created(location).build();
//    }

    //ToDo implement for Month and Year results
}
