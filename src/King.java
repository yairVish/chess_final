public class King extends Tool {
    private short[][] screenData;

    public King(short[][] screenData) {
        super(screenData);
        this.screenData = screenData;
    }

    @Override
    public int move(int to_pos_i, int to_pos_j) {
        if(!isLegal(to_pos_i,to_pos_j,getFrom_pos_i(),getFrom_pos_j())){
            return -1;
        }
        return super.move(to_pos_i, to_pos_j);
    }

    public boolean isLegal(int to_pos_i,int to_pos_j,int from_pos_i,int from_pos_j){
        if((from_pos_i>=to_pos_i+2||from_pos_i<=to_pos_i-2)){
            return false;
        }
        
        if((from_pos_j>=to_pos_j+2||from_pos_j<=to_pos_j-2)){
            return false;
        }
        return true;
    }
}