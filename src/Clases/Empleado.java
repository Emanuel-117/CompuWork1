/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author user
 */
public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String tipo;
    private int idDepartamento;
    private double salario;
    private String contraseña;

    public Empleado(int idEmpleado, String nombre, String tipo, int idDepartamento, double salario, String contraseña) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.tipo = tipo;
        this.idDepartamento = idDepartamento;
        this.salario = salario;
        this.contraseña = contraseña;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public double getSalario() {
        return salario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Empleado:\n" +
               "ID: " + idEmpleado + "\n" +
               "Nombre: " + nombre + "\n" +
               "Tipo: " + tipo + "\n" +
               "ID Departamento: " + idDepartamento + "\n" +
               "Salario: " + salario + "\n" +
               "Contraseña: " + contraseña;
    }

    private static String generarContraseñaAleatoria() {
        Random random = new Random();
        StringBuilder contraseña = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            contraseña.append(random.nextInt(10));
        }
        return contraseña.toString();
    }

    public static void agregarEmpleado() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID del Empleado: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nombre del Empleado: ");
            String nombre = scanner.nextLine();
            System.out.print("Tipo de Empleado (Permanente/Temporal): ");
            String tipo = scanner.nextLine();
            System.out.print("Salario del Empleado: ");
            double salario = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Seleccione el Departamento:");
            for (Departamento dept : Departamento.getListaDepartamentos()) {
                System.out.println(dept.getIdDepartamento() + ". " + dept.getNombre());
            }

            System.out.print("ID del Departamento: ");
            int idDepartamento = scanner.nextInt();
            scanner.nextLine();

            Departamento departamentoSeleccionado = null;
            for (Departamento dept : Departamento.getListaDepartamentos()) {
                if (dept.getIdDepartamento() == idDepartamento) {
                    departamentoSeleccionado = dept;
                    break;
                }
            }

            if (departamentoSeleccionado == null) {
                System.out.println("Departamento no encontrado. Por favor, crea el departamento primero.");
                return;
            }

            String contraseña = generarContraseñaAleatoria();
            Empleado empleado;

            if (tipo.equalsIgnoreCase("Temporal")) {
                System.out.print("Fecha de Fin (yyyy-MM-dd): ");
                String fechaFinStr = scanner.nextLine();
                try {
                    Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinStr);
                    empleado = new EmpleadoTemporal(id, nombre, tipo, idDepartamento, salario, contraseña, fechaFin);
                } catch (ParseException e) {
                    System.out.println("Fecha inválida. No se pudo crear el empleado.");
                    return;
                }
            } else if (tipo.equalsIgnoreCase("Permanente")) {
                System.out.print("Beneficios: ");
                String beneficios = scanner.nextLine();
                empleado = new EmpleadoPermanente(id, nombre, tipo, idDepartamento, salario, contraseña, beneficios);
            } else {
                System.out.println("Tipo de empleado no válido.");
                return;
            }

            departamentoSeleccionado.agregarEmpleado(empleado);
            System.out.println("Empleado agregado con éxito: " + empleado);
            System.out.println("Nota: La contraseña generada para este empleado es: " + contraseña);
        } catch (Exception e) {
            System.out.println("Error al agregar empleado: " + e.getMessage());
        }
    }

    public static void eliminarEmpleado() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID del Empleado a eliminar: ");
            int idEmpleado = scanner.nextInt();
            scanner.nextLine();

            for (Departamento dept : Departamento.getListaDepartamentos()) {
                for (Empleado emp : dept.getEmpleados()) {
                    if (emp.getIdEmpleado() == idEmpleado) {
                        dept.eliminarEmpleado(emp);
                        System.out.println("Empleado eliminado con éxito: " + emp);
                        return;
                    }
                }
            }
            System.out.println("Empleado no encontrado.");
        } catch (Exception e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
        }
    }

    public static void actualizarEmpleado() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID del Empleado a actualizar: ");
            int idEmpleado = scanner.nextInt();
            scanner.nextLine();

            for (Departamento dept : Departamento.getListaDepartamentos()) {
                for (Empleado emp : dept.getEmpleados()) {
                    if (emp.getIdEmpleado() == idEmpleado) {
                        System.out.print("Nuevo nombre del Empleado: ");
                        emp.setNombre(scanner.nextLine());

                        System.out.print("Nuevo tipo de Empleado (Permanente/Temporal): ");
                        emp.setTipo(scanner.nextLine());

                        System.out.print("Nuevo salario del Empleado: ");
                        emp.setSalario(scanner.nextDouble());
                        scanner.nextLine();

                        System.out.println("Empleado actualizado con éxito: " + emp);
                        return;
                    }
                }
            }
            System.out.println("Empleado no encontrado.");
        } catch (Exception e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
        }
    }
}