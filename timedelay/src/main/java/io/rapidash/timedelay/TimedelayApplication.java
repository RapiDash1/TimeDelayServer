package io.rapidash.timedelay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.rapidash.timedelay.log.Log;

@SpringBootApplication
public class TimedelayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimedelayApplication.class, args);
		Log log = new Log();
		log.writeLog();
		try {
			log.CalculateTimeDelay();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
