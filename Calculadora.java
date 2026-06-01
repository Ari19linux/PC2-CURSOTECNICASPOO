package Semana1;

import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("=== CASO 1: CALCULADORA BÁSICA ===");
        
        System.out.print("Ingrese el primer número: ");
        double num1 = teclado.nextDouble();
        System.out.print("Ingrese el segundo número: ");
        double num2 = teclado.nextDouble();
        
        System.out.println("\nSeleccione una operación:\n1. Suma (+)\n2. Resta (-)\n3. Multiplicación (*)\n4. División (/)");
        System.out.print("Opción: ");
        int opcion = teclado.nextInt();
        
        switch (opcion) {
            case 1: System.out.println("Resultado: " + sumar(num1, num2)); break;
            case 2: System.out.println("Resultado: " + restar(num1, num2)); break;
            case 3: System.out.println("Resultado: " + multiplicar(num1, num2)); break;
            case 4:
                if (num2 == 0) {
                    System.out.println("Error: No se puede dividir entre cero.");
                } else {
                    System.out.println("Resultado: " + dividir(num1, num2));
                }
                break;
            default: System.out.println("Opción no válida."); break;
        }
        teclado.close();
    }

    public static double sumar(double a, double b) { return a + b; }
    public static double restar(double a, double b) { return a - b; }
    public static double multiplicar(double a, double b) { return a * b; }
    public static double dividir(double a, double b) { return a / b; }
}