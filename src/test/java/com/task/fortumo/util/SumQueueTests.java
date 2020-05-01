package com.task.fortumo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SumQueueTests {

	SumQueue sumQueue = new SumQueue();

	@BeforeEach
	void resetValues() {
		SumQueue.reset();
	}

	@Test
	void addingLargeValues() {
		SumQueue.add(20123456981L);
		SumQueue.add(20111111111L);

		Long sum = sumQueue.sumNumbersInQueue();
		assertEquals(40234568092L, sum);
	}

	@Test
	void addingValuesWithVaryingSigns() {
		SumQueue.add(56012L);
		SumQueue.add(-1000L);

		Long sum = sumQueue.sumNumbersInQueue();
		assertEquals(55012L, sum);
	}

	@Test
	void addingValuesWithNegativeSigns() {
		SumQueue.add(-500L);
		SumQueue.add(-1000L);

		Long sum = sumQueue.sumNumbersInQueue();
		assertEquals(-1500L, sum);
	}

	@Test
	void defaultSumShouldBeZero() {
		Long sum = sumQueue.sumNumbersInQueue();
		assertEquals(-0L, sum);
	}

	@Test
	void sumAllShouldAddItemsInQueue() {
		SumQueue.add(20123456981L);
		SumQueue.add(20111111111L);

		Long sum = sumQueue.sumAll();
		assertEquals(40234568092L, sum);
	}

	@Test
	void afterSummingAllResetQueue() {
		SumQueue.add(20123456981L);
		SumQueue.add(20111111111L);

		Long sum = sumQueue.sumAll();
		assertEquals(40234568092L, sum);
		assertEquals(0L, sumQueue.sumAll());
	}

	@Test
	void getTotalShouldReturnSameResultFromSumAll() {
		SumQueue.add(20123456981L);
		SumQueue.add(20111111111L);

		Long sum = sumQueue.sumAll();
		Long sumTotal = sumQueue.getTotal();
		assertEquals(sum, sumTotal);
	}

}