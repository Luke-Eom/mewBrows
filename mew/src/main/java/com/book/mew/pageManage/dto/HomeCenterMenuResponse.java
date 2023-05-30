package com.book.mew.pageManage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomeCenterMenuResponse {
    private String menu;
    private String imgUrl;

}
