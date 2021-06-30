package com.ws.masterhelpdesk.model.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.EmployeeDto;
import com.ws.masterhelpdesk.dto.report.AssessmentReport;
import com.ws.masterhelpdesk.model.entity.Assessment;
import com.ws.masterhelpdesk.model.repository.IAssessmentRepository;
import com.ws.masterhelpdesk.model.service.IReportService;
import com.ws.masterhelpdesk.model.service.IEmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IReportServiceImpl implements IReportService {

	private final IAssessmentRepository iAssessmentRepository;
	private final IEmployeeService iEmployeeService;

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

}
