document.addEventListener("DOMContentLoaded", function() {
    let radioConDomicilio = document.getElementById("flexRadioDefault2");
    let radioSinDomicilio = document.getElementById("flexRadioDefault1");
    let domicilioContainer = document.getElementById("domicilio-container");
  
    // Escucha cambios en los radios
    radioConDomicilio.addEventListener("change", function() {
      if (radioConDomicilio.checked) {
        domicilioContainer.style.display = "block";  // Muestra el contenedor de domicilio
      }
    });
  
    radioSinDomicilio.addEventListener("change", function() {
      if (radioSinDomicilio.checked) {
        domicilioContainer.style.display = "none";   // Oculta el contenedor de domicilio
      }
    });
  });