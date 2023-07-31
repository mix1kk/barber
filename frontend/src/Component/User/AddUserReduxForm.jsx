import React from "react";
import { Field, reduxForm } from 'redux-form'


const AddUserForm = (props) => {
    
    return (
        <form onSubmit={props.handleSubmit}>
            <Field component = {"input"} name={"userName"}  placeholder = "userName"/> 
            <Field component = {"input"} name={"userCompany"}  placeholder = "userCompany"/> 
            <Field component = {"input"} name={"userPhoneNumber"}  placeholder = "userPhoneNumber"/>
            <Field component = {"input"} name={"userProfession"} placeholder = "userProfession"/>
            <Field component = {"input"} name={"userRole"} placeholder = "userRole"/>
            <Field component = {"input"} name={"usersAccessed"}  placeholder = "usersAccessed"/> 
            <Field component = {"input"} name={"password"}  placeholder = "password"/>
            <Field component = {"input"} name={"email"} placeholder = "email"/>
            <Field component = {"input"} name={"picture"} placeholder = "picture"/>
            <button>Добавить</button>
        </form> 
    )
}

const AddUserReduxForm = reduxForm({form: 'AddUserForm'})(AddUserForm)


export default AddUserReduxForm;