package com.example.demo.scheduling;

import com.example.demo.service.DataFetcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataFetcherScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(DataFetcherScheduledTasks.class);
    private DataFetcherService dataFetcherService;

    public DataFetcherScheduledTasks(DataFetcherService dataFetcherService) {
        this.dataFetcherService = dataFetcherService;
    }

    @Scheduled(cron="${scheduling.update_agile_engine_data_cron}")
    public void reportCurrentTime() {
        Integer pages = dataFetcherService.fetchImagesByPage("1").getPages();
        if (pages != null) {
            for (int i = 2; i < pages; i++) {
                dataFetcherService.fetchImagesByPage(String.valueOf(i)).getPages();
            }
        }

		log.info("fetched all data from "+ pages + " pages ");
    }
}