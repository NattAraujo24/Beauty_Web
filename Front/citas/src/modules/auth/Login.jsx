import React, { useState, useEffect } from 'react';
import { Navigate, useNavigate } from 'react-router-dom';
import { Container, Table, Button, Row, Modal, Form } from 'react-bootstrap'
import '../../shared/plugins/Login.css';
import Swal from 'sweetalert2';

import BPLogo from '../../assets/BPLogo.png'

const Login = () => {

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const navegacion = useNavigate();
  const url = `http://localhost:8080/api-beautypalace/`


  useEffect(() => {
    validateSession();
  }, []);

  const validateSession = () => {
    if (localStorage.getItem('sesionId')) {
      switch (localStorage.getItem('rol')) {
        case 'admin':
          navigateToAdmin();
          break;
        default:
          break;
      }
    }
  }

  const navigateToAdmin = () => {
    setIsLoading(true);
    // Simulate a loading delay for better user experience
    setTimeout(() => {
      setIsLoading(false);
      navegacion('/admin');
    }, 1500);
  }

  const buscarPersona = async () => {
    let found = false;
    try {
      let usuario = null;
      let rol = '';

      const admin = await fetch(`${url}user/admins/`);
      const adminData = await admin.json();

      for (let index = 0; index < adminData.data.length; index++) {
        const element = adminData.data[index];
        if (element.email === email && element.password === password) {
          usuario = element;
          rol = 'admin';
          found = true;
        }
      }

      if (!found) {
        const cliente = await fetch(`${url}user/clients/`);
        const clienteData = await cliente.json();

        for (let index = 0; index < clienteData.data.length; index++) {
          const element = clienteData.data[index];
          if (element.email === email && element.password === password) {
            found = true;
          }
        }
        if (found) {
          Swal.fire({
            icon: 'error',
            title: 'Acceso denegado',
            text: 'Los clientes no pueden acceder a esta área.',
            showConfirmButton: false,
            timer: 2500
          });
        }
      }

      if (!found) {
        Swal.fire({
          icon: 'error',
          title: 'Usuario no encontrado',
          showConfirmButton: false,
          timer: 1500
        });
      } else {
        if (rol === 'admin') {
          localStorage.setItem("rol", rol);
          localStorage.setItem("sesionId", usuario.id);
          let dato = localStorage.getItem("sesionUser");

          navigateToAdmin();
        }
      }
    } catch (error) {
      console.error('Error en la solicitud de datos:', error);
      Swal.fire({
        icon: 'error',
        title: 'Ocurrió un error al buscar el usuario',
        text: 'Por favor, intenta nuevamente más tarde.',
        showConfirmButton: false,
        timer: 2000
      });
    } finally {
    }
  }

  return (
    <div className='body-login'>
      <div className="login-container" >
        <div className="login-form">
          <div className="login-card">
            <img src={BPLogo} alt="BPLogo" style={{ width: '100%', display: 'block', margin: '0 auto' }} />
          </div>
          <Form className="login-form">
            <Form.Group controlId="formBasicEmail">
              <Form.Control required
                type="email"
                placeholder="Correo electrónico"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="form-control"
              />
            </Form.Group>
            <Form.Group controlId="formBasicPassword">
              <Form.Control required
                type="password"
                placeholder="Contraseña"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="form-control"
              />
            </Form.Group>
            <Button
              type="button"
              onClick={buscarPersona}
              variant='outline-light'
              className="login-button"
              disabled={isLoading || !email || !password}
            >
              {isLoading ? 'Cargando...' : 'Iniciar sesión'}
            </Button>
          </Form>
        </div>
      </div>
    </div >
  );
}

export default Login;
