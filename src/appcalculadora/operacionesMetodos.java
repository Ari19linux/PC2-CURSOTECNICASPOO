package appcalculadora;

public class operacionesMetodos {

    // Suma de dos enteros
    public int sumar(int a, int b) {
        return a + b;
    }

    // Suma de dos decimales
    public double sumar(double a, double b) {
        return a + b;
    }

    // Suma de tres enteros
    public int sumar(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {

        operacionesMetodos op = new operacionesMetodos();

        System.out.println("Suma de 2 enteros: " + op.sumar(10, 20));
        System.out.println("Suma de 2 decimales: " + op.sumar(5.5, 4.5));
        System.out.println("Suma de 3 enteros: " + op.sumar(10, 20, 30));
    }
}
