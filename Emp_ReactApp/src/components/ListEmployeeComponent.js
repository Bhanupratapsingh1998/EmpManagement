import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Container, Pagination, PaginationItem, PaginationLink } from "reactstrap";
import EmployeeService from "../services/EmployeeService";

const ListEmployeeComponent = () => {
  //   const navigate = useNavigate();
  // const [employees, setEmployess] = useState([]);
  const [employees, setEmployess] = useState([], {
    content: [],
    totalPages: "",
    totalElements: "",
    pageSize: "",
    lastPage: false,
    pageNumber: "",
  });

  const changePage = (pageNumber = 0, pageSize = 5) => {
    EmployeeService.getAllEmployees(pageNumber, pageSize)
      .then((res) => setEmployess(res.data))
      .catch((err) => console.log(err));
  };
  useEffect(() => {
    getAllEmployees();
  }, []);

  const getAllEmployees = () => {
    EmployeeService.getAllEmployees(0, 5)
      .then((res) => setEmployess(res.data))
      .catch((err) => console.log(err));
  };

  const deleteEmployee = (id) => {
    EmployeeService.deleteEmployee(id)
      .then((res) => {
        getAllEmployees();
      })
      .catch((err) => console.log(err));
  };
  return (
    <div>
      <h2 className="text-center mt-3">Employee List</h2>
      <div className="row">
        <Link className="btn btn-primary mb-2" to="/addEmployees">
          Add Employee
        </Link>
        {/* <button onClick={() => navigate("/addEmployees")}>Add Employee</button> */}
      </div>
      <div className="row">
        <table className="table table-striped table-bordered">
          <thead>
            <tr>
              <th>Emp Id</th>
              <th>Employee First Name</th>
              <th>Employee Last Name</th>
              <th>Employee Email Id</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {employees.map((employee) => (
              <tr key={employee.id}>
                <td>{employee.id}</td>
                <td>{employee.firstName}</td>
                <td>{employee.lastName}</td>
                <td>{employee.emailId}</td>
                <td>
                  <Link to={`/editEmployee/${employee.id}`} className="btn btn-warning m-2">
                    Edit
                  </Link>
                  <button onClick={() => deleteEmployee(employee.id)} className="btn btn-danger">
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <Container className="text-center mt-3">
        <Pagination>
          <PaginationItem disabled={employees.pageNumber == 0}>
            <PaginationLink previous></PaginationLink>
          </PaginationItem>
          {[...Array(employees.totalPages)].map((item, index) => (
            <PaginationItem onClick={() => changePage(index)} active={employees.pageNumber == index} key={index}>
              <PaginationLink>{index + 1}</PaginationLink>
            </PaginationItem>
          ))}

          <PaginationItem disabled={employees.lastPage}>
            <PaginationLink next></PaginationLink>
          </PaginationItem>
        </Pagination>
      </Container>
    </div>
  );
};

export default ListEmployeeComponent;
