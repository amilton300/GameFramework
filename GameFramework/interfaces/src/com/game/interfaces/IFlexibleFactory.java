package com.game.interfaces;

public interface IFlexibleFactory {
	public IPrototype create(String name);
    public boolean addPrototype(String name);
    public boolean removePrototype(String name);
    public void setCore(ICore core);
}
