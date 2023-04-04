import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ListEmployeeComponent from "./components/ListEmployeeComponent";
import AddEmployeeComponent from "./components/AddEmployeeComponent";
import HeaderComponent from "./components/HeaderComponent";
import FooterComponent from "./components/FooterComponent";

function App() {
  return (
    <div>
      <BrowserRouter>
        <HeaderComponent />
        <div className="container">
          <Routes>
            <Route exact path="/" element={<ListEmployeeComponent />} />
            <Route path="/listEmployess" element={<ListEmployeeComponent />} />
            <Route path="/addEmployees" element={<AddEmployeeComponent />} />
            <Route path="/editEmployee/:id" element={<AddEmployeeComponent />} />
          </Routes>
        </div>
        <FooterComponent />
      </BrowserRouter>
    </div>
  );
}

export default App;
