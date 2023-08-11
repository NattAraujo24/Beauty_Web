import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Container, Table, Button, Row, Modal, Form } from 'react-bootstrap'

import '../../shared/plugins/Style.css'

import imgRespuesta from '../../assets/Admin_Preguntas.png'
import imgRedButton from '../../assets/IconDelete.png';
import imgYellowButton from '../../assets/IconUpdate.png';
import Swal from 'sweetalert2';


const PreguntaScreen = () => {

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

  const url = `http://localhost:8080/api-beautypalace/preguntas/`
  const [preguntas, setPreguntasDatos] = useState([]);
  const [showModal, setShowModal] = useState(false);

  /*const [id, setId] = useState('');
  const [pregunta, setPregunta] = useState('');
  const [respuesta, setRespuesta] = useState('');*/
  const [formData, setFormData] = useState({
    id: '',
    pregunta: '',
    respuesta: ''
  });

  useEffect(() => {
    cargarPreguntas();
  }, []);

  const cargarPreguntas = async () => {
    const respuesta = await axios.get(url);
    setPreguntasDatos(respuesta.data.data)
    console.log(respuesta.data.data)
    //console.clear()
  };

  const handleShowModal = () => {
    setShowModal(true);
    setFormData({
      id: '',
      pregunta: '',
      respuesta: ''
    });
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleEditQuestion = (question) => {
    setFormData({
      id: question.id,
      pregunta: question.pregunta,
      respuesta: question.respuesta
    });
    setShowModal(true);
  };


  const handleSaveQuestion = async () => {
    try {
      if (formData.id) {
        await axios.put(url + 'update/', formData);
      } else {
        await axios.post(url, formData);
      }

      Swal.fire({
        icon: 'success',
        iconColor: '#c28f60',
        title: 'Pregunta guardada correctamente',
        showConfirmButton: false,
        timer: 1500
      });
      cargarPreguntas();
      handleCloseModal();
    } catch (error) {

      Swal.fire({
        icon: 'error',
        iconColor: '#97714D',
        title: 'Error al guardar el la pregunta',
        showConfirmButton: false,
        timer: 1500
      });

      console.error('Error', error);
    }
  };

  const handleDeleteCatalogo = async (productId) => {
    // Mostrar un mensaje de confirmación antes de eliminar
    const confirmationResult = await Swal.fire({
      title: '¿Estás seguro?',
      text: 'Esta acción eliminará la pregunta permanentemente.',
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
          title: 'Pregunta eliminada correctamente',
          showConfirmButton: false,
          timer: 1500
        });
        cargarPreguntas();
      } catch (error) {
        Swal.fire({
          icon: 'error',
          iconColor: '#97714D',
          title: 'Error al eliminar la pregunta',
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
          backgroundImage: `url(${imgRespuesta})`,
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
            >
              Agregar pregunta
            </Button>
          </div>
          <Table striped bordered hover style={{ width: '100%', overflowX: 'auto' }}>
            <thead>
              <tr style={{ backgroundColor: 'blue', color: 'white' }}>
                <th>#</th>
                <th>Pregunta</th>
                <th>Respuesta</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {preguntas.length > 0 ? (
                preguntas.map((question, index) => (
                  <tr key={question.id} style={{ borderColor: 'black' }}>
                    <td>{index + 1}</td>
                    <td>{question.pregunta}</td>
                    <td>{question.respuesta}</td>
                    <td>
                      <Button style={{ marginRight: '10px', backgroundColor: '#d9e021' }}
                        onClick={() => handleEditQuestion(question)}>
                        <img src={imgYellowButton} alt="Yellow Button" style={{ width: iconSize, height: iconSize }} />
                      </Button>
                      <Button style={{ backgroundColor: '#c1272d' }}
                        onClick={() => handleDeleteCatalogo(question.id)}>
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
          <Modal.Title>{formData.id ? 'Editar Pregunta' : 'Agregar Pregunta'}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="formNameProduct" className='mb-3'>
              <Form.Label style={{ color: '#c28f60' }}>Pregunta</Form.Label>
              <Form.Control
                type="text"
                value={formData.pregunta}
                onChange={(e) =>
                  setFormData({ ...formData, pregunta: e.target.value })
                }
              />
            </Form.Group>


            <Form.Group controlId="formDescrption" className='mb-3'>
              <Form.Label style={{ color: '#c28f60' }}>Respuesta</Form.Label>
              <Form.Control
                type="text"
                value={formData.respuesta}
                onChange={(e) =>
                  setFormData({ ...formData, respuesta: e.target.value })
                }
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>
            Cerrar
          </Button>
          <Button style={{ background: '#c28f60' }} onClick={handleSaveQuestion}>
            Guardar
          </Button>
        </Modal.Footer>
      </Modal>

    </>
  )
}

export default PreguntaScreen