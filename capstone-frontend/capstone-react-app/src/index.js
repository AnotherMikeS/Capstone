import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import App from './App';
import SignUp from "./components/SignUp";
import Schedule from "./components/Schedule";
import LogIn from "./components/LogIn";
import Home from "./components/Home";
import './LogIn.css';
import NotFound from './components/NotFound';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<App />}>
                <Route path="home" element={<Home />} />
                <Route path="login" element={<LogIn />} />
                <Route path="signup" element={<SignUp/>} />
                <Route path="schedule" element={<Schedule />} />
            </Route>
        </Routes>
    </BrowserRouter>
);