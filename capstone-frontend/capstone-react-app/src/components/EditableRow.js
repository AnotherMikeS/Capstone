import React from "react";

const EditableRow = ({userInfo, editFormData, handleEditFormChange, handleCancelClick}) => { // This appears when an ID is selected for Edit.
    return (
        <tr>
            <td>{userInfo.firstName}</td>
            <td>{userInfo.lastName}</td>
            <td>
                <select>
                    <option value="2022-07-01 12:00pm">2020-07-01 12:00pm</option>
                    <option value="2022-07-02 1:00pm">2020-07-02 1:00pm</option>
                    <option value="2022-07-03 2:00pm">2020-07-03 2:00pm</option>
                    onChange={handleEditFormChange}
                </select>
            </td>
            <td>
                <input 
                    type="text" 
                    required="required" 
                    placeholder="Audition Selection" 
                    name="lastName"
                    value={editFormData.lastName}
                    onChange={handleEditFormChange}
                ></input>
            </td>
            <td>
                <select>
                    <option value="1">Singing</option> 
                    <option value="2">Acting</option> 
                    value={editFormData.dob}
                    onChange={handleEditFormChange}
                </select>
            </td>

            <td>
                <button type="submit" class="btn btn-outline-success btn-sm">Save</button>
                <button type="button" class="btn btn-outline-danger btn-sm" onClick={handleCancelClick}>Cancel</button>
            </td>
        </tr>
    )
}

export default EditableRow;