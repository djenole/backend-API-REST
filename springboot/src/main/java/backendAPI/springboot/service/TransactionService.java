package backendAPI.springboot.service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

import backendAPI.springboot.model.Transaction;

@Service
public class TransactionService {
    
    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void clearTransactions() {
        transactions.clear();
    }

    public DoubleSummaryStatistics getStatistics() {
       OffsetDateTime now = OffsetDateTime.now();
       return transactions.stream()
                .filter(t -> t.getDataHora().isAfter(now.minusMinutes(60)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
    }
}
