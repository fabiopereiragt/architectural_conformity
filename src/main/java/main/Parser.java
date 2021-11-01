package main;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.google.common.base.Strings;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.DirExplorer;
import dto.ClassData;

public class Parser {
	
	private static List<ClassData> allClassData = new ArrayList<ClassData>();
	
	public static void listClasses(File projectDir) {
		
	new DirExplorer(
			(level, path, file) -> path.endsWith(".java"), 
			(level, path, file) -> {
				System.out.println(path);
				System.out.println(Strings.repeat("=", path.length()));
				try {
					CompilationUnit cu = StaticJavaParser.parse(file);
					Visitor v = new Visitor();
					v.visit(cu, null);
					ClassData classData = new ClassData(
							v.getNameClass(), 
							v.getPackageName(),
							v.getClassType(),
							v.getFields());
					insert(classData);
					System.out.println(); // empty line
				} catch (IOException e) {
					new RuntimeException(e);
				}
			}).explore(projectDir);
		}
	
	private static void insert(ClassData cl) {
		allClassData.add(cl);
	}
	
	public static List<ClassData> getAllClassData() {
		return allClassData;
	}
	
}
