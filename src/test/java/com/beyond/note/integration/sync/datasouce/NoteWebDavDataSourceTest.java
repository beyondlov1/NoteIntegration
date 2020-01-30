package com.beyond.note.integration.sync.datasouce;

import com.beyond.note.integration.entity.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoteWebDavDataSourceTest {

    @Autowired
    private NoteWebDavDataSource noteWebDavDataSource;

    @Test
    void selectAll() throws Exception {
        List<Note> notes = noteWebDavDataSource.selectAll();
        System.out.println(notes);
    }
}