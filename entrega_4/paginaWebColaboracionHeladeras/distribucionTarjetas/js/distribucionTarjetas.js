// Espera a que el DOM esté completamente cargado
document.addEventListener("DOMContentLoaded", function() {
    let cantidadTarjetasRepartir = document.getElementById("inputCantidadTarjetas");
    let botonRegistrar = document.querySelector('button.btn.btn-primary.custom-button[type="submit"]:last-of-type');
    let h5CantidadTarjetas = document.querySelector("h5");
    
    let tarjetasRestantes;
  
    // Actualiza el contador al cargar la página
    cantidadTarjetasRepartir.addEventListener("input", function() {
      tarjetasRestantes = parseInt(cantidadTarjetasRepartir.value, 10) || 0;
      h5CantidadTarjetas.innerText = `Cantidad de tarjetas restantes: ${tarjetasRestantes}`;
    });
  
    // Maneja el clic en "Registrar Persona" para descontar una tarjeta
    botonRegistrar.addEventListener("click", function(event) {
      event.preventDefault(); // Evita el envío del formulario por defecto
  
      if (tarjetasRestantes > 0) {
        tarjetasRestantes--;
        h5CantidadTarjetas.innerText = `Cantidad de tarjetas restantes: ${tarjetasRestantes}`;
      } else {
        alert("No quedan tarjetas por repartir.");
      }
    });
  });

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