/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktv20library;

import app.App;

/**
 *
 * @author Melnikov
 */
public class JKTV20Library {
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length==0){
            App.isBase = true;
        }else{
            App.isBase = false;
        }
        App app;
        app=new App();
        app.run();
    }
    
}
