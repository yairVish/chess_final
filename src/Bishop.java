public class Bishop extends Tool{
    private short[][] screenData;
    public Bishop(short[][] screenData) {
        super(screenData);
        this.screenData = screenData;
    }
    public Bishop(short[][] screenData,char mark,boolean my_move) {
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
        int d = 0;
        if(from_pos_i==to_pos_i||from_pos_j==to_pos_j){
            return false;
        }
        if(to_pos_i-from_pos_i!=to_pos_j-from_pos_j&&to_pos_i-from_pos_i!=(to_pos_j-from_pos_j)*-1){
            return false;
        }
        if(from_pos_i>to_pos_i&&from_pos_j>to_pos_j){
            for(int i=from_pos_i-1;i>to_pos_i;i--){
                for(int j=from_pos_j-1;j>to_pos_j;j--){
                    if(d==1&&i>0){
                        i--;
                        d=0;
                    }
                    d++;
                    if(screenData[i][j]>2){
                        return false;
                    }
                }
            }
        }

        if(from_pos_i<to_pos_i&&from_pos_j<to_pos_j){
            for(int i=to_pos_i-1;i>from_pos_i;i--){
                for(int j=to_pos_j-1;j>from_pos_j;j--){
                    if(d==1&&i>0){
                        i--;
                        d=0;
                    }
                    d++;
                    if(screenData[i][j]>2){
                        return false;
                    }
                }
            }
        }
        if(from_pos_i>to_pos_i&&from_pos_j<to_pos_j){
            for(int i=from_pos_i-1;i>to_pos_i;i--){
                for(int j=from_pos_j+1;j<to_pos_j;j++){
                    if(d==1){
                        i--;
                        d=0;
                    }
                    d++;
                    if(screenData[i][j]>2){
                        return false;
                    }
                }
            }
        }
        if(from_pos_i<to_pos_i&&from_pos_j>to_pos_j){
            for(int i=from_pos_i+1;i<to_pos_i;i++){
                for(int j=from_pos_j-1;j>to_pos_j;j--){
                    if(d==1){
                        i++;
                        d=0;
                    }
                    d++;
                    if(screenData[i][j]>2){
                        return false;
                    }
                }
            }
        }

        if(isThreatened(to_pos_i,to_pos_j,from_pos_i,from_pos_j,super.getMark(),super.isMy_move())){
            return false;
        }

        return true;
    }
}
