public class Queen extends Tool {
    private short[][] screenData;

    public Queen(short[][] screenData) {
        super(screenData);
        this.screenData = screenData;
    }

    public Queen(short[][] screenData,char mark,boolean my_move) {
        super(screenData);
        this.screenData = screenData;
        super.setMark(mark);
        super.setMy_move(my_move);
    }
    @Override
    public int move(int to_pos_i, int to_pos_j) {
        if(!isLegal(to_pos_i,to_pos_j,getFrom_pos_i(),getFrom_pos_j())){
            return -1;
        }
        return super.move(to_pos_i, to_pos_j);
    }

    public boolean isLegal(int to_pos_i,int to_pos_j,int from_pos_i,int from_pos_j){
        if (!new Rook(screenData,super.getMark(),super.isMy_move())
                .isLegal(to_pos_i, to_pos_j, from_pos_i, from_pos_j) &&
                !new Bishop(screenData,super.getMark(),super.isMy_move())
                        .isLegal(to_pos_i, to_pos_j, from_pos_i, from_pos_j)) {
            return false;
        }
        return true;
    }
}