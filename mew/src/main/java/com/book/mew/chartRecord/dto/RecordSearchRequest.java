package com.book.mew.chartRecord.dto;

import com.book.mew.surgeryType.enums.SurgeryTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RecordSearchRequest {

    private Long id;

    private SurgeryTypes surgeryType;

    private String baImgUrl;

}
