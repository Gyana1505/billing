import React, { useContext, useState } from 'react'
import { AppContext } from '../../context/Appcontext'
import "./category.css"
import { deletecategory } from '../../service/categoryService'
import toast from 'react-hot-toast'
const Categorylist = () => {

  const {category,setCategory}=useContext(AppContext)
  const [search,setSearch]=useState('')
  const deletbyId=async (id)=>{
     try {
      const response=await deletecategory(id)
      if(response.status === 200){
         const update=category.filter(ele=>ele.categoryId !== id)
         setCategory(update)
         toast.success("category deleted")
      }else{
         toast.error("unable delete category")
      }
     } catch (error) {
      console.error(error)
     }
  }
  const filtercategory=category.filter(ele => ele.name.toLowerCase().includes(search.toLowerCase()))
  return (
    <div className="category-list-container" style={{height:"100vh",overflowY:"auto",overflow:"hidden"}}>
      <div className="row pe-2">
        <div className="input-group mb-3">
          <input type="text" 
          name="keyword" 
          id="keyword" 
          placeholder='search by keayword'
          className='form-control'
          onChange={(e)=>setSearch(e.target.value)}
          value={search}
          
          />
          <span className='input-group-text bg-warning'>
            <i className="bi bi-search"></i>
          </span>
        </div>
        
      </div>
      <div className="row pe-2 g-3">
        {filtercategory.map((ele,index)=>(
         <div key={index} className='col-12'>
           <div className="card p-3" style={{backgroundColor:ele.bgColor}}>
            <div className="d-flex align-items-center">
              <div style={{marginRight:"15px"}}>
                <img src={ele.imgUrl} alt={ele.name} className='category-image' />
              </div>
              <div className="flex-grow-1">
                <h5 className='mb-1 text-white'>{ele.name}</h5>
                <p className='mb-0 text-white'>{ele.items} items</p>
              </div>
              <div>
                <button onClick={()=>deletbyId(ele.categoryId)} className='btn btn-danger btn-sm'>
                  <i className='bi bi-trash'></i>
                </button>
              </div>
            </div>

          </div>
         </div>
        ))}
      </div>
    </div>
  )
}

export default Categorylist