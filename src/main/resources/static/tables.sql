create table JOB_SETTING
(
    ID          bigint       not null auto_increment comment 'id',
    JOB_ID      varchar(50)  not null unique comment 'job id',
    CRON        varchar(30)  not null comment 'CRON 表達式',
    CLASS_NAME  varchar(100) not null comment '執行job的class(含package)',
    DESCRIPTION varchar comment 'job 描述',
    EFFECTIVE   boolean default true comment 'job是否有效',
    CREATE_USER varchar(20) comment '建立人員',
    CREATE_DATE datetime comment '建立時間',
    UPDATE_USER varchar(20) comment '更新人員',
    UPDATE_DATE datetime comment '更新時間',
    primary key (ID)
);