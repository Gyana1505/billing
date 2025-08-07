import React from 'react'
import './manageitem.css'
import Itemform from '../../components/itemform/Itemform'
import Itemlist from '../../components/itemlist/Itemlist'
const Manageitems = () => {
  return (
     <div className='item-contaier text-light'>
        <div className="left-column">
          <Itemform/>
        </div>
        <div className="right-column">
          <Itemlist/>
        </div>
    </div>
  )
}

export default Manageitems