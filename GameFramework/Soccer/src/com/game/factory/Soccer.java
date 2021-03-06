package com.game.factory;

import java.util.HashMap;
import java.util.Map;

import com.game.interfaces.ICore;
import com.game.interfaces.IFlexibleFactory;
import com.game.interfaces.IPrototype;

public class Soccer implements IFlexibleFactory {
	
	public boolean addPrototype(String name) {
		try{
		IPrototype prototype = (IPrototype) Class.forName("com.game.concrete.Soccer" + name).newInstance();
		prototypes.put(name, prototype);
		}catch(Exception e){
			System.out.println(e.toString());
			return false;
		}
	return true;
	}

	public boolean removePrototype(String name) {
		try{
		this.prototypes.remove(name);
		}catch(Exception e){
			return false;
		}
	return true;
	}

	public IPrototype create(String name) {
		if(prototypes.containsKey(name))
			return prototypes.get(name).pClone();
		return null;
	}
	
	public void setCore(ICore core){
		Soccer.core = core;
	}
	
	public static ICore core;
	
	private Map<String,IPrototype> prototypes = new HashMap<String,IPrototype>();
}
