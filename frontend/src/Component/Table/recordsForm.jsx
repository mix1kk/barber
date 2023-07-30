import React from "react";
import { Field, reduxForm } from 'redux-form'


const RecordsForm = (props) => {
    return (
            <form onSubmit={props.handleSubmit}> 
                <Field initialValues="Hello" placeholder={props.records.time} component = {"input"} name={"time"} /> 
                <Field placeholder={props.records.clientName} component = {"input"} name={"clientName"}  />
                <Field placeholder={props.records.procedureName} component = {"input"} name={"service"} />
                <Field placeholder={props.records.comment} component = {"input"} name={"comment"} />
                <button>Сохранить</button>
            </form> 
    )
}

const RecordsReduxForm = reduxForm({form: 'RecordsForm'})(RecordsForm)

export default RecordsReduxForm;
