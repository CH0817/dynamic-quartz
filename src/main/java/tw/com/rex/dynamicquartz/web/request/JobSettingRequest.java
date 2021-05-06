package tw.com.rex.dynamicquartz.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "Job 參數")
@Data
public class JobSettingRequest implements Serializable {

    @ApiModelProperty(value = "job id", example = "test job")
    private String jobId;
    @ApiModelProperty(value = "CRON表達式", example = "0/2 * * * * ?")
    private String cron;
    @ApiModelProperty(value = "執行job的class(含package)", example = "tw.com.rex.dynamicquartz.job.TestJob")
    private String className;
    @ApiModelProperty(value = "job 描述", example = "測試 job")
    private String description;
    @ApiModelProperty(value = "job是否有效", example = "true")
    private Boolean effective;

}
