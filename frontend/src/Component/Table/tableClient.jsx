import React, {useState,useEffect} from "react";
import { Link, Route, Routes } from "react-router-dom";
import RecordsReduxForm from "./recordsForm";
import cs from './table.module.css';



function TableClient(props) {

    const [isUpdateId, setIsUpdateId] = useState(0);
    const [isUpdateDate, setIsUpdateDate] = useState("");
    const onClicDelite = (e,lineId) => {
       props.onDelete(lineId);    
    }

    const UpdateClic = (e,lineId, date) => {
        setIsUpdateId(prev=>prev=lineId);
        setIsUpdateDate(prev=>prev=date);
     }

     const UpdateRecrdse = (formData) => {
        
        props.UpdateRecordse(formData,isUpdateDate, isUpdateId);
        setIsUpdateDate(prev=>prev="");
        setIsUpdateId(prev=>prev=0)
     }

    return (
        <div className={cs.tab}>
            {props.client.map(c => 
            <div>
                <span>
                        {c.lineId!==isUpdateId? 
                        <table key={c.Id} className={cs.tab_total}>
                        <tr>
                            <td className={cs.tdTime}>{c.time}</td>
                            <td className={cs.tdClient}>{c.clientName}</td>
                            <td className={cs.tdSevice}>{c.procedureName}</td>
                            <td className={cs.tdSevice}>{c.comment} </td>
                            <button onClick = {e=>onClicDelite(e, c.lineId)}>Удалить</button>
                            <button onClick={(e)=>{UpdateClic(e,c.lineId, c.date)}}>Изменить</button>
                        </tr>
                        </table> :
                            <RecordsReduxForm onSubmit={UpdateRecrdse} records={c} />
                            }
                </span>
            </div>)}                 
        </div>
        
    )
}

export default TableClient;