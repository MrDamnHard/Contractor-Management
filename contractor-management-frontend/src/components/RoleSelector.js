function RoleSelector({ setRole }) {
  return (
    <div>
      <h4>Select Role</h4>
      <select onChange={(e) => setRole(e.target.value)}>
        <option value="">-- Select --</option>
        <option value="AGENT">Agent</option>
        <option value="CONTRACTOR">Contractor</option>
      </select>
    </div>
  );
}

export default RoleSelector;
