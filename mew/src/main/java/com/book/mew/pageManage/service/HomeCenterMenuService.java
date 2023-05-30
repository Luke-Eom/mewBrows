package com.book.mew.pageManage.service;

import com.book.mew.pageManage.dto.HomeCenterMenuResponse;
import com.book.mew.pageManage.entity.HomeCenterMenu;
import com.book.mew.pageManage.repository.HomeCenterMenuRepository;
import com.book.mew.schedule.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeCenterMenuService {
    private final HomeCenterMenuRepository menuRepo;

    public List<HomeCenterMenuResponse> getMenuData() {
        List<HomeCenterMenu> menus = menuRepo.findAll();

        return new ArrayList<>(menus.stream()
                .sorted(Comparator.comparing(HomeCenterMenu::getId))
                .map(HomeCenterMenu::toDto)
                .collect(Collectors.toList()));

    }

}
