package com.book.mew.pageManage.entity;

import com.book.mew.pageManage.dto.HomeCenterMenuResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HomeCenterMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String menu;
    private String imgUrl;

    public HomeCenterMenuResponse toDto() {
        return HomeCenterMenuResponse.builder()
                .menu(this.getMenu())
                .imgUrl(this.getImgUrl())
                .build();
    }

}
