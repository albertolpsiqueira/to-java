import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import EditUser from "./pages/EditUser";

const App: React.FC = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/edit/:email" element={<EditUser />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
