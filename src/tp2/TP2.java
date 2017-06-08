package tp2;


/* Skeleton do trabalho TP2. */

public class TP2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Leia requestString, número de cilindros e cilindro inicial 
           do arquivo de entrada conforme o formato especificado no trabalho.
           O arquivo de entrada é passado por args. */
        
        int[] requestString = {98, 183, 37, 122, 14, 124, 65, 67};
        int numCilindros = 200;
        int initCilindro = 53;
        
        DiskScheduler fcfs = new FCFS(requestString, numCilindros, initCilindro);
        System.out.println("Número de cilindros percorridos: " + fcfs.serviceRequests());
        fcfs.printGraph("fcfs.jpg");
        
   }
    
}
