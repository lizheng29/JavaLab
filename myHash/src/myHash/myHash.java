package myHash;

public class myHash {
	
	public static int myhash(int h){
		 h ^= (h >>> 20) ^ (h >>> 12);
		 return h ^ (h >>> 7) ^ (h >>> 4);
	}

	public static void main(String[] args) {
		Integer a = 123456;
		int sysHash = a.hashCode();
		//myhash其实是hashMap底层的方法
		int myhash = myhash(a);
		System.out.println("syshash:"+sysHash+"   myhash:"+myhash);
	}
}
