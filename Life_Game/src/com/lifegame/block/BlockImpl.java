package com.lifegame.block;

import com.lifegame.block.state.State;
import com.lifegame.block.state.evaluate.Evaluator;

public class BlockImpl implements IBlock {
	private State state;
    private int currAdjCnt;
    private int nextAdjCnt;
    public BlockImpl(State state) {
        this.state = state;
    }
	@Override
    public int getAdjCnt() {
        return currAdjCnt;
    }
	@Override
    public void incrementAdjCnt() {
    	nextAdjCnt++;
    }
	@Override
    public void tick() {
        currAdjCnt = nextAdjCnt;
        nextAdjCnt = 0;
        state = Evaluator.findStateByAdjCnt(state, currAdjCnt);
    }
	@Override
	public boolean isAlive() {
		return state==State.ALIVE;
	}
}
