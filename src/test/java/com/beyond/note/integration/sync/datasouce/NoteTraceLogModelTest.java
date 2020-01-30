package com.beyond.note.integration.sync.datasouce;

import com.beyond.note.integration.entity.Document;
import com.beyond.note.integration.repository.TraceLogRepository;
import com.beyond.sync.datasouce.sql.TraceLog;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class NoteTraceLogModelTest {

    @Autowired
    NoteTraceLogModel noteTraceLogModel;

    @Autowired
    TraceLogRepository traceLogRepository;

    @BeforeEach
    void before(){
        System.out.println("before");
        TraceLog traceLog = new TraceLog();
        traceLog.setId("0");
        traceLog.setCreateTime(DateUtils.addDays(new Date(),-1));
        traceLog.setOperationTime(DateUtils.addDays(new Date(),-1));
        traceLog.setType(Document.NOTE);

        TraceLog traceLog1 = new TraceLog();
        traceLog1.setId("1");
        traceLog1.setCreateTime(DateUtils.addDays(new Date(),-2));
        traceLog1.setOperationTime(DateUtils.addDays(new Date(),-2));
        traceLog1.setType(Document.NOTE);

        TraceLog traceLog2 = new TraceLog();
        traceLog2.setId("2");
        traceLog2.setCreateTime(DateUtils.addDays(new Date(),-3));
        traceLog2.setOperationTime(DateUtils.addDays(new Date(),-3));
        traceLog2.setType(Document.NOTE);


        traceLogRepository.save(traceLog);
        traceLogRepository.save(traceLog1);
        traceLogRepository.save(traceLog2);

    }

    @Test
    void getAllWhereOperationTimeAfter() {

        List<TraceLog> allWhereOperationTimeAfter = noteTraceLogModel.getAllWhereOperationTimeAfter(DateUtils.addDays(new Date(),-3));

        System.out.println(allWhereOperationTimeAfter);
    }

    @Test
    void getAllWithoutSourceWhereCreateTimeAfter() {
    }
}