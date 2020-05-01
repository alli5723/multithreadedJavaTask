package com.task.fortumo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FortumoApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(4, (2+2));
	}

}
