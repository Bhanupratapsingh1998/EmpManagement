import axios from "axios";
const EMPLOYEE_BASE_URL = "http://localhost:8081/api/v1";
class EmployeeService {
  getAllEmployees(pageNumber, pageSize) {
    return axios.get(`${EMPLOYEE_BASE_URL}/getEmployees?pageNumber=${pageNumber}&pageSize=${pageSize}`);
  }

  AddEmployee(employee) {
    return axios.post(`${EMPLOYEE_BASE_URL}/Employees`, employee);
  }

  getEmployeeById(employeeId) {
    return axios.get(`${EMPLOYEE_BASE_URL}/Employees/${employeeId}`);
  }

  updateEmployee(employeeId, employee) {
    return axios.put(`${EMPLOYEE_BASE_URL}/Employees/${employeeId}`, employee);
  }

  deleteEmployee(employeeId) {
    return axios.delete(`${EMPLOYEE_BASE_URL}/Employees/${employeeId}`);
  }
}
export default new EmployeeService();
