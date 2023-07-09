import React from "react";
import {connect} from 'react-redux'
import TableHeader from "./tableHeader";
import { AddClientCreator, 
    UpdateTimeCreator, 
    UpdateClientNameCreator, 
    UpdateServiceCreator, 
    UpdateCommentCreator} from "../../Redux/Master-reducer";

let mapStateToProps = (state) => {
    // console.log(state)
    // debugger;
    return{ 
        master: state.master.master
    };
}

const TableContainer = connect(mapStateToProps,{
    AddClientCreator, 
    UpdateTimeCreator, 
    UpdateClientNameCreator, 
    UpdateServiceCreator, 
    UpdateCommentCreator})(TableHeader);

export default TableContainer;