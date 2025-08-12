import { useEffect, useState } from "react";
import { createContext } from "react";
import { fetchcategory } from "../service/categoryService";

export const  AppContext=createContext(null);
export const AppContextProvider=(props)=>{
    const [category,setCategory]=useState([])

    useEffect(()=>{
        async function loadData(){
           const response=await fetchcategory();
           setCategory(response.data)
        }
        loadData()
    },[])
    const contextValue={
         category,
         setCategory
    }
return <AppContext.Provider value={contextValue}>
  {props.children}
</AppContext.Provider>
}