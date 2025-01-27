import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import MyGlobalStyles from './styles/globalStyles'
import App from './App'
import "./index.css";

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <MyGlobalStyles />
    <App/>
  </StrictMode>,
)
