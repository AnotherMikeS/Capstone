import { Link, Outlet } from "react-router-dom";
import Twelve5 from "./Twelve5.png";


export default function NavBar() {
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
        <Link to="/home"><button className="btn btn-outline-success">Home</button></Link> {" "}
        <Link to="/login"><button className="btn btn-outline-success">Log In</button></Link> {" "} {/* Will be Log Out when logged in */}
        <Link to="/signup"><button className="btn btn-outline-success">Sign Up</button></Link> {" "}
        <Link to="/schedule"><button className="btn btn-outline-success">Audition Schedule</button></Link> {/*Admin Only eventually*/}
      </nav>
      <Outlet />
    </div>
  );
}