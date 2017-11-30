package myTestProject;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Test {

    public static void calendarTest() {

        // 获得10天前的date====================================================================================
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 10);
        Date date = calendar.getTime();
        System.out.println(date.toLocaleString());
        // 格式化date为string
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateStr = sdf.format(date);
        dateStr += " 00:00:00";
        System.out.println(dateStr);

    }

    public static void hashTest() {
        // =====================================&运算以及HashMAPkey定位算法==============================================
        System.out.println("BinaryString:" + Integer.toBinaryString(20));
        System.out.println("BinaryString:" + Integer.toBinaryString(16));
        System.out.println("BinaryString:" + Integer.toBinaryString(15));
        System.out.println("BinaryString:" + Integer.toBinaryString(20 & (16 - 1)) + "   " + (20 & (16 - 1)));
    }

    public static void lambdaTest() {
        // ======================================lambda表达式=====================================================

        // 1. 不需要参数,返回值为 5
        // System.out.println(() -> 5);

        // 2. 接收一个参数(数字类型),返回其2倍的值
        Consumer<Integer> consumer1 = x -> System.out.println("x*2=" + x * 2);
        consumer1.accept(6);

        // 3. 接受2个参数(数字),并返回他们的差值
        // int x_y = (x, y) -> x – y ;
        // Consumer consumer2 = (x, y) -> System.out.println(x -> 2 * x);

        // 4. 接收2个int型整数,返回他们的和
        // int x_y=(int x, int y) -> x + y ;
        // System.out.println("x+y="+x_y);
        // 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)
        String s = "hahaha";
        Consumer<String> consumer = (String s3) -> System.out.println(s3 += "_suffix");
        consumer.accept(s);

        String[] num = {"1", "2", "3", "4", "5", "6"};
        List<String> nums = Arrays.asList(num);
        // 以前的循环方式
        for (String n : nums) {
            System.out.print(n + "; ");
        }

        System.out.println("\n");
        // 使用 lambda 表达式以及函数操作(functional operation)
        nums.forEach((one) -> System.out.print(one + "; "));

        System.out.println("\n");
        // 在 Java 8 中使用双冒号操作符(double colon operator)
        nums.forEach(System.out::print);

        System.out.println("\n");
        // 返回123456各+1之后大于等于5的数
        List<Integer> plusOne = nums.stream().map(e -> new Integer(e)).map(e -> e += 1).filter(e -> e >= 5)
                .collect(Collectors.toList());
        System.out.println("123456各+1之后大于等于5的数" + plusOne);

        System.out.println("原来的数组：" + nums);
        IntSummaryStatistics stats = nums.stream().map(e -> new Integer(e)).mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest number in List : " + stats.getMax());
        System.out.println("Lowest number in List : " + stats.getMin());
        System.out.println("Sum of all numbers : " + stats.getSum());
        System.out.println("Average of all numbers : " + stats.getAverage());

        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                System.out.print(i);
            }
        }).start();

    }

    public static void plusAndMinusTest() {
        // ++ -- 关联系：从右到左
        // ===============================================================================
        int i = 4;
        System.out.println(i++ + 5); // 9
        int j = 4;
        System.out.println(++j - 6); // -1
        int k = 4;
        System.out.println(k-- - 1); // 3
        int l = 4;
        System.out.println(l++ + 5 + (++l - 6) - (l-- - 1)); // 4

    }

    public static void setTest() {
        Set<Integer> intSet = new HashSet<Integer>();
        intSet.add(5);
        intSet.add(4);
        intSet.add(3);
        intSet.add(2);
        intSet.add(1);
        intSet.add(4);// 重复不添加
        intSet.add(5);// 重复不添加
        System.out.println("set size is : " + intSet.size());
        Iterator<Integer> iterator = intSet.iterator();
        while (iterator.hasNext()) {
            Integer cur = iterator.next();
            System.out.println(cur);
        }

    }

    public static void stringTest() {
        String str = "abc"; // 字符串池新创建的对象
        String str1 = "abc"; // 已存在，指向旧对象
        String str2 = new String("abc"); // 堆内存的新对象

        System.out.println(str == str1); // true 同为字符串常量池内容
        System.out.println(str1 == "abc"); // true 同为字符串常量池内容
        System.out.println(str2 == "abc"); // false str2在堆内存中，是新对象
        System.out.println(str1 == str2); // false str2在堆内存中，是新对象
        System.out.println(str1.equals(str2)); // true string类重写了equals方法
        System.out.println(str1 == str2.intern()); // true
        // string.intern()：如果字符串池中存在则返回，不存在则添加然后返回
        System.out.println(str2 == str2.intern()); // false
        // string.intern()：如果字符串池中存在则返回，不存在则添加然后返回
        System.out.println(str1.hashCode() == str2.hashCode()); // true
        // 字符串内容相同，hashCode相同

        StringBuilder stringBuilder = new StringBuilder(str); // JDK1.5之后才有，线程不安全，速度快，字符串+操作时候内部使用StringBuilder();
        System.out.println(stringBuilder.reverse());

        StringBuffer stringBuffer = new StringBuffer(str);// 线程安全，速度慢
        System.out.println(stringBuffer.reverse());

        str.charAt(0); // 不可用：str[0]


        String errorMessage = "";
        //errorMessage += 123;
        if(errorMessage.length()!=0){
            System.out.println("errorMessage:"+errorMessage);
        }else {
            System.out.println("errorMessage:"+errorMessage);
        }
    }

    public static void PrimitiveTypeTest() {
        // byte
        System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE);
        System.out.println("包装类：java.lang.Byte");
        System.out.println("最小值：Byte.MIN_VALUE=" + Byte.MIN_VALUE);
        System.out.println("最大值：Byte.MAX_VALUE=" + Byte.MAX_VALUE);
        System.out.println();

        // short
        System.out.println("基本类型：short 二进制位数：" + Short.SIZE);
        System.out.println("包装类：java.lang.Short");
        System.out.println("最小值：Short.MIN_VALUE=" + Short.MIN_VALUE);
        System.out.println("最大值：Short.MAX_VALUE=" + Short.MAX_VALUE);
        System.out.println();

        // int
        System.out.println("基本类型：int 二进制位数：" + Integer.SIZE);
        System.out.println("包装类：java.lang.Integer");
        System.out.println("最小值：Integer.MIN_VALUE=" + Integer.MIN_VALUE);
        System.out.println("最大值：Integer.MAX_VALUE=" + Integer.MAX_VALUE);
        System.out.println();

        // long
        System.out.println("基本类型：long 二进制位数：" + Long.SIZE);
        System.out.println("包装类：java.lang.Long");
        System.out.println("最小值：Long.MIN_VALUE=" + Long.MIN_VALUE);
        System.out.println("最大值：Long.MAX_VALUE=" + Long.MAX_VALUE);
        System.out.println();

        // float
        System.out.println("基本类型：float 二进制位数：" + Float.SIZE);
        System.out.println("包装类：java.lang.Float");
        System.out.println("最小值：Float.MIN_VALUE=" + Float.MIN_VALUE);
        System.out.println("最大值：Float.MAX_VALUE=" + Float.MAX_VALUE);
        System.out.println();

        // double
        System.out.println("基本类型：double 二进制位数：" + Double.SIZE);
        System.out.println("包装类：java.lang.Double");
        System.out.println("最小值：Double.MIN_VALUE=" + Double.MIN_VALUE);
        System.out.println("最大值：Double.MAX_VALUE=" + Double.MAX_VALUE);
        System.out.println();

        // char
        System.out.println("基本类型：char 二进制位数：" + Character.SIZE);
        System.out.println("包装类：java.lang.Character");
        // 以数值形式而不是字符形式将Character.MIN_VALUE输出到控制台
        System.out.println("最小值：Character.MIN_VALUE=" + (int) Character.MIN_VALUE);
        // 以数值形式而不是字符形式将Character.MAX_VALUE输出到控制台
        System.out.println("最大值：Character.MAX_VALUE=" + (int) Character.MAX_VALUE);
    }

    public static void charTest() {
        char a = 'A';
        char b = 'b';
        char d = 'c' + 1;

        System.out.println((int) a);
        System.out.println((int) b);
        System.out.println((int) 'c');
        System.out.println((int) d);
    }

    public static void printfDateTest() {
        // 初始化 Date 对象
        Date date = new Date();

        //printf中%n为换行
        // c的使用
        System.out.printf("全部日期和时间信息：%tc%n", date);
        // f的使用
        System.out.printf("年-月-日格式：%tF%n", date);
        // d的使用
        System.out.printf("月/日/年格式：%tD%n", date);
        // r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n", date);
        // t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n", date);
        // R的使用
        System.out.printf("HH:MM格式（24时制）：%tR", date);
    }

    public static void instantTest() {
        // 获取北京时间
        Instant now = Instant.now();
        OffsetDateTime bjNow = now.atOffset(ZoneOffset.ofHours(8));
        OffsetDateTime bjBefore = bjNow.plusMinutes(-30);
        System.out.println(bjNow);
        System.out.println(bjBefore);

        System.out.println("now.getEpochSecond():" + now.getEpochSecond());
        System.out.println("System.currentTimeMillis():" + System.currentTimeMillis());

        System.out.println(bjNow.toInstant().toEpochMilli());
        System.out.println(System.currentTimeMillis());
        System.out.println(now.toEpochMilli());

        String url = "http://smart-asr-test.tinetcloud" +
                ".com:2333/asr_callback/3000000-20170831161301-01089170766-18500136173-record-10.10.1.212" +
                "-1504167183.61-in.wav/tinet";
        System.out.println(url.split("/")[4]);

        String fileName = "3000000-20171017142338-18330691161-18330691161-record-10.10.1.212-1504513418.57-in.wav";
        System.out.println(fileName.substring(0, fileName.lastIndexOf(".")));


        String dateString = fileName.split("-")[1];
        LocalDateTime cdrTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern
                ("yyyyMMddHHmmss"));
        System.out.println(cdrTime);
        if (cdrTime.plusHours(6).isBefore(LocalDateTime.now())) {
            System.out.println("从" + cdrTime + "到现在，超过了6小时");
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒")));
    }

    public static void fileTest() {

        try {
            URI uri = new URI("http://clink-voice-wav.s3.cn-north-1.amazonaws.com" +
                    ".cn/4144003/20171024/3004414-20171024145142-18703881273-01043989530-record-10.10.62.252" +
                    "-1508827902.104092-in.wav?response-content-disposition=attachment%3B%20filename%3D3004414" +
                    "-20171024145142-18703881273-01043989530-record-10.10.62.252-1508827902.104092-in" +
                    ".wav&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20171024T065415Z&X-Amz-SignedHeaders=host&X-Amz" +
                    "-Expires=86400&X-Amz-Credential=AKIAPPHJOV2EGMJMH4DQ%2F20171024%2Fcn-north-1%2Fs3%2Faws4_request&X" +
                    "-Amz-Signature=20c4eb0862d3bbc5a9df9cac581e955569fe360e6fbc2375565f78b6fcfa7159");

            URL url = new URL("http://clink-voice-wav.s3.cn-north-1.amazonaws.com" +
                    ".cn/4144003/20171024/3004414-20171024145142-18703881273-01043989530-record-10.10.62.252" +
                    "-1508827902.104092-in.wav?response-content-disposition=attachment%3B%20filename%3D3004414" +
                    "-20171024145142-18703881273-01043989530-record-10.10.62.252-1508827902.104092-in" +
                    ".wav&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20171024T065415Z&X-Amz-SignedHeaders=host&X-Amz" +
                    "-Expires=86400&X-Amz-Credential=AKIAPPHJOV2EGMJMH4DQ%2F20171024%2Fcn-north-1%2Fs3%2Faws4_request&X" +
                    "-Amz-Signature=20c4eb0862d3bbc5a9df9cac581e955569fe360e6fbc2375565f78b6fcfa7159");
            //File file = new Path(url.getPath()).toFile();
            File file = null;
            file = new File(url.toURI());
            boolean isExists = file.exists();
            System.out.println(isExists);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void paramTest(){

        int a = 10;
        Persion persion = new Persion(20);

        System.out.println("原来的值：  "+a+"   "+persion.age);

        print(a,persion);

        System.out.println("方法外的值："+a+"   "+persion.age);
        System.out.println("所以，a是值传递，没有改变原来的值，person是引用传递，改变了原来的值");

    }

    public static void print(int a, Persion persion){
        a++;
        persion.age++;
        System.out.println("方法内的值："+a+"   "+persion.age);
    }

    static class Persion {
        public int age;
        Persion(int age){
            this.age=age;
        }
    }

    static void aesTest(){
        String text = "3004414-20171024145142-18703881273-01043989530-record-10.10.62.252";
        String password = "fdfdfdfdfdfdfdfdfdf";
        String after = AesUtil.encrypt(text,password);

        String decrypted = AesUtil.decrypt(after,password);

        System.out.println(text);
        System.out.println(after);
        System.out.println(decrypted);
    }

    public static void main(String[] args) {
        // calendarTest();
        // hashTest();
        // lambdaTest();
        //plusAndMinusTest();
        // setTest();
         //stringTest();
        //PrimitiveTypeTest();
        // charTest();
        //printfDateTest();
        //instantTest();
        //fileTest();
        //paramTest();
        aesTest();
    }
}
