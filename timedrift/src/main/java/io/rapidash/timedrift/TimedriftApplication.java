package io.rapidash.timedrift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.rapidash.timedrift.log.Log;

@SpringBootApplication
public class TimedriftApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimedriftApplication.class, args);
		Log log = new Log();
		log.writeLog();
		try {
			log.CalculateTimedrift();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
