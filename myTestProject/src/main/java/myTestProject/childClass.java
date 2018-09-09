package myTestProject;

import com.alibaba.fastjson.JSON;

/**
 * @description: 测试有继承关系的类的序列化结果
 * @author: lizheng29
 * @create: 2018-08-13 16:02
 **/
public class ChildClass extends FatherClass {

    private String childName;

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public static void main(String[] args) {
        ChildClass childClass = new ChildClass();
        childClass.setFatherName("f");
        childClass.setChildName("c");

        System.out.println("=================="+JSON.toJSONString(childClass));
    }
}
