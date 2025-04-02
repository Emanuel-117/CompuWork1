/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compuwork1;

import Clases.Departamento;
import Clases.Empleado;
import CompuWorkPresentacion.iniciarSesion;
import CompuWorkPresentacion.ventanaPrincipal;

/**
 *
 * @author user
 */
public class CompuWork1 {
    public static void main(String[] args) {
        ventanaPrincipal ventana = new ventanaPrincipal();
        iniciarSesion sesion = new iniciarSesion(ventana, true, ventana);
        sesion.setVisible(true);

        if (sesion.autenticado()) {
            inicializarDatos();
            ventana.setVisible(true);
        } else {
            System.out.println("Usuario no autenticado. Finalizando el programa...");
        }
    }

    private static void inicializarDatos() {
        Departamento.inicializarDepartamentos();
        Departamento.inicializarEmpleados();
    }
}

    private static void mostrarEmpleadosPorDepartamento() {
        try {
            System.out.println("Empleados por Departamento:");
            for (Departamento dept : Departamento.getListaDepartamentos()) {
                System.out.println(dept.getNombre() + ": " + dept.contarEmpleados() + " empleados");
                for (Empleado emp : dept.getEmpleados()) {
                    System.out.println("\t" + emp);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar empleados: " + e.getMessage());
        }
    }

    

