package com.ws.masterhelpdesk.model.service;

import java.util.List;

import com.ws.masterhelpdesk.dto.report.AssessmentReport;
import com.ws.masterhelpdesk.dto.report.TicketReport;

public interface IReportService {

	public List<AssessmentReport> assessmentEmployeeReport();
	
	public List<TicketReport> ticketsCerradosReportByYear(Integer year);
}
