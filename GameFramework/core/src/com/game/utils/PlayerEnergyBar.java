package com.game.utils;

import java.util.Observable;
import java.util.Observer;

import com.game.abstractClasses.SubjectTemplateMethod;
import com.game.core.Core;
import com.game.interfaces.IGraphicController;

public class PlayerEnergyBar implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		this.playerEnergy = ((SubjectTemplateMethod) o).getEnergy();	
	}
	
	public void draw(){
		if(this.playerEnergy > 50)
			this.graphicController.drawPlayerEnergyBar(5, 250, this.playerEnergy*5,
					10, "RED");
		else
			this.graphicController.drawPlayerEnergyBar(5, 250, this.playerEnergy*5,
					10, "GREEN");
		
	}
	
	public PlayerEnergyBar() {
		this.graphicController = Core.getInstance().getGraphicController();
	}
	
	private IGraphicController graphicController;
	
	private int playerEnergy = 0;
}
