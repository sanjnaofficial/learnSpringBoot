package com.example.dvdRentalSystem.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartUpSyncRunner implements ApplicationRunner {
    private final SyncService syncService;

    public StartUpSyncRunner(SyncService syncService) {
        this.syncService = syncService;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        syncService.syncActorsToElastic();
        syncService.syncFilmsToElastic();
    }
}
