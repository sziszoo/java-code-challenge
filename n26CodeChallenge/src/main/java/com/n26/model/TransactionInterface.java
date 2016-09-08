package com.n26.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionInterface {

	@JsonProperty("amount")
	private double amount;
	@JsonProperty("type")
	private String type;
	@JsonProperty("parent_id")
	private Long parentId;

	public TransactionInterface(double amount, String type, Long parentId) {

		this.amount = amount;
		this.type = type;

		if (parentId != null)
			this.parentId = new Long(parentId.longValue());
		else
			this.parentId = null;
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