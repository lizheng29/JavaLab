package myTestProject;

/**
 * desc：字符串压缩函数：例如输入abbbbccaa，压缩后的字符串为ab4c2a2（4为字母b连续出现的次数）
 * createTime：2020/8/31 8:13 下午
 * author：李政
 * mail：superlizheng@didiglobal.com
 */
public class StringCompress {
    public static void main(String[] args) {
        String a = "abbbbccaa";
        char[] chars = a.toCharArray();
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 0; i <= chars.length; i++) {
            char cur = chars[i];
            if (i == chars.length - 1) {
                result.append(cur).append(count == 1 ? "" : count);
                break;
            }
            if (cur == chars[i + 1]) {
                count++;
            } else {
                result.append(cur).append(count == 1 ? "" : count);
                count = 1;
            }
        }

        System.out.println(result.toString());
    }

}
