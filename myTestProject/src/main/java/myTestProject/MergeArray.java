package myTestProject;

/**
 * desc：合并有序数组
 * createTime：2020/8/31 8:14 下午
 * author：李政
 * mail：superlizheng@didiglobal.com
 */
public class MergeArray {

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5};
        int[] b = new int[]{2, 4, 6, 7, 8, 9};
        int[] c = merge(a, b);
        for (int value : c) {
            System.out.println(value);
        }
    }

    private static int[] merge(int[] array1, int[] array2) {
        if (array1 == null || array1.length == 0) {
            return array2;
        }
        if (array2 == null || array2.length == 0) {
            return array1;
        }
        int index1 = 0;
        int index2 = 0;
        int index3 = 0;
        int[] result = new int[array1.length + array2.length];
        while (index1 < array1.length && index2 < array2.length) {
            if (array1[index1] <= array2[index2]) {
                result[index3] = array1[index1];
                index1++;
            } else {
                result[index3] = array2[index2];
                index2++;
            }
            index3++;
        }
        while (index1 < array1.length) {
            result[index3] = array1[index1];
            index1++;
            index3++;
        }
        while (index2 < array2.length) {
            result[index3] = array2[index2];
            index2++;
            index3++;
        }
        return result;
    }
}
