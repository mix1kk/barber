import thunkMiddleware from "redux-thunk";
import masterReducer from "./Master-reducer";

let reducers = combineReducers({
    master: masterReducer // добавляем следующий редюсер
});


let store = createStore(reducers, applyMiddleware(thunkMiddleware));


export default store;