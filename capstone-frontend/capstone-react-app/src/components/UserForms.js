import NewUser from "./NewUser";
import LogIn from "./LogIn";
import '../CSS/UserForms.css';

export default function UserForms(props) {

    return (

        <div>
            <div className="row">
                <div className="col"/>

                <div className="col">
                    <LogIn login={props.login}/>
                </div>

                <div id="middle" className="col">
                    <div className="row">
                        <div className="col"/>
                        <div className="col"><h2>OR</h2></div>
                        <div className="col"/>
                    </div>
               </div>

               <div className="col">
                    <NewUser />
                </div>

                <div className="col"/>
            </div>
        </div>

    );
}