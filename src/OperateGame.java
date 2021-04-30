public class OperateGame {
    private short[][] screenData;
    private char mark;
    private Tool tool;
    private Pawn pawn;
    private int num = 0;
    public OperateGame(short[][] screenData){
        this.screenData = screenData;
        tool = new Tool(screenData);
        pawn = new Pawn(screenData,num);
    }

    public int operate(int pos_i,int pos_j,boolean my_move){
            tool.setMy_move(my_move);
            pawn.setMy_move(my_move);
            pawn.setNum(num);
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
                    }else{
                        return tool.move(pos_i, pos_j);
                    }
                }catch (Exception e){return 1;}
            }else{
                if((screenData[pos_i][pos_j] & 4) != 0){
                    num = 4;
                }else if((screenData[pos_i][pos_j] & 256) != 0){
                    num = 256;
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
    public void setMark(char mark) {
        this.mark = mark;
        tool.setMark(mark);
        pawn.setMark(mark);
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
