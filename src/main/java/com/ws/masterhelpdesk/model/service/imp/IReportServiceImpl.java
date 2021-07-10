package com.ws.masterhelpdesk.model.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.EmployeeDto;
import com.ws.masterhelpdesk.dto.report.AssessmentReport;
import com.ws.masterhelpdesk.dto.report.TicketReport;
import com.ws.masterhelpdesk.model.entity.Assessment;
import com.ws.masterhelpdesk.model.entity.Ticket;
import com.ws.masterhelpdesk.model.repository.IAssessmentRepository;
import com.ws.masterhelpdesk.model.repository.ITicketRepository;
import com.ws.masterhelpdesk.model.service.IReportService;
import com.ws.masterhelpdesk.model.service.IEmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IReportServiceImpl implements IReportService {

	private final IAssessmentRepository iAssessmentRepository;
	private final IEmployeeService iEmployeeService;
	private final ITicketRepository iTicketRepository;

	@Override
	@Transactional(readOnly = true)
	public List<AssessmentReport> assessmentEmployeeReport() {

		List<EmployeeDto> tecnicos = iEmployeeService.findAllEnabledEmployeeDtoWithRolTECNICO();
		List<AssessmentReport> reportList = new ArrayList<>();

		for (EmployeeDto tecnico : tecnicos) {

			Long malas = 0L, buenas = 0L, excelentes = 0L, nulas = 0L, total = 0L;

			for (Assessment assessment : iAssessmentRepository.findByTicketEmployeeEmployeeId(tecnico.getId())) {

				if (assessment.getAssessment_type() != null) {
					switch (assessment.getAssessment_type()) {
					case MALA:
						malas++;
						break;
					case BUENA:
						buenas++;
						break;
					case EXCELENTE:
						excelentes++;
						break;
					default:
						nulas++;
						break;
					}
				} else {
					nulas++;
				}

				total++;

			}

			reportList.add(AssessmentReport.builder().employeeFullname(tecnico.getName() + " " + tecnico.getSurname())
					.employeeId(tecnico.getId()).badAssessments(malas).goodAssessments(buenas)
					.excellentAssessments(excelentes).nullAssessments(nulas).totalAssessments(total).build());

		}

		return reportList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TicketReport> ticketsCerradosReportByYear(Integer year) {
		List<TicketReport> ticketsCerradosReport = new ArrayList<>();

		List<Ticket> enero = iTicketRepository.findByTicketStatusCERRADOAndMonthAndYear(1, year);

		TicketReport row = createReportRow(enero);
		row.setMonth("Enero");
		ticketsCerradosReport.add(row);

		List<Ticket> febrero = iTicketRepository.findByTicketStatusCERRADOAndMonthAndYear(2, year);

		row = createReportRow(febrero);
		row.setMonth("Febrero");
		ticketsCerradosReport.add(row);

		List<Ticket> marzo = iTicketRepository.findByTicketStatusCERRADOAndMonthAndYear(3, year);

		row = createReportRow(marzo);
		row.setMonth("Marzo");
		ticketsCerradosReport.add(row);

		List<Ticket> abril = iTicketRepository.findByTicketStatusCERRADOAndMonthAndYear(4, year);

		row = createReportRow(abril);
		row.setMonth("Abril");
		ticketsCerradosReport.add(row);

		List<Ticket> mayo = iTicketRepository.findByTicketStatusCERRADOAndMonthAndYear(5, year);

		row = createReportRow(mayo);
		row.setMonth("Mayo");
		ticketsCerradosReport.add(row);

		List<Ticket> junio = iTicketRepository.findByTicketStatusCERRADOAndMonthAndYear(6, year);

		row = createReportRow(junio);
		row.setMonth("Junio");
		ticketsCerradosReport.add(row);

		List<Ticket> julio = iTicketRepository.findByTicketStatusCERRADOAndMonthAndYear(7, year);

		row = createReportRow(julio);
		row.setMonth("Julio");
		ticketsCerradosReport.add(row);

		List<Ticket> agosto = iTicketRepository.findByTicketStatusCERRADOAndMonthAndYear(8, year);

		row = createReportRow(agosto);
		row.setMonth("Agosto");
		ticketsCerradosReport.add(row);

		List<Ticket> septiembre = iTicketRepository.findByTicketStatusCERRADOAndMonthAndYear(9, year);

		row = createReportRow(septiembre);
		row.setMonth("Septiembre");
		ticketsCerradosReport.add(row);

		List<Ticket> octubre = iTicketRepository.findByTicketStatusCERRADOAndMonthAndYear(10, year);

		row = createReportRow(octubre);
		row.setMonth("Octubre");
		ticketsCerradosReport.add(row);

		List<Ticket> noviembre = iTicketRepository.findByTicketStatusCERRADOAndMonthAndYear(11, year);

		row = createReportRow(noviembre);
		row.setMonth("Noviembre");
		ticketsCerradosReport.add(row);

		List<Ticket> diciembre = iTicketRepository.findByTicketStatusCERRADOAndMonthAndYear(12, year);

		row = createReportRow(diciembre);
		row.setMonth("Diciembre");
		ticketsCerradosReport.add(row);

		return ticketsCerradosReport;
	}

	private Long[] countTicketsByWeeks(List<Ticket> ticketsByMonth) {
		Long week1 = 0L, week2 = 0L, week3 = 0L, week4 = 0L, week5 = 0L, total = 0L;

		week1 = Long.valueOf(ticketsByMonth.stream()
				.filter(ticket -> Integer.parseInt(ticket.getCreatedAt().toString().substring(8, 10)) >= 1
						&& Integer.parseInt(ticket.getCreatedAt().toString().substring(8, 10)) <= 7)
				.collect(Collectors.toList()).size());

		week2 = Long.valueOf(ticketsByMonth.stream()
				.filter(ticket -> Integer.parseInt(ticket.getCreatedAt().toString().substring(8, 10)) >= 8
						&& Integer.parseInt(ticket.getCreatedAt().toString().substring(8, 10)) <= 14)
				.collect(Collectors.toList()).size());

		week3 = Long.valueOf(ticketsByMonth.stream()
				.filter(ticket -> Integer.parseInt(ticket.getCreatedAt().toString().substring(8, 10)) >= 15
						&& Integer.parseInt(ticket.getCreatedAt().toString().substring(8, 10)) <= 21)
				.collect(Collectors.toList()).size());

		week4 = Long.valueOf(ticketsByMonth.stream()
				.filter(ticket -> Integer.parseInt(ticket.getCreatedAt().toString().substring(8, 10)) >= 22
						&& Integer.parseInt(ticket.getCreatedAt().toString().substring(8, 10)) <= 28)
				.collect(Collectors.toList()).size());

		week5 = Long.valueOf(ticketsByMonth.stream()
				.filter(ticket -> Integer.parseInt(ticket.getCreatedAt().toString().substring(8, 10)) >= 29
						&& Integer.parseInt(ticket.getCreatedAt().toString().substring(8, 10)) <= 31)
				.collect(Collectors.toList()).size());

		total = week1 + week2 + week3 + week4 + week5;

		Long[] array = { week1, week2, week3, week4, week5, total };

		return array;
	}

	private TicketReport createReportRow(List<Ticket> month) {
		Long week1 = 0L, week2 = 0L, week3 = 0L, week4 = 0L, week5 = 0L, total = 0L;
		TicketReport row = null;

		if (!month.isEmpty()) {
			Long[] results = countTicketsByWeeks(month);
			week1 = results[0];
			week2 = results[1];
			week3 = results[2];
			week4 = results[3];
			week5 = results[4];
			total = results[5];
		}

		row = new TicketReport("", week1, week2, week3, week4, week5, total);
		return row;
	}

}
