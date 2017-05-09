package subsets;

import java.util.ArrayList;
import java.util.List;

import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0;

public class Solution {
    public void subsets(int[] nums) {
    	
    	//List<List<Integer>> result = new ArrayList<List<Integer>>();
    	int limit = 1 << nums.length;
    	//System.out.println(Integer.toBinaryString(limit));
    	int i, j, k;
    	for (i = 0; i < limit; i++)  
        {  
                j = i;  
                k = 0;  
                System.out.println(Integer.toBinaryString(j));
                System.out.print("{");  
                while (j!=0)  
                {  
                        if ((j & 1)!=0)  //j的右面第一位不是0，即第k位为1
                        {
                                System.out.print(nums[k]);  //输出第k位
                        }  
                        j >>= 1;  //右移一位
                        ++k;  
                }  
                System.out.print("}\n");  
        }  
    }
    
    public static void main(String[] args) {
    	int[] nums = {1,2,3};
    	Solution s = new Solution();
		s.subsets(nums);
	}
}