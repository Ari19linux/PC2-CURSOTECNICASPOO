package App_Contador;

class Contador {
    
    // Variable estática
    static int contador = 0;
    
    // Constructor
    public Contador() {
        contador++;
    }
    
    // Método para mostrar contador
    public static void mostrarContador() {
        System.out.println("Objetos creados: " + contador);
    }
}