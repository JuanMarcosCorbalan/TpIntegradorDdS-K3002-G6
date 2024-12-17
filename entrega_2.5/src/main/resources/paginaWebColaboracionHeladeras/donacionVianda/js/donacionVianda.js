// URL de la API
const apiUrl = '/api/heladeras';

// Referencias a los elementos
const menuHeladera = document.getElementById('menuHeladera');
const dropdownHeladeras = document.getElementById('dropdownHeladeras');

// Función para cargar las heladeras
async function cargarHeladeras() {
    try {
        // Fetch a la API
        const response = await fetch(apiUrl);
        if (!response.ok) {
            throw new Error('Error al obtener las heladeras');
        }

        // Convertir la respuesta a JSON
        const heladeras = await response.json();

        // Generar las opciones dinámicamente
        heladeras.forEach(heladera => {
            const li = document.createElement('li');
            li.innerHTML = `
          <a class="dropdown-item d-flex align-items-center" href="#" data-id="${heladera.id}">
            <img src="https://cdn-icons-png.flaticon.com/512/4151/4151152.png" alt="Ícono" class="me-2">
            <div>
              <strong>${heladera.nombre}</strong><br>
              <small>ID: ${heladera.idHeladera}</small>
            </div>
          </a>
        `;

            // Agregar evento de selección
            li.querySelector('a').addEventListener('click', function (event) {
                event.preventDefault(); // Evitar el comportamiento predeterminado del enlace

                // Actualizar el texto del botón principal
                menuHeladera.innerHTML = `
        <img src="https://cdn-icons-png.flaticon.com/512/4151/4151152.png" alt="Ícono" class="me-2">
                    <strong>${heladera.nombre}</strong><br>
                    <small>ID: ${heladera.idHeladera}</small>
    `;

                // Actualizar el campo oculto con el ID seleccionado
                document.getElementById('selectedHeladeraId').value = heladera.idHeladera;
            });

            // Añadir la opción al menú desplegable
            dropdownHeladeras.appendChild(li);
        });
    } catch (error) {
        console.error('Error al cargar las heladeras:', error);
    }
}

// Llamar a la función para cargar las heladeras al cargar la página
cargarHeladeras();