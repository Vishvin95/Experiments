
public class SegmentTreeSum {
	private int a[];
	private SegmentTreeNode st[];
	
	public SegmentTreeSum(int a[]) {
		this.a = a;
		int size = (int) (Math.pow(2, 1 + Math.ceil(Math.log(a.length)/Math.log(2)))-1);
		
		//st = new SegmentTreeNode[4*a.length-1];
		st = new SegmentTreeNode[size];
		constructST(0,0,a.length-1);
	}

	private void constructST(int node, int start, int end)
	{
		if(start==end)
		{
			st[node] = new SegmentTreeNode(start, end, a[start]);
		}
		else
		{
			int mid = (start+end)/2;
			int lc = getLeftChild(node);
			int rc = getRightChild(node);
			
			constructST(lc,start,mid);
			constructST(rc,mid+1,end);
			st[node] = new SegmentTreeNode(st[lc].getL(), st[rc].getR(), st[lc].getSum() + st[rc].getSum());
		}
	}
	
	public void update(int index, int newValue)
	{
		if(index>=a.length)			
			System.out.println("Invalid Index");
		else
		{
			int diff = a[index] - newValue;
			a[index] = newValue;
			
			updateDiff(diff,index,0);
		}
	}
	
	private void updateDiff(int diff, int index, int cur)
	{
		if(cur>st.length || st[cur] == null)
			return;
		
		st[cur].setSum(st[cur].getSum()-diff);
		int mid = (st[cur].getL() + st[cur].getR())/2;
		if(index<=mid)
			updateDiff(diff, index, getLeftChild(cur));
		else
			updateDiff(diff, index, getRightChild(cur));
	}
	
	private int queryEvaluate(int i, int l, int r)
	{
		int stCurNodeLow = st[i].getL();
		int stCurNodeHigh = st[i].getR();
		int mid = (stCurNodeHigh + stCurNodeLow) / 2;
		
		// No overlap between intervals
		if(stCurNodeLow>r || stCurNodeHigh<l)
			return 0;
		
		// Node interval includes query interval completely 
		if(stCurNodeLow>=l && stCurNodeHigh<=r)
			return st[i].getSum();
		
		// Query interval is included completely in node interval
		if(l>mid)
			return queryEvaluate(getRightChild(i),l,r);
		else if(r<=mid)
			return queryEvaluate(getLeftChild(i),l,r);
		else
			return queryEvaluate(getLeftChild(i),l,mid) + queryEvaluate(getRightChild(i),mid+1,r);
	}
	
	public int query(int l, int r)
	{
		return queryEvaluate(0,l,r);
	}
	
	private int getLeftChild(int i)
	{
		return 2*i+1;
	}
	
	private int getRightChild(int i)
	{
		return 2*i+2;
	}
	
	private int getParent(int i)
	{
		return (i-1)/2;
	}
}
