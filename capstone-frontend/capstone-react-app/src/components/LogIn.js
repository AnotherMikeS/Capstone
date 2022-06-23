import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Error from "./Error";

export default function LogIn(props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState([]);

  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();

    const response = await fetch(window.API_URL + "/authenticate", {
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
      const resp = await response.json();
      
      localStorage.setItem("token", resp.jwt_token);
      localStorage.setItem("id", parseInt(resp.appUserId, 10));
      props.login(username, resp.appUserId);

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

      <div className="messages">
        {errors.map((error, i) => (
          <Error key={i} msg={error} />
        ))}
      </div>

      <div className="container">
            <form>

                <div>
                    <h1>Log in to your account</h1>
                </div>

                <div class="row">
                    <div class="col-sm">
                        <div class="form-field">
                            <div class="form-field__control">
                                <input id="oldUser" type="text" class="form-field__input" placeholder=" " onChange={(event) => setUsername(event.target.value)} />
                                <label for="oldUser" class="form-field__label">Username</label>
                                <div class="form-field__bar"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm">
                        <div class="form-field">
                            <div class="form-field__control">
                                <input id="oldPass" type="password" class="form-field__input" placeholder=" " onChange={(event) => setPassword(event.target.value)} />
                                <label for="oldPass" class="form-field__label">Password</label>
                                <div class="form-field__bar"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <button onClick={handleSubmit} className="btn" type="submit">Submit</button>

            </form>
        </div>
      </div>
  );
}