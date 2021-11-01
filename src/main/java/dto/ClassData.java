package dto;

import java.util.List;

public class ClassData {
	private String className;
	private String packageName;
	private String classType;
	private List<String> types;

	public ClassData(String className, String packageName, String classType, List<String> types) {
		super();
		this.className = className;
		this.packageName = packageName;
		this.classType = classType;
		this.types = types;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public ClassData() {
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

}
