/*
 * Classe casella
 *
 * El constructor defineix una casella com un rectangle d'un color i si està
 * ocupada o no, el constructor no posa cap peça a la casella
 *
 * El mètode paintComponent pinta el rectangle de la casella si la casella està
 * ocupada pinta la pesa cridant al mètode de pintar peces
 *
 * El mètode setPesa col·loca una peça en la casella en questió
 */

package caballos;

/**
 *
 * @author miquelmascaro
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

class Casella {
    
    private Rectangle2D.Float rec;
    private Color col;
    private Color col2;
    private Boolean ocupada;
    private int n;

    public Casella(Rectangle2D.Float r, Color c, Boolean ocu,Color col2 ) {
        this.rec = r;
        this.col = c;
        this.col2=col2;
        this.ocupada = ocu;
        this.n=0;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.col);
        g2d.fill(this.rec);
        if (this.ocupada) {
            g2d.setFont(new Font("Arial", Font.PLAIN, 50));
            g2d.setColor(this.col2);
            g2d.drawString(String.valueOf(n), this.rec.x+50, this.rec.y+100);
            
        }
    }
    
    void setNum(int n){
        this.n=n;
    }


    public Rectangle2D.Float getRec() {
        return rec;
    }

    boolean isOcupada() {
        return ocupada;
    }

    void setOcupada(boolean b) {
        ocupada = b;
    }

    public int getNum() {
        return n;
    }

}
