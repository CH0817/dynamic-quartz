package tw.com.rex.dynamicquartz.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tw.com.rex.dynamicquartz.service.JobSettingService;

/**
 * server 啟動開始加載 job
 */
@Slf4j
@AllArgsConstructor
@Component
public class QuartzInitListener implements CommandLineRunner {

    private final JobSettingService jobSettingService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Job 加載開始");
        jobSettingService.startJob();
        log.info("Job 加載結束");
    }

}
