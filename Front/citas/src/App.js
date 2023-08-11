import React, {useEffect} from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import Login from './modules/auth/Login';

import AdminNav from './shared/components/AdminNav';
import ClienteScreen from './modules/clientes/ClienteScreen';
import CitaScreen from './modules/citas/CitaScreen';
import CatalogoScreen from './modules/catalogo/CatalogoScreen';
import OfertaScreen from './modules/oferta/OfertaScreen';
import PreguntaScreen from './modules/preguntas/PreguntaScreen';
import ConsejoScreen from './modules/consejos/ConsejoScreen';
import Perfil from './modules/auth/Perfil';
import Error from './shared/plugins/Error'
import AdminRoutes from './modules/auth/routes/AdminRoutes';

function App() {

  useEffect(() => {
    document.title = "Beauty Palace"; 
  }, []);

  return (
    //Admin
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        {/*<Route path="/changePassword" element={<ForgetPass />} />*/}
        <Route path="/admin/*" element={<AdminRoutes/>} />
        <Route path="/*" element={<Error />} />
      </Routes>
    </BrowserRouter>

    /*<Login/>*/

  );
}

export default App;
