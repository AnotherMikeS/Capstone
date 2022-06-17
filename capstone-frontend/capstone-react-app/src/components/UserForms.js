import NewUser from "./NewUser";
import LogIn from "./LogIn";
import '../UserForms.css';

export default function UserForms(props) {
    return (
        <div>
            <div className="row">
                <div className="col">
                    <LogIn login={props.login}/>
                </div>

                <div id="middle" className="col">
                    <h2>OR</h2>
                </div>

                <div className="col">
                    <NewUser />
                </div>
            </div>
        </div>

    );
}