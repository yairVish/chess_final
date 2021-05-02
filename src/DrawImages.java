import javax.swing.*;
import java.awt.*;

public class DrawImages {
    private Image king;
    private Image queen;
    private Image rook;
    private Image bishop;
    private Image horse;
    private Image pawn;
    private Image king_b;
    private Image quin_b;
    private Image rook_b;
    private Image bishop_b;
    private Image horse_b;
    private Image pawn_b;
    private Image brown;
    private Image white;
    private Image green;
    private Image glass;

    public DrawImages(){
        king = new ImageIcon("king_t.png").getImage();
        queen = new ImageIcon("queen_t.png").getImage();
        rook = new ImageIcon("rook_t.png").getImage();
        bishop = new ImageIcon("bishop_t.png").getImage();
        horse = new ImageIcon("horse_t.png").getImage();
        pawn = new ImageIcon("pawn.png").getImage();
        king_b = new ImageIcon("king_b.png").getImage();
        quin_b = new ImageIcon("queen_b2.png").getImage();
        rook_b = new ImageIcon("rook_b.png").getImage();
        bishop_b = new ImageIcon("bishop_b.png").getImage();
        horse_b = new ImageIcon("horse_b.png").getImage();
        pawn_b = new ImageIcon("pawn_b.png").getImage();
        white = new ImageIcon("white.png").getImage();
        brown = new ImageIcon("brown.png").getImage();
        green = new ImageIcon("green.png").getImage();
        glass = new ImageIcon("glass.png").getImage();
    }

    public Image getKing() {
        return king;
    }

    public Image getQueen() {
        return queen;
    }

    public Image getRook() {
        return rook;
    }

    public Image getBishop() {
        return bishop;
    }

    public Image getHorse() {
        return horse;
    }

    public Image getPawn() {
        return pawn;
    }

    public Image getKing_b() {
        return king_b;
    }

    public Image getQuin_b() {
        return quin_b;
    }

    public Image getRook_b() {
        return rook_b;
    }

    public Image getBishop_b() {
        return bishop_b;
    }

    public Image getHorse_b() {
        return horse_b;
    }

    public Image getPawn_b() {
        return pawn_b;
    }

    public Image getBrown() {
        return brown;
    }

    public Image getWhite() {
        return white;
    }

    public Image getGreen() {
        return green;
    }

    public Image getGlass() {
        return glass;
    }
}
