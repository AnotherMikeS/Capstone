import React, { useState, useEffect } from 'react';
import { findAllInRenderedTree } from 'react-dom/test-utils';
import Error from "./Error";

// Edit an existing audition
export default function Edit() {

    // Must be logged in (and be ADMIN?) in order to use
    // the EDIT feature to edit an audition


    // sets state for inputs
    const [selection, setNewSelection] = useState("");
    const [timeSlot, setNewTimeSlot] = useState(""); // what is this since it's drop down?, not string..
    const [role, setNewRole] = useState("");
    const [errors, setErrors] = useState([]);

    const [submitted, setSubmitted] = useState(false);


    // handle new role
    // i.e. will you be acting or singing?
    const handleNewRole = (e) => {
        setNewRole(e.target.value);
        setSubmitted(false);
    }

    // handle new selection
    const handleNewSelection = (e) => {
        setNewSelection(e.target.value);
        setSubmitted(false);
    };

    // handle new timeslot
    // drop down menu
    const handleNewTimeSlot = (e) => {
        setNewTimeSlot(e.target.value);
        setSubmitted(false);
    }

    // handle submit
    const handleSubmit = async (e) => {
        e.preventDefault();
    };

    const response = fetch("http://localhost:8080/api/theater/auditionee", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            // auditioneeId,
            // appUserId,
            // partId,
            timeSlot,
            selection,
        }),
    });

    // if the request is a success, display a message
    if (response.status === 200 - 299) {
        response.json();
        // if error, display errors
    } else if (response.status === 400) {
        const errors = response.json();
        setErrors(errors);
    } else if (response.status === 403) {
        setErrors(["Edit failed"]);
    } else {
        setErrors(["Unknown error"]);
    }
};

return (

    <div className="form">
        <div>
            <h1>Make Changes To An Audition</h1>
        </div>

        <form>
            <label className="label">Type of Audition</label>
            <select className="role" value={role} onChange={handleNewRole}>
                <option value="Keep My Current Audition Type"></option>
                <option value="Acting">Acting</option>
                <option value="Singing">Singing</option>
            </select>
            <br></br>
            <br></br>

            <label className="label">Piece To Be Performed</label>
            <input className="input" type="text" value={selection} onChange={handleNewSelection} />
            <br></br>
            <br></br>

            <label className="label">Time Slot</label>
            <select className="timeslot" value={timeSlot} onChange={handleNewTimeSlot}>
                <option value="Keep My Current Time Slot">Keep Current Time Slot</option>
                <option value="2022-07-01 12:00pm">2022-07-01 12:00pm</option>
                <option value="2022-07-01 1:00pm">2022-07-01 1:00pm</option>
                <option value="2022-07-01 2:00pm">2022-07-01 2:00pm</option>
            </select>

            <br></br>
            <br></br>

            <button onClick={handleSubmit} className="btn btn-success" type="submit">Submit</button>

        </form>

    </div >
)

}