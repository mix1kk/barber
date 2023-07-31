import React, {useEffect} from "react";
import User from "./User";

const Users = (props) => {
    useEffect( () =>{
        props.getUser();
    },[])

    return(
        <div>
            {!props.users? "Подождите!" : <User users={props.users} {...props}/> }
        </div>
    )
};

export default Users;