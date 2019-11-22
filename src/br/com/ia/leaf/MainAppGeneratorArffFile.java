package br.com.ia.leaf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.ia.leaf.util.GeneratorARFF;

public class MainAppGeneratorArffFile {

	public static void main(String[] args) throws IOException {
		GeneratorARFF arff = new GeneratorARFF();
		String file = arff.generate("C:\\Users\\jorla\\Desktop\\face\\");
		
		File fileArff = new File("C:\\Users\\jorla\\Desktop\\face\\dataset.arff");
		
		if (fileArff.exists()) {
			fileArff.delete();
		}

		fileArff.createNewFile();		
		FileWriter fw = new FileWriter(fileArff.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(file);
		bw.close();
		
		System.out.println("END!");
		
	}

}
