import { Link, Outlet} from "react-router-dom";
import Twelve5 from "./Twelve5.png";


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
        {props.userStatus.username ? (
            <button onClick={props.logout} className="btn">Logout {props.userStatus.username}</button>
        ) : (
            <Link to="/forms"><button className="btn">Log In/Register</button></Link>
        )} {" "}
        <Link to="/signup"><button className="btn">Sign Up</button></Link> {" "}
        <Link to="/schedule"><button className="btn">Audition Schedule</button></Link> {/*Admin Only eventually*/}
        <Link to="/myaccount"><button className="btn">My Account</button></Link> 
      </nav>
      <Outlet />
      
      {/* {localStorage.getItem("token") ? <button className="btn btn-outline-success">Logout</button> : <Link to="/forms"><button className="btn btn-outline-success">Log In/Sign Up</button></Link>} {" "} {/* Will be Log Out when logged in */}
    </div>
  );
}