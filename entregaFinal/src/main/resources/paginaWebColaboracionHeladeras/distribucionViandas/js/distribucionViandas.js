/*
// URL de la API
const apiUrl = '/api/heladeras';

// Referencias a los elementos
const menuHeladeraOrigen = document.getElementById('menuHeladeraOrigen');
const dropdownHeladerasOrigen = document.getElementById('dropdownHeladerasOrigen');
const menuHeladeraDestino = document.getElementById('menuHeladeraDestino');
const dropdownHeladerasDestino = document.getElementById('dropdownHeladerasDestino');

// Variables para mantener las selecciones actuales
let heladeraOrigenSeleccionada = null;
let heladeraDestinoSeleccionada = null;

// Función para cargar las heladeras en un desplegable
async function cargarHeladeras() {
    try {
        // Fetch a la API
        const response = await fetch(apiUrl);
        if (!response.ok) {
            throw new Error('Error al obtener las heladeras');
        }

        // Convertir la respuesta a JSON
        const heladeras = await response.json();

        // Cargar las heladeras en ambos desplegables
        cargarHeladerasEnDropdown(menuHeladeraOrigen, dropdownHeladerasOrigen, 'selectedHeladeraIdOrigen', heladeras, 'origen');
        cargarHeladerasEnDropdown(menuHeladeraDestino, dropdownHeladerasDestino, 'selectedHeladeraIdDestino', heladeras, 'destino');
    } catch (error) {
        console.error('Error al cargar las heladeras:', error);
    }
}

// Función para cargar opciones en un desplegable
function cargarHeladerasEnDropdown(menu, dropdown, inputHiddenId, heladeras, tipo) {
    dropdown.innerHTML = ''; // Limpiar el contenido del dropdown

    heladeras.forEach(heladera => {
        const li = document.createElement('li');
        li.innerHTML = `
            <a class="dropdown-item d-flex align-items-center ${isOptionDisabled(heladera.idHeladera, tipo) ? 'disabled' : ''}" href="#" data-id="${heladera.idHeladera}">
                <img src="https://cdn-icons-png.flaticon.com/512/4151/4151152.png" alt="Ícono" class="me-2">
                <div>
                    <strong>${heladera.nombre}</strong><br>
                    <small>ID: ${heladera.idHeladera}</small>
                </div>
            </a>
        `;

        // Agregar evento de selección solo si no está deshabilitado
        const link = li.querySelector('a');
        if (!link.classList.contains('disabled')) {
            link.addEventListener('click', function (event) {
                event.preventDefault(); // Evitar el comportamiento predeterminado del enlace

                // Actualizar el texto del botón principal
                menu.innerHTML = `
                    <img src="https://via.placeholder.com/20" alt="Ícono" class="me-2">
                    <strong>${heladera.nombre}</strong><br>
                    <small>ID: ${heladera.idHeladera}</small>
                `;

                // Actualizar el campo oculto con el ID seleccionado
                document.getElementById(inputHiddenId).value = heladera.idHeladera;

                // Guardar la selección actual
                if (tipo === 'origen') {
                    heladeraOrigenSeleccionada = heladera.idHeladera;
                } else {
                    heladeraDestinoSeleccionada = heladera.idHeladera;
                }

                // Actualizar los desplegables para reflejar las restricciones
                cargarHeladeras();
            });
        }

        // Añadir la opción al menú desplegable
        dropdown.appendChild(li);
    });
}

// Función para verificar si una opción debe estar deshabilitada
function isOptionDisabled(heladeraId, tipo) {
    return (tipo === 'origen' && heladeraId === heladeraDestinoSeleccionada) ||
        (tipo === 'destino' && heladeraId === heladeraOrigenSeleccionada);
}

// Cargar las heladeras al cargar la página
document.addEventListener('DOMContentLoaded', cargarHeladeras);*/

// URL de la API
const apiUrl = '/api/heladeras';

// Referencias a los elementos
const menuHeladeraOrigen = document.getElementById('menuHeladeraOrigen');
const dropdownHeladerasOrigen = document.getElementById('dropdownHeladerasOrigen');
const menuHeladeraDestino = document.getElementById('menuHeladeraDestino');
const dropdownHeladerasDestino = document.getElementById('dropdownHeladerasDestino');
const inputCantidadViandas = document.getElementById('inputCantidadViandas');
const botonCargar = document.querySelector('button[type="submit"]');
const mensajeError = document.createElement('div');

