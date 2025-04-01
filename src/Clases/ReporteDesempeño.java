/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change esta license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit esta template
 */
package Clases;
/**
 *
 * @author user
 */
import java.util.Scanner;

public class ReporteDesempeño {
    private Empleado empleado;
    private String rendimiento;

    public ReporteDesempeño(Empleado empleado, String rendimiento) {
        this.empleado = empleado;
        this.rendimiento = rendimiento;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(String rendimiento) {
        this.rendimiento = rendimiento;
    }

    public String generarReporte() {
        return "Empleado ID: " + empleado.getIdEmpleado() + "\n" +
               "Nombre: " + empleado.getNombre() + "\n" +
               "Rendimiento: " + rendimiento;
    }

    @Override
    public String toString() {
        return generarReporte();
    }

    public static void mostrarReporte() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese el ID del Empleado: ");
            int idEmpleado = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            System.out.print("Ingrese el rendimiento del empleado: ");
            String rendimiento = scanner.nextLine();

            Empleado empleado = buscarEmpleado(idEmpleado);

            if (empleado != null) {
                ReporteDesempeño reporte = new ReporteDesempeño(empleado, rendimiento);
                System.out.println("Reporte de desempeño generado:");
                System.out.println(reporte);
            } else {
                System.out.println("Empleado no encontrado. Verifique el ID.");
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar el reporte: " + e.getMessage());
        }
    }

    public static void verDesempeño(int idEmpleado) {
        try {
            Empleado empleado = buscarEmpleado(idEmpleado);

            if (empleado != null) {
                String rendimiento = "Rendimiento satisfactorio"; 
                System.out.println("Reporte de Desempeño:");
                System.out.println("Empleado ID: " + empleado.getIdEmpleado());
                System.out.println("Nombre: " + empleado.getNombre());
                System.out.println("Rendimiento: " + rendimiento);
            } else {
                System.out.println("Empleado no encontrado. Verifique el ID.");
            }
        } catch (Exception e) {
            System.out.println("Error al ver el desempeño del empleado: " + e.getMessage());
        }
    }

    private static Empleado buscarEmpleado(int idEmpleado) {
        try {
            for (Departamento dept : Departamento.getListaDepartamentos()) {
                for (Empleado emp : dept.getEmpleados()) {
                    if (emp.getIdEmpleado() == idEmpleado) {
                        return emp;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el empleado: " + e.getMessage());
        }
        return null;
    }
}