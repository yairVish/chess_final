public class Pawn extends Tool{
    private short[][] screenData;
    private int num;
    public Pawn(short[][] screenData,int num) {
        super(screenData);
        this.num = num;
        this.screenData=screenData;
    }

    public Pawn(short[][] screenData,int num,char mark,boolean my_move) {
        super(screenData);
        this.num = num;
        this.screenData=screenData;
        super.setMark(mark);
        super.setMy_move(my_move);
    }

    @Override
    public int move(int to_pos_i,int to_pos_j){
        if(!isLegal(to_pos_i,to_pos_j,getFrom_pos_i(),getFrom_pos_j())&&num==4){
            return -1;
        }
        if(!isLegalB(to_pos_i,to_pos_j,getFrom_pos_i(),getFrom_pos_j())&&num==256){
            return -1;
        }

        return super.move(to_pos_i,to_pos_j);
    }

    public boolean isLegal(int to_pos_i,int to_pos_j,int from_pos_i,int from_pos_j){
        int step=2;
        boolean scanTools=false;
        if(from_pos_i==6){
            step=3;
            if(from_pos_i==to_pos_i+2)
                scanTools=scanForTools(to_pos_i-1,from_pos_j);
        }
        if(((to_pos_i>from_pos_i||from_pos_i>=to_pos_i+step)||to_pos_j!=from_pos_j||scanTools
            )&&new OperateGame(screenData).locIsEmpty(to_pos_i,to_pos_j)){
            return false;
        }

        if(!isLegalEat(to_pos_i,to_pos_j,from_pos_i,from_pos_j,false)
                &&!new OperateGame(screenData).locIsEmpty(to_pos_i,to_pos_j)){
            return false;
        }
        if(isThreatened(to_pos_i,to_pos_j,from_pos_i,from_pos_j,super.getMark(),super.isMy_move())){
            return false;
        }

        if(to_pos_i==from_pos_i&&to_pos_j==from_pos_j){
            return false;
        }
        return true;
    }

    public boolean isLegalB(int to_pos_i,int to_pos_j,int from_pos_i,int from_pos_j){
        int step=2;
        boolean scanTools=false;
        if(from_pos_i==1){
            step=3;
            if(to_pos_i==from_pos_i+2)
                scanTools=scanForTools(from_pos_i,from_pos_j);
        }
        //System.out.println("("+to_pos_i+"<"+from_pos_i+"||"+to_pos_i+">="+(from_pos_i+step)+")||"+to_pos_j+"!="+from_pos_j);
        if(((to_pos_i<from_pos_i||to_pos_i>=from_pos_i+step)||to_pos_j!=from_pos_j||scanTools
            )&&new OperateGame(screenData).locIsEmpty(to_pos_i,to_pos_j)){
            return false;
        }
        if(!isLegalEatB(to_pos_i,to_pos_j,from_pos_i,from_pos_j,false)
                &&!new OperateGame(screenData).locIsEmpty(to_pos_i,to_pos_j)){
            return false;
        }

        if(isThreatened(to_pos_i,to_pos_j,from_pos_i,from_pos_j,super.getMark(),super.isMy_move())){
            return false;
        }

        if(to_pos_i==from_pos_i&&to_pos_j==from_pos_j){
            return false;
        }

        return true;
    }

    public boolean isLegalEat(int to_pos_i,int to_pos_j,int from_pos_i,int from_pos_j,boolean king){
        if(to_pos_i==from_pos_i&&to_pos_j==from_pos_j){
            return false;
        }

        if(to_pos_i==from_pos_i-1
                &&(to_pos_j==from_pos_j-1||to_pos_j==from_pos_j+1)
                &&(screenData[to_pos_i][to_pos_j]>=256||king)){
            return true;
        }else{
            return false;
        }
    }

    public boolean isLegalEatB(int to_pos_i,int to_pos_j,int from_pos_i,int from_pos_j,boolean king){
        if(to_pos_i==from_pos_i&&to_pos_j==from_pos_j){
            return false;
        }

        if(to_pos_i==from_pos_i+1&&(to_pos_j==from_pos_j-1||to_pos_j==from_pos_j+1)&&
                (screenData[to_pos_i][to_pos_j]<256&&screenData[to_pos_i][to_pos_j]!=1
                &&screenData[to_pos_i][to_pos_j]!=2||king)){
            return true;
        }else{
            return false;
        }
    }

    private boolean scanForTools(int pos_i,int pos_j){
        for(int i=pos_i+1;i<=pos_i+2;i++){
            if(screenData[i][pos_j]!=1&&screenData[i][pos_j]!=2){
               return true;
            }
        }
        return false;
    }
    public void setNum(int num) {
        this.num=num;
    }
}
