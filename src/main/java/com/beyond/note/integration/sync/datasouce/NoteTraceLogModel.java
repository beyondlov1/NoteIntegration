package com.beyond.note.integration.sync.datasouce;

import com.beyond.note.integration.entity.Document;
import com.beyond.note.integration.repository.TraceLogRepository;
import com.beyond.sync.datasouce.sql.SqlLogModel;
import com.beyond.sync.datasouce.sql.TraceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class NoteTraceLogModel implements SqlLogModel {

    @Autowired
    private TraceLogRepository traceLogRepository;

    @Override
    public List<TraceLog> getAllWhereOperationTimeAfter(Date date) {
        return traceLogRepository.findAllByTypeAndOperationTimeGreaterThan(Document.NOTE,date);
    }

    @Override
    public List<TraceLog> getAllWithoutSourceWhereCreateTimeAfter(Date date, String key) {
        return traceLogRepository.findAllByTypeAndCreateTimeGreaterThan(Document.NOTE,date);
    }
}
