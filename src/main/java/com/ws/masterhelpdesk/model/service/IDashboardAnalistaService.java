package com.ws.masterhelpdesk.model.service;

import com.ws.masterhelpdesk.dto.report.LineChartDto;
import com.ws.masterhelpdesk.dto.report.PieChartDto;

public interface IDashboardAnalistaService {

	Integer findCustomerRequestCountByToday();

	Integer findCustomerRequestCountByMonth();

	Integer findTicketsATENDIENDOCountByToday();

	PieChartDto findCustomerRequestCountByRequestTypeName();

	LineChartDto findTicketsCerradosOfWeekByEmployeeId(Long employeeId);
}
