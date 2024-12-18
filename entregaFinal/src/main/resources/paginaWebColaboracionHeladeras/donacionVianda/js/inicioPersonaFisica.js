document.getElementById("enviar").addEventListener("click", function(){
    var usuario = document.getElementById("usuario").value;
    var contrasenia = document.getElementById("contrasenia").value;
    var errorUsuario = false;
    var errorContrasenia = false;
    
    errorUsuario = validar_usuario(usuario);
    errorContrasenia = validar_contrasenia(contrasenia);
    
    
    if (errorUsuario == true || errorContrasenia ==true) {
        document.getElementById("datos-invalidos").style.visibility = "visible";
        document.getElementById("usuario").style.border = "2px solid red";
        document.getElementById("contrasenia").style.border = "2px solid red";
    }else{
        //console.log(nombre + " " + apellido);
        document.getElementById("usuario").style.border = "";
        document.getElementById("contrasenia").style.border = "";
        document.getElementById("datos-invalidos").style.visibility = "hidden";
        alert("Registro exitoso!");
    }

});

function validar_usuario(usuario)
{
    if (usuario == "" || usuario == "admin") {
        return true;
    } 
    return false;
}

function validar_contrasenia(contrasenia)
{
    if (contrasenia == "" || contrasenia == "1234" || contrasenia == "contrasenia") {
       return true;
    }
    return false;
}



