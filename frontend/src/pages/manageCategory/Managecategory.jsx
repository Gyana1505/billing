import React from 'react'
import "./managecategoy.css"
import Categoryform from '../../components/categoryForm/Categoryform'
import Categorylist from '../../components/categorylist/Categorylist'
const Managecategory = () => {
  return (
    <div className='category-contaier text-light'>
        <div className="left-column">
          <Categoryform/>
        </div>
        <div className="right-column">
          <Categorylist/>
        </div>
    </div>
  )
}

export default Managecategory