package com.ws.masterhelpdesk.model.service;

import com.ws.masterhelpdesk.dto.report.MyAssessmentReport;

public interface IDashboardTecnicoService {

	Integer findMyCurrentTicketsByDay(String chartSequence);

	Integer findMyCurrentTicketsCerradosByDay(String chartSequence);

	Integer findAllMyCurremtTicketsCerrados();

	MyAssessmentReport findMyAssesstments();
}
