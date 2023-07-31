import React from "react";
import {connect} from 'react-redux'
import {getUser, addUser} from "../../Redux/User-reducer";
import Users from "./Users";

let mapStateToProps = (state) => {
//     console.log(state);
//     debugger;
    return{ 

        users: state.user.users
    };
}

const Userontainer = connect(mapStateToProps,{getUser, addUser})(Users);

export default Userontainer;