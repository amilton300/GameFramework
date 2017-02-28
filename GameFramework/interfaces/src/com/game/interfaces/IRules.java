package com.game.interfaces;

public interface IRules {
	public void init();
	public void verifyPlayerPoint();
	public void verifyEnemyPoint();
	public void verifyGameOver();
	public void verifyCollision();
}
