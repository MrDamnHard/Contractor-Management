import { useState } from "react";
import RoleSelector from "./components/RoleSelector";
import JobList from "./components/JobList";
import CreateJob from "./components/CreateJob";
import WorkOrderList from "./components/WorkOrderList";
import SubmitInvoice from "./components/SubmitInvoice";
import InvoiceList from "./components/InvoiceList";
import "./App.css";

function App() {
  const [role, setRole] = useState("");

  return (
    <div className="container">
      <h2>Contractor Job Management</h2>

      <RoleSelector setRole={setRole} />

      {role === "CONTRACTOR" && (
        <div className="section">
          <JobList />
          <SubmitInvoice />
        </div>
      )}

      {role === "AGENT" && (
        <div className="section">
          <CreateJob />
          <WorkOrderList />
          <InvoiceList />
        </div>
      )}
    </div>
  );

}

export default App;
