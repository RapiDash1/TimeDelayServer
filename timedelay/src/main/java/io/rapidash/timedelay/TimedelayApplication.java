package io.rapidash.timedelay;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.rapidash.timedelay.log.Log;

@SpringBootApplication
public class TimedelayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimedelayApplication.class, args);
		Log log = new Log();
		try {
			log.CalculateTimeDelay();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
