document.getElementById('registroForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita que el formulario se envíe de manera tradicional

    // Obtener los valores de los campos del formulario
    const datos = {
        nombres: document.getElementById('nombres').value,
        apellidos: document.getElementById('apellidos').value,
        usuario: document.getElementById('usuario').value,  // Nuevo campo
        contrasenia: document.getElementById('contraseña').value,  // Nuevo campo
        fechaNacimiento: document.getElementById('fecha-nacimiento').value,
        correo: document.getElementById('correo').value,
        telefono: document.getElementById('telefono').value,
        whatsapp: document.getElementById('whatsapp').value,
        calle: document.getElementById('calle').value,
        altura: document.getElementById('altura').value,
        localidad: document.getElementById('localidad').value,
        ciudad: document.getElementById('ciudad').value,
        pais: document.getElementById('pais').value
    };

    // Enviar los datos como JSON al backend
    fetch('/api/registro', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' // Decimos que estamos enviando JSON
        },
        body: JSON.stringify(datos) // Convertimos el objeto JavaScript a un JSON string
    })
        .then(response => response.json()) // Procesamos la respuesta JSON
        .then(data => {
            console.log(data); // Muestra la respuesta del backend
            alert('Registro exitoso');
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Hubo un error al registrar los datos');
        });
});
