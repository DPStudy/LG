package com.lifegame.board;

import com.lifegame.block.state.State;
import com.lifegame.board.Board.BoardIterator;

public interface IBoard {
    public void calculateNext();
    public void tick();
    public boolean[][] release();
    public BoardIterator iterator();
}
