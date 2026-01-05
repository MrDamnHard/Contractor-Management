import { useState } from "react";
import api from "../api";

function CreateJob() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const createJob = () => {
    api.post("/jobs", {
      title: title,
      description: description,
      createdBy: { id: 1 }   // Agent ID (simulated)
    })
    .then(() => {
      alert("Job created successfully");
      setTitle("");
      setDescription("");
    })
    .catch(() => alert("Failed to create job"));
  };

  return (
    <div className="card">
      <h3>Create Job</h3>

      <input
        placeholder="Job Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <br /><br />

      <input
        placeholder="Job Description"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />
      <br /><br />

      <button className="primary" onClick={createJob}>Create Job</button>
    </div>
  );
}

export default CreateJob;
