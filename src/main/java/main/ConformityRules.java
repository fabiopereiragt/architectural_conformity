package main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dto.ClassData;

public class ConformityRules {

	private static List<String> classNameViolation = new ArrayList<String>();
	
	// Objetos DTO deve ser utilizados apenas por classes do tipo Controller.
	public static void ra1(List<ClassData> allClass) {	

		List<ClassData> filterClass = allClass
				.stream()
				.filter(x -> x.getClassType() != null)
				// busca todos com exceção (not) 
				.filter(Predicate.not(x -> x.getClassType().contains("RestController") 
						|| x.getClassName().contains("DTO")))
				.collect(Collectors.toList());		
		
		filterClass.forEach(x -> {				
			x.getTypes().forEach(types -> {
				if(types.contains("DTO")) {
					classNameViolation.add(x.getClassName());
				}
			});
		});
		
		if(classNameViolation.size() > 0) {
			printResult(classNameViolation, "RA1");
			classNameViolation.clear();
		}else {
			System.out.println("There was no violation RA1 on this current project.");
		}	
	}
	
	// Classes DTO e Entity não devem depender de nenhuma classe específica do sistema.
		public static void ra2(List<ClassData> allClass) {	

			List<ClassData> filterClass = allClass
					.stream()
					.filter(x -> x.getClassType() != null)					
					.filter(x -> x.getClassType().contains("Entity") 
							|| x.getClassName().contains("DTO"))
					.collect(Collectors.toList());		
			
		/*	System.out.println("RA2 ----------------------- \n");
			filterClass.forEach(x -> System.out.println(x.getClassName()));*/
			
			filterClass.forEach(x -> {				
				x.getTypes().forEach(types -> {
					if(types.contains("Service") || 
							types.contains("RestController") ||
								types.contains("Repository")) {
						classNameViolation.add(x.getClassName());
					}
				});
			});
			
			if(classNameViolation.size() > 0) {
				printResult(classNameViolation, "RA2");
				classNameViolation.clear();
			}else {
				System.out.println("\n- RA2: There was no violation RA2 on this current project.");
			}	
		}
		
		// As classes do pacote Repository devem ser utilizadas apenas pelos Services.
		public static void ra3(List<ClassData> allClass) {	

			List<ClassData> filterClass = allClass
					.stream()
					.filter(x -> x.getClassType() != null)
					// busca todos com exceção (not) 
					.filter(Predicate.not(x -> x.getClassType().contains("Service")
							|| x.getClassName().contains("Repository")))
					.collect(Collectors.toList());			
			
		 /* System.out.println("RA3 ----------------------- \n");
			filterClass.forEach(x -> System.out.println(x.getClassName()));*/
		
			filterClass.forEach(x -> {				
				x.getTypes().forEach(types -> {
					if(types.contains("Repository")) {
						classNameViolation.add(x.getClassName());
					}
				});
			});
			
			if(classNameViolation.size() > 0) {
				printResult(classNameViolation, "RA3");
				classNameViolation.clear();
			}else {
				System.out.println("\n- RA3: There was no violation RA3 on this current project.");
			}	
		}
		
		// As classes JPA Repository devem depender apenas de Entity.
		public static void ra4(List<ClassData> allClass) {	

			List<ClassData> filterClass = allClass
					.stream()
					.filter(x -> x.getClassType() != null)
					// busca todos com exceção (not) 
					.filter(x -> x.getClassType().contains("Repository"))
					.collect(Collectors.toList());			
			
		 /* System.out.println("RA4 ----------------------- \n");
			filterClass.forEach(x -> System.out.println(x.getClassName()));*/
		
			filterClass.forEach(x -> {				
				x.getTypes().forEach(types -> {
					if(types.contains("Service") || 
							types.contains("RestController") ||
								types.contains("Repository") ||
									types.contains("DTO")
								) {
						classNameViolation.add(x.getClassName());
					}
				});
			});
			
			if(classNameViolation.size() > 0) {
				printResult(classNameViolation, "RA4");
				classNameViolation.clear();
			}else {
				System.out.println("\n- RA4: There was no violation RA4 on this current project.");
			}	
		}
		
		// Toda classe Entity deve implementar Serializable.
		public static void ra5(List<ClassData> allClass) {	

			List<ClassData> filterClass = allClass
					.stream()
					.filter(x -> x.getClassType() != null)
					.filter(x -> x.getClassType().contains("Entity"))
					.collect(Collectors.toList());			
			
	    /*  System.out.println("RA4 ----------------------- \n");
			filterClass.forEach(x -> System.out.println(x.getClassName()));*/
		
			filterClass.forEach(x -> {				
				x.getTypes().forEach(types -> {
					if(types.contains("Service") || 
							types.contains("RestController") ||
								types.contains("Repository") ||
									types.contains("DTO")
								) {
						classNameViolation.add(x.getClassName());
					}
				});
			});
			
			if(classNameViolation.size() > 0) {
				printResult(classNameViolation, "RA4");
				classNameViolation.clear();
			}else {
				System.out.println("\n- RA4: There was no violation RA4 on this current project.");
			}	
		}
		
	private static void printResult(List<String> classNameViolation, String violation) {
		System.out.println("\n- " + violation + ": The classes below committed violation:\n");
		classNameViolation.forEach(c -> System.out.println(c));
		
	}

}