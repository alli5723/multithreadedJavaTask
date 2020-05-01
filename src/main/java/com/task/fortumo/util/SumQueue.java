package com.task.fortumo.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SumQueue {

	Logger logger = LoggerFactory.getLogger(SumQueue.class);

	// Rather than keeping a queue, we can also use a single value to keep memory lighter
	private static List<Long> numberQueue = new ArrayList<>();
	private static Long total = 0L;
	private static boolean ended = false;

	public synchronized Long sumAll() {
		total = sumNumbersInQueue();

		reset();
		notifyAll();
		return total;
	}

	public synchronized Long getTotal() {
		// Wait for notification that task has ended, then return total
		while (!ended) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				logger.error("Thread interrupted"); 
			}
		}
		return total;
	}

	// Static helper methods

	public static void add(Long value) {
		ended = false;
		numberQueue.add(value);
	}

	public Long sumNumbersInQueue() {
		return numberQueue.stream()
						.reduce((a, b) -> b + a)
						.orElse(0L);
	}

	public static void reset() {
		ended = true;
		numberQueue.clear();
	}

}