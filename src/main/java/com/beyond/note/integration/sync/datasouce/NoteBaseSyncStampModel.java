package com.beyond.note.integration.sync.datasouce;

import com.beyond.note.integration.entity.Document;
import com.beyond.note.integration.repository.BaseSyncStampRepository;
import com.beyond.note.integration.sync.VirtualKeyManager;
import com.beyond.sync.datasouce.webdav.support.stamp.SyncStampModel;
import com.beyond.sync.entity.BaseSyncStamp;
import com.beyond.sync.entity.SyncStamp;
import com.beyond.sync.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class NoteBaseSyncStampModel implements SyncStampModel {

    @Autowired
    private BaseSyncStampRepository baseSyncStampRepository;

    @Autowired
    private VirtualKeyManager virtualKeyManager;

    @Override
    public void update(SyncStamp syncStamp, String oppositeKey) throws IOException {
        BaseSyncStamp stamp = new BaseSyncStamp();
        stamp.setId(IDUtil.uuid());
        stamp.setLocalKey(virtualKeyManager.getKey());
        stamp.setRemoteKey(oppositeKey);
        stamp.setLastModifyTime(syncStamp.getLastModifyTime());
        stamp.setLastSyncTime(syncStamp.getLastSyncTimeEnd());
        stamp.setLastSyncTimeStart(syncStamp.getLastSyncTimeStart());
        stamp.setType(Document.NOTE);
        baseSyncStampRepository.save(stamp);
    }

    @Override
    public SyncStamp retrieve(String oppositeKey) throws IOException {
        String thisKey = virtualKeyManager.getKey();

        BaseSyncStamp baseSyncStamp = baseSyncStampRepository.findByLocalKeyAndRemoteKeyAndType(thisKey, oppositeKey, Document.NOTE);

        if (baseSyncStamp == null) {
            baseSyncStamp = baseSyncStampRepository.findByLocalKeyAndRemoteKeyAndType(oppositeKey, thisKey, Document.NOTE);
        }
        return baseSyncStamp == null ? SyncStamp.ZERO :
                SyncStamp.create(baseSyncStamp.getLastModifyTime(),
                        baseSyncStamp.getLastSyncTimeStart(),
                        baseSyncStamp.getLastSyncTime());
    }


    @Override
    public Map<String, SyncStamp> findAllConnectMe() throws IOException {
        String thisKey = virtualKeyManager.getKey();
        List<BaseSyncStamp> baseSyncStamps = baseSyncStampRepository.findAllByLocalKeyAndType(thisKey, Document.NOTE);

        Map<String,SyncStamp> syncStamps = new LinkedHashMap<>();
        for (BaseSyncStamp baseSyncStamp : baseSyncStamps) {
            syncStamps.put(baseSyncStamp.getRemoteKey(),
                    SyncStamp.create(baseSyncStamp.getLastModifyTime(),
                            baseSyncStamp.getLastSyncTimeStart(),
                            baseSyncStamp.getLastSyncTime()));
        }

        List<BaseSyncStamp> list2 =baseSyncStampRepository.findAllByRemoteKeyAndType(thisKey, Document.NOTE);
        for (BaseSyncStamp baseSyncStamp : list2) {
            syncStamps.put(baseSyncStamp.getRemoteKey(),
                    SyncStamp.create(baseSyncStamp.getLastModifyTime(),
                            baseSyncStamp.getLastSyncTimeStart(),
                            baseSyncStamp.getLastSyncTime()));
        }
        return syncStamps;
    }
}
