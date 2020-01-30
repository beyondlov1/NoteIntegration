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

import java.util.Date;
import java.util.List;

@Component
public class NoteDeleteFile2SqlMessageHandler implements MessageHandler {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private TraceLogModel traceLogModel;

    @SuppressWarnings("unchecked")
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Object payload = message.getPayload();
        if (payload instanceof List) {
            List<String> toDeleteIds = (List<String>) payload;
            List<Note> toDeleteNotes = noteRepository.findAllByIdInAndValid(toDeleteIds, true);
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
        traceLogModel.record(notes);
    }
}
