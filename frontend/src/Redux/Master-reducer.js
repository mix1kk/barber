import { assortimentAPI } from "../API/api";

//const тип action = "тип action"; пример action
const ADD_CLIENT = "ADD_CLIENT"; 
const UPDATE_TIME = "UPDATE_TIME"; 
const UPDATE_CLIENT_NAME = "UPDATE_CLIENT_NAME"; 
const UPDATE_SERVICE = "UPDATE_SERVICE";
const UPDATE_COMMENT = "UPDATE_COMMENT";

const initialState = {
    master: [
        {   Id:1,
            MasterName: "Иванов Иван",
            Date: '29.06.2023',
            ClientName:[ 

                {   Id:1,
                    time:"8:30",
                    clientName:"Петрова Катя",
                    service:"monicur",
                    comment:"bla-bla"
                },

                {   Id:2,
                    time:"9:00",
                    clientName:"Сидорова Катя",
                    service:"pedicur",
                    comment:"bla-bla"
                }
        ]},

        { 
            Id: 2,
            MasterName: "Петухов Петр",
            Date: '30.06.2023',
            ClientName: [ 
                {
                    Id:1,
                    time:"8:30",
                    clientName:"Иванова Катя",
                    service:"Просто подстрич ногти",
                    comment:"bla-bla"} ] }
        ],
    isFiching:true
    
};

const masterReducer = (state = initialState, action) => {
    // console.log(action);
    // debugger;
    let stateCopy;
    switch (action.type){
        case ADD_CLIENT:
            stateCopy = {...state};
            stateCopy.master = [...state.master];
            stateCopy.master.ClientName = [...state.master[action.masterId-1].ClientName]
            stateCopy.master[action.masterId-1].ClientName.push(action.newClient) 
            console.log(stateCopy);
            //debugger;
            return stateCopy;

        case UPDATE_TIME:
            stateCopy = {...state};
            stateCopy.master = [...state.master];
            stateCopy.master.ClientName = [...state.master[action.masterId-1].ClientName]
            stateCopy.master[action.masterId-1].ClientName[action.clientId-1].time = action.newTime
            return stateCopy;

        case UPDATE_CLIENT_NAME:
            stateCopy = {...state};
            stateCopy.master = [...state.master];
            stateCopy.master.ClientName = [...state.master[action.masterId-1].ClientName]
            stateCopy.master[action.masterId-1].ClientName[action.clientId-1].clientName = action.newClientName
            return stateCopy;

        case UPDATE_SERVICE:
            stateCopy = {...state};
            stateCopy.master = [...state.master];
            stateCopy.master.ClientName = [...state.master[action.masterId-1].ClientName]
            stateCopy.master[action.masterId-1].ClientName[action.clientId-1].service = action.newService
            return stateCopy;

        case UPDATE_COMMENT:
            stateCopy = {...state};
            stateCopy.master = [...state.master];
            stateCopy.master.ClientName = [...state.master[action.masterId-1].ClientName]
            stateCopy.master[action.masterId-1].ClientName[action.clientId-1].comment = action.newComment
            return stateCopy;

        default: 
            return state
    }
};

// export const название-диспатча = (параметр) => ({type: название action, параметр}); пример dispatch или добавляем thunk
export const AddClientCreator = (newClient,masterId) => ({type: ADD_CLIENT, newClient,masterId});

export const UpdateTimeCreator = (newTime,masterId,clientId) => ({type: UPDATE_TIME, newTime,masterId,clientId});
export const UpdateClientNameCreator = (newClientName,masterId,clientId) => ({type: UPDATE_CLIENT_NAME, newClientName,masterId,clientId});
export const UpdateServiceCreator = (newService,masterId,clientId) => ({type: UPDATE_SERVICE, newService,masterId,clientId});
export const UpdateCommentCreator = (newComment,masterId,clientId) => ({type: UPDATE_COMMENT, newComment,masterId,clientId});

export default masterReducer;