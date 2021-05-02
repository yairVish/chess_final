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
        if(locIsEmpty(pos_i,pos_j)){
                try {
                    if (num == 4) {
                        pawn.setFrom_pos_i(tool.getFrom_pos_i());
                        pawn.setFrom_pos_j(tool.getFrom_pos_j());
                        int result = pawn.move(pos_i, pos_j);
                        if(result!=-1){
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else if(num==256){
                        pawn.setFrom_pos_i(tool.getFrom_pos_i());
                        pawn.setFrom_pos_j(tool.getFrom_pos_j());
                        int result = pawn.move(pos_i, pos_j);
                        if(result!=-1){
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else if(num==8||num==512){
                        horse.setFrom_pos_i(tool.getFrom_pos_i());
                        horse.setFrom_pos_j(tool.getFrom_pos_j());
                        int result = horse.move(pos_i, pos_j);
                        if(result!=-1){
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else if(num==16||num==1024){
                        bishop.setFrom_pos_i(tool.getFrom_pos_i());
                        bishop.setFrom_pos_j(tool.getFrom_pos_j());
                        int result = bishop.move(pos_i, pos_j);
                        if(result!=-1){
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else if(num==32||num==2048){
                        rook.setFrom_pos_i(tool.getFrom_pos_i());
                        rook.setFrom_pos_j(tool.getFrom_pos_j());
                        int result = rook.move(pos_i, pos_j);
                        if(result!=-1){
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else if(num==64||num==4096){
                        queen.setFrom_pos_i(tool.getFrom_pos_i());
                        queen.setFrom_pos_j(tool.getFrom_pos_j());
                        int result = queen.move(pos_i, pos_j);
                        if(result!=-1){
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else if(num==128||num==8192){
                        king.setFrom_pos_i(tool.getFrom_pos_i());
                        king.setFrom_pos_j(tool.getFrom_pos_j());
                        int result = king.move(pos_i, pos_j);
                        if(result!=-1){
                            tool.setFrom_pos_i(-1);
                            tool.setFrom_pos_j(-1);
                        }
                        return result;
                    }else{
                        return tool.move(pos_i, pos_j);
                    }
                }catch (Exception e){return 1;}
            }else{
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
    private void setTools(boolean my_move){
        pawn.setMy_move(my_move);
        pawn.setNum(num);
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
}
