import thunkMiddleware from "redux-thunk";
import masterReducer from "./Master-reducer";
import { applyMiddleware, combineReducers, createStore } from "redux";
import { reducer as formReducer } from 'redux-form'

let reducers = combineReducers({
    master: masterReducer, // добавляем следующий редюсер
    form: formReducer
});


let store = createStore(reducers, applyMiddleware(thunkMiddleware));


export default store;