package cn.dpc.drools.model;

import lombok.Data;

@Data
public class Fare {
	private Integer nightSurcharge;
	private Integer rideFare;

	public Long getTotalFare() {
		return (long)(nightSurcharge + rideFare);
	}
}
