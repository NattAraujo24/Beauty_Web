import React, { useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import { Link, NavLink } from "react-router-dom";
import '../plugins/Style.css'
import BPLogo from "../../assets/BPLogo.png"
import Swal from 'sweetalert2';
import { Button } from "react-bootstrap";


const AdminNav = () => {

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

  const navigate = useNavigate();

  const handleHomeClick = () => {
    navigate('/');
  };


  const logOut = () => {
    Swal.fire({
      title: '¿Estás seguro de salir?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Confirmar',
      cancelButtonText: 'Cancelar',
      reverseButtons: true,
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire({
          title: 'Hasta pronto...',
          icon: 'info',
          showConfirmButton: false,
          allowOutsideClick: false,
        });
        setTimeout(() => {
          localStorage.clear();
          Swal.close();
          navigate('/login');
        }, 1500);
      }
    });
  };

  return (

    <nav className="navbar navbar-expand-lg" style={{ backgroundColor: '#4A3C37', fontFamily: 'Courier New, monospace' }}>
      <div className="container-fluid">
        <img src={BPLogo} alt="BPLogo" className="BPLogo" style={{ margin: 'auto', paddingRight: '5px' }} />
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarColor01"
          aria-controls="navbarColor01"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarColor01">
          <ul className="navbar-nav me-auto">
            {/*<li className="nav-item">
              <NavLink className="nav-link" to="/perfil"  style={{color:'white'}}>
                Perfil
              </NavLink>
  </li>*/}
            <li className="nav-item">
              <NavLink className="nav-link" to="clientes" style={{ color: 'white' }}>
                Clientes
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="citas" style={{ color: 'white' }}>
                Citas
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="catalogos" style={{ color: 'white' }}>
                Catálogos
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="ofertas" style={{ color: 'white' }}>
                Ofertas
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="preguntas" style={{ color: 'white' }}>
                Preguntas
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="consejos" style={{ color: 'white' }}>
                Consejos
              </NavLink>
            </li>
          </ul>
          <div className="btn-log" data-tooltip="Size: 20Mb"
          onClick={logOut}>
            <div className="btnlog-wrapper">
              <div className="text">Cerrar sesión</div>
              <span className="icon">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  aria-hidden="true"
                  role="img"
                  width="2em"
                  height="2em"
                  preserveAspectRatio="xMidYMid meet"
                  viewBox="0 0 24 24"
                >
                  <path
                    fill="none"
                    stroke="currentColor"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth="2"
                    d="M12 15V3m0 12l-4-4m4 4l4-4M2 17l.621 2.485A2 2 0 0 0 4.561 21h14.878a2 2 0 0 0 1.94-1.515L22 17"
                  ></path>
                </svg>
              </span>
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default AdminNav;
