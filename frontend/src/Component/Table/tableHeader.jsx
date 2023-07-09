import React from "react";
import cs from './table.module.css';
import TableClient from "./tableClient";

function TableHeader(props) {
    //console.log(props);
    // debugger;
    return (
        <div className={cs.tab}>
            {props.master.map(m =>
            <div key={m.Id}>
               <div className={cs.header}>{m.MasterName}</div>
               <div className={cs.header}>{m.Date}</div> 
               <TableClient Client = {m.ClientName} masterId = {m.Id} {...props}/>
            </div> 
                )}
        </div>
        
    )
}

export default TableHeader;