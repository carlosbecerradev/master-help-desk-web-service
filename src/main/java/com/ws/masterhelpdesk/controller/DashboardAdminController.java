package com.ws.masterhelpdesk.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.masterhelpdesk.dto.report.LineChartDto;
import com.ws.masterhelpdesk.model.service.IDashboardAdminService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dashboard-admin")
@AllArgsConstructor
public class DashboardAdminController {

	private final IDashboardAdminService iDashboardAdminService;

	@GetMapping("/current-customers")
	public ResponseEntity<Integer> getCustomersCount() {
		return new ResponseEntity<Integer>(iDashboardAdminService.countAllCustomer(), HttpStatus.OK);
	}

	@GetMapping("/current-tecnicos")
	public ResponseEntity<Integer> getTecnicosCount() {
		return new ResponseEntity<Integer>(iDashboardAdminService.countAllTecnico(), HttpStatus.OK);
	}

	@GetMapping("/current-tickets")
	public ResponseEntity<Integer> getTicketsCount() {
		return new ResponseEntity<Integer>(iDashboardAdminService.countAllTickets(), HttpStatus.OK);
	}

	@GetMapping("/chart/tickets-of-the-week")
	public ResponseEntity<LineChartDto> getTicketsAnalyticsOfWeek() {
		return new ResponseEntity<LineChartDto>(iDashboardAdminService.findTicketsAnalyticsThisWeek(), HttpStatus.OK);
	}

	@GetMapping("/chart/tickets-of-the-year")
	public ResponseEntity<LineChartDto> getTicketsAnalyticsOfYear() {
		return new ResponseEntity<LineChartDto>(iDashboardAdminService.findTicketsAnalyticsThisYear(), HttpStatus.OK);
	}

}
