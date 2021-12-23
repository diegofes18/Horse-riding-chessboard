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
    JButton aceptar,borrar,s1,s2,s3;
    JLabel texto;
    int n,i_inicial,j_inicial;
    int[][]sol1,sol2,sol3;

    public Caballos(int n) {
        
        super("Movimientos caballo");
        texto=new JLabel(" ");
        texto.setFont(new Font("Arial", Font.PLAIN, 18));
        lateral=new JPanel();
        sol1=new int[n][n];
        sol2=new int[n][n];
        sol3=new int[n][n];
        tauler = new Tauler(n);
        tauler.addMouseListener(this);
        borrar=new JButton("Voler a Empezar");
        borrar.setBounds(900, 650, 200, 75);
//        aceptar=new JButton("ACEPTAR");
//        aceptar.setBounds(900, 650, 200, 75);
        s1=new JButton("SOLUCION 1");
        s2=new JButton("SOLUCION 2");
        s3=new JButton("SOLUCION 3");
        s1.setBounds(912, 150, 175, 43);
        s2.setBounds(912, 250, 175, 43);
        s3.setBounds(912, 350, 175, 43);
        lateral.setBounds(800,0,400, 827);
//        lateral.add(aceptar);
        lateral.add(texto);
        this.setPreferredSize(new Dimension(1200, 827));
//        this.getContentPane().add(aceptar);
        this.getContentPane().add(s1);
        this.getContentPane().add(s2);
        this.getContentPane().add(s3);
        this.getContentPane().add(borrar);
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
                    if(problema_caballo(sol1,n,cx,cy,0)){
                        tauler.asigna(sol1);
                        tauler.repaint();
                    }
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
                    if(problema_caballo(sol2,n,cx,cy,1)){
                        tauler.asigna(sol2);
                        tauler.repaint();
                    }
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
                    if(problema_caballo(sol3,n,cx,cy,2)){
                        tauler.asigna(sol3);
                        tauler.repaint();
                    }
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
                   tauler.buidatot();
                   tauler.repaint();
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
   
    public static boolean solucion (int [][]tablero, int n, int caballos,int cx,int cy,int sol){
        
        if(caballos==n*n){
            return true; 
        }
        int []desp_x={-1,1,-1,1,2,2,-2,-2};
        int []desp_y={2,2,-2,-2,1,-1,1,-1};
        //solucion 1
       if(sol==0){
        for(int i=0;i<desp_x.length;i++){
            if(esValido(tablero,cy+desp_y[i],cx+desp_x[i],n)){
               tablero[cy+desp_y[i]][cx+desp_x[i]] =caballos+1;
               if(solucion(tablero,n,caballos+1,cx+desp_x[i],cy+desp_y[i],sol)){
                   return true;
               }
               tablero[cy+desp_y[i]][cx+desp_x[i]]=0;
            }
        }
        
       }
       //solucion 2
        if (sol==1){
           for(int i=desp_x.length-1;i>0;i--){
             if(esValido(tablero,cy+desp_y[i],cx+desp_x[i],n)){
                tablero[cy+desp_y[i]][cx+desp_x[i]] =caballos+1;
                if(solucion(tablero,n,caballos+1,cx+desp_x[i],cy+desp_y[i],sol)){
                    return true;
                }
                tablero[cy+desp_y[i]][cx+desp_x[i]]=0;
             }
           }
       }
       //solucion 3
       if(sol==2){
            int []desp2_x={1,-1,1,-1,2,-2,2,-2};
            int []desp2_y={-2,-2,2,2,1,1,-1,-1};
            for(int i=0;i<desp2_x.length;i++){
                if(esValido(tablero,cy+desp2_y[i],cx+desp2_x[i],n)){
                   tablero[cy+desp2_y[i]][cx+desp2_x[i]] =caballos+1;
                   if(solucion(tablero,n,caballos+1,cx+desp2_x[i],cy+desp2_y[i],sol)){
                       return true;
                   }
                   tablero[cy+desp2_y[i]][cx+desp2_x[i]]=0;
                }
            }
       }
       return false;
    }
    
   public static boolean problema_caballo(int [][]tablero,int n,int cx,int cy,int sol){
        tablero[cx][cy]=1;
        return solucion(tablero,n,1,cx,cy,sol);
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
