package com.ws.masterhelpdesk.model.service;

import com.ws.masterhelpdesk.dto.report.LineChartDto;

public interface IDashboardAdminService {

	Integer countAllCustomer();

	Integer countAllTecnico();

	Integer countAllTickets();

	LineChartDto findTicketsAnalyticsThisWeek();

	LineChartDto findTicketsAnalyticsThisYear();
}
