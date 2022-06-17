import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Error from "./Error";

// pass in props for global user


// Edit an existing audition
export default function Edit() {

    // Must be logged in (and be ADMIN?) in order to use
    // the EDIT feature to edit an audition


    // // sets state for inputs
    // const [selection, setNewSelection] = useState("");
    // const [timeSlot, setNewTimeSlot] = useState(); // what is this since it's drop down?, not string..
    // const [role, setNewRole] = useState();
    // const [idToEdit, setIdToEdit] = useState();
    // const [messages, setMessages] = useState();
    // const [errors, setErrors] = useState([]);
    const [formData, setFormData] = useState({
     partId: "",
     timeSlot: "",
    selection: ""
    })

    // const navigate = useNavigate();

    // const [submitted, setSubmitted] = useState(false);


    // handle new role
    // i.e. will you be acting or singing?
    // const handleNewRole = (e) => {
    //     setNewRole(e.target.value);
    //     setSubmitted(false);
    // }

    // // handle new selection
    // const handleNewSelection = (e) => {
    //     setNewSelection(e.target.value);
    //     setSubmitted(false);
    // };

    // // handle new timeslot
    // // drop down menu
    // const handleNewTimeSlot = (e) => {
    //     setNewTimeSlot(e.target.value);
    //     setSubmitted(false);
    // }

    // // handle submit
    // const handleSubmit = async (e) => {
    //     e.preventDefault();

    //     if (idToEdit) {
    //         const editedAuditionee = {
    //             auditioneeId: idToEdit,
    //             appUserId: editedAuditionee.appUserId,
    //             partId: formData.partId,
    //             timeSlot: formData.timeSlot,
    //             selection: formData.selection
    //         };
    //         const body = JSON.stringify(editedAuditionee);
    //         const response = await fetch(`http://localhost:8080/api/theater/auditionee/${idToEdit}`, {
    //             method: "PUT",
    //             headers: {
    //                 "Content-Type": "application/json",
    //             },
    //             body
    //         }).then(resp => {
    //             if (resp.status >= 200 && resp.status <= 299) {
    //                 console.log(`edit success with status: ${resp.status}`);
    //                 setMessages(`Successful edit with status: ${resp.status}`);
    //             } else if (resp.status >= 400 && resp.status <= 499) {
    //                 console.log(`${resp.status}: bad request`);
    //                 setMessages(`${resp.status}: bad request`);
    //             } else {
    //                 console.log(`edit failed with status: ${resp.status}`);
    //                 setMessages(`Edit failed with status: ${resp.status}`);
    //             }

    //         })
    //     };

    //     const handleFormChange = (e) => {
    //         const nextFormData = { ...formData };
    //         nextFormData[e.target.name] = e.target.value;
    //         setFormData(nextFormData);
    //     }

    return (

        <div className="form">
            <div>
                <h1>Make Changes To An Audition</h1>
            </div>

            <form>
                <label className="label">Type of Audition</label>
                <select className="role">
                <option value="Keep My Current Audition Type"></option>
                        <option value="Acting">Acting</option>
                        <option value="Singing">Singing</option>
                    </select>
                <br></br>
                <br></br>

                <label className="label">Piece To Be Performed</label>
                <input className="input" type="text" />
                <br></br>
                <br></br>

                <label className="label">Time Slot</label>
                <select className="timeslot">
                <option value="Keep My Current Time Slot">Keep Current Time Slot</option>
                        <option value="2022-07-01 12:00pm">2022-07-01 12:00pm</option>
                        <option value="2022-07-01 1:00pm">2022-07-01 1:00pm</option>
                        <option value="2022-07-01 2:00pm">2022-07-01 2:00pm</option>
                 </select>

                <br></br>
                <br></br>

                <button className="btn btn-success" type="submit">Submit</button>

            </form>

        </div >
    )

}