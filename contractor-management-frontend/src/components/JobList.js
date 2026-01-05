import { useEffect, useState } from "react";
import api from "../api";

function JobList() {
  const [jobs, setJobs] = useState([]);
  const [appliedJobIds, setAppliedJobIds] = useState([]);

  useEffect(() => {
    // Load all jobs
    api.get("/jobs")
      .then(res => setJobs(res.data))
      .catch(() => alert("Failed to load jobs"));

    // Load contractor's applied work orders
    api.get("/work-orders")
      .then(res => {
        const applied = res.data
          .filter(
            wo =>
              wo.contractor?.id === 2 &&
              wo.status === "SUBMITTED"
          )
          .map(wo => wo.job?.id);

        setAppliedJobIds(applied);
      })
      .catch(() => alert("Failed to load applied jobs"));
  }, []);

  const applyForJob = (jobId) => {
    api.post("/work-orders/apply", {
      jobId: jobId,
      contractorId: 2,
      note: "Interested"
    })
    .then(() => {
      alert("Applied successfully");
      setAppliedJobIds(prev => [...prev, jobId]);
    })
    .catch(err => {
      if (err.response?.data?.message) {
        alert(err.response.data.message);
      } else {
        alert("You have already applied for this job");
      }
    });
  };

  return (
    <div className="section">
      <h3>Available Jobs</h3>

      {jobs.length === 0 && <p>No jobs available</p>}

      {jobs.map(job => (
        <div key={job.id} className="card">
          <b>{job.title}</b>
          <p>{job.description}</p>

          {appliedJobIds.includes(job.id) ? (
            <button className="disabled" disabled>
              Applied
            </button>
          ) : (
            <button
              className="primary"
              onClick={() => applyForJob(job.id)}
            >
              Apply
            </button>
          )}
        </div>
      ))}
    </div>
  );
}

export default JobList;
