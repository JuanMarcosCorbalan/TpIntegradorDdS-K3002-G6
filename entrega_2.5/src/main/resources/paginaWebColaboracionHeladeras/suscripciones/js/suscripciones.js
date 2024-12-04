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

  const locations = [
    { lat: -34.659662, lng: -58.467978, name: "Heladera 1", state: "Habilitado", img: "https://cdn-icons-png.flaticon.com/512/4151/4151152.png" },
    { lat: -34.649662, lng: -58.457978, name: "Heladera 2", state: "En mantenimiento", img: "https://cdn-icons-png.flaticon.com/512/4151/4151152.png" }
  ];

  const stateIcons = {
    "Habilitado": "https://icones.pro/wp-content/uploads/2021/04/icone-cercle-rempli-vert.png",
    "En mantenimiento": "https://icones.pro/wp-content/uploads/2021/04/icone-cercle-rempli-gris.png",
    "Deshabilitado": "https://icones.pro/wp-content/uploads/2021/04/icone-cercle-rempli-rouge.png"
  };

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
      currentInfoWindow = infoWindow;

      selectedHeladera = location.name;

      document.getElementById('selectAction').disabled = false;
    });

    infoWindow.addListener('closeclick', function () {
      document.getElementById('selectAction').disabled = true;
      document.getElementById('notificationInputGroup').style.display = 'none';
    });
  });

  document.getElementById('selectAction').addEventListener('change', function () {
    const action = this.value;
    const notificationGroup = document.getElementById('notificationInputGroup');

    if (action === 'quedanNViandas' || action === 'faltanNViandas') {
      notificationGroup.style.display = 'block';
    } else {
      notificationGroup.style.display = 'none';
    }
  });

  document.getElementById('viandaThreshold').addEventListener('change', function () {
    const threshold = this.value;
    if (threshold > 0 && selectedHeladera) {
      console.log(`Configuración guardada para ${selectedHeladera}: ${threshold} viandas.`);
    }
  });

  document.querySelector('button[type="submit"]').addEventListener('click', function (event) {
    event.preventDefault();

    const action = document.getElementById('selectAction').value;
    const threshold = document.getElementById('viandaThreshold').value;

    if (action && selectedHeladera) {
      let message = `Acción seleccionada: ${action} para la heladera ${selectedHeladera}.`;
      if (threshold) {
        message += ` Notificación configurada con un umbral de ${threshold} viandas.`;
      }
      alert(message);
    } else {
      alert('Por favor, seleccione una heladera y una acción.');
    }
  });

  document.getElementById('selectAction').disabled = true;
  document.getElementById('notificationInputGroup').style.display = 'none';
}

initMap();
