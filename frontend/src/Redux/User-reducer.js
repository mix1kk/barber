import { userAPI } from "../API/api";

//const тип action = "тип action"; пример action
// const ADD_CLIENT = "ADD_CLIENT"; 
// const UPDATE_TIME = "UPDATE_TIME"; 
// const UPDATE_CLIENT_NAME = "UPDATE_CLIENT_NAME"; 
// const UPDATE_SERVICE = "UPDATE_SERVICE";
const ADD_USER = "ADD_USER";
const SET_USER = "SET_USER";

let initialState = {
    users:[]
};

    // новые данные [
//   {
//     "userId": 0,
//     "userName": "string",
//     "userCompany": "string",
//     "userPhoneNumber": "string",
//     "userProfession": "string",
//     "userRole": "string",
//     "usersAccessed": "string",
//     "password": "string",
//     "email": "string",
//     "picture": "string"
//   }
// ]


let userReducer = (state = initialState, action) => {
    let stateCopy;
    switch (action.type){
        case SET_USER: 
            stateCopy = {...state};
            if(state.users.toString()==action.users.toString()){
                return state
            }
                
            stateCopy.users = [...state.users, ...action.users];
            return stateCopy;
        // case ADD_USER:
        //     stateCopy = {...state};
        //     stateCopy.master = [...state.master];
        //     stateCopy.master.userRecords = [...state.master[action.masterId-1].ClientName]
        //     stateCopy.master[action.masterId-1].ClientName.push(action.newClient) 
        //     return stateCopy;

        // case UPDATE_TIME:
        //     stateCopy = {...state};
        //     stateCopy.master = [...state.master];
        //     stateCopy.master.ClientName = [...state.master[action.masterId-1].ClientName]
        //     stateCopy.master[action.masterId-1].ClientName[action.clientId-1].time = action.newTime
        //     return stateCopy;

        // case UPDATE_CLIENT_NAME:
        //     stateCopy = {...state};
        //     stateCopy.master = [...state.master];
        //     stateCopy.master.ClientName = [...state.master[action.masterId-1].ClientName]
        //     stateCopy.master[action.masterId-1].ClientName[action.clientId-1].clientName = action.newClientName
        //     return stateCopy;

        // case UPDATE_SERVICE:
        //     stateCopy = {...state};
        //     stateCopy.master = [...state.master];
        //     stateCopy.master.ClientName = [...state.master[action.masterId-1].ClientName]
        //     stateCopy.master[action.masterId-1].ClientName[action.clientId-1].service = action.newService
        //     return stateCopy;

        // case UPDATE_COMMENT:
        //     stateCopy = {...state};
        //     stateCopy.master = [...state.master];
        //     stateCopy.master.ClientName = [...state.master[action.masterId-1].ClientName]
        //     stateCopy.master[action.masterId-1].ClientName[action.clientId-1].comment = action.newComment
        //     return stateCopy;

        default: 
            return state
    }
};

// export const название-диспатча = (параметр) => ({type: название action, параметр}); пример dispatch или добавляем thunk

//export const UpdateCommentCreator = (newComment,masterId,clientId) => ({type: UPDATE_COMMENT, newComment,masterId,clientId});
export const SetUserCreator = (users) => ({type:SET_USER,users});
// export const AddUserCreator = (users) => ({type:SET_USER,users});

export const getUser= () => {   // Thunk
    return (dispatch) => {
        userAPI.GetUser().then(response => {
        dispatch (SetUserCreator(response))
      });
    };
}

export const addUser= (user) => {   // Thunk
    return (dispatch) => {
        userAPI.AddUser(user);
        userAPI.GetUser().then(response => {
            dispatch (SetUserCreator(response))
          });
    };
}

export default userReducer;
