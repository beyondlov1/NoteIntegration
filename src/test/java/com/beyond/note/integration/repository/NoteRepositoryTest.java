package com.beyond.note.integration.repository;

import com.beyond.note.integration.entity.Attachment;
import com.beyond.note.integration.entity.Note;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.collection.internal.PersistentBag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void save() {
        Note note = Note.create();
        note.setContent("hello1");
        note.setTitle("hello title");
        note.setId("erwe");
        List<Attachment> attachments = new ArrayList<>();
        Attachment attachment = new Attachment();
        Note note2 = new Note();
        note2.setId(note.getId());
        attachment.setNote(note);
        attachment.setNoteId("erwe1223");
        attachment.setId("hell");
        attachment.setName("yes iam attachment");
        attachments.add(attachment);
        note.setAttachments(attachments);
        noteRepository.save(note);
        List<Note> all = noteRepository.findAll();
        for (Note note1 : all) {
            System.out.println(note1);
            List<Attachment> attachments1 = note1.getAttachments();
            for (Attachment attachment1 : attachments1) {
                System.out.println(attachment1);
//                System.out.println(attachment1.getNote().getId());
            }
        }
        noteRepository.deleteAll();
        List<Note> all2 = noteRepository.findAll();
        for (Note note1 : all2) {
            System.out.println(note1);
            List<Attachment> attachments1 = note1.getAttachments();
            for (Attachment attachment1 : attachments1) {
                System.out.println(attachment1);
//                System.out.println(attachment1.getNote().getId());
            }
        }
    }

    @Test
    void findAll() throws JsonProcessingException {
        List<Note> all = noteRepository.findAll();
        System.out.println(objectMapper.writeValueAsString(all));
    }

}