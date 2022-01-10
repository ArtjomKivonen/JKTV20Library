/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.mycomopnents.ButtonComponent;
import app.mycomopnents.CaptionComponent;
import app.mycomopnents.EditorComponent;
import app.mycomopnents.GuestButtonsComponent;
import app.mycomopnents.GuestComponent;
import app.mycomopnents.InfoComponent;
import app.mycomopnents.TabAddReaderComponents;
import entity.Reader;
import entity.Role;
import entity.User;
import entity.UserRoles;
import facade.ReaderFacade;
import facade.RoleFacade;
import facade.UserFacade;
import facade.UserRolesFacade;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Melnikov
 */
public class GuiApp extends JFrame{
    public static final int WITH_WINDOWS = 600;
    public static final int HEIGHT_WINDOWS = 450;
    public static User user;
    public static String role;
    private GuestComponent guestComponent;
    private GuestButtonsComponent guestButtonsComponent;
    private TabAddReaderComponents tabAddReaderComponents;
    private  GuiApp guiApp = this;
    private UserFacade userFacade = new UserFacade();
    private ReaderFacade readerFacade = new ReaderFacade();
    private RoleFacade roleFacade = new RoleFacade();
    private UserRolesFacade userRolesFacade = new UserRolesFacade();

    
    public GuiApp() {
        List<User> users = userFacade.findAll();
        if(users.isEmpty()) {
          User user = new User();
          user.setLogin("admin");
          user.setPassword("12345");
          
          Reader reader = new Reader();
          reader.setFirstname("admin");
          reader.setLastname("admin");
          reader.setPhone("123456789");
          readerFacade.create(reader);
          user.setReader(reader);
          userFacade.create(user);
          
          Role role = new Role();
          role.setRoleName("ADMINISTRATOR");
          roleFacade.create(role);
          
          UserRoles userRoles = new UserRoles();
          userRoles.setUser(user);
          userRoles.setRole(role);
          userRolesFacade.create(userRoles);
          
          role = new Role();
          role.setRoleName("MANAGER");
          roleFacade.create(role);
          userRoles = new UserRoles();
          userRoles.setRole(role);
          userRoles.setUser(user);
          userRolesFacade.create(userRoles);
          
          role = new Role();
          role.setRoleName("READER");
          roleFacade.create(role);
          userRoles = new UserRoles();
          userRoles.setRole(role);
          userRoles.setUser(user);
          userRolesFacade.create(userRoles);
        }
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        this.setTitle("JPTV20 Library");
        this.setPreferredSize(new Dimension(WITH_WINDOWS,HEIGHT_WINDOWS));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        guestComponent = new GuestComponent();
        guestButtonsComponent = new GuestButtonsComponent("Войти", "Зарегистрироваться", GuiApp.WITH_WINDOWS, 50,100,10,200);
        this.add(guestButtonsComponent);
        guestButtonsComponent.getButton1().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int widthWindows = 350;
                JDialog dialogLogin = new JDialog(guiApp, "Введите логин и пароль", Dialog.ModalityType.DOCUMENT_MODAL);
                dialogLogin.setPreferredSize(new Dimension(widthWindows, 250));
                dialogLogin.setMinimumSize(getPreferredSize());
                dialogLogin.setMaximumSize(getPreferredSize());
                dialogLogin.getContentPane().setLayout(new BoxLayout(dialogLogin.getContentPane(), BoxLayout.Y_AXIS));
                dialogLogin.setLocationRelativeTo(null);

                CaptionComponent captionComponent = new CaptionComponent("Enter login and password", 400, 27);
                InfoComponent infoComponent = new InfoComponent("", widthWindows, 27);
                EditorComponent loginComponent= new EditorComponent("Login", widthWindows, 27, 50, 200);
                EditorComponent passwordComponent= new EditorComponent("Password", widthWindows, 27, 50, 200);
                ButtonComponent enterComponent = new ButtonComponent("login", widthWindows, 27, 150, 200);
                
                dialogLogin.getContentPane().add(Box.createRigidArea(new Dimension(0, 10)));
                dialogLogin.getContentPane().add(captionComponent);
                dialogLogin.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
                dialogLogin.getContentPane().add(loginComponent);
                dialogLogin.getContentPane().add(Box.createRigidArea(new Dimension(0, 10)));
                dialogLogin.getContentPane().add(passwordComponent);
                dialogLogin.getContentPane().add(Box.createRigidArea(new Dimension(0, 10)));
                dialogLogin.getContentPane().add(enterComponent);
                
                enterComponent.getButton().addActionListener(new ActionListener(){
                  @Override
                  public void actionPerformed(ActionEvent ae) {
                    User user = userFacade.find(loginComponent.getEditor().getText().trim());
                    if(user == null){
                        infoComponent.getInfo().setText("Нет такого пользователя");
                        return;
                    }
                    if(!user.getPassword().equals(passwordComponent.getEditor().getText().trim())){
                      infoComponent.getInfo().setText("Нет такого пользователя, или неверный пароль");
                      return;
                    }
                    GuiApp.user = user;
                    String role = userRolesFacade.topRole(user);
                    GuiApp.role = role;
                    dialogLogin.setVisible(false);
                    dialogLogin.dispose();
                  }
                });
                
                
                dialogLogin.pack();
                dialogLogin.setVisible(true);
                
//                login.getContentPane().add(tabAddReaderComponents);
//                login.pack();
//                login.setVisible(true);
            }
        });
        guestButtonsComponent.getButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                guiApp.getContentPane().remove(guestComponent);
                tabAddReaderComponents = new TabAddReaderComponents(GuiApp.WITH_WINDOWS);
                guiApp.getContentPane().add(tabAddReaderComponents);
                guiApp.repaint();
                guiApp.revalidate();
                
                
            }
        });
        this.add(guestComponent);
        
//        
//        JTabbedPane jTabbedPane = new JTabbedPane();
//        jTabbedPane.setPreferredSize(new Dimension(WITH_WINDOWS,HEIGHT_WINDOWS));
//        jTabbedPane.setMinimumSize(jTabbedPane.getPreferredSize());
//        jTabbedPane.setMaximumSize(jTabbedPane.getPreferredSize());
//        TabReaderComponents tabReaderComponents = new TabReaderComponents(this.getWidth());
//        jTabbedPane.addTab("Читатель", tabReaderComponents);
//        TabManagerComponent tabManagerComponent = new TabManagerComponent(this.getWidth());
//        jTabbedPane.addTab("Библиотекарь", tabManagerComponent);
//        this.getContentPane().add(jTabbedPane);
//        TabDirectorComponent tabDirectorComponent = new TabDirectorComponent(this.getWidth());
//        jTabbedPane.addTab("Директор", tabDirectorComponent);
        
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuiApp().setVisible(true);
            }
        });
    }

}
