package com.game.interfaces;

public interface IMenuComposite {
	public void moveIn();
	public boolean addChild(IMenuComposite component);
	public boolean removeChild(IMenuComposite component);
}
