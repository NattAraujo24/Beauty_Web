import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Container, Table, Button, Modal, Form, Row, Col } from 'react-bootstrap';
import FeatherIcon from 'feather-icons-react/build/FeatherIcon';
import imgCita from '../../assets/Admin_Citas.png'
import imgRedButton from '../../assets/IconDelete.png';
import imgYellowButton from '../../assets/IconUpdate.png';
import Swal from 'sweetalert2';


const CitaScreen = () => {

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
  const apiUrl = 'http://localhost:8080/api-beautypalace/agenda/';

  const [citas, setCitas] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [formData, setFormData] = useState({
    id: '',
    name: '',
    lastNameClient: '',
    nameClient: '',
    typeOfService: '',
    day: '',
    startTime: '',
    timeEnd: ''
  });

  useEffect(() => {
    cargarCitas();
  }, []);

  const cargarCitas = async () => {
    try {
      const respuesta = await axios.get(apiUrl);
      setCitas(respuesta.data.data);
      //console.clear()
    } catch (error) {
      console.error('Error al cargar las citas:', error);
    }
  };

  const handleShowModal = () => {
    setShowModal(true);
    setFormData({
      id: '',
      name: '',
      lastNameClient: '',
      nameClient: '',
      typeOfService: '',
      day: '',
      startTime: '',
      timeEnd: ''
    });
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleEditCita = (cita) => {
    setFormData({
      id: cita.id,
      name: cita.name,
      lastNameClient: cita.lastNameClient,
      nameClient: cita.nameClient,
      typeOfService: cita.typeOfService,
      day: cita.day,
      startTime: cita.startTime,
      timeEnd: cita.timeEnd
    });
    setShowModal(true);
  };


  const handleSaveCita = async () => {
    try {
      if (formData.id) {
        await axios.put(apiUrl + 'update/', formData);
      } else {
        await axios.post(apiUrl, formData);
      }
      
      Swal.fire({
        icon: 'success',
        iconColor: '#c28f60',
        title: 'Cita guardada correctamente',
        showConfirmButton: false,
        timer: 1500
      });
      cargarCitas();
      handleCloseModal();
    } catch (error) {

      Swal.fire({
        icon: 'error',
        title: 'Error al guardar la cita',
        showConfirmButton: false,
        timer: 1500
      });

      console.error('Error al guardar la cita:', error);
    }
  };

  const handleDeleteCita = async (productId) => {
    // Mostrar un mensaje de confirmación antes de eliminar
    const confirmationResult = await Swal.fire({
      title: '¿Estás seguro?',
      text: 'Esta acción eliminará la cita permanentemente.',
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
        await axios.delete(apiUrl + productId);
        Swal.fire({
          icon: 'success',
          iconColor: '#c28f60',
          title: 'Cita eliminada correctamente',
          showConfirmButton: false,
          timer: 1500
        });
        cargarCitas();
      } catch (error) {
        Swal.fire({
          icon: 'error',
          iconColor: '#97714D',
          title: 'Error al eliminar el producto',
          showConfirmButton: false,
          timer: 1500
        });
        console.error('Error al eliminar la cita:', error);
      }
    }
  };

  return (
    <>

      <Container fluid
        style={{
          backgroundImage: `url(${imgCita})`,
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
              Agregar cita
            </Button>
          </div>
          <Table striped bordered hover style={{ width: '110%', overflowX: 'auto' }}>
            <thead>
              <tr style={{ backgroundColor: 'blue', color: 'white' }}>
                <th>#</th>
                <th>Nombre del servicio</th>
                <th>Nombre del cliente</th>
                <th>Tipo de servicio</th>
                <th>Dia de la cita</th>
                <th>Hora de Inicio y Fin</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {citas.length > 0 ? (
                citas.map((cita, index) => (
                  <tr key={cita.id} style={{ borderColor: 'black' }}>
                    <td>{index + 1}</td>
                    <td>{cita.name}</td>
                    <td>{cita.nameClient} {cita.lastNameClient}</td>
                    <td>{cita.typeOfService}</td>
                    <td>{cita.day}</td>
                    <td>{cita.startTime} - {cita.timeEnd}</td>
                    <td>
                      <Button style={{ marginRight: '10px', backgroundColor: '#d9e021' }}
                        onClick={() => handleEditCita(cita)}>
                        <img src={imgYellowButton} alt="Yellow Button" style={{ width: iconSize, height: iconSize }} />
                      </Button>
                      <Button style={{ backgroundColor: '#c1272d' }}
                      onClick={() => handleDeleteCita(cita.id)}>
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
        <Modal.Header style={{background: '#c28f60'}} closeButton>
          <Modal.Title>{formData.id ? 'Editar Cita' : 'Agregar Cita'}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="formName" className='mb-3'>
              <Form.Label style={{color: '#c28f60'}}>Nombre del servico</Form.Label>
              <Form.Control
                type="text"
                value={formData.name}
                onChange={(e) =>
                  setFormData({ ...formData, name: e.target.value })
                }
              />
            </Form.Group>

            <Form.Group controlId="formNameClient" className='mb-3'>
              <Form.Label style={{color: '#c28f60'}}>Nombre del cliente</Form.Label>
              <Form.Control
                type="text"
                value={formData.nameClient}
                onChange={(e) =>
                  setFormData({ ...formData, nameClient: e.target.value })
                }
              />
            </Form.Group>

            <Form.Group controlId="formLastname" className='mb-3'>
              <Form.Label style={{color: '#c28f60'}}>Apellido del cliente</Form.Label>
              <Form.Control
                type="text"
                value={formData.lastNameClient}
                onChange={(e) =>
                  setFormData({ ...formData, lastNameClient: e.target.value })
                }
              />
            </Form.Group>

            <Form.Group controlId="formTypeOfService" className='mb-3'>
              <Form.Label style={{color: '#c28f60'}}>Tipo de servicio</Form.Label>
              <Form.Control
                type="text"
                placeholder="Tipo de servicio"
                value={formData.typeOfService}
                onChange={(e) =>
                  setFormData({ ...formData, typeOfService: e.target.value })
                }
              />
            </Form.Group>

            <Form.Group controlId="formDay" className='mb-3'>
              <Form.Label style={{color: '#c28f60'}}>Día de la cita</Form.Label>
              <Form.Control
                as="select"
                value={formData.day}
                onChange={(e) => setFormData({ ...formData, day: e.target.value })}
              >
                <option value="">Seleccione un día</option>
                <option value="Lunes">Lunes</option>
                <option value="Martes">Martes</option>
                <option value="Miércoles">Miércoles</option>
                <option value="Jueves">Jueves</option>
                <option value="Viernes">Viernes</option>
                <option value="Sábado">Sábado</option>
                <option value="Domingo">Domingo</option>
              </Form.Control>
            </Form.Group>

            <Form.Group controlId="formStart" className='mb-3'>
              <Form.Label style={{color: '#c28f60'}}>Hora de Inicio</Form.Label>
              <Form.Control
                type="time"
                value={formData.startTime}
                onChange={(e) =>
                  setFormData({ ...formData, startTime: e.target.value })
                }
              />
            </Form.Group>

            <Form.Group controlId="formEnd" className='mb-3'>
              <Form.Label style={{color: '#c28f60'}}>Hora de Fin</Form.Label>
              <Form.Control
                type="time"
                value={formData.timeEnd}
                onChange={(e) =>
                  setFormData({ ...formData, timeEnd: e.target.value })
                }
              />
            </Form.Group>
          </Form>
          
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>
            Cerrar
          </Button>
          <Button style={{background: '#c28f60'}} onClick={handleSaveCita}>
            Guardar
          </Button>
        </Modal.Footer>
      </Modal>

    </>
  )
}

export default CitaScreen