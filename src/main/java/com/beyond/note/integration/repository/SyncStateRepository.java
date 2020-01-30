package com.beyond.note.integration.repository;

import com.beyond.sync.entity.SyncState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface SyncStateRepository extends JpaRepository<SyncState,String> {

    @Modifying
    @Transactional
    void deleteAllByLocalInAndServerInAndType(List<String> keys, List<String> keys1, String type);
}
