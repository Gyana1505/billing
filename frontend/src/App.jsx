import Menubar from "./components/menubar/Menubar";
import {Route,Routes} from "react-router-dom"
import Daseboard from "./pages/Dashboard/Daseboard";
import Managecategory from "./pages/manageCategory/Managecategory";
import Manageuser from "./pages/manageuser/Manageuser";
import Explore from "./pages/explore/Explore";
import Manageitems from "./pages/manageitems/Manageitems";
import { Toaster } from "react-hot-toast";

function App() {

  return (
    <div>
      <Menubar/>
      <Toaster/>
      <Routes>
        <Route path="/dashbord" element={<Daseboard/>}/>
        <Route path="/category" element={<Managecategory/>}/>
        <Route path="/user" element={<Manageuser/>}/>
        <Route path="/item" element={<Manageitems/>}/>
        <Route path="/explore" element={<Explore/>}/>
         <Route path="/" element={<Daseboard/>}/>
      </Routes>
    </div>
  )
}

export default App
