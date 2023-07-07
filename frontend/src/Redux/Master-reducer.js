import { assortimentAPI } from "../API/api";

//const тип action = "тип action"; пример action
const initialState = {
    master: [
        {MasterName: "Иванов Иван",
         Date: '29.06.2023',
        ClientName: "Петрова Катя" },
        {MasterName: "петров Петр",
         Date: '30.06.2023',
        ClientName: "Иванова Катя" }
        ],
    isFiching:true
    
};

const masterReducer = (state = initialState, action) => {

    switch (action.type){
        //case тип action:
            //return {
                //...state,
                //....и т.д.}; здесь мы прописываем логику управления state. просто добавляем новый case
        default: 
            return state
    }

    // export const название-диспатча = (параметр) => ({type: название action, параметр}); пример dispatch или добавляем thunk
};

export default masterReducer;