package com.task.fortumo.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.task.fortumo.util.SumQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummingService implements SummingServiceInterface {

	Logger logger = LoggerFactory.getLogger(SummingService.class);

	ExecutorService executor = Executors.newFixedThreadPool(25);

	@Autowired
	private SumQueue sumQueue;

	public Long parseNumber(String value) {
		return Long.parseLong(value);
	}

	public Long addValue(String value) {
		// Add number to list
		Long valueAsNumber = parseNumber(value);
		SumQueue.add(valueAsNumber);
		// wait for notification
		// get total after notification and return value
		logger.info("Waiting to receive END notification");
		return sumQueue.getTotal();
	}

	@Override
	public Future<Long> sumValue(String value) {
		Callable<Long> callableTask = () -> addValue(value);
		return executor.submit(callableTask);
	}

	@Override
	public Long terminate() {
		logger.info("END command received");
		// sum up all numbers in list
		// notify other threads
		return sumQueue.sumAll();
	}

}