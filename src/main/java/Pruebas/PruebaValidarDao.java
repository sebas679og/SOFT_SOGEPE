package Pruebas;

import com.sagmade.dao.ValidarIngreso;

public class PruebaValidarDao {

	public static void main(String[] args) {
		ValidarIngreso validar = new ValidarIngreso();
        String username = "sebasorjuelag";
        String contraseña = "sebas1234";
        String tipoUsuario = "Administrador";

        boolean esValido = validar.validarUsuario(username, contraseña, tipoUsuario);

        if (esValido) {
            System.out.println("Usuario válido");
        } else {
            System.out.println("Usuario inválido");
        }

	}

}
