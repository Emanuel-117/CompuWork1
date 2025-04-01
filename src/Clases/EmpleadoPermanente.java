/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author user
 */

public class EmpleadoPermanente extends Empleado {
    private String beneficios;

    // Constructor
    public EmpleadoPermanente(int idEmpleado, String nombre, String tipo, int idDepartamento, double salario, String contraseña, String beneficios) {
        super(idEmpleado, nombre, tipo, idDepartamento, salario, contraseña);
        this.beneficios = beneficios;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    @Override
    public String toString() {
        return super.toString() + ", beneficios='" + beneficios + "'";
    }
}
