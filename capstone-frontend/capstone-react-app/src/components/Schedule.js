import React, { useState, useEffect, Fragment } from 'react';
import '../CSS/Schedule.css';


export default function Schedule() {

  const [auditions, setAuditions] = useState([]);

  useEffect(() => {
    getAuditions();
  }, []);

  const getAuditions = () => {
    fetch("http://localhost:8080/api/theater/audition")
      .then(resp => {
        if (resp.status !== 200) {
          return Promise.reject("response is not ok");
        }
        return resp.json();
      })
      .then(json => {
        setAuditions(json);
      });
  }

  console.log(auditions);

  const auditionDelete = (audition) => {

    const deleteInit = {
      method: "DELETE",
      // headers: {
      //   "Authorization": "Bearer " + localStorage.getItem("token")
      // }
    };

    return fetch(`http://localhost:8080/api/theater/audition/${audition.auditioneeId}`, deleteInit)
      .then(response => {
        if (response.status === 204) {
          
        } else {
          return Promise.reject("DELETE not successful.");
        }
      })
      .catch(console.error);
  }


  const auditioneeDelete = (audition) => {
    
    const deleteInit = {
      method: "DELETE",
      // headers: {
      //   "Authorization": "Bearer " + localStorage.getItem("token")
      // }
    };

    return fetch(`http://localhost:8080/api/theater/auditionee/${audition.auditioneeId}`, deleteInit)
      .then(response => {
        if (response.status === 200) {
          alert("Audition deleted!");
        } else {
          return Promise.reject("DELETE not successful.");
        }
      })
      .catch(console.error);
  }

  const superDelete = async (audition) => {
    await auditionDelete(audition);
    await auditioneeDelete(audition);
    getAuditions();
  }

  return (

    <div>
      <form>
        <div>
          <h1>Audition List</h1>
        </div>
        {auditions.length === 0
          ? <h2>No Auditions Found.</h2>
          :
          <table>
            <thead>
              <tr>
                <th>First Name: </th>
                <th>Last Name: </th>
                <th>Role: </th>
                <th>Actions:</th>
              </tr>
            </thead>
            <tbody>
              {auditions.map(audition => {
                return (<tr key={audition.auditionId}>
                  <td>{audition.firstName}</td>
                  <td>{audition.lastName}</td>
                  <td>{audition.role}</td>
                  <td><button type="button" onClick={(e) => { superDelete(audition) }}>Delete</button></td>
                </tr>)
              })}
            </tbody>
          </table>
        }
      </form>
    </div>
  );
}                 