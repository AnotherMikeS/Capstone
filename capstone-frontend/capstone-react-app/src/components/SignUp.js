import React, { useEffect } from "react";
import { useState } from "react";

function SignUp(addAuditionee) {

    const [role, setRole] = useState("");
    const [date, setDate] = useState("");
    const [time, setTime] = useState("");
    const [selection, setSelectionPiece] = useState("");
    const [auditionees, setAuditionees] = useState([]);
    const [allPeople, setPeople] = useState([]);
    const [submit, setSubmit] = useState(false);
    const [error, setError] = useState(false);

    const baseurl = window.API_URL + "/api/theater/auditionee";

    useEffect(() => {
        getAuditionees();
    }, []);

    const getAuditionees = () => {

        fetch("http://localhost:8080/api/theater/auditionee", { method: "GET" })
            .then(resp => {
                if (resp.status !== 200) {
                    return Promise.reject("response is not ok");
                }
                return resp.json();
            })
            .then(json => {
                setAuditionees(json);
            });
    }

    console.log(auditionees);
    var correctId;

    if (auditionees.length === 0) {
        correctId = 1;
    }
    else if (auditionees.length === 2) {
        correctId = 3;
    }
    else {
        var index = auditionees.length - 1;
        console.log(index);
        var tempAuditionee = auditionees[index];
        console.log(tempAuditionee);
        correctId = tempAuditionee.auditioneeId + 1;
        console.log(correctId);
    }

    var currentUserId = parseInt(localStorage.getItem("id"), 10);
    console.log(currentUserId);


    const auditionPost = () => {


        const newAudition = { auditioneeId: correctId, partId: parseInt(role, 10) };

        console.log(newAudition);

        const auditionInit = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            },
            body: JSON.stringify(newAudition)
        };

        return fetch("http://localhost:8080/api/theater/audition", auditionInit)
            .then(response => {
                console.log(response.status);
                if (response.status === 201) {
                    setSubmit(true);
                } else {
                    return Promise.reject("POST failed.")
                }
            })
            .catch(console.error);
    }

    const auditioneePost = () => {

        if (!error) {
            var auditionee = {
                appUserId: currentUserId,
                partId: parseInt(role, 10),
                timeSlot: date + " " + time,
                selection: selection
            };

            const auditioneeInit = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                body: JSON.stringify(auditionee)
            };

            fetch(baseurl, auditioneeInit)
                .then(response => {
                    console.log(response.status);
                    if (response.status !== 201) {
                        setError(true);
                    } else if (response.status === 201) {
                        auditionPost();
                    } else {
                        return Promise.reject("POST auditionee status was not 201.");
                    }
                })
                .catch(console.error);

        }
    }

    const handleRoleChange = (evt) => {
        setRole(evt.target.value);
    }
    const handleDateChange = (evt) => {
        setDate(evt.target.value);
    }
    const handleTimeChange = (evt) => {
        setTime(evt.target.value);
    }
    const handleSelectionChange = (evt) => {
        setSelectionPiece(evt.target.value);
    }

    const handleSubmit = async (evt) => {
        evt.preventDefault();
        await auditioneePost();

        //Set to blank
        // setRole("");
        // setDate("");
        // setTime("");
        // setSelectionPiece("");
    };


    return (
        <div>
            {(submit) ?

                <h1>You have successfully<br></br>signed up!</h1>

                :
                <form onSubmit={handleSubmit}>
                    <div class="container">
                        <div class="row">
                            <h2 class="col">Audition Sign Up</h2>
                        </div>
                        <div class="row">
                            <label htmlFor="roles">Role: </label>
                            <div class="col-3">
                                <select id="roles" name="roles" class="col-100" onChange={handleRoleChange} value={role}>
                                    <option value="" disabled selected>Choose Role</option>
                                    <option value="1" >Actor</option>
                                    <option value="2">Singer</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <label htmlFor="dates">Date: </label>
                            <div class="col-3">

                                <select id="dates" name="dates" class="col-100" onChange={handleDateChange} value={date}>
                                    <option value="" disabled selected>Choose Date</option>
                                    <option value="2022-07-01">July 1st</option>
                                    <option value="2022-07-02">July 2nd</option>
                                    <option value="2022-07-03">July 3rd</option>
                                </select>
                            </div>
                        </div>
                        {(date === "")
                            ? <div></div>
                            :
                            <div class="row">
                                <label htmlFor="times">Time: </label>
                                <div class="col-3">
                                    <select id="times" name="times" class="col-100" onChange={handleTimeChange} value={time}>
                                        <option value="" disabled selected>Choose Time</option>
                                        <option value="9:00am">9 am</option>
                                        <option value="10:00am">10 am</option>
                                        <option value="11:00am">11 am</option>
                                    </select>
                                </div>
                            </div>
                        }
                        <div class="row">
                            <label htmlFor="auditionPiece">Selection Piece: </label>
                            <div class="col-3">
                                <input autoComplete="off" id="selection" onChange={handleSelectionChange} name="selection" type="Text" value={selection}></input>
                            </div>
                        </div>
                        {(role === "" || date === "" || time === "" || selection === "")
                            ? <button className="btn" disabled>Add Audition</button>
                            : <button className="btn" type="submit">Add Audition</button>
                        }
                    </div>
                </form>
            }

            {(error) ?

                <h1>You already have an<br></br>audition scheduled.</h1>
                :
                <></>
            }

        </div>
    );
}

export default SignUp