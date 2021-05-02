public class Queen extends Tool {
    private short[][] screenData;

    public Queen(short[][] screenData) {
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
        if (!new Rook(screenData).isLegal(to_pos_i, to_pos_j, from_pos_i, from_pos_j) &&
                !new Bishop(screenData).isLegal(to_pos_i, to_pos_j, from_pos_i, from_pos_j)) {
            return false;
        }
        return true;
    }
}