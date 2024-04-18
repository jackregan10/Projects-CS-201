public class PercolationUF implements IPercolate   {

    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    PercolationUF(IUnionFind finder, int size) {
        VBOTTOM = size * size + 1;
        VTOP = size * size;
        finder.initialize(size * size + 2);
        myFinder = finder;
        myOpenCount = 0;
        myGrid = new boolean[size][size];
    }


    @Override
    public void open(int row, int col) {
        if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (myGrid[row][col]) {
			return;
		}
        myGrid[row][col] = true;
		myOpenCount += 1;

        if (row == 0) {
            myFinder.union(row*myGrid.length + col, VTOP);
        }
        if (row == myGrid.length - 1) {
            myFinder.union(row*myGrid.length + col, VBOTTOM);
        }

        int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};
        int[] coords = {row, col};
        for(int k = 0; k < rowDelta.length; k++){
            int r = coords[0] + rowDelta[k];
            int c = coords[1] + colDelta[k];
            if (inBounds(r, c) && myGrid[r][c]) {
                myFinder.union(row*myGrid.length + col, r*myGrid.length + c);
            }

        }
    }
    @Override
    public boolean isOpen(int row, int col) {
        if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row, col));
		}
		return myGrid[row][col];
    }
    @Override
    public boolean isFull(int row, int col) {
        if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
				String.format("(%d,%d) not in bounds", row,col));
		}

		return myFinder.connected(row*myGrid.length + col, VTOP);
    }
    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }
    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
    private boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
    }
}
