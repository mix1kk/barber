import React, {useState} from "react";
import cs from './table.module.css';
import ClientAddReduxForm from "./clientAddForm";

function TableClient(props) {
    //console.log(props);
    //debugger;

    const AddClient = (formData) => {
        //console.log(formData);
        let result = {
            Id:props.Client.length+1,
            time:formData.time,
            clientName:formData.clientName,
            service:formData.service,
            comment:formData.comment
        }
        props.AddClientCreator(result, props.masterId)
    }

    const onChengeTime = (e,clientId, masterId) => {
        props.UpdateTimeCreator(e.target.value, masterId, clientId);
    }

    const onChengeClientName = (e,clientId, masterId) => {
        props.UpdateClientNameCreator(e.target.value, masterId, clientId);
    }

    const onChengeService = (e,clientId, masterId) => {
        props.UpdateServiceCreator(e.target.value, masterId, clientId);
    }

    const onChengeComment = (e,clientId, masterId) => {
        props.UpdateCommentCreator(e.target.value, masterId, clientId);
    }
    return (
        <div className={cs.tab}>
            {props.Client.map(c => 
                <table key={c.Id} className={cs.tab_total}>
                    <tr>
                        <td className={cs.tdTime}>
                            <input value={c.time} onChange = {e=>onChengeTime(e, c.Id, props.masterId)}/>
                        </td>

                        <td className={cs.tdClient}>
                            <input value={c.clientName} onChange = {e=>onChengeClientName(e, c.Id, props.masterId)}/> 
                            
                        </td>
                        <td className={cs.tdSevice}>
                            <input value={c.service} onChange = {e=>onChengeService(e, c.Id, props.masterId)}/>
                            
                        </td>
                        <td className={cs.tdSevice}>
                            <input value={c.comment} onChange = {e=>onChengeComment(e, c.Id, props.masterId)}/>
                            
                        </td>
                    </tr>
                </table>)} 
                <ClientAddReduxForm onSubmit={AddClient}></ClientAddReduxForm>
        </div>
        
    )
}

export default TableClient;