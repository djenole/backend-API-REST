package backendAPI.springboot.controller;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backendAPI.springboot.service.TransactionService;

import backendAPI.springboot.dto.TransactionRequest;
import backendAPI.springboot.model.Transaction;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    
   private final TransactionService transactionService;

   public TransactionController(TransactionService transactionService) {
       this.transactionService = transactionService;
   }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionRequest request) {
        if(request.getDataHora().isAfter(OffsetDateTime.now()) ||request.getValor() <= 0) {
            return ResponseEntity.unprocessableEntity().build();
        }
        transactionService.addTransaction(new Transaction(request.getValor(), request.getDataHora()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTransactions() {
        transactionService.clearTransactions();
        return ResponseEntity.ok().build();
    }  
}