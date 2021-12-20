/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomopnents;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import facade.BookFacade;
import facade.HistoryFacade;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

/**
 *
 * @author Melnikov
 */
public class ListHistoriesComponent extends JPanel{
    private JLabel caption;
    private JList<History> list;

    public ListHistoriesComponent(String text, int widthWindow,int heightPanel, int listWidth) {
        initComponents(text, widthWindow, heightPanel, listWidth);
    }

    private void initComponents(String text, int widthWindow, int heightPanel, int listWidth) {
        this.setPreferredSize(new Dimension(widthWindow,heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        caption = new JLabel(text);
        caption.setPreferredSize(new Dimension(widthWindow/3,27));
        caption.setMinimumSize(caption.getPreferredSize());
        caption.setMaximumSize(caption.getPreferredSize());
//        caption.setBorder(BorderFactory.createLineBorder(Color.yellow));
        caption.setHorizontalAlignment(JLabel.RIGHT);
        caption.setAlignmentY(TOP_ALIGNMENT);//setVerticalAlignment(JLabel.TOP);
        caption.setFont(new Font("Tahoma",0,12));
        this.add(caption);
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        list = new JList<>();
        list.setModel(getListModel());
        list.setCellRenderer(createListHistoriesRenderer());
        list.setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);;
        list.setLayoutOrientation (JList.HEIGHT);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(listWidth, 120));
        scrollPane.setMaximumSize(scrollPane.getPreferredSize());
        scrollPane.setMinimumSize(scrollPane.getPreferredSize());
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);
        scrollPane.setAlignmentY(TOP_ALIGNMENT);
        this.add(scrollPane);
    }
    public ListModel<History> getListModel(){
       return getListModel(null);
    }
    public ListModel<History> getListModel(Reader reader) {
        if (reader == null) {
          return new DefaultListModel<>();
        }
        HistoryFacade historyFacade = new HistoryFacade(History.class);
        List<History> histories=null;

        histories = historyFacade.findAll(reader);
        DefaultListModel<History> defaultListModel = new DefaultListModel<>();
        for (History history : histories) {
            defaultListModel.addElement(history);
        }
        return defaultListModel;
    }

    private ListCellRenderer<? super History> createListHistoriesRenderer() {
      return new DefaultListCellRenderer(){
        private final Color background = new Color(0, 100, 255, 15);
        private final Color defaultBackground = (Color) UIManager.get("List.background");
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                        boolean isSelected, boolean cellHasFocus){
          Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
          if(component instanceof JLabel){
              JLabel label = (JLabel) component;
              History history = (History) value;
              StringBuilder sb = new StringBuilder();
              for (int i = 0; i < history.getBook().getAuthor().size(); i++) {
                  Author author = history.getBook().getAuthor().get(i);
                  sb.append(author.getName())
                    .append(" ")
                    .append(author.getLastname())
                    .append(". ");
              }
                  label.setText(String.format("%d. %s. %s %d. "
                          ,history.getBook().getId()
                          ,history.getBook().getCaption()
                          ,sb.toString()
                          ,history.getBook().getPublishedYear()

                  ));
                  label.setForeground(Color.RED);
              
              if(!isSelected){
                  label.setBackground(index % 2 == 0 ? background : defaultBackground);
              }
          }
          return component;
        }
      }; 
    }

    public JList<History> getJList() {
        return list;
    }
    
}
