/*
 * Programa que defineix un tauler d'escacs dins un marc i posa vuit reines
 * on indica l'usuari
 *
 */
package caballos;

/**
 *
 * @author miquelmascaro
 */
import java.awt.Dimension;
import java.awt.Font;
import caballos.Tauler;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Caballos extends JFrame implements MouseListener {

    Tauler tauler;
    JPanel lateral;
    JButton aceptar,borrar,s1,s2,s3,delete;
    JLabel texto;
    int n,i_inicial,j_inicial;
    
    static int[][][]taulers;
    static int [][]t;
    static int result=0;

    public Caballos(int n) {
        
        super("Movimientos caballo");
        texto=new JLabel(" ");
        texto.setFont(new Font("Arial", Font.PLAIN, 18));
        lateral=new JPanel();
        this.n=n;
        taulers=new int[3][n][n];
        tauler = new Tauler(n);
        tauler.addMouseListener(this);
        borrar=new JButton("Busca Soluciones");
        borrar.setBounds(900, 650, 200, 75);
        delete=new JButton("Borrar");
        delete.setBounds(900, 550, 200, 75);
        s1=new JButton("SOLUCION 1");
        s2=new JButton("SOLUCION 2");
        s3=new JButton("SOLUCION 3");
        s1.setBounds(912, 150, 175, 43);
        s2.setBounds(912, 250, 175, 43);
        s3.setBounds(912, 350, 175, 43);
        lateral.setBounds(800,0,400, 827);
        lateral.add(texto);
        this.setPreferredSize(new Dimension(1200, 827));
        this.getContentPane().add(s1);
        this.getContentPane().add(s2);
        this.getContentPane().add(s3);
        this.getContentPane().add(borrar);
        this.getContentPane().add(delete);
        this.getContentPane().add(lateral);
        this.getContentPane().add(tauler);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!tauler.haypieza()){
                    JOptionPane.showMessageDialog(null,"Primero coloca una reina") ;
                }
                else{
                    int cx=i_inicial;
                    int cy=j_inicial;
                    tauler.asigna(taulers[0]);
                    tauler.repaint();
                }
            }
        });
        s2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!tauler.haypieza()){
                    JOptionPane.showMessageDialog(null,"Primero coloca una reina") ;
                }
                else{
                    int cx=i_inicial;
                    int cy=j_inicial;
                    tauler.asigna(taulers[1]);
                    tauler.repaint();
                }
            }
        });
        s3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!tauler.haypieza()){
                    JOptionPane.showMessageDialog(null,"Primero coloca una reina") ;
                }
                else{
                    int cx=i_inicial;
                    int cy=j_inicial;
                    tauler.asigna(taulers[2]);
                    tauler.repaint();
                }
            }
        });
        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!tauler.haypieza()){
                    JOptionPane.showMessageDialog(null,"Ya no hay piezas") ;
                }
                else{
                    
                   resol(i_inicial,j_inicial);
                   
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!tauler.haypieza()){
                    JOptionPane.showMessageDialog(null,"Ya no hay piezas") ;
                }
                else{
                   
                   taulers=new int[3][n][n];
                   tauler.buidatot();
                   tauler.repaint();
                   result=0;
                }
            }
        });
    }

    public static void main(String[] args) {
        String s = JOptionPane.showInputDialog ( "Introduzca un número:" ); 
        Caballos esc = new Caballos(Integer.parseInt(s));
        esc.setVisible(true);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        
        int x = 0, y = 0, i, j = 0;
         if (e.getButton() == MouseEvent.BUTTON1 ) {
            x = e.getX();
            y = e.getY();
            boolean trobat = false;
            for (i = 0; i < Tauler.DIMENSIO && !trobat; i++) {
                for (j = 0; j < Tauler.DIMENSIO && !trobat; j++) {
                    trobat = tauler.dinsCasella(i, j, x, y);
                }
            }
            i--;
            j--;
            i_inicial=i;
            j_inicial=j;
            System.out.println("Click a: " + i + ", " + j);
           if (!tauler.isOcupat(i, j)) {
                tauler.buidatot();
                tauler.Ocupa(i,j,1);
            } else {
                Toolkit.getDefaultToolkit().beep();
                tauler.buida(i, j);
            }
           tauler.repaint();
          
        }
    }
    public void resol(int cxInicial, int cyInicial) {
       
        //Assignam coords inicials al cavall
        int cx = cxInicial;
        int cy = cyInicial;
        int moviments = 1;
        for (int i = 0; i < 3; i++) {
            t=new int[n][n];
            t[cx][cy]=1;
            if (solucion1(t, n, moviments, cx, cy)) {
                taulers[result] = t;
                //mostrarTauler
                result++;
            }
            else{
                JOptionPane.showMessageDialog(null,"No hay solucion, "+result) ;
            }

        }
    }
    public  boolean solucion1(int [][]tablero, int n, int caballos,int cx,int cy) {
        
        if ((caballos == n * n) && (noencontrado(tablero))) {
            System.out.println("Solucion diferente!");
            return true;
        }

        int ncx;
        int ncy;
        int despX[] = {-2, -2, +2, +2, +1, -1, +1, -1};
        int despY[] = {+1, -1, -1, +1, +2, +2, -2, -2};

        for (int i = 0; i < despX.length; i++) {
            ncx = cx + despX[i];
            ncy = cy + despY[i];
            if (esValido(tablero, ncx, ncy, n)) {
                tablero[ncx][ncy]=caballos+1;
                if (solucion1(tablero, n, caballos + 1, ncx, ncy)) {
                    taulers[result] = tablero;
                    return true;
                }
                tablero[ncx][ncy]=0;
            }
        }
        return false;
    }
    
    public  boolean noencontrado(int[][] t) {
        for (int i = 0; i < 3; i++) {
            if (iguales(t,taulers[i])) {
                return false;
            }
        }
        return true;
    }
    public boolean iguales(int [][]t,int[][]h){
        for(int x=0;x<n;x++){
            for(int y=0;y<n;y++){
               if(t[x][y]!=h[x][y]){
                   return false;
               }       
            }
        }
        return true;
    }
    public static boolean esValido(int [][]tablero,int f,int c,int n){
        if(f<0 || f>n-1 || c<0 || c>n-1 || tablero[f][c]!=0){
            return false;
        }
        return true;
    }
   
    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
