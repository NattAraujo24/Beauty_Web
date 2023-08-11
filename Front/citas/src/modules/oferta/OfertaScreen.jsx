import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { Container, Table, Button, Row, Modal, Form } from 'react-bootstrap'
import axios from 'axios';
import '../../shared/plugins/Style.css'
import imgOferta from '../../assets/Admin_Ofertas.png'
import imgRedButton from '../../assets/IconDelete.png';
import imgYellowButton from '../../assets/IconUpdate.png';
import Swal from 'sweetalert2';


const OfertaScreen = () => {

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
  // State para el modal
  const [showModal, setShowModal] = useState(false);

  const url = `http://localhost:8080/api-beautypalace/oferta/`
  const urlProduct = `http://localhost:8080/api-beautypalace/product/`
  const [oferta, setOferta] = useState([]);
  const [producto, setProducto] = useState([]);
  /*const [id, setId] = useState('');
  const [nombre, setNombre] = useState('');
  const [descuento, setDescuento] = useState('');
  const [product, setProduct] = useState('');
  const [descripcion, setDescripcion] = useState('');
  const [codigoDescuento, setCodigoDescuento] = useState('');
  const [fechaInicio, setFechaInicio] = useState('');*/

  ///Oferta
  const [formData, setFormData] = useState({
    id: '',
    nombre: '',
    descuento: '',
    product: '',
    descripcion: '',
    codigoDescuento: '',
    fechaInicio: ''
  });

  //Producto
  const [formDataProduct, setFormDataProduct] = useState({
    id: '',
    nombre: '',
    precio: '',
    descripcion: ''
  });

  useEffect(() => {
    cargarOferta();
    cargarProducto();
  }, []);

  const cargarOferta = async () => {
    const respuesta = await axios.get(url);
    setOferta(respuesta.data.data)
    console.log(respuesta.data.data)
    //console.clear()
  };

  const cargarProducto = async () => {
    const respuesta = await axios.get(urlProduct);
    setProducto(respuesta.data.data)
    console.log(respuesta.data.data)
    //console.clear()
  };

  const handleShowModal = () => {
    setShowModal(true);
    setFormData({
      id: '',
      nombre: '',
      descuento: '',
      product: '',
      descripcion: '',
      codigoDescuento: '',
      fechaInicio: ''
    });
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleEditOferta = (offert) => {
    setFormData({
      id: offert.id,
      nombre: offert.nombre,
      descuento: offert.descuento,
      product: offert.product,
      descripcion: offert.descripcion,
      codigoDescuento: offert.descuento,
      fechaInicio: offert.fechaInicio
    });
    setShowModal(true);
  };


  const handleSaveOferta = async () => {
    try {
      if (formData.id) {
        await axios.put(url + 'update/' + formData.id, formData);
      } else {
        await axios.post(url, formData);
      }

      Swal.fire({
        icon: 'success',
        iconColor: '#c28f60',
        title: 'Oferta guardada correctamente',
        showConfirmButton: false,
        timer: 1500
      });
      cargarOferta();
      cargarProducto();
      handleCloseModal();
    } catch (error) {

      Swal.fire({
        icon: 'error',
        title: 'Error al guardar la oferta',
        showConfirmButton: false,
        timer: 1500
      });

      console.error('Error al guardar la oferta:', error);
    }
  };

  const handleDeleteOferta = async (productId) => {
    // Mostrar un mensaje de confirmación antes de eliminar
    const confirmationResult = await Swal.fire({
      title: '¿Estás seguro?',
      text: 'Esta acción eliminará la oferta permanentemente.',
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
          title: 'Oferta eliminada correctamente',
          showConfirmButton: false,
          timer: 1500
        });
        cargarOferta();
        cargarProducto();
      } catch (error) {
        Swal.fire({
          icon: 'error',
          iconColor: '#97714D',
          title: 'Error al eliminar la oferta',
          showConfirmButton: false,
          timer: 1500
        });
        console.error('Error al eliminar la oferta:', error);
      }
    }
  };

  ////
  const [selectedDate, setSelectedDate] = useState('');

  const handleDateChange = (event) => {
    setSelectedDate(event.target.value);
  };

  return (
    <>
      <Container fluid
        style={{
          backgroundImage: `url(${imgOferta})`,
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
              Agregar oferta
            </Button>
          </div>
          <Table striped bordered hover style={{ width: '120%', overflowX: 'auto' }}>
            <thead>
              <tr style={{ backgroundColor: 'blue', color: 'white' }}>
                <th>#</th>
                <th>Nombre de la Oferta</th>
                <th>Descuento</th>
                <th>Producto</th>
                <th>Descripción</th>
                <th>Código de descuento</th>
                <th>Fecha de inicio</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {oferta.length > 0 ? (
                oferta.map((offert, index) => (
                  <tr key={offert.id} style={{ borderColor: 'black' }}>
                    <td>{index + 1}</td>
                    <td>{offert.nombre}</td>
                    <td>{offert.descuento}%</td>
                    <td>{offert.product}</td>
                    <td>{offert.descripcion}</td>
                    <td>{offert.codigoDescuento}</td>
                    <td>{offert.fechaInicio}</td>
                    <td>
                      <Button style={{ marginRight: '10px', backgroundColor: '#d9e021' }}
                        onClick={() => handleEditOferta(offert)}>
                        <img src={imgYellowButton} alt="Yellow Button" style={{ width: iconSize, height: iconSize }} />
                      </Button>
                      <Button style={{ backgroundColor: '#c1272d' }}
                        onClick={() => handleDeleteOferta(offert.id)}>
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
        <Modal.Header closeButton style={{ backgroundColor: '#97714D', color: 'white' }}>
          <Modal.Title >{formData.id ? 'Editar Oferta' : 'Agregar Oferta'}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {/* Formulario para agregar o editar el consejo */}
          <Form>
            <Form.Group className="mb-3" controlId="nombreId">
              <Form.Label>Nombre de la oferta</Form.Label>
              <Form.Control
                type="text"
                value={formData.nombre}
                onChange={(e) =>
                  setFormData({ ...formData, nombre: e.target.value })
                } />
            </Form.Group>
            <Form.Group className="mb-3" controlId="descuentoId">
              <Form.Label>Descuento</Form.Label>
              <Form.Control type='text'
                value={formData.descuento}
                onChange={(e) =>
                  setFormData({ ...formData, descuento: e.target.value })
                } />
            </Form.Group>

            <Form.Group className="mb-3" controlId="productID">
              <Form.Label>Producto</Form.Label>
              <Form.Control
                as="select" // Usamos un menú desplegable
                value={formData.product}
                onChange={(e) =>
                  setFormData({ ...formData, product: e.target.value })
                }
              >
                <option value="">Seleccione un producto</option>
                {producto.map((producto) => (
                  <option key={producto.id} value={producto.nombre}>
                    {producto.nombre}
                  </option>
                ))}
              </Form.Control>
            </Form.Group>

            <Form.Group className="mb-3" controlId="descId">
              <Form.Label>Descripción</Form.Label>
              <Form.Control type='text'
                value={formData.descripcion}
                onChange={(e) =>
                  setFormData({ ...formData, descripcion: e.target.value })
                } />
            </Form.Group>

            <Form.Group className="mb-3" controlId="codesId">
              <Form.Label>Codigo de descuento</Form.Label>
              <Form.Control type='text'
                value={formData.codigoDescuento}
                onChange={(e) =>
                  setFormData({ ...formData, codigoDescuento: e.target.value })
                } />
            </Form.Group>

            <Form.Group controlId="formStart" className='mb-3'>
              <Form.Label style={{ color: '#c28f60' }}>Hora de Fin</Form.Label>
              <Form.Control
                type="date"
                value={formData.fechaInicio}
                onChange={(e) =>
                  setFormData({ ...formData, fechaInicio: e.target.value })
                }
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="danger" onClick={handleCloseModal}>
            Cancelar
          </Button>
          <Button style={{ background: '#c28f60' }} onClick={handleSaveOferta}>
            Guardar
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  )
}

export default OfertaScreen