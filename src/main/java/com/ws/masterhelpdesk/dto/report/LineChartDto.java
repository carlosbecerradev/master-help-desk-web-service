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
public class LineChartDto {
	List<String> labels;
	List<List<Integer>> values;
}
