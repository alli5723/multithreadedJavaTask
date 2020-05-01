package com.task.fortumo.service;

import java.util.concurrent.Future;

public interface SummingServiceInterface {

	public Future<Long> sumValue(String value);

	public Long terminate();

}