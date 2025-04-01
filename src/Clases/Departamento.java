/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author user
 */

public class Departamento {
    private int idDepartamento;
    private String nombre;
    private List<Empleado> empleados;
    private static List<Departamento> listaDepartamentos = new ArrayList<>();

    public Departamento(int idDepartamento, String nombre) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public static List<Departamento> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void agregarEmpleado(Empleado empleado) {
        empleado.setIdDepartamento(this.idDepartamento);
        empleados.add(empleado);
        System.out.println("Empleado agregado al departamento " + nombre + " con éxito.");
    }

    public void eliminarEmpleado(Empleado empleado) {
        if (empleados.remove(empleado)) {
            empleado.setIdDepartamento(-1);
            System.out.println("Empleado eliminado del departamento " + nombre + " con éxito.");
        } else {
            System.out.println("El empleado no pertenece a este departamento.");
        }
    }

    public int contarEmpleados() {
        return empleados.size();
    }

    @Override
    public String toString() {
        return "Departamento[id=" + idDepartamento + ", nombre=" + nombre + ", empleados=" + empleados.size() + "]";
    }

    public static void inicializarDepartamentos() {
        try {
            listaDepartamentos.clear();
            listaDepartamentos.add(new Departamento(1, "Desarrollo de Software"));
            listaDepartamentos.add(new Departamento(2, "Tecnología de la Información"));
            listaDepartamentos.add(new Departamento(3, "Investigación y Desarrollo"));
            listaDepartamentos.add(new Departamento(4, "Ventas y Marketing"));
            listaDepartamentos.add(new Departamento(5, "Recursos Humanos"));
            listaDepartamentos.add(new Departamento(6, "Finanzas"));
            listaDepartamentos.add(new Departamento(7, "Atención al Cliente"));
            listaDepartamentos.add(new Departamento(8, "Operaciones"));
            listaDepartamentos.add(new Departamento(9, "Legal"));
            System.out.println("Departamentos inicializados con éxito.");
        } catch (Exception e) {
            System.out.println("Error al inicializar los departamentos: " + e.getMessage());
        }
    }

    public static void inicializarEmpleados() {
        try {
            listaDepartamentos.clear();
            Departamento software = new Departamento(1, "Desarrollo de Software");
            Departamento recursosHumanos = new Departamento(2, "Recursos Humanos");

            Empleado empleado1 = new Empleado(1028567123, "Juan Pérez", "Permanente", 1, 3000.0, "678123");
            Empleado empleado2 = new Empleado(1234567833, "María López", "Temporal", 1, 2500.0, "890456");
            Empleado empleado3 = new Empleado(1098765433, "Pedro Gómez", "Permanente", 2, 2800.0, "789789");
            Empleado empleado4 = new Empleado(1235678899, "Luisa Martínez", "Temporal", 2, 2000.0, "234012");
            Empleado empleado5 = new Empleado(1234567890, "Ana Torres", "Permanente", 1, 3100.0, "234345");

            software.agregarEmpleado(empleado1);
            software.agregarEmpleado(empleado2);
            software.agregarEmpleado(empleado5);
            recursosHumanos.agregarEmpleado(empleado3);
            recursosHumanos.agregarEmpleado(empleado4);

            listaDepartamentos.add(software);
            listaDepartamentos.add(recursosHumanos);
        } catch (Exception e) {
            System.out.println("Error al inicializar empleados: " + e.getMessage());
        }
    }

    public static void agregarDepartamento() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("ID del Departamento (positivo): ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (id <= 0) {
                System.out.println("El ID del departamento debe ser un número positivo.");
                return;
            }

            System.out.print("Nombre del Departamento: ");
            String nombre = scanner.nextLine();

            if (nombre.isEmpty()) {
                System.out.println("El nombre del departamento no puede estar vacío.");
                return;
            }

            listaDepartamentos.add(new Departamento(id, nombre));
            System.out.println("Departamento agregado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al agregar departamento: " + e.getMessage());
        }
    }

    public static void mostrarDepartamentos() {
        try {
            if (listaDepartamentos.isEmpty()) {
                System.out.println("No hay departamentos registrados.");
                return;
            }
            System.out.println("Lista de Departamentos:");
            for (Departamento dept : listaDepartamentos) {
                System.out.println(dept);
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar departamentos: " + e.getMessage());
        }
    }
}