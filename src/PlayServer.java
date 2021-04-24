import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
class PlayServer {
    private Player Player;
    public  class Player extends Thread {
        private Socket socket;
        private Player other;
        private int move;
        private Scanner input;
        private char mark;
        PrintWriter output;
        public Player(Socket socket, char mark) {
            this.mark=mark;
            this.socket=socket;
        }

        @Override
        public void run() {
            try {
                input = new Scanner(socket.getInputStream());
                output = new PrintWriter(socket.getOutputStream(), true);
                output.println("WELCOME " + mark);

                if(mark=='W') {
                    System.out.println("this: "+this);
                    Player = this;
                    output.println("REG ");
                }else {
                    System.out.println("Player: "+Player);
                    other = Player;
                    other.other = this;
                    System.out.println("curr: "+Player);
                    System.out.println("oppo: "+other);
                    System.out.println("this2: "+this);
                    output.println("OPP ");
                    other.output.println("START ");
                    Player.output.println("START ");
                }
                processCommands();
            } catch (IOException e){
                e.printStackTrace();
            }finally{
                if(other!=null){
                    other.output.println("OTHER_PLAYER_LEFT");

                }
                if(other!=null){
                    Player.output.println("OTHER_PLAYER_LEFT");

                }
                input.close();
                output.close();
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        private boolean illegal(Player player) {
            System.out.println("opponent2: "+other);
            System.out.println("currentPlayer2: "+Player);
            System.out.println("this: "+this);
            System.out.println("Player != player: "+(Player != player));
            if (Player != player){
                return false;
            }else{
                return true;
            }

        }

        private void processCommands() {
            while (input.hasNextLine()) {
                try {
                    String command = input.nextLine();
                    if (command.startsWith("QUIT")) {
                        return;
                    } else

                    if (command.startsWith("MOVE")&&illegal(this)) {
                        int move=Integer.parseInt(command.substring(7));

                        if(move==0){
                            Player=Player.other;
                        }
                        System.out.println("opponent: "+other);
                        System.out.println("currentPlayer: "+Player);
                        processMoveCommand(Integer.parseInt(command.substring(5,6)),Integer.parseInt(command.substring(6,7)));

                    }else{
                        output.println("ONE ");
                    }
                } catch (IllegalStateException e) {
                    output.println("MESSAGE " + e.getMessage());
                }
            }

        }

        private void processMoveCommand(int location_x,int location_y) {
            try {
                //if(currentPlayer==this){
                output.println("VALID_MOVE " + location_x+location_y);
                other.output.println("OPPONENT_MOVED " + location_x+location_y);
                //}
                //if(opponent==this){
                //opponent.output.println("VALID_MOVE " + location_x+location_y);
                //currentPlayer.output.println("OPPONENT_MOVED " + location_x+location_y);
                //}
            }catch (IllegalStateException e) {
                output.println("MESSAGE " + e.getMessage());
            }
        }
    }
}