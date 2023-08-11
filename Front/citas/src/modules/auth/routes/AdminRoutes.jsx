import React, { useEffect } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AdminNav from '../../../shared/components/AdminNav';
import ClienteScreen from '../../clientes/ClienteScreen';
import CitaScreen from '../../citas/CitaScreen';
import CatalogoScreen from '../../catalogo/CatalogoScreen';
import OfertaScreen from '../../oferta/OfertaScreen';
import PreguntaScreen from '../../preguntas/PreguntaScreen';
import ConsejoScreen from '../../consejos/ConsejoScreen';
import Error from '../../../shared/plugins/Error';

const AdminRoutes = () => {
    return (
        <>
            <AdminNav />
            <Routes>
                <Route path="/" element={<ClienteScreen />} />
                <Route path="clientes" element={<ClienteScreen />} />
                <Route path="citas" element={<CitaScreen />} />
                <Route path="catalogos" element={<CatalogoScreen />} />
                <Route path="ofertas" element={<OfertaScreen />} />
                <Route path="preguntas" element={<PreguntaScreen />} />
                <Route path="consejos" element={<ConsejoScreen />} />
                <Route path="/*" element={<Error />} />
            </Routes>
        </>
    )
}
export default AdminRoutes