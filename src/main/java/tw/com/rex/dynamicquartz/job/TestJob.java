package tw.com.rex.dynamicquartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("test job " + now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

}
