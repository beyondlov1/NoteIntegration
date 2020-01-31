package com.beyond.note.integration.sync;

import com.beyond.note.integration.entity.Note;
import com.beyond.sync.Synchronizer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SyncScheduleTask {

    private Synchronizer<Note> noteSynchronizer;

    public SyncScheduleTask(Synchronizer<Note> noteSynchronizer) {
        this.noteSynchronizer = noteSynchronizer;
    }

    @Scheduled(fixedDelay = 1000*60*10)
    public void sync() throws Exception {
        noteSynchronizer.sync();
    }
}
