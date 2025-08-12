import React, { useContext } from 'react'
import './explore.css'
import { AppContext } from '../../context/Appcontext'
const Explore = () => {
    const {category}=useContext(AppContext)
    console.log(category)
  return (
    <div className="explore-container text-light">
      <div className="left-column">
           <div className="first-row" style={{overflowY:'auto'}}>Category</div>
           <hr className="horizontal-line" />
           <div className="second-row" style={{overflowY:'auto'}}>items</div>
      </div>
      <div className="right-column d-flex flex-column">
        <div className="customer-form-container" style={{height:'15%'}}>
            customer form
        </div>
        <hr className="my-3 text-light" />
        <div className="cart-item-container" style={{height:'55%',overflowY:'auto'}}>
             customer item
        </div>
        <div className="cart-summary-container" style={{height:'30%',overflowY:'auto'}}>
          form summary
        </div>
      </div>
    </div>
  )
}

export default Explore