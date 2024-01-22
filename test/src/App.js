import React from 'react';
import { Provider } from 'react-redux';
import store from './store';

import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Main from './pages/main';
import Alarm from './pages/alarm';

import { ThemeProvider } from '@mui/material/styles';
import theme from './styles/theme'; 
import './index.css'
import NavBar from './components/NavBar/NavBar';


const App = () => {
  return (
    
    <Provider store={store}>
      <ThemeProvider theme={theme}>
        <BrowserRouter>
          <NavBar />
          <Routes>
            <Route path="/" element={<Main />} />
            <Route path="/main" element={<Main />} />
            <Route path="/alarm" element={<Alarm />} />
          </Routes>
          
          

        </BrowserRouter>
      </ThemeProvider>
    </Provider>
    
  );
};

export default App;
