package tw.com.rex.dynamicquartz.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "JOB_SETTING")
public class JobSettingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "JOB_ID")
    private String jobId;
    @Column(name = "CRON")
    private String cron;
    @Column(name = "CLASS_NAME")
    private String className;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "EFFECTIVE")
    private Boolean effective;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "UPDATE_USER")
    private String updateUser;
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

}
