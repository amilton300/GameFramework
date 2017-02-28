package com.game.interfaces;

import java.awt.Color;

public interface IGraphicController {
	public void drawRect(float x, float y, float width, float height);
	public void drawCircle(float x, float y, float radius);
	public void drawPlayer(float x, float y, float width, float height, boolean specialEnabled);
	public void drawField(float x, float y, float width, float height);
	public void drawBackgroundEffects(float x, float y);
	public void drawSpecialItem(float x, float y, float width, float height);
	public void drawPlayerEnergyBar(float x, float y, float width, float height, String color);
}
