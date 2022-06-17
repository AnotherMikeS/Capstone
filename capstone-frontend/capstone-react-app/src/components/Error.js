import { useNavigate } from "react-router-dom";

function Error({ msg }) {
  const navigate = useNavigate();

  return (
    <p>
      🙅🏾‍♂️ Error{" "}
      {navigate("../success", { replace: true }) ? ` - ${navigate.location.state.msg}` : ""}
      {msg}
    </p>
  );
}

export default Error;