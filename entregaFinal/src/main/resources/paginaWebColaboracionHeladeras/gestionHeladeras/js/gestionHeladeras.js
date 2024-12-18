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
  let selectedHeladera = null;

  // Sample marker locations and details
  const locations = [
    { lat: -34.659662, lng: -58.467978, name: "Heladera 1", state: "Habilitado", img: "https://cdn-icons-png.flaticon.com/512/4151/4151152.png" },
    { lat: -34.649662, lng: -58.457978, name: "Heladera 2", state: "En mantenimiento", img: "https://cdn-icons-png.flaticon.com/512/4151/4151152.png" }
  ];

  // Map of state to icon URL
  const stateIcons = {
    "Habilitado": "https://icones.pro/wp-content/uploads/2021/04/icone-cercle-rempli-vert.png",
    "En mantenimiento": "https://icones.pro/wp-content/uploads/2021/04/icone-cercle-rempli-gris.png",
    "Deshabilitado": "https://icones.pro/wp-content/uploads/2021/04/icone-cercle-rempli-rouge.png"
  };

  // Sample alerts
  const alerts = [
    { date: '2024-09-08 08:00', type: 'Alerta de temperatura', heladera: 'Heladera 1' },
    { date: '2024-09-08 09:30', type: 'Fraude', heladera: 'Heladera 1' },
    { date: '2024-09-08 10:15', type: 'Falla de conexión', heladera: 'Heladera 2' },
    { date: '2024-09-08 11:45', type: 'Alerta de temperatura', heladera: 'Heladera 2' }
  ];

  locations.forEach(location => {
    const marker = new AdvancedMarkerElement({
      map,
      position: location,
      title: location.name
    });

    marker.addListener('click', function () {
      if (currentInfoWindow) {
        currentInfoWindow.close();
      }

      const contentString = `
        <div style="font-family: Arial, sans-serif; max-width: 200px; text-align: center;">
          <img src="${location.img}" alt="Heladera" style="width: 50px; height: 50px; object-fit: cover; border-radius: 6px;"/>
          <h3 style="margin: 10px 0;">${location.name}</h3>
          <p style="margin: 5px 0; color: #555;">
            <img src="${stateIcons[location.state]}" alt="${location.state}" style="width: 20px; height: 20px; vertical-align: middle;"/>
            ${location.state}
          </p>
        </div>
      `;

      infoWindow.setContent(contentString);
      infoWindow.open(map, marker);
      currentInfoWindow = infoWindow; // Establecer la ventana actual

      // Establecer la heladera seleccionada
      selectedHeladera = location.name;

      // Habilitar el campo de selección cuando se abre una ventana de información
      document.getElementById('selectAction').disabled = false;
      document.querySelector('button[type="submit"]').disabled = false;
    });

    infoWindow.addListener('closeclick', function() {
      document.getElementById('selectAction').disabled = true;
      document.querySelector('button[type="submit"]').disabled = true;
    });
  });

  document.getElementById('selectAction').addEventListener('change', function () {
    const action = this.value;
    document.getElementById('reportSection').style.display = 'none';
    document.getElementById('alertsSection').style.display = 'none';

    if (action === 'report') {
      document.getElementById('firstName').value = 'Juan'; // Default value
      document.getElementById('lastName').value = 'Pérez'; // Default value
      document.getElementById('reportSection').style.display = 'block';
    } else if (action === 'viewAlerts') {
      const alertTitle = document.getElementById('alertTitle');
      const alertsList = document.getElementById('alertsList');
      alertsList.innerHTML = '';
      
      
      // Filtrar las alertas según la heladera seleccionada
      const filteredAlerts = alerts.filter(alert => alert.heladera === selectedHeladera);
      if (filteredAlerts.length > 0) {
        alertTitle.textContent = `Alertas de "${filteredAlerts[0].heladera}"`;
      } else {
        alertTitle.textContent = 'No hay alertas para esta heladera';
      }
      
      filteredAlerts.forEach(alert => {
        const alertItem = document.createElement('div');
        alertItem.className = 'alert-item';
        alertItem.innerHTML = `
          <p><strong>Fecha y Hora:</strong> ${alert.date}</p>
          <p><strong>Tipo:</strong> ${alert.type}</p>
        `;
        alertsList.appendChild(alertItem);
      });

      document.getElementById('alertsSection').style.display = 'block';
    }
  });

  // Inicialmente deshabilitar el campo selectAction y el botón de envío
  document.getElementById('selectAction').disabled = true;
  document.querySelector('button[type="submit"]').disabled = true;
}

initMap();


