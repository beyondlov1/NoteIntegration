package com.beyond.note.integration.refresh;

import com.beyond.note.integration.entity.Note;

import java.util.List;

public interface TraceLogModel {
    void record(List<Note> notes);
}
