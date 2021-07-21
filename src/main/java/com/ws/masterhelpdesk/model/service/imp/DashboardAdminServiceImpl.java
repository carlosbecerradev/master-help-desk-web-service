package com.ws.masterhelpdesk.model.service.imp;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.report.LineChartDto;
import com.ws.masterhelpdesk.model.repository.ICustomerRepository;
import com.ws.masterhelpdesk.model.repository.ITicketRepository;
import com.ws.masterhelpdesk.model.repository.IUserRepository;
import com.ws.masterhelpdesk.model.service.IDashboardAdminService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DashboardAdminServiceImpl implements IDashboardAdminService {

	private final DashboardAnalistaServiceImpl dashboardAnalistaServiceImpl;
	private final ITicketRepository iTicketRepository;
	private final ICustomerRepository iCustomerRepository;
	private final IUserRepository iUserRepository;

	@Override
	@Transactional(readOnly = true)
	public Integer countAllCustomer() {
		return iCustomerRepository.countCustomers();
	}

	@Override
	@Transactional(readOnly = true)
	public Integer countAllTecnico() {
		return iUserRepository.countTecnicos();
	}

	@Override
	@Transactional(readOnly = true)
	public Integer countAllTickets() {
		return iTicketRepository.countTickets();
	}

	@Override
	@Transactional(readOnly = true)
	public LineChartDto findTicketsAnalyticsThisWeek() {

		Instant monday = dashboardAnalistaServiceImpl.getMondayOfWeek();
		Instant tuesday = monday.plus(Duration.ofDays(1));
		Instant wednesday = tuesday.plus(Duration.ofDays(1));
		Instant thursday = wednesday.plus(Duration.ofDays(1));
		Instant friday = thursday.plus(Duration.ofDays(1));
		Instant saturday = friday.plus(Duration.ofDays(1));
		Instant sunday = saturday.plus(Duration.ofDays(1));

		List<String> labels = Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado");
		List<List<Integer>> values = new ArrayList<>();

		Integer ticketsOfMonday = iTicketRepository.countTicketsBetweenCreatedAt(monday, tuesday);
		Integer ticketsOfTuesday = iTicketRepository.countTicketsBetweenCreatedAt(tuesday, wednesday);
		Integer ticketsOfWednesday = iTicketRepository.countTicketsBetweenCreatedAt(wednesday, thursday);
		Integer ticketsOfThursday = iTicketRepository.countTicketsBetweenCreatedAt(thursday, friday);
		Integer ticketsOfFriday = iTicketRepository.countTicketsBetweenCreatedAt(friday, saturday);
		Integer ticketsOfSaturday = iTicketRepository.countTicketsBetweenCreatedAt(saturday, sunday);

		values.add(Arrays.asList(ticketsOfMonday, ticketsOfTuesday, ticketsOfWednesday, ticketsOfThursday,
				ticketsOfFriday, ticketsOfSaturday));

		return LineChartDto.builder().labels(labels).values(values).build();
	}

	@Override
	@Transactional(readOnly = true)
	public LineChartDto findTicketsAnalyticsThisYear() {
		Calendar calendar = Calendar.getInstance();
		Integer fullYear = calendar.get(Calendar.YEAR);

		Integer ticketOfJanuary = iTicketRepository.countTicketsByMonthAndYear(1, fullYear);
		Integer ticketOfFebruary = iTicketRepository.countTicketsByMonthAndYear(2, fullYear);
		Integer ticketOfMarch = iTicketRepository.countTicketsByMonthAndYear(3, fullYear);
		Integer ticketOfApril = iTicketRepository.countTicketsByMonthAndYear(4, fullYear);
		Integer ticketOfMay = iTicketRepository.countTicketsByMonthAndYear(5, fullYear);
		Integer ticketOfJune = iTicketRepository.countTicketsByMonthAndYear(6, fullYear);
		Integer ticketOfJuly = iTicketRepository.countTicketsByMonthAndYear(7, fullYear);
		Integer ticketOfAugust = iTicketRepository.countTicketsByMonthAndYear(8, fullYear);
		Integer ticketOfSeptember = iTicketRepository.countTicketsByMonthAndYear(9, fullYear);
		Integer ticketOfOctober = iTicketRepository.countTicketsByMonthAndYear(10, fullYear);
		Integer ticketOfNovember = iTicketRepository.countTicketsByMonthAndYear(11, fullYear);
		Integer ticketOfDecembar = iTicketRepository.countTicketsByMonthAndYear(12, fullYear);

		List<String> labels = Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
				"Septiembre", "Octubre", "Noviembre", "Diciembre");
		List<List<Integer>> values = new ArrayList<>();
		values.add(Arrays.asList(ticketOfJanuary, ticketOfFebruary, ticketOfMarch, ticketOfApril, ticketOfMay,
				ticketOfJune, ticketOfJuly, ticketOfAugust, ticketOfSeptember, ticketOfOctober, ticketOfNovember,
				ticketOfDecembar));

		return LineChartDto.builder().labels(labels).values(values).build();
	}

}
