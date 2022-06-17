import React, {useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import Error from "./Error";

export default function LogIn() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState([]);
    
    const navigate = useNavigate();
    //console.log(props);
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
      
        // This code executes if the request is successful
        if (response.status === 200) {
          const { jwt_token } = await response.json();
      
          localStorage.setItem("token", jwt_token);
          console.log("token");

          navigate("/home");
          document.location.reload();
        
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
        <div>
          <h2>Login</h2>
      
          {errors.map((error, i) => (
            <Error key={i} msg={error} />
          ))}
      
          <form onSubmit={handleSubmit}>
            <div>
              <label>Username:</label>
              <input
                type="text"
                onChange={(event) => setUsername(event.target.value)}
              />
            </div>
            <div>
              <label>Password:</label>
              <input
                type="password"
                onChange={(event) => setPassword(event.target.value)}
              />
            </div>
            <div>
              <button type="submit">Login</button>
            </div>
          </form>
        </div>
      );
}