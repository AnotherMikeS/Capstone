import React from "react";
import "./home.css";
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import SignUp from "./components/SignUp";
import Schedule from "./components/Schedule";
import LogIn from "./components/LogIn";
import Home from "./components/Home";
import './LogIn.css';
import NavBar from "./components/NavBar";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(

    <main>
        <BrowserRouter>
            <NavBar />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/home" element={<Home />} />
                <Route path="/login" element={<LogIn />} />
                <Route path="/signup" element={<SignUp />} />
                <Route path="/schedule" element={<Schedule />} />
            </Routes>
        </BrowserRouter>
    </main>

);