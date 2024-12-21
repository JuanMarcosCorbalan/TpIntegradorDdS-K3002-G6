
async function initMap() {
    const { Map } = await google.maps.importLibrary("maps");
    const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");

    const map = new Map(document.getElementById("map"), {
        center: { lat: -34.659662, lng: -58.467978 },
        zoom: 14,
        mapId: "4504f8b37365c3d0",
    });

    const infoWindow = new google.maps.InfoWindow();
    let currentInfoWindow = null;

    // Obtener las heladeras desde la API
    const response = await fetch('/api/heladerasDTO');
    const heladeras = await response.json();

    const stateIcons = {
        "ACTIVA": "https://icones.pro/wp-content/uploads/2021/04/icone-cercle-rempli-vert.png",
        "EN_MANTENIMIENTO": "https://icones.pro/wp-content/uploads/2021/04/icone-cercle-rempli-gris.png",
        "INACTIVO": "https://icones.pro/wp-content/uploads/2021/04/icone-cercle-rempli-rouge.png"
    };

    // Iterar sobre las heladeras obtenidas de la API
    heladeras.forEach(heladera => {
        const lat = parseFloat(heladera.latitud.replace(',', '.'));
        const lng = parseFloat(heladera.longitud.replace(',', '.'));

        const marker = new AdvancedMarkerElement({
            map,
            position: { lat, lng },
            title: `Heladera ID: ${heladera.idHeladera}`,
        });

        marker.addListener('click', async function () {
            if (currentInfoWindow) {
                currentInfoWindow.close();
            }

            const contentString = `
                <div style="font-family: Arial, sans-serif; max-width: 200px; text-align: center;">
                    <img src="https://cdn-icons-png.flaticon.com/512/4151/4151152.png" alt="Heladera"
                        style="width: 50px; height: 50px; object-fit: cover; border-radius: 6px;"/>
                    <h3 style="margin: 10px 0;">Heladera ${heladera.idHeladera}</h3>
                    <p style="margin: 5px 0; color: #555;">
                        <img src="${stateIcons[heladera.estado]}" alt="${heladera.estado}"
                            style="width: 20px; height: 20px; vertical-align: middle;"/>
                        ${heladera.estado}
                    </p>
                </div>
            `;

            infoWindow.setContent(contentString);
            infoWindow.open(map, marker);
            currentInfoWindow = infoWindow;

            // Cargar las alertas para esta heladera
            await cargarAlertas(heladera.idHeladera);

            // Mostrar la cantidad de viandas para esta heladera
            mostrarViandas(heladera);
        });
    });

    // Listener para cuando se cierra el infoWindow
    infoWindow.addListener('closeclick', () => {
        currentInfoWindow = null;
        limpiarTabla(); // Mostrar mensaje de tabla vacía
    });
}

// Función para limpiar la tabla y mostrar mensaje de tabla vacía
function limpiarTabla() {
    const tableBody = document.querySelector('#dataTable tbody');
    tableBody.innerHTML = ''; // Limpiar la tabla
    const row = document.createElement('tr');
    row.innerHTML = `<td colspan="4" class="text-center">La tabla está vacía</td>`;
    tableBody.appendChild(row);
}

// Función para cargar alertas desde la API
async function cargarAlertas(idHeladera) {
    try {
        const response = await fetch(`/api/alertas/${idHeladera}`);
        const alertas = await response.json();

        const tableBody = document.querySelector('#dataTable tbody');
        tableBody.innerHTML = ''; // Limpiar la tabla antes de agregar nuevas filas

        if (alertas.length === 0) {
            const row = document.createElement('tr');
            row.innerHTML = `<td colspan="4" class="text-center">No hay alertas para esta heladera</td>`;
            tableBody.appendChild(row);
        } else {
            alertas.forEach(alerta => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${alerta.fecha}</td>
                    <td>${alerta.hora}</td>
                    <td>${alerta.tipoAlerta}</td>
                    <td>${alerta.estadoAlerta}</td>
                `;
                tableBody.appendChild(row);
            });
        }
    } catch (error) {
        console.error('Error al cargar alertas:', error);
    }
}
// Función para mostrar la cantidad de viandas
function mostrarViandas(heladera) {
    document.getElementById("viandasActuales").textContent = heladera.cantidadViandasActual || 0;
    document.getElementById("viandasPendientesIngreso").textContent = heladera.cantidadViandasPendientesIngreso || 0;
    document.getElementById("viandasPendientesRetiro").textContent = heladera.cantidadViandasPendientesRetiro || 0;
}

initMap();