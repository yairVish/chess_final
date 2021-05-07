import javax.swing.*;

public class OperateGame {
    private short[][] screenData;
    private char mark;
    private Tool tool;
    private Pawn pawn;
    private Horse horse;
    private Bishop bishop;
    private Rook rook;
    private Queen queen;
    private King king;
    private int num = 0;
    private JFrame frame;
    private boolean check_king=true,check_rook1=true,check_rook2=true;
    private boolean check_kingO=true,check_rook1O=true,check_rook2O=true;
    public OperateGame(short[][] screenData){
        this.screenData = screenData;
        tool = new Tool(screenData);
        pawn = new Pawn(screenData,num);
        horse = new Horse(screenData);
        bishop = new Bishop(screenData);
        rook = new Rook(screenData);
        queen = new Queen(screenData);
        king = new King(screenData);
    }

    public int operate(int pos_i,int pos_j,boolean my_move){
            tool.setMy_move(my_move);
            setTools(my_move);
            pawn.setNum(num);
        if(locIsEmpty(pos_i,pos_j) || moveToEat(pos_i,pos_j)){
            System.out.println("empty");
                try {
                    if (num == 4) {
                        pawn.setFrom_pos_i(tool.getFrom_pos_i());
                        pawn.setFrom_pos_j(tool.getFrom_pos_j());
                        int result;
                        if(locIsEmpty(pos_i,pos_j)) {
                            result = pawn.move(pos_i, pos_j);
                        }else{
                            result = pawn.eat(pos_i, pos_j);
                        }
                        if(result!=-1){
                            if(pos_i == 0){
                                int v = screenData[pos_i][pos_j];
                                if(v%2==0){
                                    v-=2;
                                }else{
                                    v-=1;
                                }
                                screenData[pos_i][pos_j] = (short) (screenData[pos_i][pos_j]-v);
                                screenData[pos_i][pos_j] = (short) (screenData[pos_i][pos_j]+64);
                            }
                            checkEnd(pos_i,pos_j,my_move);
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else if(num==256){
                        pawn.setFrom_pos_i(tool.getFrom_pos_i());
                        pawn.setFrom_pos_j(tool.getFrom_pos_j());
                        int result;
                        if(locIsEmpty(pos_i,pos_j)) {
                            result = pawn.move(pos_i, pos_j);
                        }else{
                            result = pawn.eat(pos_i, pos_j);
                        }
                        if(result!=-1){
                            if(pos_i == 7){
                                int v = screenData[pos_i][pos_j];
                                if(v%2==0){
                                    v-=2;
                                }else{
                                    v-=1;
                                }
                                screenData[pos_i][pos_j] = (short) (screenData[pos_i][pos_j]-v);
                                screenData[pos_i][pos_j] = (short) (screenData[pos_i][pos_j]+4096);
                            }
                            checkEnd(pos_i,pos_j,my_move);
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else if(num==8||num==512){
                        horse.setFrom_pos_i(tool.getFrom_pos_i());
                        horse.setFrom_pos_j(tool.getFrom_pos_j());
                        int result;
                        if(locIsEmpty(pos_i,pos_j)) {
                            result = horse.move(pos_i, pos_j);
                        }else{
                            result = horse.eat(pos_i, pos_j);
                        }
                        if(result!=-1){
                            checkEnd(pos_i,pos_j,my_move);
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else if(num==16||num==1024){
                        bishop.setFrom_pos_i(tool.getFrom_pos_i());
                        bishop.setFrom_pos_j(tool.getFrom_pos_j());
                        int result;
                        if(locIsEmpty(pos_i,pos_j)) {
                            result = bishop.move(pos_i, pos_j);
                        }else{
                            result = bishop.eat(pos_i, pos_j);
                        }
                        if(result!=-1){
                            checkEnd(pos_i,pos_j,my_move);
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else if(num==32||num==2048){
                        rook.setFrom_pos_i(tool.getFrom_pos_i());
                        rook.setFrom_pos_j(tool.getFrom_pos_j());
                        int result;
                        if(locIsEmpty(pos_i,pos_j)) {
                            result = rook.move(pos_i, pos_j);
                        }else{
                            result = rook.eat(pos_i, pos_j);
                        }
                        if(result!=-1){
                            if(my_move&&mark=='W'){//White
                                if(tool.getFrom_pos_i()==7&&tool.getFrom_pos_j()==7){
                                    check_rook1=false;
                                }
                                if(tool.getFrom_pos_i()==7&&tool.getFrom_pos_j()==0){
                                    check_rook2=false;
                                }
                            }
                            if(!my_move&&mark=='W'){//Black
                                if(tool.getFrom_pos_i()==0&&tool.getFrom_pos_j()==7){
                                    check_rook1O=false;
                                }
                                if(tool.getFrom_pos_i()==0&&tool.getFrom_pos_j()==0){
                                    check_rook2O=false;
                                }
                            }
                            if(my_move&&mark=='B'){//Black
                                if(tool.getFrom_pos_i()==0&&tool.getFrom_pos_j()==7){
                                    check_rook1=false;
                                }
                                if(tool.getFrom_pos_i()==0&&tool.getFrom_pos_j()==0){
                                    check_rook2=false;
                                }
                            }
                            if(!my_move&&mark=='B'){//white
                                if(tool.getFrom_pos_i()==7&&tool.getFrom_pos_j()==7){
                                    check_rook1O=false;
                                }
                                if(tool.getFrom_pos_i()==7&&tool.getFrom_pos_j()==0){
                                    check_rook2O=false;
                                }
                            }
                            checkEnd(pos_i,pos_j,my_move);
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else if(num==64||num==4096){
                        queen.setFrom_pos_i(tool.getFrom_pos_i());
                        queen.setFrom_pos_j(tool.getFrom_pos_j());
                        int result;
                        if(locIsEmpty(pos_i,pos_j)) {
                            result = queen.move(pos_i, pos_j);
                        }else{
                            result = queen.eat(pos_i, pos_j);
                        }
                        if(result!=-1){
                            checkEnd(pos_i,pos_j,my_move);
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else if(num==128||num==8192){
                        king.setFrom_pos_i(tool.getFrom_pos_i());
                        king.setFrom_pos_j(tool.getFrom_pos_j());
                        int result;
                        if(locIsEmpty(pos_i,pos_j)) {
                            result = king.move(pos_i, pos_j);
                        }else{
                            result = king.eat(pos_i, pos_j);
                        }
                        Castling castling = new Castling(screenData,mark,my_move);
                        boolean b_king = my_move ? check_king : check_kingO;
                        boolean b_rook1 = my_move ? check_rook1 : check_rook1O;
                        boolean b_rook2 = my_move ? check_rook2 : check_rook2O;
                        if(result!=-1){
                            if(my_move){
                                check_king=false;
                            }
                            if(!my_move){
                                check_kingO=false;
                            }
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }else if(castling.isCastlingS(pos_i,pos_j,tool.getFrom_pos_i(),tool.getFrom_pos_j()
                                ,b_king,b_rook1)){
                            if(my_move){
                                check_king=false;
                            }else{
                                check_kingO=false;
                            }
                            castling.doCastlingS(tool.getFrom_pos_i());
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }else if(castling.isCastlingL(pos_i,pos_j,tool.getFrom_pos_i(),tool.getFrom_pos_j()
                                ,b_king,b_rook2)){
                            if(my_move){
                                check_king=false;
                            }else{
                                check_kingO=false;
                            }
                            castling.doCastlingL(tool.getFrom_pos_i());
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else{
                        return tool.move(pos_i, pos_j);
                    }
                }catch (Exception e){return 1;}
            }else{
                System.out.println("select");
                if((screenData[pos_i][pos_j] & 4) != 0){
                    num = 4;
                }else if((screenData[pos_i][pos_j] & 8) != 0) {
                    num = 8;
                }else if((screenData[pos_i][pos_j] & 16) != 0){
                    num = 16;
                }else if((screenData[pos_i][pos_j] & 32) != 0){
                    num = 32;
                }else if((screenData[pos_i][pos_j] & 64) != 0){
                    num = 64;
                }else if((screenData[pos_i][pos_j] & 128) != 0){
                    num = 128;
                }else if((screenData[pos_i][pos_j] & 256) != 0){
                    num = 256;
                }else if((screenData[pos_i][pos_j] & 512) != 0){
                    num = 512;
                }else if((screenData[pos_i][pos_j] & 1024) != 0){
                    num = 1024;
                }else if((screenData[pos_i][pos_j] & 2048) != 0){
                    num = 2048;
                }else if((screenData[pos_i][pos_j] & 4096) != 0){
                    num = 4096;
                }else if((screenData[pos_i][pos_j] & 8192) != 0){
                    num = 8192;
                }else{
                    num = 0;
                }
                return tool.select(pos_i, pos_j);
            }
        }

    public boolean locIsEmpty(int pos_i,int pos_j) {

        if(screenData[pos_i][pos_j]==1||screenData[pos_i][pos_j]==2){
            return true;
        }
        return false;
    }

    public boolean moveToEat(int pos_i,int pos_j) {

        if(!locIsEmpty(pos_i,pos_j)
           &&tool.getFrom_pos_i()!=-1
           &&tool.getFrom_pos_j()!=-1
           &&((mark=='W'&&screenData[pos_i][pos_j]>256&&tool.isMy_move())
                ||(mark=='B'&&screenData[pos_i][pos_j]<=130&&tool.isMy_move())
                ||(mark=='B'&&screenData[pos_i][pos_j]>256&&!tool.isMy_move())
                ||(mark=='W'&&screenData[pos_i][pos_j]<=130&&!tool.isMy_move()))){
            return true;
        }
        return false;
    }
    private void checkEnd(int pos_i,int pos_j,boolean my_move){
        System.out.println("{1");
        if(new Tool(screenData).isThreatened(pos_i,pos_j,pos_i,pos_j,mark,!my_move)){
            System.out.println("{2");
            boolean check = new EndGame(screenData,mark,my_move).isCheckmate();
            if(check&&my_move){
                System.out.println("You Win");
                JOptionPane.showMessageDialog(frame, "You Win");
                System.exit(1);
            }
            if(check&&!my_move){
                System.out.println("You lose");
                JOptionPane.showMessageDialog(frame, "You lose");
                System.exit(1);
            }
        }else{
            boolean check = new EndGame(screenData,mark,my_move).isCheckmate();
            if(check||new EndGame(screenData,mark,my_move).isDraw()){
                JOptionPane.showMessageDialog(frame, "Draw");
                System.exit(1);
            }
        }
    }
    private void setTools(boolean my_move){
        pawn.setMy_move(my_move);
        horse.setMy_move(my_move);
        bishop.setMy_move(my_move);
        rook.setMy_move(my_move);
        queen.setMy_move(my_move);
        king.setMy_move(my_move);
    }
    public void setMark(char mark) {
        this.mark = mark;
        tool.setMark(mark);
        pawn.setMark(mark);
        horse.setMark(mark);
        bishop.setMark(mark);
        rook.setMark(mark);
        queen.setMark(mark);
        king.setMark(mark);
    }

    public Tool getTool() {
        return tool;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public int getNum() {
        return num;
    }

    public boolean isCheck_king() {
        return check_king;
    }

    public boolean isCheck_rook1() {
        return check_rook1;
    }

    public boolean isCheck_rook2() {
        return check_rook2;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
