package com.beyond.note.integration.refresh.file2Sql;

import com.beyond.note.integration.entity.Note;
import com.beyond.note.integration.refresh.TraceLogModel;
import com.beyond.note.integration.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class NoteFile2SqlMessageHandler implements MessageHandler {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private TraceLogModel traceLogModel;

    @SuppressWarnings("unchecked")
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Object payload = message.getPayload();
        if (payload instanceof List) {
            List<Note> notes = (List<Note>) payload;
            List<Note> sqlNotes = noteRepository.findAll();
            notes.removeAll(sqlNotes);

            updateSql(notes);
        }
    }

    private void updateSql(List<Note> notes) {
        if (CollectionUtils.isEmpty(notes)){
            return;
        }
        noteRepository.saveAll(notes);
        traceLogModel.record(notes);
    }
}
