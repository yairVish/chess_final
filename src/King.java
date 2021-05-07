public class King extends Tool {
    private short[][] screenData;
    private char mark='E';
    private boolean my_move;
    public King(short[][] screenData) {
        super(screenData);
        this.screenData = screenData;
    }

    public King(short[][] screenData,char mark,boolean my_move) {
        super(screenData);
        this.screenData = screenData;
        this.mark = mark;
        this.my_move = my_move;
    }

    @Override
    public int move(int to_pos_i, int to_pos_j) {
        if(!isLegal(to_pos_i,to_pos_j,getFrom_pos_i(),getFrom_pos_j(),false)){
            return -1;
        }
        return super.move(to_pos_i, to_pos_j);
    }

    public boolean isLegal(int to_pos_i,int to_pos_j,int from_pos_i,int from_pos_j,boolean king){
        if((from_pos_i>=to_pos_i+2||from_pos_i<=to_pos_i-2)){
            return false;
        }
        
        if((from_pos_j>=to_pos_j+2||from_pos_j<=to_pos_j-2)){
            return false;
        }
        super.setMark(mark);
        super.setMy_move(my_move);
        if(isThreatened(to_pos_i,to_pos_j,from_pos_i,from_pos_j,super.getMark(),super.isMy_move())&&!king){
            System.out.println("isThreatened");
            return false;
        }
        if(to_pos_i==from_pos_i&&to_pos_j==from_pos_j){
            return false;
        }
        System.out.println("!isThreatened?");
        return true;
    }

    public boolean isThreatened(int to_pos_i,int to_pos_j,int from_pos_i,int from_pos_j){
        int first=0;
        int last=0;
        int value_king = 128;
        if(mark=='W'&&my_move){//White
            first=256;
            last=8192;
            value_king=128;
        }
        if(mark=='B'&&my_move){//Black
            first=4;
            last=128;
            value_king=8192;
        }
        if(mark=='W'&&!my_move){//Black
            first=4;
            last=128;
            value_king=8192;
        }
        if(mark=='B'&&!my_move){//White
            first=256;
            last=8192;
            value_king=128;
        }

        short[][] screenData = new short[8][8];
        int king_pos_i=0;
        int king_pos_j=0;

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if((this.screenData[i][j] & value_king) != 0){
                    king_pos_i = i;
                    king_pos_j = j;
                }
                screenData[i][j]=this.screenData[i][j];
            }
        }

        if(my_move)
            screenData[from_pos_i][from_pos_j] = (short) (screenData[from_pos_i][from_pos_j] - 16384);

        int valueK=screenData[from_pos_i][from_pos_j];
        if(valueK%2==0){
            valueK-=2;
        }else{
            valueK-=1;
        }
        if(king_pos_i==from_pos_i&&king_pos_j==from_pos_j){
            king_pos_i = to_pos_i;
            king_pos_j = to_pos_j;
        }
        screenData[from_pos_i][from_pos_j] = (short) (screenData[from_pos_i][from_pos_j]-valueK);
        screenData[to_pos_i][to_pos_j] = (short) (screenData[to_pos_i][to_pos_j]+valueK);

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) {
                String space="";
                if(String.valueOf(screenData[i][j]).length()==1){
                    space="   ";
                }
                if(String.valueOf(screenData[i][j]).length()==2){
                    space="  ";
                }
                if(String.valueOf(screenData[i][j]).length()==3){
                    space=" ";
                }
                System.out.print(", "+space+screenData[i][j]);
            }
            System.out.println();
        }
        for(int value=first;value<=last;value+=value) {
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if((screenData[i][j] & value) != 0){
                        if(value==4){
                            if(new Pawn(screenData,4).isLegalEat(king_pos_i,king_pos_j,i,j,true)){
                                return true;
                            }
                        }else if(value==256){
                            if(new Pawn(screenData,256).isLegalEatB(king_pos_i,king_pos_j,i,j,true)){
                                return true;
                            }
                        }else if(value==8||value==512){
                            if(new Horse(screenData).isLegal(king_pos_i,king_pos_j,i,j)){
                                return true;
                            }
                        }else if(value==16||value==1024){
                            if(new Bishop(screenData).isLegal(king_pos_i,king_pos_j,i,j)){
                                return true;
                            }
                        }else if(value==32||value==2048){
                            if(new Rook(screenData).isLegal(king_pos_i,king_pos_j,i,j)){
                                return true;
                            }
                        }else if(value==64||value==4096){
                            if(new Queen(screenData).isLegal(king_pos_i,king_pos_j,i,j)){
                                return true;
                            }
                        }else if(value==128||value==8192){
                            if(new King(screenData).isLegal(king_pos_i,king_pos_j,i,j,true)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void setMark(char mark) {
        this.mark = mark;
        super.setMark(mark);
    }

    @Override
    public void setMy_move(boolean my_move) {
        this.my_move = my_move;
        super.setMy_move(my_move);
    }
}
