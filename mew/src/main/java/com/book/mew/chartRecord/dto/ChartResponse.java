package com.book.mew.chartRecord.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChartResponse {

    private Long id;

    private String baImgUrl;

    private String msg;

}
