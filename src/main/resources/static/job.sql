INSERT INTO JOB_SETTING (JOB_ID, CRON, CLASS_NAME, DESCRIPTION, EFFECTIVE, CREATE_USER, CREATE_DATE, UPDATE_USER,
                         UPDATE_DATE)
VALUES ('job01', '0/2 * * * * ?', 'tw.com.rex.dynamicquartz.job.TestJob', '測試 job', 1, 'Rex.Yu', CURRENT_TIMESTAMP(),
        'Rex.Yu', CURRENT_TIMESTAMP());

INSERT INTO JOB_SETTING (JOB_ID, CRON, CLASS_NAME, DESCRIPTION, EFFECTIVE, CREATE_USER, CREATE_DATE, UPDATE_USER,
                         UPDATE_DATE)
VALUES ('job02', '0/5 * * * * ?', 'tw.com.rex.dynamicquartz.job.TestJob02', '測試 job', 0, 'Rex.Yu', CURRENT_TIMESTAMP(),
        'Rex.Yu', CURRENT_TIMESTAMP());