// Variables para mantener las selecciones actuales y capacidades
let heladeraOrigenSeleccionada = null;
let heladeraDestinoSeleccionada = null;
let heladeras = [];

// Función para cargar las heladeras en los desplegables
async function cargarHeladeras() {
    try {
        const response = await fetch(apiUrl);
        if (!response.ok) throw new Error('Error al obtener las heladeras');

        heladeras = await response.json();
        cargarHeladerasEnDropdown(menuHeladeraOrigen, dropdownHeladerasOrigen, 'selectedHeladeraIdOrigen', heladeras, 'origen');
        cargarHeladerasEnDropdown(menuHeladeraDestino, dropdownHeladerasDestino, 'selectedHeladeraIdDestino', heladeras, 'destino');
    } catch (error) {
        console.error('Error al cargar las heladeras:', error);
    }
}

// Función para cargar opciones en un desplegable
function cargarHeladerasEnDropdown(menu, dropdown, inputHiddenId, heladeras, tipo) {
    dropdown.innerHTML = '';
    heladeras.forEach(heladera => {
        const li = document.createElement('li');
        li.innerHTML = `
            <a class="dropdown-item d-flex align-items-center ${isOptionDisabled(heladera.idHeladera, tipo) ? 'disabled' : ''}" href="#" data-id="${heladera.idHeladera}">
                <img src="https://cdn-icons-png.flaticon.com/512/4151/4151152.png" alt="Ícono" class="me-2">
                <div>
                    <strong>${heladera.nombre}</strong><br>
                    <small>ID: ${heladera.idHeladera}</small>
                </div>
            </a>
        `;

        const link = li.querySelector('a');
        if (!link.classList.contains('disabled')) {
            link.addEventListener('click', function (event) {
                event.preventDefault();
                menu.innerHTML = `
                    <img src="https://cdn-icons-png.flaticon.com/512/4151/4151152.png" alt="Ícono" class="me-2">
                    <strong>${heladera.nombre}</strong><br>
                    <small>ID: ${heladera.idHeladera}</small>
                `;
                document.getElementById(inputHiddenId).value = heladera.idHeladera;
                if (tipo === 'origen') heladeraOrigenSeleccionada = heladera.idHeladera;
                else heladeraDestinoSeleccionada = heladera.idHeladera;
                cargarHeladeras();
                validarCapacidad();
            });
        }
        dropdown.appendChild(li);
    });
}

// Función para verificar si una opción debe estar deshabilitada
function isOptionDisabled(heladeraId, tipo) {
    return (tipo === 'origen' && heladeraId === heladeraDestinoSeleccionada) ||
        (tipo === 'destino' && heladeraId === heladeraOrigenSeleccionada);
}

// Validar capacidad máxima de la heladera destino
function validarCapacidad() {
    const heladeraDestino = heladeras.find(h => h.idHeladera === heladeraDestinoSeleccionada);
    const cantidadViandas = parseInt(inputCantidadViandas.value, 10);
    mensajeError.className = 'text-danger mt-2';
    mensajeError.innerHTML = '';

    if (heladeraDestino && cantidadViandas > heladeraDestino.capacidad_maxima) {
        mensajeError.textContent = `Error: La cantidad de viandas (${cantidadViandas}) excede la capacidad máxima (${heladeraDestino.capacidad_maxima}).`;
        if (!inputCantidadViandas.parentNode.contains(mensajeError)) {
            inputCantidadViandas.parentNode.appendChild(mensajeError);
        }
        inputCantidadViandas.classList.add('is-invalid');
        botonCargar.disabled = true;
    } else {
        inputCantidadViandas.classList.remove('is-invalid');
        if (inputCantidadViandas.parentNode.contains(mensajeError)) {
            inputCantidadViandas.parentNode.removeChild(mensajeError);
        }
        botonCargar.disabled = false;
    }
}

// Evento para validar al cambiar la cantidad de viandas
inputCantidadViandas.addEventListener('input', validarCapacidad);

// Cargar las heladeras al cargar la página
document.addEventListener('DOMContentLoaded', cargarHeladeras);






