package com.lifegame;

import java.util.Scanner;

import com.lifegame.block.BlockImpl;
import com.lifegame.block.IBlock;
import com.lifegame.block.state.State;
import com.lifegame.board.Board;
import com.lifegame.board.Board.BoardIterator;
import com.lifegame.board.IBoard;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String[] params = sc.nextLine().split(" ");

		int width = Integer.parseInt(params[0]);
		int height = Integer.parseInt(params[1]);
		
		IBlock[][] blocks = new IBlock[height][width];
		for (int i = 0; i < height; i++) {
			char[] line = sc.nextLine().toCharArray();
			for (int j = 0; j < line.length; j++) {
				if (line[j] == '1')
					blocks[i][j] = new BlockImpl(State.ALIVE);
				else
					blocks[i][j] = new BlockImpl(State.DEAD);
			}
		}
		sc.close();

		if (width <= 0 || height <= 0)
			return;
		
		IBoard board = new Board(blocks);
		while (true) {
			board.calculateNext();
			board.tick();
//			boolean[][] next = board.release();
			BoardIterator iterator = board.iterator();
			IBlock block = iterator.current();
			do {
				do {
					System.out.print(block.isAlive()?1:0);
//					System.out.print(block.getAdjCnt());
				} while((block = iterator.next()) != null);
				System.out.println();
			} while((block = iterator.lineChange()) != null);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
