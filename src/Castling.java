
public class Castling {
     private short[][] screenData;
     private char mark;
     private boolean my_move;
     public Castling(short[][] screenData,char mark,boolean my_move){
     	this.screenData=screenData;
     	this.mark = mark;
     	this.my_move = my_move;
	 }
	 public boolean isCastlingS(int to_pos_i,int to_pos_j,int from_pos_i,int from_pos_j,boolean king,boolean rook){
         if(king&&rook&&from_pos_j==to_pos_j-2&&from_pos_i==to_pos_i
         &&new OperateGame(screenData).locIsEmpty(to_pos_i,to_pos_j)
         &&new OperateGame(screenData).locIsEmpty(to_pos_i,to_pos_j-1)
         &&!new Tool(screenData).isThreatened(to_pos_i,to_pos_j,from_pos_i,from_pos_j,mark,my_move)
         &&!new Tool(screenData).isThreatened(from_pos_i,from_pos_j,from_pos_i,from_pos_j,mark,my_move)
         &&!new Tool(screenData).isThreatened(to_pos_i,to_pos_j-1,from_pos_i,from_pos_j,mark,my_move)){
             return true;
         }else{
             return false;
         }
     }
    public boolean isCastlingL(int to_pos_i,int to_pos_j,int from_pos_i,int from_pos_j,boolean king,boolean rook){
        if(king&&rook&&from_pos_j==to_pos_j+2&&from_pos_i==to_pos_i
                &&new OperateGame(screenData).locIsEmpty(to_pos_i,to_pos_j)
                &&new OperateGame(screenData).locIsEmpty(to_pos_i,to_pos_j+1)
                &&new OperateGame(screenData).locIsEmpty(to_pos_i,to_pos_j-1)
                &&!new Tool(screenData).isThreatened(to_pos_i,to_pos_j,from_pos_i,from_pos_j,mark,my_move)
                &&!new Tool(screenData).isThreatened(from_pos_i,from_pos_j,from_pos_i,from_pos_j,mark,my_move)
                &&!new Tool(screenData).isThreatened(to_pos_i,to_pos_j+1,from_pos_i,from_pos_j,mark,my_move)
                &&!new Tool(screenData).isThreatened(to_pos_i,to_pos_j-1,from_pos_i,from_pos_j,mark,my_move)){
            return true;
        }else{
            return false;
        }
    }

     public void doCastlingS(int pos_i){
         int value_rook = 32;
         int value_king = 128;
         if(pos_i==0){
             value_rook = 2048;
             value_king = 8192;
         }
        if(my_move){
            screenData[pos_i][4] = (short) (screenData[pos_i][4] - 16384);
        }
        screenData[pos_i][4] = (short) (screenData[pos_i][4] - value_king);
        screenData[pos_i][6] = (short) (screenData[pos_i][6] + value_king);
        screenData[pos_i][7] = (short) (screenData[pos_i][7] - value_rook);
        screenData[pos_i][5] = (short) (screenData[pos_i][5] + value_rook);

    }
    public void doCastlingL(int pos_i){
        int value_rook = 32;
        int value_king = 128;
        if(pos_i==0){
            value_rook = 2048;
            value_king = 8192;
        }
        if(my_move){
            screenData[pos_i][4] = (short) (screenData[pos_i][4] - 16384);
        }
        screenData[pos_i][4] = (short) (screenData[pos_i][4] - value_king);
        screenData[pos_i][2] = (short) (screenData[pos_i][2] + value_king);
        screenData[pos_i][0] = (short) (screenData[pos_i][0] - value_rook);
        screenData[pos_i][3] = (short) (screenData[pos_i][3] + value_rook);

    }
}
