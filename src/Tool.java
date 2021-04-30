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
        if(this.from_pos_i!=-1&&this.from_pos_j!=-1&&my_move==true) {
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
        System.out.println("from_pos_iM: "+from_pos_i);
        System.out.println("from_pos_jM: "+from_pos_j);
        if(from_pos_i==-1||from_pos_j==-1||!isLegal(from_pos_i,from_pos_j)){
            if (my_move)
                return 1;
            else
                return 0;
        }
        this.to_pos_i=to_pos_i;
        this.to_pos_j=to_pos_j;
        System.out.println("from_pos_i: "+from_pos_i);
        System.out.println("from_pos_j: "+from_pos_j);
        System.out.println("to_pos_i: "+to_pos_i);
        System.out.println("to_pos_j: "+to_pos_j);
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
}




