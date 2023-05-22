package com.book.mew.schedule.repository;

import com.book.mew.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional findByUserId(Long id);
}
