import { Link, Outlet } from "react-router-dom";
import Twelve5 from "../Images/Twelve5.png";


export default function NavBar(props) {
  return (
    <div>
      <h1
        style={{
          textAlign: "center",
        }}
      >
        <img className="logo" src={Twelve5} style={{ padding: "0rem", width: 300, height: 300 }} />
      </h1>
      <nav
        style={{
          borderBottom: "solid 1px",
          paddingBottom: "1rem",
          paddingRight: "1rem",
          textAlign: "center",
        }}
      >
        <Link to="/home"><button className="btn">Home</button></Link> {" "}

        {(localStorage.getItem("token"))
          ? (<button onClick={props.logout} className="btn">Logout</button>)
          : (<Link to="/forms"><button className="btn">Log In/Register</button></Link>)} {" "}

        {(localStorage.getItem("token") && (localStorage.getItem("id") !== "1"))
          ? <Link to="/signup"><button className="btn">Sign Up</button></Link>
          : <></>} {" "}

        {(localStorage.getItem("token") && (localStorage.getItem("id") === "1"))
          ? <Link to="/schedule"><button className="btn">Audition Schedule</button></Link>
          : <></>} {" "}

        {localStorage.getItem("token")
          ? <Link to="/myaccount"><button className="btn">My Account</button></Link>
          : <></>}
      </nav>
      <Outlet />
    </div>
  );
}