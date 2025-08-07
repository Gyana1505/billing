import React from 'react'
import './manageuser.css'
import Userform from '../../components/userform/Userform'
import Userlist from '../../components/userlist/Userlist'
const Manageuser = () => {
  return (
    <div className='user-contaier text-light'>
        <div className="left-column">
          <Userform/>
        </div>
        <div className="right-column">
          <Userlist/>
        </div>
    </div>
  )
}

export default Manageuser