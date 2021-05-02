import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Main extends JPanel implements ActionListener {
    private final static short levelData[][] = {
            {2049, 514,1025,4098,8193,1026, 513,2050},
            { 258, 257, 258, 257, 258, 257, 258, 257},
            {   1,   2,   1,   2,   1,   2,   1,   2},
            {   2,   1,   2,   1,   2,   1,   2,   1},
            {   1,   2,   1,   2,   1,   2,   1,   2},
            {   2,   1,   2,   1,   2,   1,   2,   1},
            {   5,   6,   5,   6,   5,   6,   5,   6},
            {  34,   9,  18,  65, 130,  17,  10,  33},
    };
    private final static int N_BLOCKS_X = levelData.length;
    private final static int N_BLOCKS_Y = levelData[0].length;
    private final static int PLAYER_SPEED=5;
    private final static int VAL_ENEMY=0;
    private final static int VAL_FIRE=10;
    private final static int LIFE=3;
    private final static int PORT=5433;
    private final static int BLOCK_SIZE = 45;
    private static final int SCREEN_SIZE_X= N_BLOCKS_X * BLOCK_SIZE;
    private static final int SCREEN_SIZE_Y= N_BLOCKS_Y * BLOCK_SIZE;
    private static final int SCREEN_SIZE = 300;
    private short[][] screenData;
    private Timer timer;
    private Dimension d;
    private final Font smallFont = new Font("Helvetica", Font.BOLD, 14);
    private Image ii;
    private Image king;
    private Image queen;
    private Image rook;
    private Image bishop;
    private Image horse;
    private Image pawn;
    private Image king_b;
    private Image quin_b;
    private Image rook_b;
    private Image bishop_b;
    private Image horse_b;
    private Image pawn_b;
    private Image brown;
    private Image white;
    private Image green;
    private Image glass;
    private OperateGame operateGame;
    private final Color dotColor = new Color(192, 192, 0);
    private Color mazeColor;
    private boolean inGame = true;
    private boolean ib11 = false;
    private boolean b1 = true;
    private boolean b2 = true;
    private boolean ag = true;
    private boolean ag2 = false;
    private boolean send = false;
    private boolean client;
    private boolean turn=true;
    private boolean turn2=true;
    private boolean chek_king=true;
    private int num;
    private int move = 0;
    private int turn_i = 1;
    private int pos_ir2 = 0;
    public  static int pos_jr2 =0;
    private Socket socket;
    private int k=0;
    private int n=0;
    private int count=0;
    private  int pos_ir;
    private  int pos_jr;
    private int loc;
    private int loc_y;
    private int loc2;
    private int loc_y2;
    private int mouse_x;
    private int mouse_y;
    private char mark;
    private int wa=0;
    private boolean ib12=false;
    private boolean bw;
    private boolean e1;
    private boolean chek_rook=true;
    private boolean chek_rook2=true;
    private int pos_irb;
    private int pos_jrb;
    private int moveb=-1;
    private boolean ib12b=false;
    private boolean ib11b=false;
    private boolean e=true;
    private boolean chek_w=true;
    private boolean chek_b=true;
    private boolean chek_b1=true;
    private int ir=0;
    private int jr=0;
    private int []arr_ir=new int [8];
    private int []arr_jr=new int [8];
    private int num2;
    private int glass_x=50;
    private boolean tr2=false;
    private boolean tr=false;
    private boolean horse2=false;
    private boolean horse_b2=false;
    private boolean separate=true;
    private boolean approval;
    private boolean go=true;
    private Scanner in;
    private PrintWriter out;
    private boolean clear=true;
    private boolean break1=true;
    private boolean legal=true;
    private boolean think;
    private boolean chek_rook_b=true;
    private boolean chek_king_b=true;
    private boolean chek_rook_b2=true;
    JFrame frame;
    public Main(String serverAddress,JFrame frame) throws Exception{
        socket = new Socket(serverAddress, PORT);
        this.frame=frame;
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
        initVariables();
        initBoard();
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouse_x=e.getX();
                mouse_y=e.getY();
                mouse_x=mouse_x/BLOCK_SIZE;
                mouse_y=mouse_y/BLOCK_SIZE;

                if(!b2){
                    if(mouse_y==7){
                        mouse_y=0;
                    }else
                    if(mouse_y==6){
                        mouse_y=1;
                    }else
                    if(mouse_y==5){
                        mouse_y=2;
                    }else
                    if(mouse_y==4){
                        mouse_y=3;
                    }else
                    if(mouse_y==3){
                        mouse_y=4;
                    }else
                    if(mouse_y==2){
                        mouse_y=5;
                    }else
                    if(mouse_y==1){
                        mouse_y=6;
                    }else
                    if(mouse_y==0){
                        mouse_y=7;
                    }
                }

                send=true;
            }
        });
    }
    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        drawImage();
    }

    private void initVariables() {

        screenData = new short[N_BLOCKS_X * N_BLOCKS_X][N_BLOCKS_Y * N_BLOCKS_Y];
        mazeColor = new Color(5, 100, 5);
        d = new Dimension(400, 400);
        for(int i=0;i<VAL_ENEMY;i++){
        }
        timer = new Timer(40, this);
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        initGame();
    }
    private void showIntroScreen(Graphics2D g2d){
        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);
        String s = "Press 's' to start.";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);
        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (SCREEN_SIZE - metr.stringWidth(s)) / 2, SCREEN_SIZE / 2);
    }
    private void playGame(Graphics2D g2d) {
        //System.out.println("Game...");
        if(turn){
            System.out.println("ILEEGAL");
            out.println("ILEEGAL");
            turn=false;
        }

        if(send){
            int pr_i = operateGame.getTool().getFrom_pos_i();
            int pr_j = operateGame.getTool().getFrom_pos_j();

            if(operateGame.locIsEmpty(mouse_y,mouse_x)&&pr_i!=-1&&pr_j!=-1){
                if ((screenData[pr_i][pr_j] & 4) != 0) {
                    turn_i = new Pawn(screenData, 4).isLegal(mouse_y, mouse_x, pr_i, pr_j) ? 0 : 1;
                }else if ((screenData[pr_i][pr_j] & 256) != 0){
                    turn_i = new Pawn(screenData,256).isLegalB(mouse_y,mouse_x,pr_i,pr_j) ? 0 : 1;
                }else if ((screenData[pr_i][pr_j] & 8) != 0 || (screenData[pr_i][pr_j] & 512) != 0){
                    turn_i = new Horse(screenData).isLegal(mouse_y,mouse_x,pr_i,pr_j) ? 0 : 1;
                }else if ((screenData[pr_i][pr_j] & 16) != 0 || (screenData[pr_i][pr_j] & 1024) != 0){
                    turn_i = new Bishop(screenData).isLegal(mouse_y,mouse_x,pr_i,pr_j) ? 0 : 1;
                }else if ((screenData[pr_i][pr_j] & 32) != 0 || (screenData[pr_i][pr_j] & 2048) != 0){
                    turn_i = new Rook(screenData).isLegal(mouse_y,mouse_x,pr_i,pr_j) ? 0 : 1;
                }else if ((screenData[pr_i][pr_j] & 64) != 0 || (screenData[pr_i][pr_j] & 4096) != 0){
                    turn_i = new Queen(screenData).isLegal(mouse_y,mouse_x,pr_i,pr_j) ? 0 : 1;
                }else if ((screenData[pr_i][pr_j] & 128) != 0 || (screenData[pr_i][pr_j] & 8192) != 0){
                    turn_i = new King(screenData).isLegal(mouse_y,mouse_x,pr_i,pr_j) ? 0 : 1;
                }else{
                    turn_i = 0;
                }
            }else{
                turn_i = 1;
            }
            //Think a=new Think();class to fix
            System.out.println("t: "+turn_i);
            out.println("MOVE " + mouse_x+mouse_y+turn_i);
            send=false;
        }

    }
    private void glass(Graphics2D g2d){
        g2d.drawImage(glass,glass_x+move,160, this);
    }
    public void play(){
        try {
            String response = in.nextLine();
            System.out.println(response);
            char mark = response.charAt(8);
            int i1=0;
            char opponentMark = mark == 'W' ? 'B' : 'W';
            frame.setTitle("Chess: Player " + mark);
            operateGame.setMark(mark);
            while (in.hasNextLine()){
                response = in.nextLine();
                if (response.startsWith("VALID_MOVE")){
                    loc =Integer.parseInt(response.substring(12,13));
                    loc_y =Integer.parseInt(response.substring(11,12));
                    //Move_white a=new Move_white(true);class to fix
                    operateGame.operate(loc,loc_y,true);
                    System.out.println("pos1: "+move);
                } else if (response.startsWith("OPPONENT_MOVED")) {
                    ib11=false;
                    try{
                        loc =Integer.parseInt(response.substring(16,17));
                        loc_y =Integer.parseInt(response.substring(15,16));
                    }catch(Exception e){
                        System.err.println(loc_y);
                    }
                    for(int i=0;i<Main.N_BLOCKS_X;i++){
                        for(int j=0;j<Main.N_BLOCKS_Y;j++){
                            if((screenData[i][j]&16384)!=0){
                                screenData[i][j]=(short) (screenData[i][j]-16384);
                            }
                        }
                    }
                    operateGame.operate(loc,loc_y,false);
                    //Move_white a=new Move_white(false);class to fix
                }
                else if (response.startsWith("DEFEAT")){
                    break;
                } else if (response.startsWith("TIE")) {
                    break;
                } else if (response.startsWith("OTHER_PLAYER_LEFT")) {
                    JOptionPane.showMessageDialog(frame, "Other player left");
                    System.exit(1);
                    break;
                }
                else if (response.startsWith("REG")&&b1){
                    b1=false;
                    b2=true;
                }else if (response.startsWith("OPP")&&b2){
                    b2=false;
                    b1=true;
                }else
                if(response.startsWith("START")){
                    inGame=true;
                }
            }
            out.println("QUIT");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //socket.close();
            // frame.dispose();
        }
    }
    private void drawMaze(Graphics2D g2d) {
        // short i = 0;
        int x, y;
        int i=0;
        int j=0;
        try{
            for (y = 0; y < SCREEN_SIZE_X; y += BLOCK_SIZE) {
                for (x = 0; x < SCREEN_SIZE_Y; x += BLOCK_SIZE) {
                    g2d.setColor(mazeColor);
                    g2d.setStroke(new BasicStroke(2));
                    //System.out.println(j);
                    if ((screenData[i][j] & 1) != 0) {
                        g2d.drawImage(white, x, y, this);

                    }
                    if ((screenData[i][j] & 2) != 0) {
                        g2d.drawImage(brown, x, y, this);
                    }
                    if ((screenData[i][j] & 4) != 0) {
                        g2d.drawImage(pawn, x, y, this);
                    }
                    if ((screenData[i][j] & 8) != 0) {
                        g2d.drawImage(horse, x, y, this);
                    }
                    if ((screenData[i][j] & 16) != 0){
                        g2d.drawImage(bishop, x, y, this);
                    }
                    if ((screenData[i][j] & 32) != 0){
                        g2d.drawImage(rook, x, y, this);
                    }
                    if ((screenData[i][j] & 64) != 0) {
                        g2d.drawImage(queen, x-4, y-2, this);
                    }
                    if ((screenData[i][j] & 128) != 0) {
                        g2d.drawImage(king, x, y, this);
                    }
                    if ((screenData[i][j] & 256) != 0) {
                        g2d.drawImage(pawn_b, x, y, this);
                    }
                    if ((screenData[i][j] & 512) != 0) {
                        g2d.drawImage(horse_b, x, y, this);
                    }
                    if ((screenData[i][j] & 1024) != 0) {
                        g2d.drawImage(bishop_b, x, y, this);
                    }
                    if ((screenData[i][j] & 2048) != 0) {
                        g2d.drawImage(rook_b, x, y, this);
                    }
                    if ((screenData[i][j] & 4096) != 0){
                        g2d.drawImage(quin_b, x-5, y, this);
                    }
                    if ((screenData[i][j] & 8192) != 0) {

                        g2d.drawImage(king_b, x, y, this);
                    }
                    if ((screenData[i][j] & 16384) != 0) {
                        g2d.drawImage(green, x, y, this);
                    }


                    j++;
                }
                i++;
                j=0;
            }
        }catch(Exception e){
            System.err.println("class Board"+i);
            System.err.println("class Board"+j);
        }
    }
    private void drawMaze_O(Graphics2D g2d) {
        // short i = 0;
        int x, y;
        int i=0;
        int j=0;
        try{
            for (y = SCREEN_SIZE_X-BLOCK_SIZE; y >= 0; y -= BLOCK_SIZE) {
                for (x = 0; x < SCREEN_SIZE_Y; x += BLOCK_SIZE) {
                    g2d.setColor(mazeColor);
                    g2d.setStroke(new BasicStroke(2));
//System.out.println(j);
                    if ((screenData[i][j] & 1) != 0) {
                        g2d.drawImage(white, x, y, this);
                    }
                    if ((screenData[i][j] & 2) != 0) {
                        g2d.drawImage(brown, x, y, this);

                    }
                    if ((screenData[i][j] & 4) != 0) {
                        g2d.drawImage(pawn, x, y, this);
                    }
                    if ((screenData[i][j] & 8) != 0) {
                        g2d.drawImage(horse, x, y, this);
                    }
                    if ((screenData[i][j] & 16) != 0) {
                        g2d.drawImage(bishop, x, y, this);
                    }
                    if ((screenData[i][j] & 32) != 0) {
                        g2d.drawImage(rook, x, y, this);
                    }
                    if ((screenData[i][j] & 64) != 0) {
                        g2d.drawImage(queen, x-4, y-2, this);
                    }
                    if ((screenData[i][j] & 128) != 0) {
                        g2d.drawImage(king, x, y, this);
                    }
                    if ((screenData[i][j] & 256) != 0) {
                        g2d.drawImage(pawn_b, x, y, this);
                    }
                    if ((screenData[i][j] & 512) != 0) {
                        g2d.drawImage(horse_b, x, y, this);
                    }
                    if ((screenData[i][j] & 1024) != 0) {
                        g2d.drawImage(bishop_b, x, y, this);
                    }
                    if ((screenData[i][j] & 2048) != 0) {
                        g2d.drawImage(rook_b, x, y, this);
                    }
                    if ((screenData[i][j] & 4096) != 0) {
                        g2d.drawImage(quin_b, x-5, y, this);
                    }
                    if ((screenData[i][j] & 8192) != 0) {
                        g2d.drawImage(king_b, x, y, this);
                    }
                    if ((screenData[i][j] & 8192) != 0) {
                        g2d.drawImage(king_b, x, y, this);
                    }
                    if ((screenData[i][j] & 16384) != 0) {
                        g2d.drawImage(green, x, y, this);
                    }


                    j++;
                }
                i++;
                j=0;
            }
        }catch(Exception e){
            System.err.println("class Board"+i);
            System.err.println("class Board"+j);
        }
    }
    private void drawImage(){
        DrawImages drawImages=new DrawImages();
        king = drawImages.getKing();
        queen = drawImages.getQueen();
        rook = drawImages.getRook();
        bishop = drawImages.getBishop();
        horse = drawImages.getHorse();
        pawn = drawImages.getPawn();
        king_b = drawImages.getKing_b();
        quin_b = drawImages.getQuin_b();
        rook_b = drawImages.getRook_b();
        bishop_b = drawImages.getBishop_b();
        horse_b = drawImages.getHorse_b();
        pawn_b = drawImages.getPawn_b();
        white = drawImages.getWhite();
        brown = drawImages.getBrown();
        green = drawImages.getGreen();
        glass = drawImages.getGlass();
    }
    public void initGame() {
        for(int i=0;i<VAL_ENEMY;i++){

        }
        initLevel();
    }
    private void initLevel() {
        int i = 0;
        int i2 = -1;
        int j = 0;
        try {
            for (i = 0; i < N_BLOCKS_X; i++) {
                for (j = 0; j < N_BLOCKS_Y;j++) {
                    screenData[i][j] = levelData[i][j];
                }
            }
            operateGame=new OperateGame(screenData);
        }catch(Exception e) {
            System.err.println(i);
            System.err.println(j);
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);

        if(!b1){
            drawMaze(g2d);
        }
        if (inGame) {
            if(!b2){
                drawMaze_O(g2d);
            }
            //new Crown(g2d);class to fix
            playGame(g2d);
        }else{
            //Opening a=new Opening(g2d);class to fix
            if(go){
                move+=4;
            }else{
                move-=4;
            }
            glass(g2d);
            if(move>=200){
                go=false;
            }
            if(move<=50){
                go=true;
            }
        }

        //g2d.drawImage(ii, 5, 5, this);
        // Toolkit.getDefaultToolkit().sync();
        // g2d.dispose();
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {

                } else if (key == KeyEvent.VK_RIGHT) {


                } else if (key == KeyEvent.VK_UP) {


                } else if (key == KeyEvent.VK_DOWN) {

                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    //inGame = false;
                } else if (key == KeyEvent.VK_P) {
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                }
            } else {
                if (key == KeyEvent.VK_ENTER) {
                    inGame = true;
                    initGame();
                }

            }
            if (key == KeyEvent.VK_UP) {

            }
            if (key == KeyEvent.VK_DOWN) {
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_RIGHT){
            }
            if (key == KeyEvent.VK_LEFT){
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
