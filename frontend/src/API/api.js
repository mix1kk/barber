import axios from "axios";

const instance = axios.create({
    baseURL: 'http://localhost:3001/'
})
export const assortimentAPI = {
    GetAssortiment(changingIsFetching, setAssortiment) 
    {
        return instance.get("assortiment").then(response => {           
            return response.data
        });
    }
}