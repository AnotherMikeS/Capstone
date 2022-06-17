import React, { useState, useEffect } from 'react';


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
    const [username, setUsername] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");

    useEffect(() => {
        // get a list of all people
        fetch("http://localhost:8080/api/theater/person")
            .then(resp => resp.json())
            .then(data => {
                setPersons(data);
            })
    }, []);

    console.log("all persons: ", persons);
    var userId = localStorage.getItem("id");
    console.log("user id: ", userId);
    var personId;

    // loop through all people until we find the app_user_id that matches the global state
    for (var i = 0; i < persons.length; i++) {

        // take that result and get the person_id
        if (persons[i].appUserId === userId) {
            personId = (persons[i].personId)
        }
        console.log("person id: ", personId);

    }

    // display user's account info



}