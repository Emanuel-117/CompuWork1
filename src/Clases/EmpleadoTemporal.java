/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Date;

/**
 *
 * @author user
 */

public class EmpleadoTemporal extends Empleado {
    private Date fechaFin;

    // Constructor
    public EmpleadoTemporal(int idEmpleado, String nombre, String tipo, int idDepartamento, double salario, String contraseña, Date fechaFin) {
        super(idEmpleado, nombre, tipo, idDepartamento, salario, contraseña);
        this.fechaFin = fechaFin;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return super.toString() + ", fechaFin=" + fechaFin;
    }
}
