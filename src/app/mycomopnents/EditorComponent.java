/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomopnents;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Melnikov
 */
public class EditorComponent extends JPanel{
    
    private JLabel caption;
    private JTextField editor;
    
    public EditorComponent(String text, int widthWindow,int heightPanel, int editorWidth) {
        initComponents(text, widthWindow, heightPanel, editorWidth);
    }

    private void initComponents(String text, int widthWindow, int heightPanel, int editorWidth) {
        this.setPreferredSize(new Dimension(widthWindow,heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        caption = new JLabel(text);
        caption.setPreferredSize(new Dimension(widthWindow/3,heightPanel));
        caption.setMinimumSize(caption.getPreferredSize());
        caption.setMaximumSize(caption.getPreferredSize());
//        caption.setBorder(BorderFactory.createLineBorder(Color.yellow));
        caption.setHorizontalAlignment(JLabel.RIGHT);
        caption.setFont(new Font("Tahoma",0,12));
        this.add(caption);
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        editor = new JTextField();
        editor.setPreferredSize(new Dimension(editorWidth, 27));
        editor.setMaximumSize(editor.getPreferredSize());
        editor.setMinimumSize(editor.getPreferredSize());
        this.add(editor);
    }

    public JLabel getCaption() {
        return caption;
    }

    public JTextField getEditor() {
        return editor;
    }
    
}
