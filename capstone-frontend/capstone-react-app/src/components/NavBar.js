import { Link, Outlet} from "react-router-dom";
import Twelve5 from "./Twelve5.png";


export default function NavBar({loggedIn}) {

  const handleLogout = (e) => {
    localStorage.removeItem("token");
    document.location.reload();
  }

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
        {loggedIn ? (
            <button onClick={handleLogout} className="btn btn-outline-success">Logout</button>
        ) : (
            <Link to="/forms"><button className="btn btn-outline-success">Log In/Register</button></Link>
        )}
        <Link to="/signup"><button className="btn btn-outline-success">Sign Up</button></Link> {" "}
        <Link to="/schedule"><button className="btn btn-outline-success">Audition Schedule</button></Link> {/*Admin Only eventually*/}
      </nav>
      <Outlet />
      
      {/* {localStorage.getItem("token") ? <button className="btn btn-outline-success">Logout</button> : <Link to="/forms"><button className="btn btn-outline-success">Log In/Sign Up</button></Link>} {" "} {/* Will be Log Out when logged in */}
    </div>
  );
}