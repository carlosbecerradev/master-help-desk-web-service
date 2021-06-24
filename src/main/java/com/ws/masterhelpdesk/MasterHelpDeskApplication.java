package com.ws.masterhelpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MasterHelpDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterHelpDeskApplication.class, args);
	}

}
