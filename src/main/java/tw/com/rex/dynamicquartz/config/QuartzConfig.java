package tw.com.rex.dynamicquartz.config;

import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

@Configuration
public class QuartzConfig {

    @Resource(name = "taskExecutor")
    private Executor taskExecutor;

    @Bean("schedulerFactoryBean")
    public SchedulerFactoryBean createFactoryBean(JobFactory jobFactory) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(jobFactory);
        factoryBean.setTaskExecutor(taskExecutor);
        factoryBean.setOverwriteExistingJobs(true);
        factoryBean.setStartupDelay(60);
        return factoryBean;
    }

    @Bean
    public Scheduler scheduler(@Qualifier("schedulerFactoryBean") SchedulerFactoryBean factoryBean) {
        return factoryBean.getScheduler();
    }

}
