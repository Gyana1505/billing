import React, { useContext, useEffect, useState } from 'react'
import toast from 'react-hot-toast'
import { addCategory } from '../../service/categoryService'
import { AppContext } from '../../context/Appcontext'

const Categoryform = () => {
  const {setCategory,category} = useContext(AppContext)
  const [loading,setLoading]=useState(false)
  const [image,setImage]=useState(null)
  const [data,setData]=useState({
    name:"",
    description:"",
    bgColor:"#2c2c2c"
  })
  useEffect(()=>{
  console.log(data)
  },[data])
  const onChangeHandler =(e)=>{
      const value=e.target.value
      const name=e.target.name
      setData(()=>({...data,[name]:value}))
  }
  const onSubmitHandler=async (e)=>{
    e.preventDefault();
    setLoading(true)
    if (!image) {
      toast.error("Please uplode image")
      setLoading(false)
      return
    }
    const formdata=new FormData()
    formdata.append("category",JSON.stringify(data))
    formdata.append("file",image)

    try {
      const response=await addCategory(formdata)
      if (response.status === 201) {
         setCategory([...category,response.data])
        toast.success("category added")
        setData({
           name:"",
           description:"",
           bgColor:"#2c2c2c"
        })
        setImage(null)
      }
    } catch (error) {
      toast.error("error in add category")
    }finally{
      setLoading(false)
    }
  }
  return (
    <div className="mx-2 mt-2">
      <div className="row">
        <div className="card col-md-12 form-container">
          <div className="card-body">
            <form onSubmit={onSubmitHandler}>
              <div className='mb-3'>
                <label htmlFor="image" className='form-label'>
                  <img src={image?URL.createObjectURL(image) : "https://placehold.co/600x400"} alt="" width={48} />
                </label>
                <input type="file" name="image" id="image" className='form-control' hidden  
                onChange={(e) => {
                  const file = e.target.files[0] || null;
                   setImage(file); // ✅ only set image
                }}/>
              </div>
              <div className='mb-3'>
                <label htmlFor="name" className='form-label'>Name</label>
                <input type="text" 
                name="name" id="name" 
                placeholder='Category Name' 
                className='form-control'
                onChange={onChangeHandler}
                value={data.name}
                />
              </div>
              <div className='mb-3'>
                <label htmlFor="description" className='form-label'>Description</label>
                <textarea
                rows="5"
                name="description" 
                id="description" 
                placeholder='Write content here..' 
                className='form-control' 
                 onChange={onChangeHandler}
                value={data.description}
                />
              </div>
              <div className='mb-3'>
                <label htmlFor="bgcolor" className='form-label'>Background color</label>
                <br />
                <input 
                type="color" 
                name="bgColor" 
                id="bgcolor" 
                placeholder='#fffff' 
                 onChange={onChangeHandler}
                value={data.bgColor}/>
                
              </div>
              <button type="submit" className='btn btn-primary w-100'disabled={loading}>{loading?"loadding...":"Submit"}</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Categoryform