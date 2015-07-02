package com.lifegame.block;

public interface IBlock{
	public boolean isAlive();
    public int getAdjCnt();
    public void incrementAdjCnt();
    public void tick();
}
