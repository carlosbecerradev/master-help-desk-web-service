package com.ws.masterhelpdesk.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.masterhelpdesk.dto.report.LineChartDto;
import com.ws.masterhelpdesk.dto.report.PieChartDto;
import com.ws.masterhelpdesk.model.service.IDashboardAnalistaService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dashboard-analista")
@AllArgsConstructor
public class DashboardAnalistaController {

	private final IDashboardAnalistaService iDashboardAnalistaService;

	@GetMapping("/customer-request-today")
	public ResponseEntity<Integer> getCustomerRequestCountByToday() {
		return new ResponseEntity<Integer>(iDashboardAnalistaService.findCustomerRequestCountByToday(), HttpStatus.OK);
	}

	@GetMapping("/customer-request-month")
	public ResponseEntity<Integer> getCustomerRequestCountByMonth() {
		return new ResponseEntity<Integer>(iDashboardAnalistaService.findCustomerRequestCountByMonth(), HttpStatus.OK);
	}

	@GetMapping("/tickets-atendiendo-today")
	public ResponseEntity<Integer> getTicketsATENDIENDOCountByToday() {
		return new ResponseEntity<Integer>(iDashboardAnalistaService.findTicketsATENDIENDOCountByToday(),
				HttpStatus.OK);
	}

	@GetMapping("/chart/customer-request-count")
	public ResponseEntity<PieChartDto> getCustomerRequestPieChart() {
		return new ResponseEntity<PieChartDto>(iDashboardAnalistaService.findCustomerRequestCountByRequestTypeName(),
				HttpStatus.OK);
	}

	@GetMapping("/chart/ticket-of-week/employee/{idEmployee}")
	public ResponseEntity<LineChartDto> getTicketsCerradosOfWeekByTecnico(@PathVariable("idEmployee") Long idEmployee) {
		return new ResponseEntity<LineChartDto>(
				iDashboardAnalistaService.findTicketsCerradosOfWeekByEmployeeId(idEmployee), HttpStatus.OK);
	}

}
