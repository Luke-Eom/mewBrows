package com.book.mew.pageManage.controller;

import com.book.mew.pageManage.dto.HomeCenterMenuResponse;
import com.book.mew.pageManage.service.HomeCenterMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage")
public class PageManageController {
    private final HomeCenterMenuService menuService;

    @GetMapping("/center-menus")
    public ResponseEntity<List<HomeCenterMenuResponse>> getMenus() {
        return ResponseEntity.ok(menuService.getMenuData());

    }

}
