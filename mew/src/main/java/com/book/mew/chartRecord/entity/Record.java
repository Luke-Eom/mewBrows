package com.book.mew.chartRecord.entity;

import com.book.mew.schedule.entity.Schedule;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Record {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private Chart chart;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Setter
    @Column(name = "ba_img_url")
    private String baImgUrl;     // b4&after

    // schedule과 join?, createTime, updateTime -> Common
}
