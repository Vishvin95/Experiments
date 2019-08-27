
public class EndPoint {

	public static void main(String[] args) {
		int a[] = {2,7,51,4,37,13};
		
		//Segment tree with sum query
		SegmentTreeSum st = new SegmentTreeSum(a);
		System.out.println(st.query(1, 5));
		st.update(1, 2);
		System.out.println("Done");
		
		// Segment tree with minimum query
		SegmentTreeMinimum stm = new SegmentTreeMinimum(a);
		stm.constructST();
		System.out.println(stm.RMQ(1, 4));
	}

}
