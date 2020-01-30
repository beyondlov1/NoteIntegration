package com.beyond.note.integration.sync.datasouce;

import com.beyond.note.integration.entity.Account;
import com.beyond.note.integration.entity.Note;
import com.beyond.sync.SyncStateManager;
import com.beyond.sync.datasouce.DataSource;
import com.beyond.sync.datasouce.MultiDataSource;
import com.beyond.sync.datasouce.webdav.DavDataSourceProperty;
import com.beyond.sync.datasouce.webdav.DefaultMultiDavDataSource;
import com.beyond.sync.datasouce.webdav.support.SardineDavClient;
import com.beyond.sync.entity.SyncStamp;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class NoteWebDavDataSource implements MultiDataSource<Note> {

    private DefaultMultiDavDataSource<Note> defaultMultiDavDataSource;

    public NoteWebDavDataSource(Account account,SyncStateManager syncStateManager) {
        DavDataSourceProperty property = new DavDataSourceProperty(account.getUsername(), account.getPassword(), account.getServer());
        defaultMultiDavDataSource = new DefaultMultiDavDataSource<>(new SardineDavClient(account.getUsername(), account.getPassword()), property, Note.class, syncStateManager);
    }

    @Override
    public void setChosenKey(String key) {
        defaultMultiDavDataSource.setChosenKey(key);
    }

    @Override
    public String getChosenKey() {
        return defaultMultiDavDataSource.getChosenKey();
    }

    @Override
    public void initLastSyncStamps() throws IOException {
        defaultMultiDavDataSource.initLastSyncStamps();
    }

    @Override
    public Map<String, SyncStamp> getSyncStampsCache() {
        return defaultMultiDavDataSource.getSyncStampsCache();
    }

    @Override
    public void setSyncStampsCache(Map<String, SyncStamp> syncStamps) {
        defaultMultiDavDataSource.setSyncStampsCache(syncStamps);
    }

    @Override
    public void init() {
        defaultMultiDavDataSource.init();
    }

    @Override
    public String getKey() {
        return defaultMultiDavDataSource.getKey();
    }

    @Override
    public void saveAll(List<Note> notes, String... oppositeKeys) throws Exception {
        defaultMultiDavDataSource.saveAll(notes, oppositeKeys);
    }

    @Override
    public List<Note> selectAll() throws Exception {
        return defaultMultiDavDataSource.selectAll();
    }

    @Override
    public boolean isChanged(DataSource<Note> targetDataSource) throws IOException {
        return defaultMultiDavDataSource.isChanged(targetDataSource);
    }

    @Override
    public List<Note> getChangedData(SyncStamp syncStamp, @Nullable DataSource<Note> targetDataSource) throws IOException {
        return defaultMultiDavDataSource.getChangedData(syncStamp, targetDataSource);
    }

    @Override
    public SyncStamp getLastSyncStamp(DataSource<Note> targetDataSource) throws IOException {
        return defaultMultiDavDataSource.getLastSyncStamp(targetDataSource);
    }

    @Override
    public void updateLastSyncStamp(SyncStamp syncStamp, DataSource<Note> targetDataSource) throws IOException {
        defaultMultiDavDataSource.updateLastSyncStamp(syncStamp, targetDataSource);
    }

    @Override
    public SyncStamp getLatestSyncStamp() throws IOException {
        return defaultMultiDavDataSource.getLatestSyncStamp();
    }

    @Override
    public void updateLatestSyncStamp(SyncStamp syncStamp) throws IOException {
        defaultMultiDavDataSource.updateLatestSyncStamp(syncStamp);
    }

    @Override
    public Class<Note> clazz() {
        return defaultMultiDavDataSource.clazz();
    }

    @Override
    public boolean tryLock(Long time) {
        return defaultMultiDavDataSource.tryLock(time);
    }

    @Override
    public boolean isLocked() {
        return defaultMultiDavDataSource.isLocked();
    }

    @Override
    public boolean tryLock() {
        return defaultMultiDavDataSource.tryLock();
    }

    @Override
    public boolean release() {
        return defaultMultiDavDataSource.release();
    }
}
