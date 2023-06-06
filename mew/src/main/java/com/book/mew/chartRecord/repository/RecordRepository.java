package com.book.mew.chartRecord.repository;

import com.book.mew.chartRecord.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {

}
