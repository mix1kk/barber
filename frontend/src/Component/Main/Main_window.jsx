import { useState } from 'react';
import React from "react";
import Table from "../Table/table";
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
            <Table data = {master}/>
            <Table data = {master}/>            
        </div>
    )
}

export default Main;