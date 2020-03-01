package com.beyond.note.integration.sync.datasouce;

import com.beyond.note.integration.entity.Note;
import com.beyond.note.integration.repository.AttachmentRepository;
import com.beyond.note.integration.repository.NoteRepository;
import com.beyond.sync.datasouce.sql.TraceableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Component
public class NoteModel implements TraceableModel<Note> {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public List<Note> findAllById(Collection<String> keySet) {
        return noteRepository.findAllById(keySet);
    }

    @Override
    public void addAll(List<Note> addList) {
        noteRepository.saveAll(addList);
    }

    @Override
    public void updateAll(List<Note> updateList) {
        noteRepository.saveAll(updateList);
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }
}
