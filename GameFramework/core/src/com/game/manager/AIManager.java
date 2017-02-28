package com.game.manager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.game.abstractClasses.AICreator;

public class AIManager {
	
	public String[] loadAI(String gameName)throws MalformedURLException,
    ClassNotFoundException, InstantiationException,
    IllegalAccessException{
    	currentDir = new File("./plugins/artificialIntelligence/Custom/" + gameName);
    	aI = currentDir.list();
    	if(aI != null)
    		for(int i = 0; i < aI.length; ++i)
    			aI[i] = aI[i].split("\\.")[0];
    	else{
    		currentDir = new File("./plugins/artificialIntelligence/Default");
    		aI = currentDir.list();
    		if(aI != null)
        		for(int i = 0; i < aI.length; ++i)
        			aI[i] = aI[i].split("\\.")[0];
    	}
    	return aI;
    }
    
    public AICreator startAI(String gameName, String aIName) throws MalformedURLException,
    ClassNotFoundException, InstantiationException,
    IllegalAccessException{
    	String dir = "./plugins/artificialIntelligence/Custom/";
    	currentDir = new File(dir + gameName);
    	dir += gameName;
    	aI = currentDir.list();
    	if(aI == null){
    		dir = "./plugins/artificialIntelligence/Default";
    		currentDir = new File(dir);
    		aI = currentDir.list();
    	}
    	
    	URL[] jars = new URL[aI.length];

    	for(int i = 0; i < aI.length; ++i){
    		jars[i] = (new File(dir + "/" + aIName + ".jar")).toURL();
    	}
    	
    	URLClassLoader ulc = new URLClassLoader(jars);
    	   
        return (AICreator) Class.forName("com.game.aICreator." + aIName + "AICreator", true, ulc).newInstance();
    }
    
    public static AIManager getInstance(){
    	if(instance == null)
    		instance = new AIManager();
    	return instance;
    }
    
    private AIManager(){	
    }
    
    private static AIManager instance;
    
    File currentDir;
    String []aI;
}
