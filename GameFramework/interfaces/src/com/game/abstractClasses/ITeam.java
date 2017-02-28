package com.game.abstractClasses;

import java.util.Observable;
import java.util.Observer;

import com.game.interfaces.IArtificialIntelligence;

public abstract class ITeam implements Observer {
	public abstract void init();
	public abstract void reset();
	public abstract void updateTeam();
	public void setAI(IArtificialIntelligence ai){	
		this.ai = ai;
	};
	public SubjectTemplateMethod getSubject(){
		return this.player;
	}
	public void update(Observable arg0, Object arg1) {
		
	}
	
	public void executeSpecialAbility(int playerID){}
	
	protected IArtificialIntelligence ai;
	protected SubjectTemplateMethod player;
}