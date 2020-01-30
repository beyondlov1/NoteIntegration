package com.beyond.note.integration.refresh;

import com.beyond.note.integration.entity.Document;
import com.beyond.note.integration.entity.Note;
import com.beyond.note.integration.repository.TraceLogRepository;
import com.beyond.sync.datasouce.sql.TraceLog;
import com.beyond.sync.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TraceLogModelImpl implements TraceLogModel{

    @Autowired
    private TraceLogRepository traceLogRepository;

    @Override
    public void record(List<Note> notes) {
        List<TraceLog> traceLogs = notes.stream().map(x -> {
            TraceLog traceLog = new TraceLog();
            traceLog.setId(IDUtil.uuid());
            traceLog.setType(Document.NOTE);
            traceLog.setCreateTime(new Date());
            traceLog.setOperationTime(x.getLastModifyTime());
            traceLog.setDocumentId(x.getId());
            return traceLog;
        }).collect(Collectors.toList());
        traceLogRepository.saveAll(traceLogs);
    }
}
