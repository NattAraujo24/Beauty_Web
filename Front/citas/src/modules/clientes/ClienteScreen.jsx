import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';

import axios from 'axios';
import { Container, Table, Button, Row } from 'react-bootstrap'

import '../../shared/plugins/Style.css'

import imgClient from '../../assets/Admin_Clientes.png'
import imgRedButton from '../../assets/IconDelete.png';
import imgYellowButton from '../../assets/IconUpdate.png';

const ClienteScreen = () => {

  const navigation = useNavigate();
  
  useEffect(() => {
    sesionActiva();
  }, []);

  const sesionActiva = () => {
    const id = localStorage.getItem("sesionId")
    const rol = localStorage.getItem("rol")

    if (id === null || rol != 'admin') {
      navigation('/login');
    }
  }

  const iconSize = '30px';

  const url = `http://localhost:8080/api-beautypalace/user/clients/`
  const [clientes, setClientes] = useState([]);
  const [id, setId] = useState('');
  const [name, setName] = useState('');
  const [lastName, setLastname] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [tokenPassword, setTokenPassword] = useState('');
  const [typeOfUser, setTypeOfUser] = useState('');

  useEffect(() => {
    cargarClientes();
  }, []);

  const cargarClientes = async () => {
    const respuesta = await axios.get(url);
    setClientes(respuesta.data.data)
    console.log(respuesta.data.data)
    //console.clear()
  };

  return (
    <>
      <Container fluid
        style={{
          backgroundImage: `url(${imgClient})`,
          backgroundSize: 'cover',
          backgroundRepeat: 'no-repeat',
          backgroundPosition: 'center',
          minHeight: 'calc(100vh - 60px)',
          padding: '30px',// Resta la altura de la barra de navegación para que no se superponga
          boxSizing: 'border-box',
          fontFamily: 'Courier New, monospace',
        }}
      >

        <Container
          style={{
            width: '100%',
            height: '220px', // Altura fija de la tabla
            overflowX: 'auto', // Agrega un scrollbar horizontal cuando el contenido desborda el ancho del contenedor
          }}
        >
          <Table striped bordered hover style={{ width: '100%', overflowX: 'auto' }}>
            <thead>
              <tr style={{ backgroundColor: 'blue', color: 'white' }}>
                <th>#</th>
                <th>Nombre(s)</th>
                <th>Apellido(s)</th>
                <th>Correo Electrónico</th>
              </tr>
            </thead>
            <tbody>
            {clientes.length > 0 ? (
              clientes.map((client, index) => (
                <tr key={client.id} style={{ borderColor: 'black' }}>
                  <td>{index + 1}</td>
                  <td>{client.name}</td>
                  <td>{client.lastName}</td>
                  <td>{client.email}</td>
                </tr>
                ))
                ) : (
                    <tr>
                      <td colSpan="8" style={{ textAlign: 'center' }}>
                        No hay registros
                      </td>
                    </tr>
                )}
            </tbody>
            
          </Table>
        </Container>
      </Container>
    </>
  )
}

export default ClienteScreen