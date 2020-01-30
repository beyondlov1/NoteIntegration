package com.beyond.note.integration;

import com.beyond.sync.Synchronizer;
import com.beyond.sync.entity.SyncStamp;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

public class SyncStampMessageHandler implements MessageHandler {

    private Synchronizer synchronizer;

    public SyncStampMessageHandler(Synchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Object payload = message.getPayload();
        if (payload instanceof SyncStamp) {
            boolean isNeeded = needSync((SyncStamp) payload);
            if (isNeeded){
                try {
                    synchronizer.sync();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean needSync(SyncStamp syncStamp) {
        //TODO
        return true;
    }

}
