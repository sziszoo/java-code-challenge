package com.n26;

import com.n26.model.Transaction;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByType(String type);
	Transaction findByTransactionId(long transactionId);
	List<Transaction> findByParentId(long parentId);
}