import React, { useState } from "react";
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import SignUp from "./components/SignUp";
import Schedule from "./components/Schedule";
import LogIn from "./components/LogIn";
import Home from "./components/Home";
//import './UserForms.css';
import NavBar from "./components/NavBar";
import UserForms from "./components/UserForms";

export default function App() {

    const [userStatus, setUserStatus] = useState({
        user: null,
        login(username) {
            setUserStatus((prev) => ({ ...prev, user: username }));
        },
        logout() {
            localStorage.removeItem("token");
            setUserStatus((prev) => ({ ...prev, user: null }));
        },
    });

    return (
        <BrowserRouter>
            <NavBar userStatus={userStatus} />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/home" element={<Home />} />
                <Route path="/forms" element={<UserForms />} />
                <Route path="/signup" element={<SignUp />} />
                <Route path="/schedule" element={<Schedule />} />
                <Route path="/login" element={<LogIn userStatus={userStatus} test={3} />}/>

                {/* <Route path="/login" element={userStatus.user ? (<Navigate to="/" />) : (<LogIn userStatus={userStatus} test={3} />)}/> */}
            </Routes>
        </BrowserRouter>
    );
}