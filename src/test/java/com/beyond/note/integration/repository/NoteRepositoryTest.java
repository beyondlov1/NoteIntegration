package com.beyond.note.integration.repository;

import com.beyond.note.integration.entity.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    void save() {
        Note note = Note.create();
        note.setContent("hello1");
        note.setTitle("hello title");
        note.setId("erwe");
        noteRepository.save(note);
    }

}