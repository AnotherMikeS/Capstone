import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Error from "./Error";

export default function LogIn(props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState([]);

  const navigate = useNavigate();
  console.log("LogIn: ", props);

  const handleSubmit = async (event) => {
    event.preventDefault();

    const response = await fetch("http://localhost:8080/authenticate", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username,
        password,
      }),
    });

    if (response.status === 200) {
      const please = await response.json();
      console.log(please);
      
      const loggedInUser = await fetch
      
      localStorage.setItem("token", please.jwt_token);
      localStorage.setItem("id", please.appUserId);
      props.login(username, 1);

      navigate("/home");

    } else if (response.status === 400) {
      const errors = await response.json();
      setErrors(errors);
    } else if (response.status === 403) {
      setErrors(["Login failed."]);
    } else {
      setErrors(["Unknown error."]);
    }
  };

  return (

    <div className="form">
      <div>
        <h1>Login</h1>
      </div>

      <div className="messages">
        {errors.map((error, i) => (
          <Error key={i} msg={error} />
        ))}
      </div>

      <form>
          <label className="label">Username:</label>
          <input className="input" type="text" onChange={(event) => setUsername(event.target.value)}/>

        
          <label className="label">Password:</label>
          <input className="input" type="password" onChange={(event) => setPassword(event.target.value)}/> <br></br>

          <button onClick={handleSubmit} className="btn" type="submit">Login</button>

      </form>
      </div>
  );
}