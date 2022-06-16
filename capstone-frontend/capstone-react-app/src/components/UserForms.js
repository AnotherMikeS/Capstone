import NewUser from "./NewUser";
import LogIn from "./LogIn";

export default function UserForms() {
    return (
        <div className="row">
            <div className="col">
                <LogIn />
            </div>
            <div className="col">
                <NewUser />
            </div>
        </div>
    );
}