
public class RecursiveSearch {
	
	public static void recursiveSearch(int[] data, int start ,int end ,int target){
		
		
		int m = start + (end-start)>>1;
		if(target==data[m]){
			System.out.println("查找的数据为："+data[m] + "下标为："+m);
		}else if(target<data[m]){
			end = m;
			recursiveSearch(data,start,end,target);
		}else if(target>data[m]){
			start = m;
			recursiveSearch(data,start,end,target);
		}
		
	}

	public static void main(String[] args) {
		int[] data = {1,2,3,4,5,6,7,8,9,10,13,15,16,17,20,25,26,27,28,37,38,39,58,69,79,88,99};
		recursiveSearch(data,0,data.length,100);
	}
}
