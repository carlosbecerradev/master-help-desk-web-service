package com.ws.masterhelpdesk.model.service;

import com.ws.masterhelpdesk.dto.AssessmentDto;
import com.ws.masterhelpdesk.model.entity.Assessment;
import com.ws.masterhelpdesk.model.entity.Ticket;

public interface IAssessmentService {

	public Assessment findAssessmentByToken(String token);

	public AssessmentDto findAssessmentDtoByToken(String token);

	public Assessment insertByTicket(Ticket ticket);

	public boolean update(AssessmentDto assessmentDto);

}
