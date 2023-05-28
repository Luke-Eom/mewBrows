package com.book.mew.chart.entity;

import com.book.mew.schedule.entity.Schedule;
import com.book.mew.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Chart {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User userId;
    @OneToOne
    private Schedule scheduleId;
    private String chartUrl;

    // schedule과 join?, createTime, updateTime -> Common
}
