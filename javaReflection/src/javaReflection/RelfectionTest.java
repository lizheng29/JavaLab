package javaReflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RelfectionTest {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		/*MySelfInfo mySelfInfo = new MySelfInfo();
		mySelfInfo.name ="LZ";
		mySelfInfo.sex = 1;
		mySelfInfo.showMe();*/
		
		Class myClass = Class.forName("javaReflection.MySelfInfo");
		System.out.println("获取构造方法信息");
		Constructor[] constructors = myClass.getConstructors();
		for(Constructor constructor : constructors) {
			System.out.println("构造方法："+constructor.toString());
		}
		System.out.println("=====================\n");
		
		
		System.out.println("获取method信息");
		Method[] methods = myClass.getMethods();
		for(Method m:methods){
			System.out.println("方法名："+m.getName()+"返回类型"+m.getReturnType()+"参数个数"+m.getParameterCount());
		}
		System.out.println("=====================\n");
		
		
		System.out.println("获取declaredMethod信息");
		Method[] declaredMethods = myClass.getDeclaredMethods();
		for(Method m : declaredMethods) {
			System.out.println("方法名"+m.getName()+"方法返回类型"+m.getReturnType());
		}
		System.out.println("=====================\n");
		
		System.out.println("获取field信息(公有）");
		Field[] fields = myClass.getFields();
		for(Field f:fields){
			System.out.println("变量名："+f.getName()+"变量类型"+f.getType());
		}
		System.out.println("=====================\n");
		
		System.out.println("获取field信息（全部）");
		Field[] declaredField = myClass.getDeclaredFields();
		for(Field f:declaredField){
			System.out.println("变量名："+f.getName()+"变量类型"+f.getType());
		}
		
		//实例化一个新的bean
		MySelfInfo m = (MySelfInfo)myClass.newInstance();
		m.name = "LZ";
		m.sex = 1;
		//设置身高（私有属性）
		Field height = m.getClass().getDeclaredField("height");
		height.setAccessible(true);
		height.set(m, 182);
		height.setAccessible(false);
		//设置体重（私有属性）
		Field weight = m.getClass().getDeclaredField("weight");
		weight.setAccessible(true);
		weight.set(m, 89.0);
		weight.setAccessible(false);
		//用反射调用bean的方法
		Method method =m.getClass().getMethod("showMe");
		method.invoke(m);
		//m.showMe();
		
	}

}
