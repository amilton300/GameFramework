package com.game.button;

import java.util.ArrayList;
import java.util.List;

import com.game.interfaces.IMenuComposite;

public class ButtonsComposite implements IMenuComposite{

	@Override
	public void moveIn() {
		for(IMenuComposite child: this.children)
			child.moveIn();
	}
	 
	public boolean addChild(IMenuComposite item){
		return this.children.add(item);
	}

	public boolean removeChild(IMenuComposite item) {
		return this.children.remove(item);
	}
	
	public ButtonsComposite(){
		this.children = new ArrayList<IMenuComposite>();	
	}
	
	protected List<IMenuComposite> children;
}
