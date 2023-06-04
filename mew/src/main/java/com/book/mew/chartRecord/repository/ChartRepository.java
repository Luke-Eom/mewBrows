package com.book.mew.chartRecord.repository;

import com.book.mew.chartRecord.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChartRepository extends JpaRepository<Chart, Long> {

}
