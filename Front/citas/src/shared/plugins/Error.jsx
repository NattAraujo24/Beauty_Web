import React from 'react';
import { Spinner } from 'react-bootstrap';

const Error = () => {
  return (
    <div style={{ textAlign: 'center', marginTop: '20px' }}>
      <h1 style={{ color: '#c28f60', fontSize: '3rem' }}>Error 404</h1>
      <p style={{ color: '#97714D' }}>Página no encontrada</p>
      <div style={{ marginTop: '20px' }}>
        <Spinner animation="border" variant="dark" />
      </div>
    </div>
  );
}

export default Error;
