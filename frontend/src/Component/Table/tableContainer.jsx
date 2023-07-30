import React from "react";
import {connect} from 'react-redux'
import TableHeader from "./tableHeader";
import { AddClientCreator, 
    getClient,
    addRecordsUser,
    updateRecordsUser,
    deliteRecordsUser} from "../../Redux/Master-reducer";

    

let mapStateToProps = (state) => {
    return{ 
        master: state.master,
        isDidMount:state.master.isDidMount,
        userName:state.master.userName
    };
}

const TableContainer = connect(mapStateToProps,{
    AddClientCreator, 
    getClient, addRecordsUser,updateRecordsUser, deliteRecordsUser})(TableHeader);

export default TableContainer;