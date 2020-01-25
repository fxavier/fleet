package com.xavier.fleet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class FleetApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FleetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Random random = new Random();

		for (int i = 0; i < 24; i++) {
			int num = random.nextInt(1000);
			System.out.print(i + "->" + num + ",");

		}
		/*System.out.println(">>>>>>>>PremierBet>>>>>>>>>>>>>>>>>>>>>>");
		for (int i = 1; i <= 18; i++) {
			int number = random.nextInt(4);
			System.out.println(i + ">>" + number);
		}*/
	}
}
