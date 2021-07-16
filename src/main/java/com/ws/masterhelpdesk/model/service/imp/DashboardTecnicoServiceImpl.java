package com.ws.masterhelpdesk.model.service.imp;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.report.MyAssessmentReport;
import com.ws.masterhelpdesk.model.entity.AssessmentType;
import com.ws.masterhelpdesk.model.entity.Employee;
import com.ws.masterhelpdesk.model.entity.TicketStatus;
import com.ws.masterhelpdesk.model.repository.IAssessmentRepository;
import com.ws.masterhelpdesk.model.repository.ITicketRepository;
import com.ws.masterhelpdesk.model.service.IDashboardTecnicoService;
import com.ws.masterhelpdesk.model.service.IEmployeeService;
import com.ws.masterhelpdesk.security.service.AuthenticationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DashboardTecnicoServiceImpl implements IDashboardTecnicoService {

	private final ITicketRepository iTicketRepository;
	private final AuthenticationService authenticationService;
	private final IEmployeeService iEmployeeService;
	private final IAssessmentRepository iAssessmentRepository;

	@Override
	@Transactional(readOnly = true)
	public Integer findMyCurrentTicketsByDay(String chartSequence) {
		Employee enployee = iEmployeeService.findEmployeeByUser(authenticationService.getCurrentLoggedInUser());

		Instant startOfDay = Instant.parse(chartSequence).truncatedTo(ChronoUnit.DAYS);
		Instant endOfDay = startOfDay.plus(Duration.ofDays(1));
		return iTicketRepository.countTicketsByEmployeeAndDay(startOfDay, endOfDay, enployee);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer findMyCurrentTicketsCerradosByDay(String chartSequence) {
		Employee enployee = iEmployeeService.findEmployeeByUser(authenticationService.getCurrentLoggedInUser());

		Instant startOfDay = Instant.parse(chartSequence).truncatedTo(ChronoUnit.DAYS);
		Instant endOfDay = startOfDay.plus(Duration.ofDays(1));
		return iTicketRepository.countTicketsByEmployeeAndTicketStatusAndDay(startOfDay, endOfDay, enployee,
				TicketStatus.CERRADO);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer findAllMyCurremtTicketsCerrados() {
		Employee enployee = iEmployeeService.findEmployeeByUser(authenticationService.getCurrentLoggedInUser());

		return iTicketRepository.countTicketsByTicketStatusAndEmployee(enployee, TicketStatus.CERRADO);
	}

	@Override
	@Transactional(readOnly = true)
	public MyAssessmentReport findMyAssesstments() {
		Employee enployee = iEmployeeService.findEmployeeByUser(authenticationService.getCurrentLoggedInUser());

		List<String> labels = Arrays.asList("Malas", "Buenas", "Excelentes", "Vacías");

		Integer malas = iAssessmentRepository.countAssessmentsByEmployeeAndAssessmentType(enployee,
				AssessmentType.MALA);
		Integer buenas = iAssessmentRepository.countAssessmentsByEmployeeAndAssessmentType(enployee,
				AssessmentType.BUENA);
		Integer excelentes = iAssessmentRepository.countAssessmentsByEmployeeAndAssessmentType(enployee,
				AssessmentType.EXCELENTE);
		Integer vacias = iAssessmentRepository.countAssessmentsByEmployeeAndAssessmentTypeNULL(enployee);

		List<Integer> values = Arrays.asList(malas, buenas, excelentes, vacias);

		return MyAssessmentReport.builder().labels(labels).values(values).build();
	}

}
