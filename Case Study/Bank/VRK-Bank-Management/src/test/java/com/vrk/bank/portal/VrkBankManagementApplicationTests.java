package com.vrk.bank.portal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class VrkBankManagementApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertEquals(100, new Random().nextInt(4));
	}

}
