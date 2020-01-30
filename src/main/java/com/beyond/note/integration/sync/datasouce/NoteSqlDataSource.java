package com.beyond.note.integration.sync.datasouce;

import com.beyond.note.integration.entity.Note;
import com.beyond.note.integration.sync.VirtualKeyManager;
import com.beyond.sync.datasouce.DataSource;
import com.beyond.sync.datasouce.MultiDataSource;
import com.beyond.sync.datasouce.sql.DefaultSqlDataSource;
import com.beyond.sync.entity.SyncStamp;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Component
public class NoteSqlDataSource implements MultiDataSource<Note> {

    private MultiDataSource<Note> multiDataSource;

    public NoteSqlDataSource(VirtualKeyManager virtualKeyManager,
                             NoteTraceLogModel noteTraceLogModel,
                             NoteBaseSyncStampModel noteBaseSyncStampModel,
                             NoteModel noteModel) {
        this.multiDataSource = new DefaultSqlDataSource<>(
                noteModel,
                noteBaseSyncStampModel,
                noteTraceLogModel,
                Note.class,
                virtualKeyManager.getKey()
        );
    }

    @Override
    public void setChosenKey(String key) {
        multiDataSource.setChosenKey(key);
    }

    @Override
    public String getChosenKey() {
        return multiDataSource.getChosenKey();
    }

    @Override
    public void initLastSyncStamps() throws IOException {
        multiDataSource.initLastSyncStamps();
    }

    @Override
    public Map<String, SyncStamp> getSyncStampsCache() {
        return multiDataSource.getSyncStampsCache();
    }

    @Override
    public void setSyncStampsCache(Map<String, SyncStamp> syncStamps) {
        multiDataSource.setSyncStampsCache(syncStamps);
    }

    @Override
    public void init() {
        multiDataSource.init();
    }

    @Override
    public String getKey() {
        return multiDataSource.getKey();
    }

    @Override
    public void saveAll(List<Note> notes, String... oppositeKeys) throws Exception {
        multiDataSource.saveAll(notes, oppositeKeys);
    }

    @Override
    public List<Note> selectAll() throws Exception {
        return multiDataSource.selectAll();
    }

    @Override
    public boolean isChanged(DataSource<Note> targetDataSource) throws Exception {
        return multiDataSource.isChanged(targetDataSource);
    }

    @Override
    public List<Note> getChangedData(SyncStamp syncStamp, @Nullable DataSource<Note> targetDataSource) throws Exception {
        return multiDataSource.getChangedData(syncStamp, targetDataSource);
    }

    @Override
    public SyncStamp getLastSyncStamp(DataSource<Note> targetDataSource) throws Exception {
        return multiDataSource.getLastSyncStamp(targetDataSource);
    }

    @Override
    public void updateLastSyncStamp(SyncStamp syncStamp, DataSource<Note> targetDataSource) throws Exception {
        multiDataSource.updateLastSyncStamp(syncStamp, targetDataSource);
    }

    @Override
    public SyncStamp getLatestSyncStamp() throws Exception {
        return multiDataSource.getLatestSyncStamp();
    }

    @Override
    public void updateLatestSyncStamp(SyncStamp syncStamp) throws Exception {
        multiDataSource.updateLatestSyncStamp(syncStamp);
    }

    @Override
    public Class<Note> clazz() {
        return multiDataSource.clazz();
    }

    @Override
    public boolean tryLock() {
        return multiDataSource.tryLock();
    }

    @Override
    public boolean tryLock(Long time) {
        return multiDataSource.tryLock(time);
    }

    @Override
    public boolean isLocked() {
        return multiDataSource.isLocked();
    }

    @Override
    public boolean release() {
        return multiDataSource.release();
    }
}
