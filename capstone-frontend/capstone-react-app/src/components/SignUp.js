import React, { useEffect } from "react";
import {useState} from "react";

function SignUp(addAudition){

  const[firstName, setFirstName] = useState("");
  const[lastName, setLastName] = useState("");


  const handleFirstChange = (evt) => {
      setFirstName(evt.target.value);
  }
  const handleLastChange = (evt) => {
      setLastName(evt.target.value);
  }

  const handleSubmit = (evt) => {
      evt.preventDefault();
      var agent = {
          agentId: 0, //must be 0 when adding
          firstName: firstName,
          lastName: lastName,
          heightInInches: 60 //can't be null
      }
      addAudition(agent);
      setFirstName("");
      setLastName("");
  };

  return (
      <form onSubmit={handleSubmit}>
          <div class="container">
              <div class="row">
                  <h2 class ="col">Audition Sign Up</h2>
              </div>
                  <div class="row">
                      <label htmlFor = "firstName">First Name: </label>
                      <div class="col-3">
                      <input id = "firstName" onChange={handleFirstChange} name="firstName" type="Text" value={firstName}></input>
                      </div>
                  </div>
                  <div class="row">
                      <label htmlFor = "lastName">Last Name: </label>
                      <div class="col-3">
                      <input id = "lastName" onChange={handleLastChange} name="lastName" type="Text" value = {lastName}></input>
                      </div>
                  </div>
                  <div class="row">
                      <label htmlFor = "lastName">Role: </label>
                      <div class="col-3">
                      <select id="roles" name="roles" class="col-7">
                        <option value="actor">Actor</option>
                        <option value="singer">Singer</option>
                      </select>
                      </div>
                  </div>
                  <div class="row">
                      <label htmlFor = "lastName">Date: </label>
                      <div class="col-3">
                      <select id="dates" name="dates" class="col-7">
                        <option value="7-1">July 1st</option>
                        <option value="7-2">July 2nd</option>
                        <option value="7-3">July 3rd</option>
                      </select>
                      </div>
                  </div>
                  <div class="row">
                      <label htmlFor = "auditionPiece">Audition Piece: </label>
                      <div class="col-3">
                      <input id = "lastName" onChange={handleLastChange} name="lastName" type="Text" value = {lastName}></input>
                      </div>
                  </div>
                  <div className="col-2">
                      <button className="btn btn-primary" type="submit">Add Audition</button>
                  </div>
              </div>
              
          </form>
  );
}

export default SignUp