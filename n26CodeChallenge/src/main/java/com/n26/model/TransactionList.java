package com.n26.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionList {

	private List<Long> transactionIds;

	public TransactionList() {
		this.transactionIds = new ArrayList<>();
	}

	public void addTransactionId(long id) {
		Long transactionId = new Long(id);
		this.transactionIds.add(transactionId);
	}

	public List<Long> getTransactionIds() {
		return transactionIds;
	}
}