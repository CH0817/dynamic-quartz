package tw.com.rex.dynamicquartz.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.rex.dynamicquartz.service.JobSettingService;
import tw.com.rex.dynamicquartz.web.request.JobSettingRequest;

@Api(tags = "Job Configuration Setting")
@RestController("/job")
@AllArgsConstructor
public class JobController {

    private final JobSettingService jobSettingService;

    /**
     * 新增 job
     *
     * @param request JobSettingRequest
     */
    @ApiOperation(value = "新增 job")
    @PostMapping(path = "/add",
                 consumes = {MediaType.APPLICATION_JSON_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<Boolean> add(@RequestBody JobSettingRequest request) throws Exception {
        jobSettingService.addJob(request);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @ApiOperation("暫停 job")
    @PostMapping("/pause/{taskId}")
    public HttpEntity<Boolean> pauseJob(@ApiParam(value = "任務 id", required = true)
                                        @PathVariable String taskId) throws Exception {
        jobSettingService.pauseJob(taskId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @ApiOperation("暫停全部 job")
    @PostMapping("/pause/all")
    public HttpEntity<Boolean> pauseAllJob() throws Exception {
        jobSettingService.pauseAllJob();
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @ApiOperation("恢復 job")
    @PostMapping("/resume/{taskId}")
    public HttpEntity<Boolean> resumeJob(@ApiParam(value = "任務 id", required = true)
                                         @PathVariable String taskId) throws Exception {
        jobSettingService.resumeJob(taskId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @ApiOperation("恢復全部 job")
    @PostMapping("/resume/all")
    public HttpEntity<Boolean> resumeAllJob() throws Exception {
        jobSettingService.resumeAllJob();
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @ApiOperation("刪除 job")
    @DeleteMapping("/delete/{taskId}")
    public HttpEntity<Boolean> deleteJob(@ApiParam(value = "任務 id", required = true)
                                         @PathVariable String taskId) throws Exception {
        jobSettingService.deleteJob(taskId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @ApiOperation("更新 job")
    @PutMapping("/update")
    public HttpEntity<Boolean> updateJob(@RequestBody JobSettingRequest request) throws Exception {
        jobSettingService.updateJob(request);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
