import React, { useState, useEffect, Fragment } from 'react';
import '../MyAccount.css';
import EditableRow from './EditableRow';
import ReadOnlyRow from './ReadOnlyRow';


// My Account page
// See announcements
// See account info (username, first name, last name)
// See your audition info (use auditioneeId)
// edit button which takes you to edit page

export default function MyAccount() {
    // setState
    // user
    const [user, setUser] = ([]);
    const [persons, setPersons] = useState([]);
    const [auditionees, setAuditionees] = useState([]);
    const [username, setUsername] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [editUserId, setEditUserId] = useState(null); // Need to find ID in order to edit and delete. If NULL, Editable Row does NOT show.
    const [editFormData, setEditFormData] = useState({ // Need blank object in order to store updated data
        firstName: "",
        lastName: "",
        timeSlot: "",
        selection: "",
        role: ""
    });


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

        fetch(`http://localhost:8080/api/theater/auditionee/${userId}`, init)
            .then(resp => resp.json())
            .then(data => {
                setAuditionees(data);
            })
    }, []);

    var userId = localStorage.getItem("id");

    console.log("all Auditionees: " + auditionees.auditioneeId);

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

    var partNumber = auditionees.partId;
    var partString;
    if (partNumber === 1) {
        partString = "Acting";
    } else if (partNumber === 2) {
        partString = "Singing";
    }

    const userInfo = {
        firstName: firstName,
        lastName: lastName,
        timeSlot: auditionees.timeSlot,
        selection: auditionees.selection,
        part: partString
    }

    var userInfoArray = [];
    userInfoArray[0] = userInfo;

    // display user's account info
    // first name, last name (from person)
    // username (from app_user)
    // timeslot (from auditionee), selection (from auditionee) 
    // part (from part)
    // access type (from app_role)
    const handleEditFormSubmit = (event) => { // Form submit is an event

        const editedUser = { // Must fill the editedAgent object with data from the editForm input
            userId: editUserId, // DON'T FORGET TO INCLUDE ID ON PUT REQUEST!!!!
            firstName: editFormData.firstName,
            lastName: editFormData.lastName,
            timeSlot: editFormData.timeSlot,
            selection: editFormData.selection,
            role: editFormData.role
        };

        // const init = { // Prepare the PUT request
        //     method: "PUT",
        //     headers: {
        //         "Content-Type": "application/json",
        //         "Accept": "application/json"
        //     },
        //     body: JSON.stringify(editedAgent)
        // };

        // fetch(`http://localhost:8080/api/agent/${editedAgent.agentId}`, init) // Perform the PUT request with ID
        //     .then(response => {
        //         if (response.status === 404) {
        //             console.log("Agent not found.");
        //         } else if (response.status === 400) {
        //             alert("Agents must be older than 12 and taller than 36 inches."); // Invalid data produces a warning popup
        //         } else if (response.status === 204) {
        //             console.log("Agent updated.");
        //         } else {
        //             console.log(`Agent update failed with sataus ${response.status}`);
        //         }
        //     });

        setEditUserId(null); // In order for the Editable Row to disappear, ID must be null
    }

    // GET NEW DATA FROM EDITS
    const handleEditFormChange = (event) => { // Form Change is event
        event.preventDefault(); // Don't want page to refresh before we're ready

        const fieldName = event.target.getAttribute("name"); // Get each input field by it's name
        const fieldValue = event.target.value; // Assign the value of each input field

        const newFormData = { ...editFormData }; // Create object with edited data from inputs
        newFormData[fieldName] = fieldValue; // Fill newFormData
        setEditFormData(newFormData); // Set the Form for use
    }

    // MORE EDIT/PUT
    const handleEditClick = (event, userInfo) => { // Edit click is an event and also decides which agent to edit
        event.preventDefault(); // Don't refresh page on click
        setEditUserId(userInfo.userId); // Set ID for edit

        const formValues = { // Set the form values
            firstName: userInfo.firstName,
            lastName: userInfo.lastName,
            timeSlot: userInfo.timeSlot,
            selection: userInfo.selection,
            role: userInfo.role
        };

        setEditFormData(formValues);
    }

    const handleCancelClick = () => {
        setEditUserId(null); // Editable Row must disappear if edit is cancelled
    }


    return (

        <div>
            <form onSubmit={handleEditFormSubmit}>
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
                        {/* <tr>
                            <td>{firstName}</td>
                            <td>{lastName}</td>
                            <td>{auditionees.timeSlot}</td>
                            <td>{auditionees.selection}</td>
                            <td>{partString}</td>
                        </tr> */}
                        {userInfo.map(u => {
                            return (<Fragment>
                                {editUserId === u.userId ? (
                                    <EditableRow
                                        editFormData={editFormData}
                                        handleEditFormChange={handleEditFormChange}
                                        handleCancelClick={handleCancelClick}
                                    />
                                ) : (
                                    <ReadOnlyRow user={u}
                                        handleEditClick={handleEditClick}
                                    />
                                )}
                            </Fragment>)
                        }
                        
                    )}
                    </tbody>
                </table>
            </form>
        </div>


    );
}