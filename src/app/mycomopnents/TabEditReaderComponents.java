/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomopnents;

import entity.Reader;
import facade.ReaderFacade;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

/**
 *
 * @author Melnikov
 */
public class TabEditReaderComponents extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private ComboBoxModel comboBoxModel;
    private ComboBoxReadersComponent comboBoxReadersComponent;
    private EditorComponent nameComponent;
    private EditorComponent lastNameComponent;
    private EditorComponent phoneComponent;
    private ButtonComponent buttonComponent;
    private Reader reader;
    public TabEditReaderComponents(int widthPanel) {
//        this.comboBoxModel = comboBoxModel;
        initComponents(widthPanel);
    }

    private void initComponents(int widthPanel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponent = new CaptionComponent("Изменение данных читателя", widthPanel, 31);
        this.add(captionComponent); 
        infoComponent = new InfoComponent("", widthPanel, 31);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        phoneComponent = new EditorComponent("Телефон", widthPanel, 31, 200);
        lastNameComponent = new EditorComponent("Фамилия", widthPanel, 31, 300);
        nameComponent = new EditorComponent("Имя", widthPanel, 31, 300);
        comboBoxReadersComponent = new ComboBoxReadersComponent("Читатели", widthPanel, 30, 300);
//        comboBoxReadersComponent.getComboBox().setModel(comboBoxModel);
        comboBoxReadersComponent.getComboBox().setSelectedIndex(-1);
        comboBoxReadersComponent.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                reader=(Reader) ie.getItem();
                nameComponent.getEditor().setText(reader.getFirstname());
                lastNameComponent.getEditor().setText(reader.getLastname());
                phoneComponent.getEditor().setText(reader.getPhone());
            }
        });
        this.add(comboBoxReadersComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        this.add(nameComponent);
        this.add(lastNameComponent);
        this.add(phoneComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        buttonComponent = new ButtonComponent("Изменить данные читателя", widthPanel, 35, widthPanel/3+5, 200);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButtonEditReader());
    }
    private ActionListener clickToButtonEditReader(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(nameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setText("Введите имя");
                    return;
                }
                reader.setFirstname(nameComponent.getEditor().getText());
                if(lastNameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setText("Введите фамилию");
                    return;
                }
                reader.setLastname(lastNameComponent.getEditor().getText());
                
                if(phoneComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setText("Введите телефон");
                    return;
                } 
                reader.setPhone(phoneComponent.getEditor().getText());
                
                ReaderFacade readerFacade = new ReaderFacade();
                
                try {
                    readerFacade.edit(reader);
                    infoComponent.getInfo().setText("Читатель успешно изменен");
                    comboBoxReadersComponent.getComboBox().setSelectedIndex(-1);
                    phoneComponent.getEditor().setText("");
                    lastNameComponent.getEditor().setText("");
                    nameComponent.getEditor().setText("");
                } catch (Exception e) {
                    infoComponent.getInfo().setText("Читателя изменить не удалось");
                }
            }
        };
    }

    public void addComboBoxModel() {
        nameComponent.getEditor().setText("");
        lastNameComponent.getEditor().setText("");
        phoneComponent.getEditor().setText("");
        infoComponent.getInfo().setText("");
        ReaderFacade readerFacade = new ReaderFacade();
        List<Reader> readers = readerFacade.findAll();
        DefaultComboBoxModel<Reader> defaultComboBoxModel = new DefaultComboBoxModel<>();
        for (Reader reader : readers) {
            defaultComboBoxModel.addElement(reader);
        }
        comboBoxReadersComponent.getComboBox().setModel(defaultComboBoxModel);
        comboBoxReadersComponent.getComboBox().setSelectedIndex(-1);
        phoneComponent.getEditor().setText("");
        lastNameComponent.getEditor().setText("");
        nameComponent.getEditor().setText("");
    }
    
    
}
