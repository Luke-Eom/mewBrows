package com.book.mew.chartRecord.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "EYEBROWS")
@DiscriminatorValue("EYEBROWS")
public class BrowsChart extends Chart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Map<String, Object> browsData;

}
