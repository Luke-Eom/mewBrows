package com.book.mew.surgeryType.entity;

import com.book.mew.surgeryType.enums.SurgeryTypes;
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
public class SurgeryType {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private SurgeryTypes surgeryType;

    @Column
    private String detail;

    @Column
    private String imgUrl;

}
