/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change esta license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit esta template
 */
package compuwork1;

import Clases.Departamento;
import Clases.Empleado;
import Clases.ReporteDesempeño;
import CompuWorkPresentacion.ventanaPrincipal;
import CompuWorkPresentacion.iniciarSesion;
import java.util.Scanner;
/**
 *
 * @author user
 */

public class CompuWork1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ventanaPrincipal ventana = new ventanaPrincipal();
        iniciarSesion sesion =new iniciarSesion (ventana, true);
        sesion.setVisible(true);

        try {
            Departamento.inicializarDepartamentos();
            Departamento.inicializarEmpleados();

            String rol = autenticarUsuario(scanner);

            if (rol.equals("ADMINISTRADOR")) {
                menuAdministrador(scanner);
            } else if (rol.equals("EMPLEADO")) {
                menuEmpleado(scanner);
            } else {
                System.out.println("Rol no válido. Finalizando el programa.");
            }
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private static String autenticarUsuario(Scanner scanner) {
        String rol = "INVALIDO";

        while (rol.equals("INVALIDO")) {
            try {
                System.out.print("Ingrese su usuario: ");
                String usuario = scanner.nextLine();

                System.out.print("Ingrese su contraseña: ");
                String contraseña = scanner.nextLine();

                if (usuario.equalsIgnoreCase("Administrador") && contraseña.equals("123456")) {
                    System.out.println("¡Acceso concedido! Bienvenido, Administrador.");
                    return "ADMINISTRADOR";
                }

                for (Departamento dept : Departamento.getListaDepartamentos()) {
                    for (Empleado emp : dept.getEmpleados()) {
                        if (emp.getNombre().equals(usuario) && emp.getContraseña().equals(contraseña)) {
                            System.out.println("¡Acceso concedido! Bienvenido, Empleado.");
                            return "EMPLEADO";
                        }
                    }
                }

                System.out.println("Usuario o contraseña incorrectos. Intente nuevamente.");
            } catch (Exception e) {
                System.out.println("Error durante la autenticación: " + e.getMessage());
            }
        }

        return rol;
    }

    private static void menuAdministrador(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Menú del Administrador ---");
            System.out.println("1. Agregar Empleado");
            System.out.println("2. Agregar Departamento");
            System.out.println("3. Generar Reporte de Desempeño");
            System.out.println("4. Mostrar Empleados por Departamento");
            System.out.println("5. Eliminar Empleado");
            System.out.println("6. Actualizar Empleado");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        Empleado.agregarEmpleado();
                        break;
                    case 2:
                        Departamento.agregarDepartamento();
                        break;
                    case 3:
                        ReporteDesempeño.mostrarReporte();
                        break;
                    case 4:
                        mostrarEmpleadosPorDepartamento();
                        break;
                    case 5:
                        Empleado.eliminarEmpleado();
                        break;
                    case 6:
                        Empleado.actualizarEmpleado();
                        break;
                    case 7:
                        System.out.println("Saliendo del sistema...");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error al procesar la opción: " + e.getMessage());
                scanner.nextLine(); // Limpiar el buffer en caso de error
            }
        }
    }

    private static void menuEmpleado(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Menú del Empleado ---");
            System.out.println("1. Ver mi Desempeño");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese su ID de Empleado para ver su desempeño: ");
                        int idEmpleado = scanner.nextInt();
                        scanner.nextLine();
                        ReporteDesempeño.verDesempeño(idEmpleado);
                        break;
                    case 2:
                        System.out.println("Saliendo del sistema...");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error al procesar la opción: " + e.getMessage());
                scanner.nextLine(); // Limpiar el buffer en caso de error
            }
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
}