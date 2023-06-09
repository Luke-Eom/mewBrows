package com.book.mew.chartRecord.dto;

import com.book.mew.surgeryType.enums.SurgeryTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChartFormatResponse {

    private Long chartId;

    private String surgeryType;

}
