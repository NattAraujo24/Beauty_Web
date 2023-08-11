import React from "react";
import { Link, NavLink } from "react-router-dom";
import '../plugins/Style.css'

const ClienteNav = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <div className="container-fluid">
        <a className="navbar-brand">Cliente</a>
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
            <li className="nav-item">
              <NavLink className="nav-link" to="/inicio"  >
                Inicio
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/user/clientes" >
                Clientes
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/user/citas" >
                Citas
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/user/catalogos" >
                Catálogos
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/user/ofertas"  >
                Ofertas
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/user/preguntas" >
                Preguntas
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/user/consejos" >
                Consejos
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/user/redes" >
                Redes Sociales
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/user/puntuacion" >
                Puntuación del servicio
              </NavLink>
            </li>
          </ul>
          <div className="btn-log" data-tooltip="Size: 20Mb">
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
  )
}

export default ClienteNav