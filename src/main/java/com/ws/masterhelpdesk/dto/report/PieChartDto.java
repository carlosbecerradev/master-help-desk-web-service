package com.ws.masterhelpdesk.dto.report;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PieChartDto {
	List<String> labels;
	List<Integer> values;
}
