package com.company.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;


public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 300;
    private final int B_Height = 300;
    private final int Dot_Size = 10;
    private final int All_DOts = 900;
    private final int RAND_pos = 29;
    private final int DELEY = 140;
    private final int x[] = new int[All_DOts];
    private final int y[] = new int[All_DOts];
    private int dots;
    private int apple_x;
    private int apple_y;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public Board() {

        addKeyListener( new TAdapter() );
        setBackground( Color.BLACK );
        setFocusable( true );
        setPreferredSize( new Dimension( B_WIDTH, B_Height ) );
        LoadImage();
        initGame();

    }

    private void LoadImage() {
        ImageIcon iid = new ImageIcon( "F:\\dot.png" );
        ball = iid.getImage();
        ImageIcon iia = new ImageIcon( "F:\\apple.png" );
        apple = iia.getImage();
        ImageIcon iih = new ImageIcon(  "F:\\head.png");
        head = iih.getImage();

    }

    private void initGame() {

        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;

        }
        locateApple();
        timer = new Timer( DELEY, this );
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent( g );
        doDrawing( g );

    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage( apple, apple_x, apple_y, this );
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage( head, x[z], y[z], this );
                } else {
                    g.drawImage( ball, x[z], y[z], this );

                }

            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver( g );

        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font( "Helvetica", Font.BOLD, 14 );
        FontMetrics metr = getFontMetrics( small );
        g.setColor( Color.white );
        g.setFont( small );
        g.drawString( msg, (B_WIDTH - metr.stringWidth( msg )) / 2, B_WIDTH / 2 );

    }

    private void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();

        }
    }

    private void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];

        }
        if (leftDirection) {
            x[0] -= Dot_Size;

        }
        if (rightDirection) {
            x[0] += Dot_Size;

        }
        if (upDirection) {
            y[0] -= Dot_Size;

        }
        if (downDirection) {
            y[0] += Dot_Size;

        }
    }

    private void checkCollision() {
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;

            }
        }
        if (y[0] >= B_Height) {
            inGame = false;

        }
        if (y[0] < 0) {
            inGame = false;

        }
        if (x[0] >= B_WIDTH) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;

        }
        if (!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {
        int r = (int) (Math.random() * RAND_pos);
        apple_x = ((r * Dot_Size));

        r = (int) (Math.random() * RAND_pos);
        apple_y = ((r * Dot_Size));

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();

    }

    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;

            }
            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;

            }
            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;

            }
            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;

            }

        }


    }

}



