/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui3;

import entity.Reader;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jvm
 */
class JReaderPanel extends JPanel{

  public JReaderPanel() {
    this.setPreferredSize(new Dimension(480,290));;
    this.setMaximumSize(this.getPreferredSize());
    JLabel jLabelReaders = new JLabel("Читатели");
    this.add(jLabelReaders);
    JComboBox<Reader> jComboBoxReaders = new JComboBox<>();
    jComboBoxReaders.setPreferredSize(new Dimension(300,27));
    jComboBoxReaders.setMaximumSize(jComboBoxReaders.getPreferredSize());
    this.add(jComboBoxReaders);
  }
  
}
