package com.beyond.note.integration.config;

import com.beyond.note.integration.entity.Account;
import com.beyond.note.integration.entity.Note;
import com.beyond.note.integration.sync.WebDavProperty;
import com.beyond.note.integration.sync.datasouce.NoteWebDavDataSource;
import com.beyond.sync.DefaultMultiSynchronizer;
import com.beyond.sync.SyncStateManager;
import com.beyond.sync.Synchronizer;
import com.beyond.sync.datasouce.MultiDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.messaging.MessageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class IntegrationConfig {

//    @Bean
//    public IntegrationFlow noteWebDavIntegrationFlow(WebDavProperty webDavProperty, Synchronizer<Note> noteSynchronizer) {
//        MessageSource<SyncStamp> syncStampWebDavMessageSource = new SyncStampWebDavMessageSource(webDavProperty.getAccountMap().get("mydrive"));
//        MessageHandler syncStampMessageHandler = new SyncStampMessageHandler(noteSynchronizer);
//        return IntegrationFlows.from(syncStampWebDavMessageSource, e -> e.poller(Pollers.fixedDelay(10000)))
//                .transform(SyncStamp.class, source -> source)
//                .handle(syncStampMessageHandler)
//                .get();
//    }


    @Bean
    public IntegrationFlow noteSql2FileIntegrationFlow(MessageSource<List<Note>> noteSql2FileMessageSource,
                                                       MessageHandler noteSql2FileMessageHandler) {
        return IntegrationFlows.from(noteSql2FileMessageSource, e -> e.poller(Pollers.fixedDelay(2000)))
                .transform(List.class, source -> source)
                .handle(noteSql2FileMessageHandler)
                .get();
    }


    @Bean
    public IntegrationFlow noteFile2SqlIntegrationFlow(MessageSource<List<Note>> noteFile2SqlMessageSource,
                                                       MessageHandler noteFile2SqlMessageHandler) {
        return IntegrationFlows.from(noteFile2SqlMessageSource, e -> e.poller(Pollers.fixedDelay(2000)))
                .transform(List.class, source -> source)
                .handle(noteFile2SqlMessageHandler)
                .get();
    }

    @Bean
    public IntegrationFlow noteDeleteFile2SqlIntegrationFlow(MessageSource<List<String>> noteDeleteFile2SqlMessageSource,
                                                       MessageHandler noteDeleteFile2SqlMessageHandler) {
        return IntegrationFlows.from(noteDeleteFile2SqlMessageSource, e -> e.poller(Pollers.fixedDelay(2000)))
                .transform(List.class, source -> source)
                .handle(noteDeleteFile2SqlMessageHandler)
                .get();
    }

    @Bean
    public Synchronizer<Note> noteSynchronizer(List<MultiDataSource<Note>> noteDataSources,
                                               ExecutorService executorService,
                                               SyncStateManager syncStateManager,
                                               WebDavProperty webDavProperty) {
        List<NoteWebDavDataSource> noteWebDavDataSources = new ArrayList<>();
        Map<String, Account> accountMap = webDavProperty.getAccountMap();
        for (String serverName : accountMap.keySet()) {
            Account account = webDavProperty.getAccountMap().get(serverName);
            noteWebDavDataSources.add(new NoteWebDavDataSource(account, syncStateManager));
        }
        noteDataSources.addAll(noteWebDavDataSources);
        return new DefaultMultiSynchronizer<>(noteDataSources, executorService, syncStateManager);
    }



    @Bean
    ExecutorService executorService() {
        return new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors() * 2 + 1, 60,
                60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
    }
}
