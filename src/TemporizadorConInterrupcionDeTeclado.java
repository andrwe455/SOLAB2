import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class TemporizadorConInterrupcionDeTeclado {
    private static int contador = 0;
    private static Timer timer;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Temporizador con Interrupción de Teclado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocation(500,300);
        frame.setLayout(null);
        JLabel label1 = new JLabel("detener tecla i");
        JLabel label2 = new JLabel("reanudar tecla r");
        JLabel label3 = new JLabel("Finalizar todo tecla s");
        JLabel label = new JLabel("Contador: " + contador);
        
        label1.setBounds(0, 10, 100, 20);
        label2.setBounds(110, 10, 150, 20);
        label3.setBounds(240, 10, 150, 20);
        
        label.setBounds(0, 100,250,25);
        
        frame.add(label);
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);

        timer = new Timer(10, e -> {
            contador++;
            label.setText("Contador: " + contador);
        });

        frame.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == 's') { // Presiona 'i' para interrumpir el temporizador
                        timer.stop();
                    System.out.println("Temporizador interrumpido.");
                    frame.dispose();
                        try {
                            throw new InterruptedException();
                        } catch (InterruptedException ex) {
                            JOptionPane.showMessageDialog(null, "Proceso interrumpido","ALerta",2);
                        }
                    }
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'i') { // Presiona 'i' para interrumpir el temporizador
                    timer.stop();
                    System.out.println("Temporizador interrumpido.");
                    JOptionPane.showMessageDialog(null, "Proceso interrumpido","ALerta",2);
                }
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == 'r') { // Presiona 'r' para reanudar el temporizador
                    //timer.start();
                    System.out.println("Temporizador reanudado.");
                    //JOptionPane.showMessageDialog(null, "Proceso reanudado","ALerta",2);
                    int k = JOptionPane.showConfirmDialog(null,"Se reanudara el proceso","Alerta",
                            JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

                    if(k==JOptionPane.OK_OPTION)
                    {
                        timer.start();
                    }
                    else
                    {
                        frame.dispose();
                    }

                }
            }

        });

        frame.setFocusable(true);
        frame.requestFocus();
        frame.setVisible(true);

        timer.start();
    }
}
