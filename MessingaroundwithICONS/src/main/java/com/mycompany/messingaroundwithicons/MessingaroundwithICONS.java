/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.messingaroundwithicons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author styde
 */

class  PathIcon  extends JFrame implements ActionListener
{ 
    private JButton  ok;
    private JTextField    pathfield=null;
    private JLabel lable=null;
    private String pathtext;
        public PathIcon()
        {
                super();
                pathtext="icon/java.png";
                lable=new JLabel("Icon Path :");
                pathfield=new JTextField(pathtext,24);
            
                ok= new JButton("ok");
                Icon    one= new ImageIcon("icon/java.png");
                pathtext=pathfield.getText();
                Icon    two= new ImageIcon(pathtext);
                
                
           
                ok.setIcon(one);
                ok.setToolTipText("press to do the operation");
                pathfield.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));


                
                JPanel N=new JPanel();
                N.setSize(100, 200);
                N.add(lable);
                N.add(pathfield);
                
                JPanel S= new JPanel(new GridLayout(1,1));
                S.add(ok);
                
                Container   X= getContentPane() ;

                X.add(N, BorderLayout.NORTH);
                X.add(S, BorderLayout.SOUTH);

                 Component   A [ ] =S.getComponents();
        for ( int i =0 ; i < A.length; i++)
       {
          ((AbstractButton) A[i]).addActionListener(this);
       }
        
        

        pathfield.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                ok.doClick();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                ok.doClick();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
               ok.doClick();
            }
        });
                setSize(400,200);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                
                setVisible(true);


        
}
   
    @Override
    public void actionPerformed(ActionEvent e) {
        Object T=e.getSource();
         if(T==ok)
         {  pathtext=null;
            pathtext=pathfield.getText();
            Icon    two= new ImageIcon(pathtext);

                 ok.setIcon(two);

    }
     else if ( T==pathfield)
            ok.doClick();

    
}
}
public class MessingaroundwithICONS {

    public static void main(String[] args) {
        PathIcon PI=new PathIcon();
    }
}

