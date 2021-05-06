package tw.com.rex.dynamicquartz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.rex.dynamicquartz.entity.JobSettingEntity;

import java.util.List;

@Repository
public interface JobSettingRepository extends JpaRepository<JobSettingEntity, Integer> {

    /**
     * 查詢有效的 job
     *
     * @return JobSettingEntity list
     */
    List<JobSettingEntity> findByEffectiveTrue();

    /**
     * job id 查詢
     *
     * @param jobId job id
     * @return JobSettingEntity
     */
    JobSettingEntity findByJobId(String jobId);

}
