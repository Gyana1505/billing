import axios from 'axios'
export const addCategory=async(category)=>{
    return await axios.post("http://localhost:8080/api/v1/category",category)
}

export const deletecategory=async(categoryId)=>{
    return await axios.delete(`http://localhost:8080/api/v1/category/${categoryId}`)
}

export const fetchcategory=async()=>{
    return axios.get("http://localhost:8080/api/v1/category")
}
