package com.book.mew.chartRecord.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RecordResponse {

    private Long recordId;

    private String baImgUrl;

    private String captureImgUrl;

    private String msg;


}
