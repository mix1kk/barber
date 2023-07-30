import { clientAPI, userAPI } from "../API/api";

//const тип action = "тип action"; пример action
const ADD_CLIENT = "ADD_CLIENT"; 
const UPDATE_TIME = "UPDATE_TIME"; 
const UPDATE_CLIENT_NAME = "UPDATE_CLIENT_NAME"; 
const UPDATE_SERVICE = "UPDATE_SERVICE";
const UPDATE_COMMENT = "UPDATE_COMMENT";
const SET_CLIENT = "SET_CLIENT";
const SET_IS_DID_MOUNT = "SET_IS_DID_MOUNT"
const DELITE_CLIENT = "DELITE_CLIENT"

let initialState = {
    master:[]
    
};

let masterReducer = (state = initialState, action) => {
    let stateCopy;
    switch (action.type){
        case SET_CLIENT: 
        stateCopy = {master:[]};
        stateCopy.master.push(...action.master);
        
        for (let i = 0;i<action.master.length;i++){
            stateCopy.master[i].userRecords=[ ...action.master[i].userRecords];
        }
        action.master = [];
        return stateCopy;


        // console.log(state.master.toString() !== action.master.toString());
        //     if(state.master.toString() !== action.master.toString())
        //     {
        //         stateCopy = {...state};
        //         stateCopy.master = [...state.master, ...action.master];
        //         for (let i = 0;i<stateCopy.master.length;i++){
        //             stateCopy.master[i].userRecords = [ ...action.master[i].userRecords]
        //         }
        //         return stateCopy;
              
        //     }
        //     else return {...state};

        default: 
            return state
    }
};

// export const название-диспатча = (параметр) => ({type: название action, параметр}); пример dispatch или добавляем thunk
export const AddClientCreator = (newClient,masterId) => ({type: ADD_CLIENT, newClient,masterId});
export const SetClientCreator = (master) => ({type:SET_CLIENT,master});
//export const DeliteClientCreator = (i) => ({type:DELITE_CLIENT,i})
export const getClient = (id, startDate, endDate) => {   // Thunk
    return (dispatch) => {
        //dispatch(DeliteClientCreator());
        clientAPI.GetClient(id, startDate, endDate).then(response => {
        dispatch (SetClientCreator(response))
      });
    };
}

export const deliteRecordsUser = (id, lineId, startDate, endDate) => { //id,, startDate, endDate
    return (dispatch) => {
        clientAPI.DeliteRecordsUser(lineId).then(response=>{
            if(response==="NO_CONTENT")
            {
                clientAPI.GetClient(id, startDate, endDate).then(response => {
                    dispatch (SetClientCreator(response))});
            }
        });
        
    };
}

export const addRecordsUser= (userId, records, startDate, endDate) => { 
    return (dispatch) => {
       // dispatch(DeliteClientCreator());
        clientAPI.recordsUser(userId, records).then(response => {
            if(response==="OK")
            {
                clientAPI.GetClient(userId, startDate, endDate).then(response => {
                        dispatch (SetClientCreator(response))});
            }
          });
    };
}

export const updateRecordsUser = (userId, lineId, records, startDate, endDate) => { 
    return (dispatch) => {
        //dispatch(DeliteClientCreator());
        clientAPI.UpdateRecordsUser(lineId, records).then(response=>{
            if(response==="OK")
            {
                clientAPI.GetClient(userId, startDate, endDate).then(response => {
                    dispatch (SetClientCreator(response))});
            }
        });
    };
}

export default masterReducer;
