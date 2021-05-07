public class EndGame {
    private short[][]screenData;
    private char mark;
    private boolean my_move;
    public EndGame(short[][]screenData,char mark,boolean my_move){
        this.screenData=screenData;
        this.mark = mark;
        this.my_move = my_move;
    }
    public boolean isCheckmate(){
        int first=0;
        int last=0;
        if(mark=='W'&&my_move){//White
            first=256;
            last=8192;
        }
        if(mark=='B'&&my_move){//Black
            first=4;
            last=128;
        }
        if(mark=='W'&&!my_move){//Black
            first=4;
            last=128;
        }
        if(mark=='B'&&!my_move){//White
            first=256;
            last=8192;
        }
        for(int value=first;value<=last;value+=value) {
            for(int i=0;i<8;i++) {
                for (int j = 0; j < 8; j++) {
                    if((screenData[i][j] & value) != 0){
                        if(value==4){
                            if(!checkOptions(value,i,j))
                                return false;
                        }else if(value==256){
                            if(!checkOptions(value,i,j))
                                return false;
                        }else if(value==8||value==512){
                            if(!checkOptions(value,i,j))
                                return false;
                        }else if(value==16||value==1024){
                            if(!checkOptions(value,i,j))
                                return false;
                        }else if(value==32||value==2048){
                            if(!checkOptions(value,i,j))
                                return false;
                        }else if(value==64||value==4096){
                            if(!checkOptions(value,i,j))
                                return false;
                        }else if(value==128||value==8192){
                            if(!checkOptions(value,i,j))
                                return false;
                            }
                        }
                    }
                }
            }
        return true;
    }
    private boolean checkOptions(int value,int from_pos_i,int from_pos_j){
        for(int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                    if(value==4){
                        if(isLegalEat(i,j,value)&&
                        new Pawn(screenData,value,mark,!my_move).isLegal(i,j,from_pos_i,from_pos_j)){
                            return false;
                        }
                    }else if(value==256){
                        if(isLegalEat(i,j,value)&&
                                new Pawn(screenData,value,mark,!my_move).isLegalB(i,j,from_pos_i,from_pos_j)){
                            return false;
                        }
                    }else if(value==8||value==512){
                        if(isLegalEat(i,j,value)&&
                                new Horse(screenData,mark,!my_move).isLegal(i,j,from_pos_i,from_pos_j)){
                            return false;
                        }
                    }else if(value==16||value==1024){
                        if(isLegalEat(i,j,value)&&
                                new Bishop(screenData,mark,!my_move).isLegal(i,j,from_pos_i,from_pos_j)){
                            return false;
                        }
                    }else if(value==32||value==2048){
                        if(isLegalEat(i,j,value)&&
                                new Rook(screenData,mark,!my_move).isLegal(i,j,from_pos_i,from_pos_j)){
                            return false;
                        }
                    }else if(value==64||value==4096){
                        if(isLegalEat(i,j,value)&&
                                new Queen(screenData,mark,!my_move).isLegal(i,j,from_pos_i,from_pos_j)){
                            return false;
                        }
                    }else if(value==128||value==8192){
                        if(isLegalEat(i,j,value)&&
                                new King(screenData,mark,!my_move).isLegal(i,j,from_pos_i,from_pos_j,false)){
                            return false;
                        }
                    }
                }
            }
        return true;
        }
    private boolean isLegalEat(int pos_i,int pos_j,int value){
        if(new OperateGame(screenData).locIsEmpty(pos_i,pos_j)){
            return true;
        }
        if(value>=256&&screenData[pos_i][pos_j]>=256){
            return false;
        }
        if(value<=128&&screenData[pos_i][pos_j]<=128){
            return false;
        }
        return true;
    }
    public boolean isDraw(){
        int pawn = 0;
        int horse = 0;
        int bishop = 0;
        int rook = 0;
        int queen = 0;
        int king = 0;
        for(int value=4;value<=8192;value+=value) {
            for(int i=0;i<8;i++) {
                for (int j = 0; j < 8; j++) {
                    if((screenData[i][j] & value) != 0){
                        if(value==4||value==256){
                            pawn++;
                        }else if(value==8||value==512){
                            horse++;
                        }else if(value==16||value==1024){
                            bishop++;
                        }else if(value==32||value==2048){
                            rook++;
                        }else if(value==64||value==4096){
                            queen++;
                        }else if(value==128||value==8192){
                            king++;
                        }
                    }
                }
            }
        }
        if(pawn==0&&rook==0&&queen==0&&king==2){
            return true;
        }
        return false;
    }
}
