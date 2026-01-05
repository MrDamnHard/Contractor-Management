import { useEffect, useState } from "react";
import api from "../api";

function InvoiceList() {
  const [invoices, setInvoices] = useState([]);

  useEffect(() => {
    api.get("/invoices")
      .then(res => setInvoices(res.data))
      .catch(() => alert("Failed to load invoices"));
  }, []);

  return (
    <div className="section">
      <h3>Submitted Invoices</h3>

      {invoices.length === 0 && <p>No invoices submitted yet</p>}

      {invoices.map(inv => (
        <div key={inv.id} className="card">
          <p><b>Invoice ID:</b> {inv.id}</p>
          <p><b>Amount:</b> ₹{inv.amount}</p>
          <p><b>Status:</b> {inv.status}</p>
          <p><b>Work Order ID:</b> {inv.workOrder?.id}</p>
          <p><b>Job:</b> {inv.workOrder?.job?.title}</p>
        </div>
      ))}
    </div>
  );
}

export default InvoiceList;
