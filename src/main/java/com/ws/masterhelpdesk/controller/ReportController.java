package com.ws.masterhelpdesk.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.masterhelpdesk.dto.report.AssessmentReport;
import com.ws.masterhelpdesk.model.service.IReportService;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reports")
@AllArgsConstructor
public class ReportController {

	private final IReportService iReportService;

	@GetMapping("/assessments-employee")
	public ResponseEntity<List<AssessmentReport>> getAssessmentReportList() {
		return new ResponseEntity<List<AssessmentReport>>(iReportService.assessmentEmployeeReport(), HttpStatus.OK);
	}

	@GetMapping("/assessments-employee/pdf")
	public ResponseEntity<byte[]> getAssessmentReport() {
		String filename = "report-" + Instant.now().toString() + ".pdf";
		String filePath = "src//main//resources//static//reportTemplate//assessmentsXEmployeeReport.jasper";
		List<AssessmentReport> reportInfo = iReportService.assessmentEmployeeReport();

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentDisposition(ContentDisposition.builder("inline").filename(filename).build());
		return ResponseEntity.ok().headers(responseHeaders).contentType(MediaType.APPLICATION_PDF)
				.body(generateReport(filePath, reportInfo));
	}

	byte[] generateReport(String filePath, List<?> list) {
		byte[] data = null;

		try {
			Path directorioRescuros = Paths.get(filePath);
			String rootPath = directorioRescuros.toFile().getAbsolutePath();
			System.out.println("rootpath: " + rootPath);

			JasperPrint rpt = JasperFillManager.fillReport(rootPath, null, new JRBeanCollectionDataSource(list));
			data = JasperExportManager.exportReportToPdf(rpt);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}
