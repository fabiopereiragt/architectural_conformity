package test;

public class Dog {
	private int age;
	private String name;
	
	void doSomething(Byte[] valueByte, int z) {
		String varS = "Not make sense";
		System.out.println(varS);
	}

	public int getAge() {

		int ageBase = 5;
		return age + ageBase;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
