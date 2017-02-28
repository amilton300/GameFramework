package com.game.interfaces;

public interface ICommandInvoker {
	public void execute();
	public void addCommand(ICommand command);
	public boolean hasCommand();
}
