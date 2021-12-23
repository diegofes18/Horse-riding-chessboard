/*
 * Classe Tauler definida com un panell on es defineix una taula 8x8 de caselles
 *
 * El constructor defineix totes les caselles com a rectangles d'un color
 * determinat inicialitzant-les buides
 *
 * El mètode paintComponent recorr el tauler pintant les caselles
 *
 * El mètode getPreferredSize retorna el tamant del tauler
 *
 * El mètode posa col·loca una peça a la posició que indiquen els dos enters,
 * les peces són les constants definides per la classe Pesa
 */
package caballos;

/**
 *
 * @author miquelmascaro
 */

import caballos.Casella;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Tauler extends JPanel {
    
    public static  int DIMENSIO ;
    private static final int MAXIM = 800;
    private static  int COSTAT ;
    private static final Color BLANC = Color.LIGHT_GRAY;
    private static final Color NEGRE = Color.DARK_GRAY;
    private Casella t[][];

    public Tauler(int n) {
        DIMENSIO=n;
        COSTAT=MAXIM/DIMENSIO;
        t = new Casella[DIMENSIO][DIMENSIO];
        int y = 0;
        for (int i = 0; i < DIMENSIO; i++) {
            int x = 0;
            for (int j = 0; j < DIMENSIO; j++) {
                Rectangle2D.Float r =
                        new Rectangle2D.Float(x, y, COSTAT, COSTAT);
                Color col;
                Color cnum;
                if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {
                    col = BLANC;
                    cnum=NEGRE;
                } else {
                    col = NEGRE;
                    cnum=BLANC;
                }
                t[i][j] = new Casella(r, col, false,cnum);
                x += COSTAT;
            }
            y += COSTAT;
        }
    }
    public void asigna(int [][]tauler){
       
        
        int y = 0;
        for (int i = 0; i < DIMENSIO; i++) {
            int x = 0;
            for (int j = 0; j < DIMENSIO; j++) {
                Rectangle2D.Float r =
                        new Rectangle2D.Float(x, y, COSTAT, COSTAT);
                Color col;
                Color cnum;
                if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {
                    col = BLANC;
                    cnum=NEGRE;
                } else {
                    col = NEGRE;
                    cnum=BLANC;
                }
                Casella c=new Casella(r,col,true,cnum);
                c.setNum(tauler[i][j]);
                t[i][j] = c;
                x += COSTAT;
            }
            y += COSTAT;
        } 
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < DIMENSIO; i++) {
            for (int j = 0; j < DIMENSIO; j++) {                
                t[i][j].paintComponent(g);
            }
        }
    }
    public boolean haypieza(){
         for (int i = 0; i < DIMENSIO; i++) {
            for (int j = 0; j < DIMENSIO; j++) {                
                if(t[i][j].isOcupada()){
                    return true;
                }
            }
        }
         return false;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAXIM, MAXIM);
    }



    boolean dinsCasella(int i, int j, int x, int y) {
        return t[i][j].getRec().contains(x, y);
    }

    boolean isOcupat(int i, int j) {
        return t[i][j].isOcupada();
    }

    void buida(int i, int j) {
        t[i][j].setOcupada(false);
    }
    void buidatot(){
        for (int i = 0; i < DIMENSIO; i++) {
            for (int j = 0; j < DIMENSIO; j++) {                
               t[i][j].setOcupada(false);
            }
        }
    }
    void Ocupa(int i,int j,int n){
        t[i][j].setOcupada(true);
        t[i][j].setNum(n);
    }
    

    Rectangle getRectangle(int i, int j) {
        return t[i][j].getRec().getBounds();
    }
}
