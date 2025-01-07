package Bingo;

import java.util.Random;

import daw.com.Pantalla;
import daw.com.Teclado;

public class JuegoBingo {

	public static void main(String[] args) {
		
		 // Inicialización de variables
		
        int[][] cartonesBingo = new int[3][5];
        boolean[][] numsMarcados = new boolean[3][5];
        int[] numsBombo = new int[90]; 
        boolean[] numsSacados = new boolean[90];
        Random random = new Random(); 
        boolean ganador = false; 

        // Bucle para generar los números del bombo
        
        for (int i = 0; i < 90; i++) {
        	numsBombo[i] = i + 1; 
        }

        // Se mezclan los números del bombo
        for (int i = numsBombo.length - 1; i > 0; i--) { 
            int j = random.nextInt(i + 1); 
            int nums = numsBombo[i]; 
            numsBombo[i] = numsBombo[j]; 
            numsBombo[j] = nums;
        }

        
        // Generar el cartón del usuario
        
        int[] usados = new int[90]; 					
        
        for (int fila = 0; fila < 3; fila++) { 			
            for (int col = 0; col < 5; col++) {
                int numero; 							
                do {
                    numero = random.nextInt(90) + 1; 	
                } while (usados[numero - 1] == 1); 		
                
                usados[numero - 1] = 1; 				
                cartonesBingo[fila][col] = numero;
                numsMarcados[fila][col] = false;
            }
        }

        
        // Mostrar al usuario su cartón
        
        System.out.println("Bienvenido al juego del BINGO");
        Pantalla.escribirSaltoLinea();
        String nombre = Teclado.leerString("Escribe tu nombre, jugador: ");
        Pantalla.escribirSaltoLinea();
        System.out.println(nombre + ", tu cartón es el siguiente: ");
        Pantalla.escribirSaltoLinea();
        
        try { 
            Thread.sleep(1*1000);
          } catch (Exception e) {
        	   System.out.println(e);
        }
        
        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 5; col++) {
                System.out.print(cartonesBingo[fila][col] + "\t");
            }
            System.out.println();
        }

        System.out.println("------------------------------------");
        Pantalla.escribirSaltoLinea();

        
        // Sacar los números y marcarlos en el cartón
        
        int indiceBombo = 0;
        while (!ganador || indiceBombo < 90) {
            int numeroSacado = numsBombo[indiceBombo];
            numsSacados[numeroSacado - 1] = true;
            indiceBombo++;

            System.out.println("El número es... ");
            Pantalla.escribirSaltoLinea();

            try { 
                Thread.sleep(2*1000);
               } catch (Exception e) {
                	System.out.println(e);
             }
            
            System.out.println("El " + numeroSacado);
            Pantalla.escribirSaltoLinea();

            try { 
            	Thread.sleep(1*1000);
               } catch (Exception e) {
            	   System.out.println(e);
             }
            
            
            // Marcar el número en el cartón
            
            for (int fila = 0; fila < 3; fila++) {
                for (int col = 0; col < 5; col++) {
                
                    if (cartonesBingo[fila][col] == numeroSacado) {
                    	numsMarcados[fila][col] = true;
                    }
                }
               
            }

            // Verificar si hay línea o bingo
            
            boolean linea = false;
            boolean bingo = true;

            for (int fila = 0; fila < 3; fila++) {
            	
                boolean filaCompleta = true;
                
                for (int col = 0; col < 5; col++) {
                    if (!numsMarcados[fila][col]) {
                        filaCompleta = false;
                        break;
                  }
            }
                
                if (filaCompleta) {
                    linea = true;
                    break;
                }
            }

            for (int fila = 0; fila < 3; fila++) {
                for (int col = 0; col < 5; col++) {
                    if (!numsMarcados[fila][col]) {
                        bingo = false;
                        break;
                    }
                }
            }
            
            if (bingo) {
                Pantalla.escribirSaltoLinea();
                System.out.println("¡Bingo! "+ nombre + " has ganado.");
                Pantalla.escribirSaltoLinea();
                ganador = true;
                break;
                
            } else if (linea) {
                System.out.println("¡Línea! " + nombre + " ha hecho línea.");
                Pantalla.escribirSaltoLinea();
            }

            
            // Imprimir el cartón actualizado 

            System.out.println("Cartón de " + nombre + ": ");
            Pantalla.escribirSaltoLinea();

            
            for (int fila = 0; fila < 3; fila++) {
                for (int col = 0; col < 5; col++) {
                    System.out.print(cartonesBingo[fila][col] + (numsMarcados[fila][col] ? "**" : " ") + "\t" );
                }
                System.out.println();
     
            }
            
            System.out.println("------------------------------------");
            Pantalla.escribirSaltoLinea();
            
            String opcion = Teclado.leerString(nombre + " ¿te atreves a seguir jugando? (SI/NO) ");

            if (opcion.equals("NO")) {
            	Pantalla.escribirSaltoLinea();
            	System.out.println(nombre + " ha finalizado el juego. Gracias por jugar.");
            	Pantalla.escribirSaltoLinea();
            	break;
            }
        	Pantalla.escribirSaltoLinea();
            
        }
		

	}

}
