package com.rentr.contractormanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.rentr.contractormanagement.entity.Job;
import com.rentr.contractormanagement.enums.JobStatus;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByStatus(JobStatus status);
}
