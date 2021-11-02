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
		
		String description = "(Objetos DTO deve ser utilizados apenas "
				+ "por classes do tipo Controller).";

		List<ClassData> filterClass = 
				allClass.stream()
				.filter(x -> x.getClassType() != null)
				// busca todos com exceção (not)
				.filter(Predicate.not(x -> x.getClassType().contains("RestController") 
							|| x.getClassName().contains("DTO")))
				.collect(Collectors.toList());

		filterClass.forEach(x -> {
			x.getTypes().forEach(types -> {
				if (types.contains("DTO")) {
					classNameViolation.add(x.getClassName());
				}
			});
		});

		if (classNameViolation.size() > 0) {
			printResult(classNameViolation, "RA1", description);
			classNameViolation.clear();
		} else {
			System.out.println("\n- RA1: There was no violation RA1 on this current project.");
			System.out.println(description);
		}
	}

	// Classes DTO e Entity não devem depender de nenhuma classe específica do
	// sistema.
	public static void ra2(List<ClassData> allClass) {
		
		String description = "(Classes DTO e Entity não devem depender"
				+ " de nenhuma classe específica do sistema).";

		List<ClassData> filterClass = 
				allClass.stream()
				.filter(x -> x.getClassType() != null)
				.filter(x -> x.getClassType().contains("Entity") || 
						x.getClassName().contains("DTO"))
				.collect(Collectors.toList());

		filterClass.forEach(x -> {
			x.getTypes().forEach(types -> {
				if (types.contains("Service") || 
						types.contains("RestController") || 
							types.contains("Repository")) {
					classNameViolation.add(x.getClassName());
				}
			});
		});

		if (classNameViolation.size() > 0) {
			printResult(classNameViolation, "RA2", description);
			classNameViolation.clear();
		} else {
			System.out.println("\n- RA2: There was no violation RA2 on this current project.");
			System.out.println(description);
		}
	}

	// As classes do pacote Repository devem ser utilizadas apenas pelos Services.
	public static void ra3(List<ClassData> allClass) {

		String description = "(As classes do pacote Repository devem "
				+ "ser utilizadas apenas pelos Services).";
				
		List<ClassData> filterClass = 
				allClass.stream()
				.filter(x -> x.getClassType() != null)
				// busca todos com exceção (not)
				.filter(Predicate.not(x -> x.getClassType().contains("Service") || 
						x.getClassName().contains("Repository")))
				.collect(Collectors.toList());

		filterClass.forEach(x -> {
			x.getTypes().forEach(types -> {
				if (types.contains("Repository")) {
					classNameViolation.add(x.getClassName());
				}
			});
		});

		if (classNameViolation.size() > 0) {
			printResult(classNameViolation, "RA3", description);
			classNameViolation.clear();
		} else {
			System.out.println("\n- RA3: There was no violation RA3 on this current project.");
			System.out.println(description);
		}
	}

	// As classes JPA Repository devem depender apenas de Entity.
	public static void ra4(List<ClassData> allClass) {

		String description = "(As classes JPA Repository devem depender apenas de Entity).";
		List<ClassData> filterClass = 
				allClass.stream()
				.filter(x -> x.getClassType() != null)
				// busca todos com exceção (not)
				.filter(x -> x.getClassType().contains("Repository"))
				.collect(Collectors.toList());

		filterClass.forEach(x -> {
			x.getTypes().forEach(types -> {
				if (types.contains("Service") ||
						types.contains("RestController") ||
							types.contains("Repository") || 
								types.contains("DTO")) {
					classNameViolation.add(x.getClassName());
				}
			});
		});

		if (classNameViolation.size() > 0) {
			printResult(classNameViolation, "RA4", description);
			classNameViolation.clear();
		} else {
			System.out.println("\n- RA4: There was no violation RA4 on this current project.");
			System.out.println(description);
		}
	}

	// Toda classe Entity deve implementar Serializable.
	public static void ra5(List<ClassData> allClass) {
		
		String description = "(Toda classe Entity deve implementar Serializable).";

		List<ClassData> filterClass = 
				allClass.stream()
				.filter(x -> x.getClassType() != null)
				.filter(x -> x.getClassType().contains("Entity"))
				.collect(Collectors.toList());

		filterClass.forEach(x -> {
			x.getTypes().forEach(types -> {
				if (types.contains("Serializable")) {
					classNameViolation.add(x.getClassName());
				}
			});
		});

		filterClass.forEach(fc -> {
			for (int i = 0; i < classNameViolation.size(); i++) {
				if (classNameViolation.get(i).contains(fc.getClassName())) {
					classNameViolation.remove(i);
				}
			}
		});

		if (classNameViolation.size() > 0) {
			printResult(classNameViolation, "RA5", description);
			classNameViolation.clear();
		} else {
			System.out.println("\n- RA5: There was no violation RA5 on this current project.");
			System.out.println(description);
		}
	}

	private static void printResult(List<String> classNameViolation, String violation, String description) {
		System.out.println("\n- " + violation + ": The classes below committed violation:");
		System.out.println(description + "\n");
		classNameViolation.forEach(c -> System.out.println(c));
	}

}