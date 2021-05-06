package tw.com.rex.dynamicquartz.service;

import org.quartz.SchedulerException;
import tw.com.rex.dynamicquartz.web.request.JobSettingRequest;

public interface JobSettingService {

    /**
     * server 啟動開始執行 job
     */
    void startJob() throws Exception;

    /**
     * 新增 job
     *
     * @param request JobSettingRequest
     */
    void addJob(JobSettingRequest request) throws ClassNotFoundException, SchedulerException;

    /**
     * 停止 job
     *
     * @param taskId task id
     */
    void pauseJob(String taskId) throws SchedulerException;

    /**
     * 停止所有 job
     */
    void pauseAllJob() throws SchedulerException;

    /**
     * 恢復 job
     *
     * @param taskId task id
     */
    void resumeJob(String taskId) throws SchedulerException;

    /**
     * 恢復所有 job
     */
    void resumeAllJob() throws SchedulerException;

    /**
     * 刪除 job
     *
     * @param taskId task id
     */
    void deleteJob(String taskId) throws SchedulerException, ClassNotFoundException;

    /**
     * 更新 job
     *
     * @param request JobSettingRequest
     */
    void updateJob(JobSettingRequest request) throws SchedulerException, ClassNotFoundException;

}
