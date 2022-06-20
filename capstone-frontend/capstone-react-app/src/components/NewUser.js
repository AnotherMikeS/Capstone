import React, { useState, useEffect } from 'react';

export default function NewUser() {

    // Set State for User Inputs
    const [users, setUsers] = useState([]);
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    // Set States for Validating Inputs
    const [submitted, setSubmitted] = useState(false);
    const [error, setError] = useState(false);

    // Prepare list of existing users
    useEffect(() => {
        fetch("http://localhost:8080/api/theater/person")
            .then(response => {
                if (response.status !== 200) {
                    return Promise.reject("There was an error.");
                }
                return response.json();
            })
            .then(json => setUsers(json))
            .catch(console.log);
    }, []);

    // Handle First Name Change
    const handleFirstName = (e) => {
        setFirstName(e.target.value);
        setSubmitted(false);
    };

    // Handle Last Name Change
    const handleLastName = (e) => {
        setLastName(e.target.value);
        setSubmitted(false);
    };

    // Handle UserName Change
    const handleUsername = (e) => {
        setUsername(e.target.value);
        setSubmitted(false);
    };

    // Handle Password Change
    const handlePassword = (e) => {
        setPassword(e.target.value);
        setSubmitted(false);
    };


    // Fetch for App User
    const postAppUser = () => {
        const newAppUser = { username, password };
        const initAppUser = { // Initialize POST request that will go to sql app_user
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newAppUser)
        };

        return fetch(`http://localhost:8080/create_account`, initAppUser) // POST app_user
            .then(response => {
                if (response.status === 404) {
                    setError(true);
                } else if (response.status === 400) {
                    setError(true);
                    alert("User already Exists!")
                } else if (response.status === 201) {
                    setError(false);
                } else {
                    alert("User could not be created");
                }
            });
    }

    // Fetch for Person
    const postPerson = () => {
        const newPerson = { appUserId: (users.length + 1), firstName, lastName }; // How to get app_user_id ??
        const initPerson = { // Initialize POST request that will go to sql person
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(newPerson)
        };

        return fetch(`http://localhost:8080/api/theater/person`, initPerson) // POST person
            .then(response => {
                if (response.status === 404) {
                    setError(true);
                } else if (response.status === 400) {
                    setError(true);
                    alert("This user already exists!")
                } else if (response.status === 201) {
                    setError(false);
                    alert("Success!")
                } else {
                    setError(true);
                }
            });
    }

    // Handle Form Submission
    const handleSubmit = async (e) => {
        e.preventDefault();

        if (firstName === '' || lastName === '' || username === '' || password === '') {
            setError(true);
        }

        await postAppUser();

        await postPerson();

        if (!error) {
            //document.location.reload();
        }
    };

    // // Successful
    // const successMessage = () => {
    //     return (
    //         <div
    //             className="success"
    //             style={{
    //                 display: submitted ? '' : 'none',
    //             }}>
    //             <h1>User {username} successfully registered!</h1>
    //         </div>
    //     );
    // };

    // // If Errors
    // const errorMessage = () => {
    //     return (
    //         <div
    //             className="error"
    //             style={{
    //                 display: error ? '' : 'none',
    //             }}>
    //             <h3>Error: User could not be created.</h3>
    //         </div>
    //     );
    // };

    return (
        <div className="container">
            <form novalidate>
                <div>
                    <h1>New User Registration</h1>
                </div>
                <div class="col-sm">
                    <div class="form-field">
                        <div class="form-field__control">
                            <input id="firstname" type="text" class="form-field__input" placeholder=" " value={firstName} onChange={handleFirstName} />
                            <label for="firstname" class="form-field__label">First name</label>
                            <div class="form-field__bar"></div>
                        </div>
                    </div>
                </div>
                <div class="col-sm">
                    <div class="form-field">
                        <div class="form-field__control">
                            <input id="lastname" type="text" class="form-field__input" placeholder=" " value={lastName} onChange={handleLastName} />
                            <label for="lastname" class="form-field__label">Last name</label>
                            <div class="form-field__bar"></div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm">
                        <div class="form-field">
                            <div class="form-field__control">
                                <input id="newUser" type="text" class="form-field__input" placeholder=" " value={username} onChange={handleUsername} />
                                <label for="newUser" class="form-field__label">Username</label>
                                <div class="form-field__bar"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm">
                        <div class="form-field">
                            <div class="form-field__control">
                                <input id="newPass" type="password" class="form-field__input" placeholder=" " value={password} onChange={handlePassword} />
                                <label for="newPass" class="form-field__label">Password</label>
                                <div class="form-field__bar"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <button onClick={handleSubmit} className="btn" type="submit">Submit</button>

            </form>
        </div>

        // <div className="form">
        //     <div>
        //         <h1>New User Registration</h1>
        //     </div>

        //     <div className="messages">
        //         {errorMessage()}
        //         {successMessage()}
        //     </div>

        //     <form>
        //         <label className="label">First Name</label>
        //         <input className="input" type="text" value={firstName} onChange={handleFirstName} /><br></br>

        //         <label className="label">Last Name</label>
        //         <input className="input" type="text" value={lastName} onChange={handleLastName} /><br></br>

        //         <label className="label">Username</label>
        //         <input className="input" type="text" value={username} onChange={handleUsername} /><br></br>

        //         <label className="label">Password (8 characters: digit, number, symbol)</label>
        //         <input className="password" type="password" value={password} onChange={handlePassword} /><br></br>

        //         <button onClick={handleSubmit} className="btn" type="submit">Submit</button>
        //     </form>
        // </div>
    );
}