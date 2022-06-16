import { setSelectionRange } from '@testing-library/user-event/dist/utils';
import React, { useState, useEffect } from 'react';

// Edit an existing audition
export default function Edit() {

    // Must be logged in (and be ADMIN?) in order to use
    // the EDIT feature to edit an audition


    // sets state for inputs
    const [selection, setNewSelection] = useState("");
    const [timeSlot, setNewTimeSlot] = useState(""); // what is this since it's drop down?, not string..
    const [role, setNewRole] = useState("");

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

    return (

        <div className="form">
            <div>
                <h1>Make Changes To Your Audition</h1>
            </div>

            <form>
                <label className="label">Will You Be Acting or Singing?</label>
                <input className="input" type="text" value={role} onChange={handleNewRole} /><br></br>

                <label className="label">Piece You Will Perform</label>
                <input className="input" type="text" value={selection} onChange={handleNewSelection} /><br></br>

                <label className="label">Time Slot</label>
                <select className="timeslot" id="timeslot" name="timeslot" value={timeSlot} onChange={handleNewTimeSlot}>
                    <option value="Keep My Current Time Slot">Keep My Current Time Slot</option>
                    <option value="2022-07-01 12:00pm">2022-07-01 12:00pm</option>
                    <option value="2022-07-01 1:00pm">2022-07-01 1:00pm</option>
                    <option value="2022-07-01 2:00pm">2022-07-01 2:00pm</option>
                </select>

            </form>

        </div >
    )

}