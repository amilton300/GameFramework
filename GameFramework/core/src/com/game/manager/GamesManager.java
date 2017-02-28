/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.manager;

import com.game.interfaces.IFlexibleFactory;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * @author Amilton
 */
public class GamesManager{
 
    public String[] loadGames() throws MalformedURLException,
            ClassNotFoundException, InstantiationException,
            IllegalAccessException{
    currentDir = new File("./plugins/games");
    games = currentDir.list();
    
    for(int i = 0; i < games.length; ++i)
    	games[i] = games[i].split("\\.")[0];
    
    return games;
    }
    
    public IFlexibleFactory startGame(String name) throws MalformedURLException,
                        ClassNotFoundException, InstantiationException,
                        IllegalAccessException{
    currentDir = new File("./plugins/games");
    games = currentDir.list();
    URL[] jars = new URL[games.length];
    
    for(int i = 0; i < games.length; ++i){
    jars[i] = (new File("./plugins/games/" + name + ".jar")).toURL();
    }
    
    URLClassLoader ulc = new URLClassLoader(jars);
   
    return (IFlexibleFactory) Class.forName("com.game.factory." + name, true, ulc).newInstance();
    }
    
    public static GamesManager getInstance(){
    	if(instance == null)
    		instance = new GamesManager();
    	return instance;	
    }
    
    private GamesManager(){
    }
    
    private static GamesManager instance;
    
    File currentDir;
    String []games;
}