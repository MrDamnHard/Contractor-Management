package com.rentr.contractormanagement.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.rentr.contractormanagement.entity.Job;
import com.rentr.contractormanagement.enums.JobStatus;
import com.rentr.contractormanagement.repository.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job createJob(Job job) {
        job.setStatus(JobStatus.OPEN);
        return jobRepository.save(job);
    }

    public List<Job> getOpenJobs() {
        return jobRepository.findByStatus(JobStatus.OPEN);
    }
}
