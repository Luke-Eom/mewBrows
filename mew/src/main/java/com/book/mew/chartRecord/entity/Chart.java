package com.book.mew.chartRecord.entity;

import com.book.mew.schedule.entity.Schedule;
import lombok.*;

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

    @OneToOne(mappedBy = "chart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Record record;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Setter
    @Column(name = "ba_img_url")
    private String baImgUrl;     // b4&after

    // schedule과 join?, createTime, updateTime -> Common
}
