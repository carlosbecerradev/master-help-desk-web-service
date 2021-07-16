package com.ws.masterhelpdesk.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.masterhelpdesk.dto.report.MyAssessmentReport;
import com.ws.masterhelpdesk.model.service.IDashboardTecnicoService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dashboard-tecnico")
@AllArgsConstructor
public class DashboardTecnicoController {

	private final IDashboardTecnicoService iDashboardTecnicoService;

	@GetMapping("/tickets-asignados-hoy/{date}")
	public ResponseEntity<Integer> getTicketsAsignadosHoy(@PathVariable("date") String date) {
		return new ResponseEntity<Integer>(iDashboardTecnicoService.findMyCurrentTicketsByDay(date), HttpStatus.OK);
	}

	@GetMapping("/tickets-cerrados-hoy/{date}")
	public ResponseEntity<Integer> getTicketsCerradosHoy(@PathVariable("date") String date) {
		return new ResponseEntity<Integer>(iDashboardTecnicoService.findMyCurrentTicketsCerradosByDay(date),
				HttpStatus.OK);
	}

	@GetMapping("/tickets-cerrados")
	public ResponseEntity<Integer> getAllTicketsCerrados() {
		return new ResponseEntity<Integer>(iDashboardTecnicoService.findAllMyCurremtTicketsCerrados(), HttpStatus.OK);
	}

	@GetMapping("/my-assesstments")
	public ResponseEntity<MyAssessmentReport> getMyAssetssments() {
		return new ResponseEntity<MyAssessmentReport>(iDashboardTecnicoService.findMyAssesstments(), HttpStatus.OK);
	}
}
