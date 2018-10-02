package com.company.Swing;

import javax.swing.*;
import java.awt.*;


    public class Snake extends JFrame {
        public Snake(){
            add(new Board());
            setResizable( true );
            pack();
            setTitle( "Snake" );
            setLocationRelativeTo(null  );
            setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        }
        public static void main(String args[]){
            EventQueue.invokeLater( new Runnable() {
                @Override
                public void run() {
                    JFrame ex=new Snake();
                    ex.setVisible( true );
                }
            } );
        }

    }

