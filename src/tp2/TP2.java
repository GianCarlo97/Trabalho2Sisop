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
        System.out.println("Número de cilindros percorridos fcfs: " + fcfs.serviceRequests());
        fcfs.printGraph("fcfs.jpg");
        
        DiskScheduler sstf = new SSTF(requestString, numCilindros, initCilindro);
        System.out.println("Número de cilindros percorridos sstf: " + sstf.serviceRequests());
        sstf.printGraph("sstf.jpg");
        
        DiskScheduler scan = new SCAN(requestString, numCilindros, initCilindro);
        System.out.println("Número de cilindros percorridos scan: " + scan.serviceRequests());
        scan.printGraph("scan.jpg");
        
        DiskScheduler clook = new CLOOK(requestString, numCilindros, initCilindro);
        System.out.println("Número de cilindros percorridos clook: " + clook.serviceRequests());
        clook.printGraph("clook.jpg");
        
        DiskScheduler look = new LOOK(requestString, numCilindros, initCilindro);
        System.out.println("Número de cilindros percorridos look: " + look.serviceRequests());
        look.printGraph("look.jpg");
        
        DiskScheduler cscan = new CSCAN(requestString, numCilindros, initCilindro);
        System.out.println("Número de cilindros percorridos cscan: " + cscan.serviceRequests());
        cscan.printGraph("cscan.jpg");
        
   }
    
}
