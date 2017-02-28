package com.game.concrete;

import com.game.factory.Soccer;
import com.game.interfaces.IField;
import com.game.interfaces.IGraphicController;
import com.game.interfaces.IPrototype;

public class SoccerField implements IField, IPrototype{

	public void init(){
		this.graphicController = Soccer.core.getGraphicController();
	}
	public void showField() {
		this.graphicController.drawField(0, 0, 600, 30);
		this.graphicController.drawBackgroundEffects(200, 80);
		this.graphicController.drawRect(10, 30, 10, 150);
		this.graphicController.drawRect(580, 30, 10, 150);
	}
	
	public IPrototype pClone() {
		return new SoccerField();
	}
	
	public SoccerField(){
	}
	
	private IGraphicController graphicController;
}
