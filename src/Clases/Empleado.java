/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Clases.Departamento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
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

   
    
    private static String generarContraseñaAleatoria() {
        Random random = new Random();
        StringBuilder contraseña = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            contraseña.append(random.nextInt(10));
        }
        return contraseña.toString();
    }

    

    public static void agregarEmpleado(int id, String nombre, String tipo, double salario, int idDepartamento, String beneficios, String fechaFin) {
        try {
            Departamento departamentoSeleccionado = null;
            for (Departamento dept : Departamento.getListaDepartamentos()) {
                if (dept.getIdDepartamento() == idDepartamento) {
                    departamentoSeleccionado = dept;
                    break;
                }
            }

            if (departamentoSeleccionado == null) {
                throw new Exception("Departamento no encontrado. Por favor, crea el departamento primero.");
            }

            String contraseña = generarContraseñaAleatoria();
            Empleado empleado;

            if (tipo.equalsIgnoreCase("Temporal")) {
                if (fechaFin == null || fechaFin.isEmpty()) {
                    throw new Exception("La fecha de fin es requerida para empleados temporales.");
                }

                Date fechaFinParsed = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin);
                empleado = new EmpleadoTemporal(id, nombre, tipo, idDepartamento, salario, contraseña, fechaFinParsed);
            } else if (tipo.equalsIgnoreCase("Permanente")) {
                if (beneficios == null || beneficios.isEmpty()) {
                    throw new Exception("Los beneficios son requeridos para empleados permanentes.");
                }

                empleado = new EmpleadoPermanente(id, nombre, tipo, idDepartamento, salario, contraseña, beneficios);
            } else {
                throw new Exception("Tipo de empleado no válido.");
            }

            departamentoSeleccionado.agregarEmpleado(empleado);

            System.out.println("Empleado agregado con éxito: " + empleado);
            System.out.println("Nota: La contraseña generada para este empleado es: " + contraseña);
        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al agregar empleado: " + e.getMessage());
        }
    }

 

  public static boolean eliminarEmpleado(int idEmpleado) {
    try {
        // Buscar el empleado dentro de los departamentos
        for (Departamento dept : Departamento.getListaDepartamentos()) {
            for (Empleado emp : dept.getEmpleados()) {
                if (emp.getIdEmpleado() == idEmpleado) {
                    // Eliminar el empleado del departamento
                    dept.getEmpleados().remove(emp);
                    System.out.println("Empleado eliminado con éxito: " + emp);
                    return true; // Retornar éxito
                }
            }
        }
        System.out.println("Empleado no encontrado.");
        return false; // Retornar fallo
    } catch (Exception e) {
        System.out.println("Error al eliminar empleado: " + e.getMessage());
        return false; // Retornar fallo
    }
}
    public static boolean actualizarEmpleado(int idEmpleado, String nuevoNombre, String nuevoTipo, double nuevoSalario) {
    try {     
        for (Departamento dept : Departamento.getListaDepartamentos()) {
            for (Empleado emp : dept.getEmpleados()) {
                if (emp.getIdEmpleado() == idEmpleado) {
                    
                    emp.setNombre(nuevoNombre);
                    emp.setTipo(nuevoTipo);
                    emp.setSalario(nuevoSalario);

                    System.out.println("Empleado actualizado con éxito: " + emp);
                    return true;
                }
            }
        }
        System.out.println("Empleado no encontrado.");
        return false; 
    } catch (Exception e) {
        System.out.println("Error al actualizar empleado: " + e.getMessage());
        return false; 
   }
 }
}