package com.ws.masterhelpdesk.model.service.imp;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.report.LineChartDto;
import com.ws.masterhelpdesk.dto.report.PieChartDto;
import com.ws.masterhelpdesk.model.entity.Employee;
import com.ws.masterhelpdesk.model.entity.TicketStatus;
import com.ws.masterhelpdesk.model.repository.ICustomerRequestRepository;
import com.ws.masterhelpdesk.model.repository.IRequestTypeRepository;
import com.ws.masterhelpdesk.model.repository.ITicketRepository;
import com.ws.masterhelpdesk.model.service.IDashboardAnalistaService;
import com.ws.masterhelpdesk.model.service.IEmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DashboardAnalistaServiceImpl implements IDashboardAnalistaService {

	private final ICustomerRequestRepository iCustomerRequestRepository;
	private final IRequestTypeRepository iRequestTypeRepository;
	private final ITicketRepository iTicketRepository;
	private final IEmployeeService iEmployeeService;

	@Override
	@Transactional(readOnly = true)
	public Integer findCustomerRequestCountByToday() {
		Instant today = getCurrentDay().plus(Duration.ofHours(5));
		Instant tomorrow = today.plus(Duration.ofDays(1));
		return iCustomerRequestRepository.countCustomerRequestBetweenTodayAndTomorrow(today, tomorrow);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer findCustomerRequestCountByMonth() {
		Calendar cal = Calendar.getInstance();
		Integer monthNumber = cal.get(Calendar.MONTH) + 1;
		Integer yearNumber = cal.get(Calendar.YEAR);
		return iCustomerRequestRepository.countCustomerRequestByMonth(monthNumber, yearNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer findTicketsATENDIENDOCountByToday() {
		Instant today = getCurrentDay().plus(Duration.ofHours(5));
		Instant tomorrow = today.plus(Duration.ofDays(1));
		System.out.println("today: " + today + " - " + " tomorrow: " + tomorrow);
		return iTicketRepository.countByTicketStatusATENDIENDOAndDay(today, tomorrow);
	}

	@Override
	@Transactional(readOnly = true)
	public PieChartDto findCustomerRequestCountByRequestTypeName() {

		List<String> labels = iRequestTypeRepository.findEnabledNames();
		List<Integer> values = new ArrayList<>();

		for (String name : labels) {
			Integer count = iCustomerRequestRepository.countCustomerRequestByRequestTypeName(name);
			values.add(count);
		}

		return PieChartDto.builder().labels(labels).values(values).build();
	}

	@Override
	public LineChartDto findTicketsCerradosOfWeekByEmployeeId(Long employeeId) {

		Employee employee = iEmployeeService.getEmployeeById(employeeId);

		Instant monday = getMondayOfWeek();
		Instant tuesday = monday.plus(Duration.ofDays(1));
		Instant wednesday = tuesday.plus(Duration.ofDays(1));
		Instant thursday = wednesday.plus(Duration.ofDays(1));
		Instant friday = thursday.plus(Duration.ofDays(1));
		Instant saturday = friday.plus(Duration.ofDays(1));
		Instant sunday = saturday.plus(Duration.ofDays(1));

		System.out.println("monday" + monday);
		System.out.println("tuesday" + tuesday);
		System.out.println("wednesday" + wednesday);
		System.out.println("friday" + friday);
		System.out.println("saturday" + saturday);

		List<String> labels = Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado");
		List<List<Integer>> values = new ArrayList<>();

		Integer mondayTickets = iTicketRepository.countTicketsByEmployeeAndDay(monday, tuesday, employee);
		Integer mondayTicketsCerrados = iTicketRepository.countTicketsByEmployeeAndTicketStatusAndDay(monday, tuesday,
				employee, TicketStatus.CERRADO);
		Integer tuesdayTickets = iTicketRepository.countTicketsByEmployeeAndDay(tuesday, wednesday, employee);
		Integer tuesdayTicketsCerrados = iTicketRepository.countTicketsByEmployeeAndTicketStatusAndDay(tuesday,
				wednesday, employee, TicketStatus.CERRADO);
		Integer wednesdayTickets = iTicketRepository.countTicketsByEmployeeAndDay(wednesday, thursday, employee);
		Integer wednesdayTicketsCerrados = iTicketRepository.countTicketsByEmployeeAndTicketStatusAndDay(wednesday,
				thursday, employee, TicketStatus.CERRADO);
		Integer thursdayTickets = iTicketRepository.countTicketsByEmployeeAndDay(thursday, friday, employee);
		Integer thursdayTicketsCerrados = iTicketRepository.countTicketsByEmployeeAndTicketStatusAndDay(thursday,
				friday, employee, TicketStatus.CERRADO);
		Integer fridayTickets = iTicketRepository.countTicketsByEmployeeAndDay(friday, saturday, employee);
		Integer fridayTicketsCerrados = iTicketRepository.countTicketsByEmployeeAndTicketStatusAndDay(friday, saturday,
				employee, TicketStatus.CERRADO);
		Integer saturdayTickets = iTicketRepository.countTicketsByEmployeeAndDay(saturday, sunday, employee);
		Integer saturdayTicketsCerrados = iTicketRepository.countTicketsByEmployeeAndTicketStatusAndDay(saturday,
				sunday, employee, TicketStatus.CERRADO);

		values.add(Arrays.asList(mondayTickets, tuesdayTickets, wednesdayTickets, thursdayTickets, fridayTickets,
				saturdayTickets));
		values.add(Arrays.asList(mondayTicketsCerrados, tuesdayTicketsCerrados, wednesdayTicketsCerrados,
				thursdayTicketsCerrados, fridayTicketsCerrados, saturdayTicketsCerrados));

		return LineChartDto.builder().labels(labels).values(values).build();
	}

	public Instant getMondayOfWeek() {
		Calendar c = Calendar.getInstance();
		int currentDayOfWeekNumber = c.get(Calendar.DAY_OF_WEEK);
		Instant currentDay = getCurrentDay();

		Instant monday = currentDay.minus(Duration.ofDays(currentDayOfWeekNumber - 2)).plus(Duration.ofHours(5));

		return monday;
	}

	public Instant getCurrentDay() {
		Instant gmtMinus5 = Instant.now().minus(Duration.ofHours(5));
		return gmtMinus5.truncatedTo(ChronoUnit.DAYS);
	}

}
