package com.ws.masterhelpdesk;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.ws.masterhelpdesk.model.entity.Ticket;
import com.ws.masterhelpdesk.model.repository.ITicketRepository;

import lombok.AllArgsConstructor;

@EnableAsync
@SpringBootApplication
@AllArgsConstructor
public class MasterHelpDeskApplication implements CommandLineRunner {

	private final ITicketRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MasterHelpDeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Ticket> lista = repository.findByTicketStatusCERRADOAndMonthAndYear(6, 2021);
		int count = 0;

		List<Ticket> week1 = lista.stream()
				.filter(ticket -> Integer.parseInt(ticket.getCreatedAt().toString().substring(8, 10)) >= 7 && Integer.parseInt(ticket.getCreatedAt().toString().substring(8, 10)) <= 14)
				.collect(Collectors.toList());

		for (Ticket ticket : week1) {
			System.out.print("Año: " + ticket.getCreatedAt() + " - " + ":text: "
					+ ticket.getCreatedAt().toString().substring(8, 10) + " |||| ");
			System.out.println("Customer: " + ticket.getCustomerRequest().getCustomer().getName());
			System.out.println("----------------------------------------");
			count++;
		}
		System.out.println("Count: " + count);
	}

}
