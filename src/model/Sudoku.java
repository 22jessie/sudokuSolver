package model;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {
	private Byte[][] board;
	private int n;
	private boolean solved;
	private List<Byte> solution;


	public Sudoku(List<Byte> board){
		int i,j,k;
		this.board=new Byte[Constants.DIMENSION][Constants.DIMENSION];
		for(i=k=0;i<Constants.DIMENSION;i++) {
			for(j=0;j<Constants.DIMENSION;j++) {
				this.board[i][j]=board.get(k++);
			}
		}
		this.n=(int) Math.sqrt(Constants.DIMENSION);
		solution=null;
	}
	
	public List<Byte> getSolution(){
		return solution;
	}

	public void solve() {
		boolean hasSolution=true;
		byte n;
		for(byte i=0; i < Constants.DIMENSION;i++) {
			for(byte j=0; j < Constants.DIMENSION;j++) {
				n=board[i][j];
				if(n!=Constants.DEFAULT_VALUE) {
					if(inColumn(i,j, n) || inRow(i,j, n) || inQuadrant(i, j, n)) {
						hasSolution=false;
						break;
					}
				}
			}
			if(!hasSolution) {
				break;
			}
		}
		if(hasSolution) {
			solved=false;
			solve((byte)0,(byte)0);
			if(solved) {
				solution=new ArrayList<Byte>();
				for(int i=0; i < Constants.DIMENSION;i++) {
					for(int j=0; j < Constants.DIMENSION;j++) {
						solution.add(board[i][j]);
					}
				}
			}
		}
	}

	
	private void solve(byte col,byte row) {
		if(!solved) {
			if(row>=Constants.DIMENSION) {
				solved=true;
			}else {		
				if(board[row][col]==Constants.DEFAULT_VALUE) {
					for(byte n=1; n<=Constants.DIMENSION; n++) {
						if(!inQuadrant(row,col, n) && !inColumn(row,col, n) && !inRow(row,col, n)) {
							board[row][col]=n;
							if(col+1>=Constants.DIMENSION) {
								solve((byte)(0),(byte)(row+1));
							}else {
								solve((byte)(col+1),(byte)(row));
							}
							if(solved) {
								break;
							}else {
								board[row][col]=Constants.DEFAULT_VALUE;
							}
						}
					}
				}else {
					if(col+1>=Constants.DIMENSION) {
						solve((byte)(0),(byte)(row+1));
					}else {
						solve((byte)(col+1),(byte)(row));
					}
				}
			}
		}
	}

	private boolean inQuadrant(byte row,byte col, byte number) {
		int i,j;
		int m,o;
		int aux;
		int r,c;

		m=(row/n);
		aux=(col/n);
		for(i=0;i<n;i++) {
			for(j=0,o=aux;j<n;j++) {
				r=m*n+i;
				c=o*n+j;
				if(board[r][c]==number && r!=row && c!=col) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean inRow(byte row,byte col, byte n) {
		for(int x=0; x < Constants.DIMENSION; x++) {
			if(board[row][x]==n && x!=col) {
				return true;
			}
		}
		return false;
	}

	private boolean inColumn(byte row, byte col, byte n) {
		for(int x=0; x < Constants.DIMENSION; x++) {
			if(board[x][col]==n && x != row) {
				return true;
			}
		}
		return false;
	}

}
