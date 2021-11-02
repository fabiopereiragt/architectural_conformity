package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dto.ClassData;

public class Program {

	private static List<ClassData> classData = new ArrayList<ClassData>();

	private static final String[] FILE_PATH = { "/home/fabio/git/legiscam" };

	public static void main(String[] args) {	
		
		//Parser.listClasses(new File(FILE_PATH));		
		for(String path : FILE_PATH) {
			Parser.listClasses(new File(path));
			
			Parser.getAllClassData().forEach(allClass -> {
				classData.add(new ClassData(
						allClass.getClassName(),
						allClass.getPackageName(),
						allClass.getClassType(),
						allClass.getTypes()));
			});	
			
			Parser.getAllClassData().clear();
		}		

		for (ClassData c : classData) {
			System.out.println("-------------------------------");
			System.out.println("Class Name: " + c.getClassName());
			System.out.println("Package: " + c.getPackageName());
			System.out.println("Class Type: " + c.getClassType());
			c.getTypes().forEach(t -> System.out.println("Types: " + t));
			System.out.println();
		}
		
		System.out.println("============== Conformity Architectural =============");
		ConformityRules.ra1(classData);
		ConformityRules.ra2(classData);
		ConformityRules.ra3(classData);
		ConformityRules.ra4(classData);
		ConformityRules.ra5(classData); 
	}

}
