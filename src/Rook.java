public class Rook extends Tool{
    private short[][] screenData;
    public Rook(short[][] screenData) {
        super(screenData);
        this.screenData = screenData;
    }

    public Rook(short[][] screenData,char mark,boolean my_move) {
        super(screenData);
        this.screenData = screenData;
        super.setMark(mark);
        super.setMy_move(my_move);
    }
    @Override
    public int move(int to_pos_i,int to_pos_j){
        if(!isLegal(to_pos_i,to_pos_j,getFrom_pos_i(),getFrom_pos_j())){
            return -1;
        }
        return super.move(to_pos_i,to_pos_j);
    }

    public boolean isLegal(int to_pos_i,int to_pos_j,int from_pos_i,int from_pos_j){
        if(to_pos_i==from_pos_i&&to_pos_j==from_pos_j){
            return false;
        }
        if(from_pos_i!=to_pos_i&&from_pos_j!=to_pos_j){
            return false;
        }

        if(from_pos_i>to_pos_i){
            for(int i=to_pos_i+1;i<from_pos_i;i++){
                if(screenData[i][from_pos_j]>2){
                    return false;
                }
            }
        }else
        if(from_pos_j>to_pos_j){
            for(int i=to_pos_j+1;i<from_pos_j;i++){
                if(screenData[to_pos_i][i]>2){
                    return false;
                }
            }
        }else
        if(from_pos_i<to_pos_i){
            for(int i=from_pos_i+1;i<to_pos_i;i++){
                if(screenData[i][from_pos_j]>2){
                    return false;
                }
            }
        }else
        if(from_pos_j<to_pos_j){
            for(int i=from_pos_j+1;i<to_pos_j;i++){
                if(screenData[to_pos_i][i]>2){
                    return false;
                }
            }
        }

        if(isThreatened(to_pos_i,to_pos_j,from_pos_i,from_pos_j,super.getMark(),super.isMy_move())){
            return false;
        }

        return true;
    }
}
