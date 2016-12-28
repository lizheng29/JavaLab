package javaReflection;

public class MySelfInfo {
	
	public String name;
	
	public int sex;
	
	private int height;
	
	private double weight;
	
	@MyAnnotation
	public void showMe(){
		
		System.out.println("我的个人信息：");
		
		System.out.println("姓名："+name+ "   性别："+sex+"   身高："+height+"   体重："+weight);
	}

}
