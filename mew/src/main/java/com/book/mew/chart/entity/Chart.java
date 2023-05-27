package com.book.mew.chart.entity;

import com.book.mew.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Chart {
    private Long chartId;
    private User user;
    private String chartUrl;

    // schedule과 join?, createTime, updateTime -> Common
}
