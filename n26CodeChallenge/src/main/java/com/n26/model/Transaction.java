package com.n26.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Transaction {

	@Id
	@JsonProperty("transaction_id")
	private long transactionId;
	private double amount;
	private String type;
	@JsonProperty("parent_id")
	private Long parentId;

	public Transaction(long transactionId, double amount, String type,
			Long parentId) {

		this.transactionId = transactionId;
		this.amount = amount;
		this.type = type;

		if (parentId != null)
			this.parentId = new Long(parentId.longValue());
		else
			this.parentId = null;
	}

	public Transaction(double amount, String type, Long parentId) {

		this.amount = amount;
		this.type = type;

		if (parentId != null)
			this.parentId = new Long(parentId.longValue());
		else
			this.parentId = null;
	}

	public Transaction(double amount, String type) {

		this.amount = amount;
		this.type = type;

	}

	// for JPA
	public Transaction() {
	}

	public long getTransactionId() {
		return transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public String getType() {
		return type;
	}

	public Long getParentId() {
		return parentId;
	}

}
