import React, { useState, useEffect, Fragment } from 'react';
import '../CSS/MyAccount.css';


// My Account page
// See announcements
// See account info (username, first name, last name)
// See your audition info (use auditioneeId)
// edit button which takes you to edit page

export default function MyAccount() {
    // setState
    // user

    const [persons, setPersons] = useState([]);
    const [auditioneeList, setAuditioneeList] = useState([]);
    const [auditionee, setAuditionee] = useState({});
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");

    const [editMode, setEditMode] = useState(false);
    const toggleEdit = (evt) => {
        setEditMode(!editMode)
    };

    var userId = localStorage.getItem("id");

    useEffect(() => {
        // get a list of all people
        fetch("http://localhost:8080/api/theater/person")
            .then(resp => resp.json())
            .then(data => {
                setPersons(data);
            })

        const init = { // initialize the GET request
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        };

        fetch(`http://localhost:8080/api/theater/auditionee`, init)
            .then(resp => resp.json())
            .then(data => {
                setAuditioneeList(data);
            })
    }, []);

    var correctAuditioneeId;

    for (let i = 0; i < auditioneeList.length; i++) {
        if (userId == auditioneeList[i].appUserId) {

                correctAuditioneeId = auditioneeList[i].auditioneeId;
                const init = { // initialize the GET request
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": "Bearer " + localStorage.getItem("token")
                    }
                };

                fetch(`http://localhost:8080/api/theater/auditionee/${correctAuditioneeId}`, init)
                    .then(resp => resp.json())
                    .then(data => {
                        setAuditionee(data);
                    })

        }
    }

    const handleUpdate = () => {

        if (actualUser.part === undefined) {
            actualUser.part = auditionee.partId;
        }
        if (actualUser.timeSlot === undefined) {
            actualUser.timeSlot = auditionee.timeSlot;
        }
        if (actualUser.selection === undefined) {
            actualUser.selection = auditionee.selection;
        }

        console.log(actualUser.part);

        var editedAuditionee = {
            auditioneeId: auditionee.auditioneeId,
            appUserId: parseInt(userId),
            partId: parseInt(actualUser.part, 10),
            timeSlot: actualUser.timeSlot,
            selection: actualUser.selection
        };

        console.log(editedAuditionee);

        const init = { // initialize the GET request
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            },
            body: JSON.stringify(editedAuditionee)
        };

        fetch(`http://localhost:8080/api/theater/auditionee/${correctAuditioneeId}`, init) // Perform the PUT request with ID
            .then(response => {
                if (response.status === 404) {
                    console.log("Audition not found.");
                    //We're putting audition as the error message cause users won't know they're updating auditionee
                } else if (response.status === 400) {
                    alert("Unable to Update Audition Info"); // Invalid data produces a warning popup
                } else if (response.status === 200) {
                    console.log("Audition updated.");
                    setActualUser(editedAuditionee);
                } else {
                    console.log(`Audition update failed with status ${response.status}`);
                }
            });

        toggleEdit();
        document.location.reload();
    }

    const handleChange = (evt) => {
        const nextUser = { ...actualUser };
        nextUser[evt.target.name] = evt.target.value;
        setActualUser(nextUser);
    }


    // loop through all people until we find the app_user_id that matches the global state
    // use built in FIND function for arrays to find user whose app_user_id matches
    // currentPerson = persons.find(appUserId === userId)


    let peopleArray = persons;
    var person;
    for (let i = 0; i < peopleArray.length; i++) {
        if (userId == peopleArray[i].appUserId) {
            person = peopleArray[i];

            if (firstName !== person.firstName) {
                setFirstName(person.firstName);
            }
            if (lastName !== person.lastName) {
                setLastName(person.lastName);
            }
        }
    }

    var partNumber = auditionee.partId;
    var partString;
    if (partNumber === 1) {
        partString = "Acting";
    } else if (partNumber === 2) {
        partString = "Singing";
    }

    const userInfo = new Object();
    userInfo.firstName = firstName;
    userInfo.lastName = lastName;
    userInfo.timeSlot = auditionee.timeSlot;
    userInfo.selection = auditionee.selection;
    userInfo.part = partString;

    const [actualUser, setActualUser] = useState(userInfo);

    var userInfoArray = [];
    userInfoArray[0] = userInfo;

    // display user's account info
    // first name, last name (from person)
    // username (from app_user)
    // timeslot (from auditionee), selection (from auditionee) 
    // part (from part)
    // access type (from app_role)

    return (

        <div>
            <form>
                <div>
                    <h1>Your Account</h1>
                </div>
            {(localStorage.getItem("id") !== "1") ?
                <table>
                    <thead>
                        <tr>
                            <th>First Name: </th>
                            <th>Last Name: </th>
                            <th>Audition Time: </th>
                            <th>Selection: </th>
                            <th>Role: </th>
                            <th>Actions:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{userInfo.firstName}</td>
                            <td>{userInfo.lastName}</td>
                            <td>{editMode ?
                                <select select style={{ height: 40, width: 250, textAlign: 'center' }} name="timeSlot" id="timeSlot" onChange={handleChange}>
                                    <option value={userInfo.timeSlot} disabled selected>{userInfo.timeSlot}</option>
                                    <option value="2022-07-01 12:00pm">2022-07-01 12:00pm</option>
                                    <option value="2022-07-02 12:00pm">2022-07-02 12:00pm</option>
                                    <option value="2022-07-03 12:00pm">2022-07-03 12:00pm</option>
                                </select>
                                : userInfo.timeSlot}</td>

                            <td>{editMode ?
                                <input autoComplete="off" style={{ height: 40, width: 300 }} type="text" name="selection" id="selection" className="form-control_input" placeholder={userInfo.selection}
                                    value={actualUser.selection} onChange={handleChange} />
                                : userInfo.selection}</td>
                            <td>{editMode ?
                                <div>
                                    <select style={{ height: 40, width: 100, textAlign: 'center' }} name="part" id="part" onChange={handleChange}>
                                        <option value={userInfo.part} disabled selected>{userInfo.part}</option>
                                        <option value="1">Acting</option>
                                        <option value="2">Singing</option>
                                    </select>
                                </div>

                                : userInfo.part}</td>

                            <td>
                                <tr>
                                    <td><button type="button" className="btn" onClick={toggleEdit}>{editMode ? "Cancel" : "Edit"}</button></td>

                                    {(editMode) ? <td><button className="btn" onClick={handleUpdate}>Save</button></td> : <></>}

                                </tr>
                            </td>
                        </tr>
                    </tbody>
                </table>
                :
                <h2>You are the System Admin!</h2>
                }
            </form>
        </div>
    );
}