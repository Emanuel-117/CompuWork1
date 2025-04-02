package Clases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change esta license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit esta template
 */

import CompuWorkPresentacion.ventanaPrincipal;
import CompuWorkPresentacion.iniciarSesion;
import Clases.Departamento;
import Clases.Empleado;
/**
 *
 * @author user
 */

public class CompuWork1 {

    public static void main(String[] args) {
        ventanaPrincipal ventana = new ventanaPrincipal();
        iniciarSesion sesion = new iniciarSesion(ventana, true, ventana);
        sesion.setVisible(true);

        if (sesion.isAutenticado()) {
            inicializarDatos();
            ventana.setVisible(true);
        } else {
            System.out.println("Usuario no autenticado. Finalizando el programa...");
        }
    }

    private static void inicializarDatos() {
        System.out.println("Inicializando datos del sistema...");
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
