import { useState } from 'react';
import React from "react";
import TableContainer from "../Table/tableContainer";
//import cs from './main_window.module.css'

function Main(){
    const [master, setMaster] = useState({
        MasterName: "Иванов Иван",
        Date: '29.06.2023',
        ClientName: "Петрова Катя",
        coment: "bla-bla"
    });
    
    return(
        <div >
            <TableContainer/>    
        </div>
    )
}

export default Main;