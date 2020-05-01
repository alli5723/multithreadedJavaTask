package com.task.fortumo.api;

import java.util.concurrent.Future;

import com.task.fortumo.service.SummingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApiController {
	
	@Autowired
	private SummingService summingService;

	Logger logger = LoggerFactory.getLogger(ApiController.class);

	@PostMapping
	public ResponseEntity<Long> sumUpNumber(
			@RequestHeader(value = "content-length") int contentLength,
			@RequestBody String requestBody) {
			
		if (requestBody.toLowerCase().contains("end")) {
			// terminate process and return response
			return ResponseEntity.ok(summingService.terminate());
		}

		try {
			// convert to number,
			String numberAsString = (requestBody.length() > contentLength)
				? requestBody.substring(0, contentLength)
				: requestBody;

			Future<Long> futureSum = summingService.sumValue(numberAsString);
			Long result = futureSum.get();

			return ResponseEntity.ok(result);
		} catch (Exception ex) {
			logger.error(String.format("Unable to process request because `%s`", ex.getMessage()));
			return ResponseEntity.badRequest().body(0L);
		}
	}

}