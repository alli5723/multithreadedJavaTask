package com.task.fortumo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.Future;

import com.task.fortumo.util.SumQueue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SummingServiceTests {

	@Mock
	private SumQueue smsQueueMock;

	@InjectMocks
	SummingService summingService = new SummingService();

	@Test
	void parseNumberShouldReturnNumber() {
		Long number = summingService.parseNumber("529078");

		assertEquals(529078L, number);
	}

	@Test
	void addValueShouldReturnResult() {
		when(smsQueueMock.getTotal()).thenReturn(10L);

		Long number = summingService.addValue("529078");

		assertEquals(10L, number);
		verify(smsQueueMock, times(1)).getTotal();
	}

	@Test
	void sumValueShouldReturnFutureTotal() {
		when(smsQueueMock.getTotal()).thenReturn(8L);

		Future<Long> future = summingService.sumValue("529078");
		
		try {
			Long number = future.get();

			assertEquals(8L, number);
			verify(smsQueueMock, times(1)).getTotal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void terminateShouldSumAllPendingNumbers() {
		when(smsQueueMock.getTotal()).thenReturn(8L);

		summingService.addValue("529078");
		summingService.addValue("1529078");
		summingService.addValue("51529078000");

		verify(smsQueueMock, times(3)).getTotal();
		
		summingService.terminate();
		verify(smsQueueMock, times(1)).sumAll();
	}
}