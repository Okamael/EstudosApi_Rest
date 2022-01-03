package com.estudos.projeto.cars.ProjetoCars.infraestructure;

import java.io.InputStream;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

import lombok.SneakyThrows;

public class FileUtils {
	
	@SneakyThrows
	public static String loadFileContens(String fileName) {
		
		InputStream is = new ClassPathResource(fileName).getInputStream();
		byte[] data = new byte[is.available()];
		is.read(data);
		return new String(data);
	}
	
	public static String loadFileContens(String fileName , Map<String,String> replacements) {
		
		String fileContents = loadFileContens(fileName);
		for(Map.Entry<String, String> entry:replacements.entrySet()) {
			fileContents = fileContents.replace("{{"+entry.getKey()+"}}", entry.getValue());
		}
		
		return fileContents;
	}

}
