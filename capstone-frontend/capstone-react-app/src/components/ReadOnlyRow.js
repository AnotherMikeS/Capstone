import React from "react";

const ReadOnlyRow = ({ userInfo, handleEditClick, handleDeleteClick }) => { // This appears by default. Displays each Agent's information
    return (

        <tr>

            <td>{userInfo.firstName}</td>
            <td>{userInfo.lastName}</td>
            <td>{userInfo.timeSlot}</td>
            <td>{userInfo.selection}</td>
            <td>{userInfo.partString}</td>

            <td>
                <button type="button" class="btn btn-outline-success btn-sm" onClick={(event) => handleEditClick(event, userInfo)}>Edit</button>
            </td>
        </tr>
    )

}

export default ReadOnlyRow;