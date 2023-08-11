import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Container, Table, Button, Modal, Form, Row, Col } from 'react-bootstrap';
import FeatherIcon from 'feather-icons-react/build/FeatherIcon';
import imgCatalogo from '../../assets/Admin_Catalogo.png'
import imgRedButton from '../../assets/IconDelete.png';
import imgYellowButton from '../../assets/IconUpdate.png';
import Swal from 'sweetalert2';


const CatalogoScreen = () => {

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

  const url = `http://localhost:8080/api-beautypalace/product/`
  const [catalogo, setCatalogo] = useState([]);
  const [showModal, setShowModal] = useState(false);

  /*const [id, setId] = useState('');
  const [nombre, setNombre] = useState('');
  const [precio, setPrecio] = useState('');
  const [descripcion, setDescripcion] = useState('');*/
  const [formData, setFormData] = useState({
    id: '',
    nombre: '',
    precio: '',
    descripcion: '',
  });

  useEffect(() => {
    cargarCatalogo();
  }, []);

  const cargarCatalogo = async () => {
    const respuesta = await axios.get(url);
    setCatalogo(respuesta.data.data)
    //console.log(respuesta.data.data)
    //console.clear()
  };

  const handleShowModal = () => {
    setShowModal(true);
    setFormData({
      id: '',
      nombre: '',
      precio: '',
      descripcion: '',
    });
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleEditCatalogo = (product) => {
    setFormData({
      id: product.id,
      nombre: product.nombre,
      precio: product.precio,
      descripcion: product.descripcion,
    });
    setShowModal(true);
  };

  const handleSaveCatalogo = async () => {
    try {
      if (formData.id) {
        await axios.put(url + 'update/' + formData.id, formData);
      } else {
        await axios.post(url, formData);
      }

      Swal.fire({
        icon: 'success',
        iconColor: '#c28f60',
        title: 'Producto guardado correctamente',
        showConfirmButton: false,
        timer: 1500
      });
      cargarCatalogo();
      handleCloseModal();
    } catch (error) {

      Swal.fire({
        icon: 'error',
        iconColor: '#97714D',
        title: 'Error al guardar el producto',
        showConfirmButton: false,
        timer: 1500
      });

      console.error('Error al guardar el rpoducto:', error);
    }
  };


  const handleDeleteCatalogo = async (productId) => {
    // Mostrar un mensaje de confirmación antes de eliminar
    const confirmationResult = await Swal.fire({
      title: '¿Estás seguro?',
      text: 'Esta acción eliminará el producto permanentemente.',
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
          title: 'Producto eliminado correctamente',
          showConfirmButton: false,
          timer: 1500
        });
        cargarCatalogo();
      } catch (error) {
        Swal.fire({
          icon: 'error',
          iconColor: '#97714D',
          title: 'Error al eliminar el producto',
          showConfirmButton: false,
          timer: 1500
        });
        console.error('Error al eliminar el producto:', error);
      }
    }
  };

  return (
    <>
      <Container fluid
        style={{
          backgroundImage: `url(${imgCatalogo})`,
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
              Agregar catálogo
            </Button>
          </div>
          <Table striped bordered hover style={{ width: '100%', overflowX: 'auto' }}>
            <thead>
              <tr style={{ backgroundColor: 'blue', color: 'white' }}>
                <th>#</th>
                <th>Nombre del producto</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {catalogo.length > 0 ? (
                catalogo.map((product, index) => (
                  <tr key={product.id} style={{ borderColor: 'black' }}>
                    <td>{index + 1}</td>
                    <td>{product.nombre}</td>
                    <td>{product.descripcion}</td>
                    <td>${product.precio}</td>
                    <td>
                      <Button style={{ marginRight: '10px', backgroundColor: '#d9e021' }}
                        onClick={() => handleEditCatalogo(product)}>
                        <img src={imgYellowButton} alt="Yellow Button" style={{ width: iconSize, height: iconSize }} />
                      </Button>
                      <Button style={{ backgroundColor: '#c1272d' }}
                        onClick={() => handleDeleteCatalogo(product.id)}>
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
          <Modal.Title>{formData.id ? 'Editar Catálogo' : 'Agregar Catálogo'}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="formNameProduct" className='mb-3'>
              <Form.Label style={{color: '#c28f60'}}>Nombre del producto</Form.Label>
              <Form.Control
                type="text"
                value={formData.nombre}
                onChange={(e) =>
                  setFormData({ ...formData, nombre: e.target.value })
                }
              />
            </Form.Group>


            <Form.Group controlId="formDescrption" className='mb-3'>
              <Form.Label style={{color: '#c28f60'}}>Descrpción</Form.Label>
              <Form.Control
                type="text"
                value={formData.descripcion}
                onChange={(e) =>
                  setFormData({ ...formData, descripcion: e.target.value })
                }
              />
            </Form.Group>

            <Form.Group controlId="formTypeOfService" className='mb-3'>
              <Form.Label style={{color: '#c28f60'}}>Precio</Form.Label>
              <Form.Control
                type="text"
                value={formData.precio}
                onChange={(e) =>
                  setFormData({ ...formData, precio: e.target.value })
                }
              />
            </Form.Group>

            {/* Agrega más campos aquí */}
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>
            Cerrar
          </Button>
          <Button style={{background: '#c28f60'}} onClick={handleSaveCatalogo}>
            Guardar
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  )
}

export default CatalogoScreen