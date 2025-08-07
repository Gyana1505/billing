import axios from 'axios'
export const addCategory=async(category)=>{
    return await axios.post("https://jubilant-goldfish-wrv4wvx5v6wv35rq9-8080.app.github.dev/api/v1/category",category)
}

export const deletecategory=async(categoryId)=>{
    return await axios.delete(`https://jubilant-goldfish-wrv4wvx5v6wv35rq9-8080.app.github.dev/api/v1/category/${categoryId}`)
}

export const fetchcategory=async()=>{
    return axios.get("https://jubilant-goldfish-wrv4wvx5v6wv35rq9-8080.app.github.dev/api/v1/category")
}
