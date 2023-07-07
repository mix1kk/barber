import React from "react";
import cs from './table.module.css';

function Table(props) {
    return (
        <div className={cs.tab}>

            <div className={cs.header}>{props.data.Date}</div>
            <div className={cs.header}>{props.data.MasterName}</div>   
            <table className={cs.tab_total}>
                
                     
            <tr>
                <td className={cs.tdTime}>8:30</td>
                <td className={cs.tdClient}>{props.data.ClientName}</td>
                <td className={cs.tdSevice}>Service</td>
                <td className={cs.tdSevice}>{true? props.data.coment: <input></input>}</td>
            </tr>
            <tr>
                <td className={cs.tdTime}>9:00</td>
                <td className={cs.tdClient}>{props.data.ClientName}</td>
                <td className={cs.tdSevice}>Service</td>
                <td className={cs.tdSevice}>{true? props.data.coment: <input></input>}</td>
            </tr>
            <tr>
                <td className={cs.tdTime}>9:30</td>
                <td className={cs.tdClient}>{props.data.ClientName}</td>
                <td className={cs.tdSevice}>Service</td>
                <td className={cs.tdSevice}>{true? props.data.coment: <input></input>}</td>
            </tr>
        </table>
        </div>
        
    )
}

export default Table;