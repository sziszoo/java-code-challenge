package com.n26.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SumAmount {

	@JsonProperty("sum")
	private double sum;

	public SumAmount(double sum) {
		this.sum = sum;
	}

	public double getSum() {
		return sum;
	}
}
