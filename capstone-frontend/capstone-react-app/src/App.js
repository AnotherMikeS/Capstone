import React, { useState } from "react";
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import SignUp from "./components/SignUp";
import Schedule from "./components/Schedule";
import LogIn from "./components/LogIn";
import Home from "./components/Home";
import Edit from "./components/Edit";
//import './UserForms.css';
import NavBar from "./components/NavBar";
import UserForms from "./components/UserForms";

export default function App() {

    const [loggedIn, setLoggedIn] = useState(false);

    if (localStorage.getItem("token") && !loggedIn) {
        setLoggedIn(true);
    }  

    return (
        <BrowserRouter>
            <NavBar loggedIn={loggedIn} />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/home" element={<Home />} />
                <Route path="/forms" element={<UserForms />} />
                <Route path="/signup" element={<SignUp />} />
                <Route path="/edit" element={<Edit />} />
                <Route path="/schedule" element={<Schedule />} />
                <Route path="/login" element={<LogIn/>} />

                {/* <Route path="/login" element={userStatus.user ? (<Navigate to="/" />) : (<LogIn userStatus={userStatus} test={3} />)}/> */}
            </Routes>
        </BrowserRouter>
    );
}

// {
//     user: null,
//     login(username) {
//         setUserStatus((prev) => ({ ...prev, user: username }));
//     },
//     logout() {
//         localStorage.removeItem("token");
//         setUserStatus((prev) => ({ ...prev, user: null }));
//     },
// });