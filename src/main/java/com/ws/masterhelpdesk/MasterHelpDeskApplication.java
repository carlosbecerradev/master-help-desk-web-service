package com.ws.masterhelpdesk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.ws.masterhelpdesk.model.service.IDashboardAdminService;

import lombok.AllArgsConstructor;

@EnableAsync
@SpringBootApplication
@AllArgsConstructor
public class MasterHelpDeskApplication implements CommandLineRunner {

	private final IDashboardAdminService iDashboardAdminService;

	public static void main(String[] args) {
		SpringApplication.run(MasterHelpDeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		iDashboardAdminService.findTicketsAnalyticsThisYear();
	}

}
