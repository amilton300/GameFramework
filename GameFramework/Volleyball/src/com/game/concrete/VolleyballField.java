package com.game.concrete;

import com.game.factory.Volleyball;
import com.game.interfaces.IField;
import com.game.interfaces.IGraphicController;
import com.game.interfaces.IPrototype;

public class VolleyballField implements IField, IPrototype{

	public void init(){
		this.graphicController = Volleyball.core.getGraphicController();
	}

	public void showField() {
		this.graphicController.drawField(0, 0, 600, 30);
		this.graphicController.drawRect(310, 30, 15, 100);
	}
	
	public IPrototype pClone() {
		return new VolleyballField();
	}
	
	public VolleyballField(){
	}
	
	private IGraphicController graphicController;
}
