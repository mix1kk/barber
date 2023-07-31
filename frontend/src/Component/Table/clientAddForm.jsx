import React from "react";
import { Field, reduxForm } from 'redux-form'
import cs from './table.module.css';

const ClientAddForm = (props) => {
    
    return (
        <form onSubmit={props.handleSubmit}>
            <Field component = {"input"} className={cs.input} name={"date"}  placeholder = "Дата"/> 
            <Field component = {"input"} className={cs.input} name={"time"}  placeholder = "Время"/> 
            <Field component = {"input"} name={"clientName"}  placeholder = "Имя клиента"/>
            <Field component = {"input"} name={"service"} placeholder = "Услуга"/>
            <Field component = {"input"} name={"comment"} placeholder = "Коментарий"/>
            <button>Записать</button>
        </form> 
    )
}

const ClientAddReduxForm = reduxForm({form: 'ClientAddForm'})(ClientAddForm)

export default ClientAddReduxForm;