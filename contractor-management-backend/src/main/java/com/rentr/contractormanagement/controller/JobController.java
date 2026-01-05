package com.rentr.contractormanagement.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.rentr.contractormanagement.entity.Job;
import com.rentr.contractormanagement.service.JobService;

@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Agent creates a job
    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    // Contractor views open jobs
    @GetMapping
    public List<Job> getOpenJobs() {
        return jobService.getOpenJobs();
    }
}
