import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { Container, Table, Button, Row, Modal, Form } from 'react-bootstrap'
import axios from 'axios';
import '../../shared/plugins/Style.css'
import imgConsejo from '../../assets/Admin_Consejo.png'
import imgRedButton from '../../assets/IconDelete.png';
import imgYellowButton from '../../assets/IconUpdate.png';
import Swal from 'sweetalert2';

const ConsejoScreen = () => {

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

  const url = `http://localhost:8080/api-beautypalace/consejos/`
  const [consejos, setConsejosData] = useState([]);
  const [showModal, setShowModal] = useState(false);
  /*
  const [id, setId] = useState('');
  const [titulo, setTitulo] = useState('');
  const [consejo, setConsejo] = useState('');*/
  const [formData, setFormData] = useState({
    id: '',
    titulo: '',
    consejo: ''
  });

  useEffect(() => {
    cargarConsejos();
  }, []);

  const cargarConsejos = async () => {
    const respuesta = await axios.get(url);
    setConsejosData(respuesta.data.data)
    //console.log(respuesta.data.data)
    //console.clear()
  };

  const handleShowModal = () => {
    setShowModal(true);
    setFormData({
      id: '',
      titulo: '',
      consejo: ''
    });
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleEditConsejo = (tip) => {
    setFormData({
      id: tip.id,
      titulo: tip.titulo,
      consejo: tip.consejo
    });
    setShowModal(true);
  };

  const handleSaveTip = async () => {
    try {
      if (formData.id) {
        await axios.put(url + 'update/', formData);
      } else {
        await axios.post(url, formData);
      }

      Swal.fire({
        icon: 'success',
        iconColor: '#c28f60',

        title: 'Consejo guardado correctamente',
        showConfirmButton: false,
        timer: 1500
      });
      cargarConsejos();
      handleCloseModal();
    } catch (error) {

      Swal.fire({
        icon: 'error',
        title: 'Error al guardar el consejo',
        showConfirmButton: false,
        timer: 1500
      });

      console.error('Error:', error);
    }
  };

  const handleDeleteConsejo = async (productId) => {
    // Mostrar un mensaje de confirmación antes de eliminar
    const confirmationResult = await Swal.fire({
      title: '¿Estás seguro?',
      text: 'Esta acción eliminará el consejo permanentemente.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#c28f60',
      cancelButtonColor: '#97714D',
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar'
    });

    // Si el usuario confirma la eliminación
    if (confirmationResult.isConfirmed) {
      try {
        await axios.delete(url + productId);
        Swal.fire({
          icon: 'success',
          iconColor: '#c28f60',

          title: 'Consejo eliminado correctamente',
          showConfirmButton: false,
          timer: 1500
        });
        cargarConsejos();
      } catch (error) {
        Swal.fire({
          icon: 'error',
          iconColor: '#97714D',

          title: 'Error al eliminar el consejo',
          showConfirmButton: false,
          timer: 1500
        });
        console.error('Error', error);
      }
    }
  };

  return (
    <>
      <Container fluid
        style={{
          backgroundImage: `url(${imgConsejo})`,
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
          <div style={{ display: 'flex', justifyContent: 'flex-end', marginBottom: '1rem' }}>
            <Button
              style={{ backgroundColor: '#4a3c37' }}
              onClick={handleShowModal}
            //onClick={() => handleOpenModal('')}
            >
              Agregar consejo
            </Button>
          </div>
          <Table striped bordered hover style={{ width: '100%', overflowX: 'auto' }}>
            <thead>
              <tr>
                <th>#</th>
                <th>Consejo</th>
                <th>Mensaje</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {consejos.length > 0 ? (
                consejos.map((tip, index) => (
                  <tr key={tip.id} style={{ borderColor: 'black' }}>
                    <td>{index + 1}</td>
                    <td>{tip.titulo}</td>
                    <td>{tip.consejo}</td>
                    <td>
                      <Button style={{ marginRight: '10px', backgroundColor: '#d9e021' }}
                        onClick={() => handleEditConsejo(tip)}>
                        <img src={imgYellowButton} alt="Yellow Button" style={{ width: iconSize, height: iconSize }} />
                      </Button>
                      <Button style={{ backgroundColor: '#c1272d' }}
                        onClick={() => handleDeleteConsejo(tip.id)}>
                        <img src={imgRedButton} alt="Red Button" style={{ width: iconSize, height: iconSize }} />
                      </Button>
                    </td>
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

      <Modal show={showModal} onHide={handleCloseModal}>
        <Modal.Header style={{ background: '#c28f60' }} closeButton>
          <Modal.Title>{formData.id ? 'Editar Consejo' : 'Agregar Consejo'}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="formTitulo" className='mb-3'>
              <Form.Label style={{ color: '#c28f60' }}>Mensaje</Form.Label>
              <Form.Control
                type="text"
                value={formData.titulo}
                onChange={(e) =>
                  setFormData({ ...formData, titulo: e.target.value })
                }
              />
            </Form.Group>


            <Form.Group controlId="formConsejo" className='mb-3'>
              <Form.Label style={{ color: '#c28f60' }}>Consejo</Form.Label>
              <Form.Control
                type="text"
                value={formData.consejo}
                onChange={(e) =>
                  setFormData({ ...formData, consejo: e.target.value })
                }
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>
            Cerrar
          </Button>
          <Button style={{ background: '#c28f60' }} onClick={handleSaveTip}>
            Guardar
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  )
}

export default ConsejoScreen