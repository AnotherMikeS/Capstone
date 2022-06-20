import React, { useState } from "react";
import { BrowserRouter, Routes, Route, useNavigate } from 'react-router-dom';
import SignUp from "./components/SignUp";
import Schedule from "./components/Schedule";
import Home from "./components/Home";
import Edit from "./components/Edit";
//import './UserForms.css';
import NavBar from "./components/NavBar";
import UserForms from "./components/UserForms";
import MyAccount from "./components/MyAccount";
import './CSS/App.css';

export default function App() {
    const [userStatus, setUserStatus] = useState({ username: null, userId: 0 });

    const login = (username, userId) => {
        setUserStatus({ ...userStatus, username: username, userId: userId })
    };

    const logout = () => {
        let confirmActin = window.confirm("Are you sure you want to log out?");
        if (confirmActin) {
            localStorage.removeItem("token");
            localStorage.removeItem("id");
            setUserStatus((userStatus) => ({ ...userStatus, username: null, userId: 0 }));
            window.location = '/home';
        } else {
            alert("Log out Canceled!")
        }
    };

    return (
        <BrowserRouter>
            <NavBar userStatus={userStatus} logout={logout} />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/home" element={<Home />} />
                <Route path="/forms" element={<UserForms userStatus={userStatus} login={login} />} />
                <Route path="/signup" element={<SignUp />} />
                <Route path="/edit" element={<Edit />} />
                <Route path="/schedule" element={<Schedule />} />
                <Route path="/myaccount" element={<MyAccount />} />
            </Routes>
        </BrowserRouter>
    );
}

