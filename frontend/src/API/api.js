import axios from "axios";

const instance = axios.create({
    baseURL: 'http://104.154.140.254:8080/', //http://34.16.144.78:8080/records/1   http://localhost:3001/'  
    headers: {
        'accept': '*/*',
        'Content-Type': 'application/json'
    }
})
export const clientAPI = {
    async GetClient(id, startDate, endDate) 
    {
        return await instance.get(`records/user/${id}?startDate=${startDate}&endDate=${endDate}`).then(response=>{
            return response.data;
        })
        
        
    },

    async DeliteRecordsUser(lineId) 
    {
        return await instance.delete(`records/line/${lineId}`).then(response=>{   
        return response.data;
    })
    },

    async UpdateRecordsUser(lineId, records) 
    {
        return await instance.patch(`records/line/${lineId}`, records).then(response=>{
        return response.data;
        });
    },

    async recordsUser(userID,records) 
    {
         return await instance.post(`records/user/${userID}`, records).then(response=>{
        return response.data;
         });
    }
}

export const userAPI = {
    async GetUser() 
    {
        const response = await instance.get("users");
        return response.data;
    },
    
    

    async AddUser(user) 
    {
        const response = await instance.post("users", user);
        return response;
    },

    
}



// let test = {
//     userId: 1,
//     userName: "John Doe",
//     listRecords: [
//         {
//             date: "2020-01-01",
//             userRecords:[
//                 {
//                     lineId: 0,
//                     userId: 1,            
//                     time: "09:30",
//                     clientName: "Магомет",
//                     procedureName: "Стрижка",
//                     procedureCost: "1000",
//                     procedureDiscount: "10%",
//                     comment: "Предупредить за 30 минут"
//                 }
//             ]
//         }
//     ]
// }
