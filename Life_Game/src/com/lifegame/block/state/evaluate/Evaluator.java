package com.lifegame.block.state.evaluate;

import com.lifegame.block.state.State;

public class Evaluator {
    private Evaluator(){}
    public static State findStateByAdjCnt(State state, int adjCnt) {
    	if (state == State.ALIVE) {
    		if (adjCnt == 2 || adjCnt == 3) return State.ALIVE;
    		else return State.DEAD;
    	} else {
    		if (adjCnt == 3) return State.ALIVE;
    		else return State.DEAD;
    	}
    }
	public static <T> int width(T[][] arr) {
		if (height(arr) > 0)
			return arr[0].length;
		return 0;
	}

	public static <T> int height(T[][] arr) {
		return arr.length;
	}
}
