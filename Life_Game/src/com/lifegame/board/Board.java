package com.lifegame.board;

import com.lifegame.block.IBlock;
import com.lifegame.block.state.State;
import com.lifegame.block.state.evaluate.Evaluator;

public class Board implements IBoard{
	int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
	int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};
	private IBlock[][] blocks;
	private int width = 0, height = 0;

	public static class BoardIterator {
		private int x;
		private int y;
		private IBlock[][] blocks;
		private int width = 0;
		private int height = 0;

		public BoardIterator(int x, int y, IBlock[][] board) {
			this.x = x;
			this.y = y;
			blocks = board;
			width = Evaluator.width(board);
			if (width > 0)
				height = Evaluator.height(board);
		}

		public BoardIterator(IBlock[][] board) {
			this(0, 0, board);
		}

		public IBlock current() {
			return blocks[x][y];
		}
		
		public IBlock next() {
			if (y+1 >= width) return null;
			return blocks[x][++y];
		}

		public IBlock prev() {
			if (y < 1) return null;
			return blocks[x][--y];
		}

		public IBlock up() {
			if (x < 1) return null;
			return blocks[--x][y];
		}

		public IBlock down() {
			if (x+1 >= height) return null;
			return blocks[++x][y];
		}

		public IBlock lineChange() {
			if (x+1 >= height) return null;
			return blocks[++x][(y = 0)];
		}

	}

	public Board(int sizeX, int sizeY, IBlock[][] blocks) {
		this.blocks = blocks;
		width = sizeX;
		height = sizeY;
	}
	
	public Board(IBlock[][] board) {
		this(Evaluator.width(board), Evaluator.height(board), board);
	}

	@Override
	public BoardIterator iterator() {
		return new BoardIterator(blocks);
	}

	/*public BoardIterator iterator(int x, int y) {
		return new BoardIterator(x, y, board);
	}*/

	@Override
	public void calculateNext() {
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				for (int k=0; k<8; k++) {
					int ny = i+dy[k];
					int nx = j+dx[k];
					if (ny < 0 || nx < 0 || ny >= height || nx >= width) continue;
					if (blocks[ny][nx].isAlive()) blocks[i][j].incrementAdjCnt();
				}
			}
		}
	}

	@Override
	public void tick() {
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) 
				blocks[i][j].tick();
		}
	}

	@Override
	public boolean[][] release() {
		boolean[][] ret = new boolean[height][width];
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				ret[i][j] = blocks[i][j].isAlive();
			}
		}
		return ret;
	}
}
