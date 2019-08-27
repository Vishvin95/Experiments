
public class SegmentTreeMinimum {

	private int arr[];
	private int st[];
	
	public SegmentTreeMinimum(int a[]) {
		this.arr = a;
		this.st = new int[4 * arr.length - 1];
		constructST();
	}
	
	public void constructST() {
		
		construct(arr, 0, arr.length - 1, 0);		
	}

	private void construct(int a[], int l, int r, int i) {
		if (l == r) {
			st[i] = a[l];
		} else {
			int mid = (l + r) / 2;
			construct(a, l, mid, 2 * i + 1);
			construct(a, mid + 1, r, 2 * i + 2);
			st[i] = Integer.min(st[2 * i + 1], st[2 * i + 2]);
		}
	}

	/*
	 * The functions returns the min element in the range from l and r
	 */
	public int RMQ(int l, int r) {
		return computeRMQ(0, l, r, 0, arr.length - 1);
	}

	private int computeRMQ(int i, int l, int r, int nl, int nr) {
		// No overlap
		if (l > nr || r < nl)
			return 0;

		// Node interval is completely inside query interval
		if (l <= nl && r >= nr)
			return st[i];

		// Node interval has partial overlap
		int mid = (nl + nr) / 2;
		if (l > mid)
			return computeRMQ(2 * i + 2, l, r, mid + 1, nr);
		else if (r <= mid)
			return computeRMQ(2 * i + 1, l, r, nl, mid);
		else {
			int lans = computeRMQ(2 * i + 1, l, mid, nl, mid);
			int rans = computeRMQ(2 * i + 2, mid + 1, r, mid + 1, nr);
			return Integer.min(lans, rans);
		}
	}
}
