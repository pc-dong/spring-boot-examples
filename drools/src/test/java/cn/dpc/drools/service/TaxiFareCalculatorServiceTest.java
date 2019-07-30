package cn.dpc.drools.service;

import cn.dpc.drools.model.Fare;
import cn.dpc.drools.model.TaxiRide;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxiFareCalculatorServiceTest {

	@Autowired
	TaxiFareCalculatorService taxiFareCalculatorService;

	@Test
	public void whenNightSurchargeFalseAndDistLessThan10_thenFixWithoutNightSurcharge() {
		TaxiRide taxiRide;
		taxiRide = new TaxiRide();
		taxiRide.setIsNightSurcharge(false);
		taxiRide.setDistanceInMile(9);
		Fare rideFare = new Fare();
		Long totalCharge = taxiFareCalculatorService.calculateFare(taxiRide, rideFare);

		assertNotNull(totalCharge);
		assertEquals(Long.valueOf(70), totalCharge);
	}

	@Test
	public void whenNightSurchargeTrueAndDistLessThan10_thenFixWithNightSurcharge() {
		TaxiRide taxiRide;
		taxiRide = new TaxiRide();
		taxiRide.setIsNightSurcharge(true);
		taxiRide.setDistanceInMile(9);
		Fare rideFare = new Fare();
		Long totalCharge = taxiFareCalculatorService.calculateFare(taxiRide, rideFare);

		assertNotNull(totalCharge);
		assertEquals(Long.valueOf(100), totalCharge);
	}
}