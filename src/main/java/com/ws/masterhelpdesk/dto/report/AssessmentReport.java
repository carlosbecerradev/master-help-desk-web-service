package com.ws.masterhelpdesk.dto.report;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AssessmentReport {
	private Long employeeId;
	private String employeeFullname;
	private Long badAssessments;
	private Long goodAssessments;
	private Long excellentAssessments;
	private Long nullAssessments;
	private Long totalAssessments;
}
