
public class SegmentTreeNode {
	private int l;
	private int r;
	private int sum;
	
	public SegmentTreeNode(int l, int r, int sum) {
		this.l = l;
		this.r = r;
		this.sum = sum;
	}
	
	public int getSum() {
		return sum;
	}
	
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	public int getL() {
		return l;
	}
	
	public int getR() {
		return r;
	}
}
