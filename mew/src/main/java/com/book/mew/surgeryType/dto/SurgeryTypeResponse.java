package com.book.mew.surgeryType.dto;

import com.book.mew.surgeryType.enums.SurgeryTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SurgeryTypeResponse {
    private Long id;
    private String detail;
    private SurgeryTypes surgeryType;
    private String imgUrl;

}
