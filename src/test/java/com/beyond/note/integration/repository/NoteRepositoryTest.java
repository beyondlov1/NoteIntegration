package com.beyond.note.integration.repository;

import com.beyond.note.integration.entity.Note;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void save() {
        Note note = Note.create();
        note.setContent("hello1");
        note.setTitle("hello title");
        note.setId("erwe");
        noteRepository.save(note);
    }

    @Test
    void findAll() throws JsonProcessingException {
        List<Note> all = noteRepository.findAll();
        System.out.println(objectMapper.writeValueAsString(all));
    }

}