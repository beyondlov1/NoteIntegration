package com.beyond.note.integration.repository;

import com.beyond.sync.entity.BaseSyncStamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaseSyncStampRepository extends JpaRepository<BaseSyncStamp,String> {

    BaseSyncStamp findByLocalKeyAndRemoteKeyAndType(String thisKey, String oppositeKey, String type);

    List<BaseSyncStamp> findAllByLocalKeyAndType(String thisKey, String type);

    List<BaseSyncStamp> findAllByRemoteKeyAndType(String thisKey, String type);
}
