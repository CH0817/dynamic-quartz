package tw.com.rex.dynamicquartz.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tw.com.rex.dynamicquartz.entity.JobSettingEntity;
import tw.com.rex.dynamicquartz.repository.JobSettingRepository;
import tw.com.rex.dynamicquartz.service.JobSettingService;
import tw.com.rex.dynamicquartz.web.request.JobSettingRequest;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class JobSettingServiceImpl implements JobSettingService {

    private final JobSettingRepository repository;
    private final Scheduler scheduler;

    @Override
    public void startJob() throws Exception {
        List<JobSettingEntity> effectiveJobs = repository.findByEffectiveTrue();
        if (!CollectionUtils.isEmpty(effectiveJobs)) {
            for (JobSettingEntity jobSettingEntity : effectiveJobs) {
                scheduleJob(jobSettingEntity);
            }
        }
        scheduler.start();
    }

    @Override
    public void addJob(JobSettingRequest request) throws ClassNotFoundException, SchedulerException {
        JobSettingEntity entity = new JobSettingEntity();
        BeanUtils.copyProperties(request, entity);
        JobSettingEntity savedJob = repository.save(entity);
        scheduleJob(savedJob);
    }

    /**
     * 暫停 job
     *
     * @param jobId task id
     */
    @Override
    public void pauseJob(String jobId) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobId));
    }

    @Override
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    @Override
    public void resumeJob(String jobId) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobId));
    }

    @Override
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    @Override
    public void deleteJob(String jobId) throws SchedulerException {
        JobSettingEntity entity = repository.findByJobId(jobId);
        entity.setEffective(Boolean.FALSE);
        repository.save(entity);
        removeJob(jobId);
    }

    @Override
    public void updateJob(JobSettingRequest request) throws SchedulerException, ClassNotFoundException {
        JobSettingEntity entity = repository.findByJobId(request.getJobId());
        BeanUtils.copyProperties(request, entity, "jobId");
        JobSettingEntity savedEntity = repository.save(entity);
        removeJob(savedEntity.getJobId());
        if (savedEntity.getEffective()) {
            scheduleJob(savedEntity);
        }
    }

    /**
     * 增加 job 到 scheduler
     *
     * @param jobSettingEntity JobSettingEntity
     */
    private void scheduleJob(JobSettingEntity jobSettingEntity) throws ClassNotFoundException, SchedulerException {
        CronTrigger cronTrigger = getCronTrigger(jobSettingEntity);
        JobDetail jobDetail = getJobDetail(jobSettingEntity);
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 從 scheduler 移除 job
     *
     * @param jobId job id
     */
    private void removeJob(String jobId) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobId));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobId));
        scheduler.deleteJob(JobKey.jobKey(jobId));
    }

    /**
     * job setting to JobDetail
     *
     * @param configEntity JobSettingEntity
     * @return JobDetail
     */
    private JobDetail getJobDetail(JobSettingEntity configEntity) throws ClassNotFoundException {
        return JobBuilder.newJob()
                         .withIdentity(JobKey.jobKey(configEntity.getJobId()))
                         .withDescription(configEntity.getDescription())
                         .ofType(Class.forName(configEntity.getClassName()).asSubclass(Job.class))
                         .build();
    }

    /**
     * job setting to CronTrigger
     *
     * @param configEntity JobSettingEntity
     * @return CronTrigger
     */
    private CronTrigger getCronTrigger(JobSettingEntity configEntity) {
        return TriggerBuilder.newTrigger()
                             .withIdentity(TriggerKey.triggerKey(configEntity.getJobId()))
                             .withSchedule(CronScheduleBuilder.cronSchedule(configEntity.getCron()))
                             .build();
    }

}
