package com.beyond.note.integration.refresh.file2Sql;

import com.beyond.note.integration.entity.Document;
import com.beyond.note.integration.entity.Note;
import com.beyond.note.integration.repository.NoteRepository;
import com.beyond.note.integration.repository.TraceLogRepository;
import com.beyond.sync.datasouce.sql.TraceLog;
import com.beyond.sync.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteDeleteFile2SqlMessageHandler implements MessageHandler {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private TraceLogRepository traceLogRepository;

    @SuppressWarnings("unchecked")
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Object payload = message.getPayload();
        if (payload instanceof List) {
            List<String> toDeleteIds = (List<String>) payload;
            List<Note> toDeleteNotes = noteRepository.findAllById(toDeleteIds);
            for (Note note : toDeleteNotes) {
                note.setValid(false);
                note.setLastModifyTime(new Date());
            }
            updateSql(toDeleteNotes);
        }
    }

    private void updateSql(List<Note> notes) {
        if (CollectionUtils.isEmpty(notes)){
            return;
        }
        noteRepository.saveAll(notes);

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
