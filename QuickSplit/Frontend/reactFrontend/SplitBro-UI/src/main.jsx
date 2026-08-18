import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { BrowserRouter,Routes,Route } from 'react-router'
import Group from './Components/Group.jsx'
import AddGroup from './Components/AddGroup.jsx'
import Transaction from './Components/Transactions.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<App />} />
       <Route path="/group/:groupId" element={<Group />} />
       <Route path="/group/Add" element={<AddGroup />} />
       <Route path="/transaction/:transactionId" element={<Transaction />} />

    </Routes>
    
    </BrowserRouter>
  </StrictMode>,
)
