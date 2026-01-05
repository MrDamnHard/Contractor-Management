import { useEffect, useState } from "react";
import api from "../api";

function SubmitInvoice() {
  const [workOrders, setWorkOrders] = useState([]);
  const [selectedWorkOrder, setSelectedWorkOrder] = useState(null);
  const [amount, setAmount] = useState("");

  useEffect(() => {
    api.get("/work-orders/contractor/2/approved")
      .then(woRes => {
        api.get("/invoices").then(invRes => {

          const invoicedWorkOrderIds = invRes.data.map(
            inv => inv.workOrder?.id
          );

          const eligibleWorkOrders = woRes.data.filter(
            wo => !invoicedWorkOrderIds.includes(wo.id)
          );

          setWorkOrders(eligibleWorkOrders);
        });
      })
      .catch(() => alert("Failed to load invoice data"));
  }, []);

  const submitInvoice = () => {
    if (!selectedWorkOrder) {
      alert("Please select a work order");
      return;
    }

    api.post("/invoices", {
      amount: parseFloat(amount),
      workOrder: { id: selectedWorkOrder }
    })
    .then(() => {
      alert("Invoice submitted successfully");
      setAmount("");
      setSelectedWorkOrder(null);
    })
    .catch(err => {
      alert(err.response?.data?.message || "Invoice already submitted");
    });
  };

  return (
    <div className="section">
      <div className="card">
        <h3>Generate Invoice</h3>

        <select
          value={selectedWorkOrder || ""}
          onChange={(e) => setSelectedWorkOrder(e.target.value)}
        >
          <option value="">Select Approved Work Order</option>
          {workOrders.map(wo => (
            <option key={wo.id} value={wo.id}>
              WorkOrder #{wo.id} — Job {wo.job?.title}
            </option>
          ))}
        </select>

        {workOrders.length === 0 && (
          <p style={{ color: "gray" }}>
            No pending invoices to submit
          </p>
        )}

        <br /><br />

        <input
          placeholder="Amount"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />

        <br /><br />

        <button className="primary" onClick={submitInvoice}>
          Submit Invoice
        </button>
      </div>
    </div>
  );
}

export default SubmitInvoice;
