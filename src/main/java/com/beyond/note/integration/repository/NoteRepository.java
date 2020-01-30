package com.beyond.note.integration.repository;

import com.beyond.note.integration.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, String> {

    List<Note> findAllByValid(boolean valid);

    List<Note> findAllByIdInAndValid(List<String> ids, boolean valid);
}
