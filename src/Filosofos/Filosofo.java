
package Filosofos;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Bobillo Pérez
 */
public class Filosofo extends Thread {
    //Numero de indice de filosofo
    private int numFilosofo;
    //semaforo palillos
    private Semaphore[] semaforoPalillo;
    //posición palillos para cada filosofo
    private int[][] palillosFilosofo;

    
    // Constructor filosofo
    public Filosofo(int miIndice, Semaphore[] semaforoPalillo, int[][] palillosFilosofo) {
        this.numFilosofo = miIndice;
        this.semaforoPalillo = semaforoPalillo;
        this.palillosFilosofo = palillosFilosofo;
    }

    // Método pensar
    public void pensar() {
        //Texto salida para pensar
        System.out.println("Filosofo " + numFilosofo + "pensando.");
        try {
            // Pensar por 3000 milisegundos
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Método comer
    public void comer() throws InterruptedException {
        //Texto salida para estado hambriento
        System.out.println("Filosofo " + numFilosofo + "hambriento.");
        // Intentar y tomar los dos palillos izq y dch respectivamente
        int palilloIzquierda = palillosFilosofo[numFilosofo][0];
        int palilloDerecha = palillosFilosofo[numFilosofo][1];
        semaforoPalillo[palilloIzquierda].acquire();
        semaforoPalillo[palilloDerecha].acquire();
        

        // Texto salida estado comiendo
        System.out.println("Filosofo " + numFilosofo + "comiendo.");
        try {
            //Comer por 3000 milisegundos
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Dejar los palillos
        semaforoPalillo[palilloIzquierda].release();
        semaforoPalillo[palilloDerecha].release();
        
        // Texto salida dejar palillos
        System.out.println("Filosofo " + numFilosofo + "Termina de comer , libres palillos" + palilloIzquierda + " y " + palilloDerecha);
    }
    
    //Metodo run para que comience el ciclo
    public void run() {
    while (true) {
        try {
            pensar(); 
            comer();
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
}