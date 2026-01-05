## Contractor Job & Work Order Management System

A full-stack application that models a real-world property maintenance workflow. Agents (property managers) create maintenance jobs, and contractors apply, get assigned, and submit invoices after completion.

---

## 📖 Project Overview

In property management, maintenance tasks (plumbing, electrical repairs, general fixes) require a structured lifecycle. This application implements that workflow using a **Spring Boot** backend and a **React** frontend, focusing on correctness, clarity, and real-world constraints.

### Core Workflow
1. **Agents** raise maintenance jobs  
2. **Contractors** apply for those jobs  
3. **Agents** review and assign work  
4. **Contractors** complete work and submit invoices  

---

## ⚙️ Tech Stack

### Backend
- ☕ Java 17  
- 🍃 Spring Boot (Web, Data JPA)  
- 🐘 PostgreSQL  
- 🔌 REST APIs  

### Frontend
- ⚛️ React (Functional Components + Hooks)  
- 📡 Axios  
- 🎨 Plain CSS (clean, minimal UI)

---

## 👥 User Roles

### 🧑‍💼 Agent (Landlord / Property Manager)
- Creates maintenance jobs  
- Reviews contractor applications  
- Approves work orders  
- Views submitted invoices  

### 👷 Contractor (Service Provider)
- Views available jobs  
- Applies for jobs (only once per job)  
- Views approved work orders  
- Submits invoices after completion  

---

## 📋 Detailed Workflow Steps

### 1️⃣ Job Creation (Agent)
- Agent creates a job with title and description  
- Status: `OPEN`

### 2️⃣ Job Discovery & Application (Contractor)
- Contractor views open jobs  
- Duplicate applications prevented (frontend + backend)

### 3️⃣ Work Order Approval (Agent)
- Agent approves a contractor  
- Job becomes assigned and locked

### 4️⃣ Invoice Submission (Contractor)
- Contractor submits one invoice per work order  
- Already invoiced orders are hidden

### 5️⃣ Invoice Review (Agent)
- Agent views submitted invoices (read-only)

---


## ⚠️ Assumptions & Limitations
### No authentication

### Single agent & contractor for demo

### No payment processing

### Functional module, not production SaaS

## 🏢 Relevance to Property Platforms

### Maintenance job management

### Contractor assignment

### Approval-based workflows

### Post-completion invoicing



