package com.beyond.note.integration.sync;

import com.beyond.note.integration.repository.SyncStateRepository;
import com.beyond.sync.SyncStateManager;
import com.beyond.sync.entity.SyncState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SyncStateManagerImpl implements SyncStateManager {

    @Autowired
    private SyncStateRepository syncStateRepository;

    @Override
    public void saveAll(List<SyncState> successSyncStates) {
        syncStateRepository.saveAll(successSyncStates);
    }

    @Override
    public List<SyncState> findAll() {
        return syncStateRepository.findAll();
    }


    @Override
    public void deleteConnectedEachOtherIn(List<String> keys, Class clazz) {
        syncStateRepository.deleteAllByLocalInAndServerInAndType(keys,keys,clazz.getSimpleName().toLowerCase());
    }
}
