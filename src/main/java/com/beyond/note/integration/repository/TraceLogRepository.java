package com.beyond.note.integration.repository;

import com.beyond.sync.datasouce.sql.TraceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TraceLogRepository extends JpaRepository<TraceLog,String> {

    List<TraceLog> findAllByTypeAndOperationTimeGreaterThan(String type, Date operationTime);

    List<TraceLog> findAllByTypeAndCreateTimeGreaterThan(String note, Date createTime);
}
