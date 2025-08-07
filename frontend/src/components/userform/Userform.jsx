import React from 'react'

const Userform = () => {
  return (
    <div className="mx-2 mt-2">
      <div className="row">
        <div className="card col-md-8 form-container">
          <div className="card-body">
            <form>
              
              <div className='mb-3'>
                <label htmlFor="name" className='form-label'>Name</label>
                <input type="text" 
                name="name" id="name" 
                placeholder='Category Name' 
                className='form-control' />
              </div>
              <div className='mb-3'>
                <label htmlFor="email" className='form-label'>Email</label>
                <input type="text" 
                name="email" id="email" 
                placeholder='example@gmail.com' 
                className='form-control' />
              </div>

              <div className='mb-3'>
                <label htmlFor="password" className='form-label'>Password</label>
                <br />
                <input 
                type="password" 
                name="password" 
                id="password" 
                placeholder='************' />
                
              </div>
              <button type="submit" className='btn btn-primary w-100'>Save</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Userform