package backendAPI.springboot.controller;

import java.util.DoubleSummaryStatistics;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backendAPI.springboot.service.TransactionService;
import backendAPI.springboot.dto.StatisticsResponse;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    
    private final TransactionService transactionService;

    public StatisticsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<StatisticsResponse> getStatistics() {
        DoubleSummaryStatistics statistics = transactionService.getStatistics();
        StatisticsResponse response = new StatisticsResponse(statistics);
        return ResponseEntity.ok(response);
    }   
}
