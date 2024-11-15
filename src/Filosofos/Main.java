
package Filosofos;

import java.util.concurrent.Semaphore;
/**
 *
 * @author Miguel Bobillo Pérez
 */
public class Main {

    public static void main(String[] args) {
        // Número de filósofos
        final int filosofosnum = 5;

        // Se crea semaforo con el tamaño de filosofosnum
        Semaphore[] semaforoPalillo = new Semaphore[filosofosnum];
        for (int i = 0; i < filosofosnum; i++) {
            //Con el semaforo inicializado en 1, sendo binario
            //en un momento dado 1 palillo / 1 filosofo
            semaforoPalillo[i] = new Semaphore(1); 
        }

        // posición palillos para cada filósofo
        int[][] palillosFilosofo = new int[filosofosnum][2];
        for (int i = 0; i < filosofosnum; i++) {
            // El palillo izquierdo del filósofo será su mismo num/indice/i
            palillosFilosofo[i][0] = i; 
            // El palillo derecho es el siguiente i+1 añadido % filosfosnum para el ultimo palillo
            palillosFilosofo[i][1] = (i + 1) % filosofosnum; 
        }

        // Crear e iniciar hilo por cada filosofo
        Filosofo[] filosofos = new Filosofo[filosofosnum];
        for (int i = 0; i < filosofosnum; i++) {
            filosofos[i] = new Filosofo(i, semaforoPalillo, palillosFilosofo);
            filosofos[i].start();
        }
    }
}