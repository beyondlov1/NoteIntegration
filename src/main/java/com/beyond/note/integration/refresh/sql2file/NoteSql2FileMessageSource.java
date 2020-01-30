package com.beyond.note.integration.refresh.sql2file;

import com.beyond.note.integration.entity.Note;
import com.beyond.note.integration.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteSql2FileMessageSource implements MessageSource<List<Note>> {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Message<List<Note>> receive() {
        List<Note> all = noteRepository.findAllByValid(true);
        return new GenericMessage<>(all);
    }
}
