import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Server {
    public static void main(String[] args) throws Exception{
        int port=5433;
        try (ServerSocket listener = new ServerSocket(port)){
            System.out.println("Server is Running...");
            while (true){
                //	ExecutorService pool = Executors.newFixedThreadPool(400);
                PlayServer g=new PlayServer();
                g.new Player(listener.accept(),'W').start();
                g.new Player(listener.accept(),'B').start();

            }
        }
    }
}
