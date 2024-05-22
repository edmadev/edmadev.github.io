/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rojoamarilloverdev1;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ana
 */
public class RojoAmarilloVerdev2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Random rd = new Random();
        Scanner teclado = new Scanner(System.in);

        final int N_NUMEROS = 4; //Números a adivinar
        double suma = 0;
        int contadorPartidas = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int partidaMax = 0;
        int partidaMin = 0;

        boolean nuevaPartida = true;
        while (nuevaPartida) {

            //1. Calcular número secretos
            int[] secreto = new int[N_NUMEROS];
            for (int i = 0; i < secreto.length; i++) {
                secreto[i] = rd.nextInt(10);
                //No se ha generado el valor hasta que no comprobemos que no 
                // no está en el array
                boolean generado = false;
                while (!generado) {
                    //Buscamos el valor, si está ya en el array
                    //volvemos a generar el aleatorio y lo comparamos de nuevo
                    boolean encontrado = false;
                    int j = 0;
                    while (j < i && !encontrado) {
                        if (secreto[i] == secreto[j]) {
                            encontrado = true;
                        } else {
                            j++;
                        }
                    }
                    if (encontrado) {
                        secreto[i] = rd.nextInt(10);
                    } else {
                        generado = true;
                    }
                }
            }
            for (int i = 0; i < secreto.length - 1; i++) {
                System.out.print(secreto[i] + " ");
            }
            System.out.println(secreto[secreto.length - 1]);

            //Bucle para adivinal los números
            boolean adivinadosNumeros = false;
            int nIntentos = 0;

            while (!adivinadosNumeros) {

                //2. Lectura de los números
                int[] n = new int[N_NUMEROS];
                System.out.println("Introduce los numeros: ");
                for (int i = 0; i < n.length; i++) {
                    n[i] = teclado.nextInt();
                    while (n[i] < 0 || n[i] > 9) {
                        System.out.println("Error. El número deben estar entre "
                                + "0 y 9");
                        System.out.println("Introduce un nuevo número: ");
                        n[i] = teclado.nextInt();
                    }
                }
                //Comprobaciones para obtener los colores
                for (int i = 0; i < n.length; i++) {
                    if (n[i] == secreto[i]) {
                        System.out.print(" Verde ");
                    } else {
                        //Buscamos el número en el array (búsqueda lineal). 
                        //Si está el valor es Amarillo y si no está es Rojo
                        boolean encontrado = false;
                        int j = 0;
                        while (j < secreto.length && !encontrado) {
                            if (n[i] == secreto[j]) {
                                encontrado = true;
                            } else {
                                j++;
                            }
                        }
                        if (encontrado) {
                            System.out.print(" Amarillo ");
                        } else {
                            System.out.print(" Rojo ");
                        }
                    }
                }
                System.out.println();

                nIntentos++;

                //Actualizamos la condición del bucle
                adivinadosNumeros = true;
                for (int i = 0; i < N_NUMEROS; i++) {
                    if (n[i] != secreto[i]) {
                        adivinadosNumeros = false;
                    }
                }
            }
            System.out.println("Enhorabuena. Has acertado los números en "
                    + nIntentos + " intentos");

            contadorPartidas++;
            suma = suma + nIntentos;

            //Calcular máximo y mínimo
            if (nIntentos > max) {
                max = nIntentos;
                partidaMax = contadorPartidas;
            }
            if (nIntentos < min) {
                min = nIntentos;
                partidaMin = contadorPartidas;
            }

            System.out.println("¿Desea jugar de nuevo [1-SÍ|2-NO]");
            nuevaPartida = (teclado.nextInt() == 1);
        }

        //Estadísticas
        //Media del número de intentos
        System.out.println("La media de intentos ha sido " + (double) suma
                / contadorPartidas);

        //El máximo número de intentos para adivinar el número ha sido
        System.out.println("El máximo número de intentos es: " + max + " en la "
                + "partida " + partidaMax);

        //El mínimo número de intentos para adivinar el número ha sido
        System.out.println("El mínimo número de intentos es: " + min + " en la "
                + "partida " + partidaMin);

    }

}
