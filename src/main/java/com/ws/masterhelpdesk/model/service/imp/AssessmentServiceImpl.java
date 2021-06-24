package com.ws.masterhelpdesk.model.service.imp;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.AssessmentDto;
import com.ws.masterhelpdesk.dto.TicketDto;
import com.ws.masterhelpdesk.model.entity.Assessment;
import com.ws.masterhelpdesk.model.entity.AssessmentType;
import com.ws.masterhelpdesk.model.entity.Ticket;
import com.ws.masterhelpdesk.model.repository.IAssessmentRepository;
import com.ws.masterhelpdesk.model.service.IAssessmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssessmentServiceImpl implements IAssessmentService {

	private final IAssessmentRepository iAssessmentRepository;

	@Override
	@Transactional(readOnly = true)
	public Assessment findAssessmentByToken(String token) {
		return iAssessmentRepository.findByToken(token)
				.orElseThrow(() -> new RuntimeException("Assessment with token: " + token + " is not found."));
	}

	@Override
	@Transactional(readOnly = true)
	public AssessmentDto findAssessmentDtoByToken(String token) {
		Assessment assessmentFound = findAssessmentByToken(token);

		return AssessmentDto.builder().token(token).observation(assessmentFound.getObservation())
				.assessmentType(assessmentFound.getAssessment_type().toString())
				.ticketDto(TicketDto.builder().id(assessmentFound.getTicket().getTicketId()).build()).build();
	}

	@Override
	@Transactional(readOnly = false)
	public Assessment insertByTicket(Ticket ticket) {
		String token = UUID.randomUUID().toString();

		Assessment assessment = new Assessment();
		assessment.setTicket(ticket);
		assessment.setToken(token);
		assessment.setObservation("");
		assessment.setEnabled(true);
		assessment.setCreatedAt(Instant.now());
		assessment.setAssessment_type(null);
		return iAssessmentRepository.save(assessment);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(AssessmentDto assessmentDto) {
		Assessment assessmentFound = findAssessmentByToken(assessmentDto.getToken());
		assessmentFound.setAssessment_type(AssessmentType.valueOf(assessmentDto.getAssessmentType()));
		assessmentFound.setObservation(assessmentDto.getObservation());
		assessmentFound.setEnabled(false);
		iAssessmentRepository.save(assessmentFound);
	}

}
