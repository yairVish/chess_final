import java.util.concurrent.TransferQueue;

public class Tool {
    private short[][] screenData;
    private int from_pos_i=-1;
    private int from_pos_j=-1;
    private int to_pos_i=-1;
    private int to_pos_j=-1;
    private boolean my_move;
    private char mark;

    public Tool(short[][] screenData){
        this.screenData = screenData;
    }

    public int select(int from_pos_i,int from_pos_j){
        System.out.println("isLegal(from_pos_i,from_pos_j): "+isLegal(from_pos_i,from_pos_j));
        if(!isLegal(from_pos_i,from_pos_j)) {
            if(this.from_pos_i!=-1&&this.from_pos_j!=-1&&my_move){
                screenData[this.from_pos_i][this.from_pos_j] = (short) (screenData[this.from_pos_i][this.from_pos_j] - 16384);
            }
            this.from_pos_i=-1;
            this.from_pos_j=-1;
            if (my_move)
                return 1;
            else
                return 0;
        }
        if(this.from_pos_i!=-1&&this.from_pos_j!=-1&& my_move) {
            screenData[this.from_pos_i][this.from_pos_j]
                    = (short) (screenData[this.from_pos_i][this.from_pos_j] - 16384);
        }
        this.from_pos_i=from_pos_i;
        this.from_pos_j=from_pos_j;
        if(my_move)
            screenData[from_pos_i][from_pos_j] = (short) (screenData[from_pos_i][from_pos_j] + 16384);

        if (my_move)
            return 1;
        else
            return 0;
    }

    public int move(int to_pos_i,int to_pos_j){
        if(from_pos_i==-1||from_pos_j==-1||!isLegal(from_pos_i,from_pos_j)){
            if (my_move)
                return 1;
            else
                return 0;
        }
        this.to_pos_i=to_pos_i;
        this.to_pos_j=to_pos_j;
        if(my_move)
            screenData[from_pos_i][from_pos_j] = (short) (screenData[from_pos_i][from_pos_j] - 16384);
        int value=screenData[from_pos_i][from_pos_j];
        if(value%2==0){
            value-=2;
        }else{
            value-=1;
        }
        screenData[from_pos_i][from_pos_j] = (short) (screenData[from_pos_i][from_pos_j]-value);
        screenData[to_pos_i][to_pos_j] = (short) (screenData[to_pos_i][to_pos_j]+value);

        from_pos_i = -1;
        from_pos_j = -1;
        if (my_move)
            return 0;
        else
            return 1;
    }

    public int eat(int pos_i,int pos_j){
        int value = screenData[pos_i][pos_j];
        int result = move(pos_i,pos_j);
        if(result!=-1) {
            if (value % 2 == 0) {
                value -= 2;
            } else {
                value -= 1;
            }
            screenData[pos_i][pos_j] = (short) (screenData[pos_i][pos_j] - value);
        }
        return result;
    }

    private boolean isLegal(int pos_i,int pos_j){
        for(int i=4;i<=8192;i+=i) {
            if ((screenData[pos_i][pos_j] & i) != 0) {
                if ((my_move && mark == 'W' && i >= 256)
                        || (!my_move && mark == 'W' && i <= 128)
                        || (my_move && mark == 'B' && i <= 128)
                        || (!my_move && mark == 'B' && i >= 256)) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isLegalEat(int pos_i,int pos_j){
        for(int i=4;i<=8192;i+=i){
            if ((screenData[pos_i][pos_j] & i) != 0){
                if ((my_move && mark == 'W' && i <=128)
                        || (!my_move && mark == 'W' && i >=256)
                        || (my_move && mark == 'B' && i >=256)
                        || (!my_move && mark == 'B' && i <=128)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isThreatened(int to_pos_i,int to_pos_j,int from_pos_i,int from_pos_j,char mark
            ,boolean my_move){

        if(mark!='W'&&mark!='B'){
            return false;
        }

        System.out.println("mark! "+mark);
        System.out.println("my_move! "+my_move);
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
        System.out.println("################################ before #########################################");
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

        System.out.println("################################ after #########################################");
        System.out.println("from_pos_iS: "+from_pos_i);
        System.out.println("from_pos_jS: "+from_pos_j);
        System.out.println("to_pos_iS: "+to_pos_j);
        System.out.println("to_pos_jS: "+to_pos_j);
        if(my_move&&screenData[from_pos_i][from_pos_j]>=16384)
            screenData[from_pos_i][from_pos_j] = (short) (screenData[from_pos_i][from_pos_j] - 16384);
        if(screenData[to_pos_i][to_pos_j]!=1&&screenData[to_pos_i][to_pos_j]!=2
        &&(to_pos_i!=from_pos_i||to_pos_j!=from_pos_j)){
            int valueO=screenData[to_pos_i][to_pos_j];
            if(valueO%2==0){
                valueO-=2;
            }else{
                valueO-=1;
            }
            screenData[to_pos_i][to_pos_j] = (short) (screenData[to_pos_i][to_pos_j]-valueO);
        }
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

    public void setScreenData(short[][] screenData) {
        this.screenData = screenData;
    }

    public short[][] getScreenData() {
        return screenData;
    }

    public void setMy_move(boolean my_move) {
        this.my_move = my_move;
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }

    public int getFrom_pos_i() {
        return from_pos_i;
    }

    public void setFrom_pos_i(int from_pos_i) {
        this.from_pos_i = from_pos_i;
    }

    public int getFrom_pos_j() {
        return from_pos_j;
    }

    public void setFrom_pos_j(int from_pos_j) {
        this.from_pos_j = from_pos_j;
    }

    public boolean isMy_move() {
        return my_move;
    }
}




