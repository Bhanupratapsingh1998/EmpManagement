import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";

const AddEmployeeComponent = () => {
  const navigate = useNavigate();
  const [firstName, setFistName] = useState("");
  const [lastName, setLastName] = useState("");
  const [emailId, setEmailId] = useState("");
  const { id } = useParams();

  const saveEmployee = (e) => {
    e.preventDefault();
    const employee = { firstName, lastName, emailId };
    // console.log(employee);
    if (id) {
      EmployeeService.updateEmployee(id, employee)
        .then((res) => {
          console.log(res.data);
          navigate("/listEmployess");
        })
        .catch((err) => console.log(err));
    } else {
      EmployeeService.AddEmployee(employee)
        .then((res) => {
          console.log(res.data);
          navigate("/listEmployess");
        })
        .catch((err) => console.log(err));
    }
  };
  useEffect(() => {
    EmployeeService.getEmployeeById(id)
      .then((res) => {
        setFistName(res.data.firstName);
        setLastName(res.data.lastName);
        setEmailId(res.data.emailId);
      })

      .catch((err) => console.log(err));
  }, []);

  const title = () => {
    if (id) {
      return <h2 className="text-center">Update Employee</h2>;
    } else {
      return <h2 className="text-center">Add Employee</h2>;
    }
  };

  return (
    <div>
      <div className="container mt-5">
        <div className="row">
          <div className="card col-md-6 offset-md-3">
            {title()}
            <div className="card-body">
              <form>
                <div className="form-group">
                  <label for="name">FirstName:</label>
                  <input
                    type="text"
                    className="form-control"
                    name="firstName"
                    id="firstName"
                    placeholder="Enter your firstName"
                    value={firstName}
                    onChange={(e) => setFistName(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label for="email">LastName:</label>
                  <input
                    type="text"
                    className="form-control"
                    name="lastName"
                    id="lastName"
                    placeholder="Enter your lastName"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label for="email">Email:</label>
                  <input
                    type="email"
                    className="form-control"
                    name="emailId"
                    id="emailId"
                    placeholder="Enter your email"
                    value={emailId}
                    onChange={(e) => setEmailId(e.target.value)}
                  />
                </div>
                <button onClick={(e) => saveEmployee(e)} className="btn btn-success m-2">
                  Submit
                </button>
                <button onClick={() => navigate("/listEmployess")} className="btn btn-danger">
                  Cancel
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddEmployeeComponent;
