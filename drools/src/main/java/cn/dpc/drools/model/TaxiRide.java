package cn.dpc.drools.model;

import lombok.Data;

@Data
public class TaxiRide {
	private Boolean isNightSurcharge;

	private Integer distanceInMile;
}
