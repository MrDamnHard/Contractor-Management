import { useEffect, useState } from "react";
import api from "../api";

function WorkOrderList() {
  const [workOrders, setWorkOrders] = useState([]);

  useEffect(() => {
    api.get("/work-orders")
      .then(res => setWorkOrders(res.data))
      .catch(() => alert("Failed to load work orders"));
  }, []);

  const approveWorkOrder = (id) => {
    api.put(`/work-orders/${id}/approve`)
      .then(() => {
        alert("Work order approved");
        return api.get("/work-orders");
      })
      .then(res => setWorkOrders(res.data))
      .catch(() => alert("Approval failed"));
  };

  return (
    <div className="section">
      <h3>Work Orders</h3>

      {workOrders.length === 0 && <p>No work orders</p>}

      {workOrders.map((wo) => (
        <div key={wo.id} className="card">
          <p><b>WorkOrder ID:</b> {wo.id}</p>
          <p><b>Job ID:</b> {wo.job?.id}</p>
          <p><b>Contractor ID:</b> {wo.contractor?.id}</p>
          <p><b>Status:</b> {wo.status}</p>

          {wo.status === "SUBMITTED" && (
            <button
              className="success"
              onClick={() => approveWorkOrder(wo.id)}
            >
              Approve
            </button>
          )}
        </div>
      ))}
    </div>
  );
}

export default WorkOrderList;
