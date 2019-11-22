package br.com.ia.leaf.util;

import java.awt.Image;

import ij.ImagePlus;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.ImageStatistics;

public class GeneratorARFF {
    int pessoa = 1;
	public String generate(String path){

		StringBuilder arff = new StringBuilder();
		arff.append(getRelation());
		for (int i = 1; i <= 200; i++){
            arff.append(getData(path, 1, 14, Integer.toString(i), pessoa));
        }
		return arff.toString();
	}
	
	private String getRelation(){
		StringBuilder arff = new StringBuilder();
        arff.append("@relation Leaf\n");
        arff.append("@attribute histogram numeric\n");
        arff.append("@attribute stdDev numeric\n");
        arff.append("@attribute mean numeric\n");
        String pessoas = new String();
        for(int i = 1; i<=200; i++){
            pessoas += Integer.toString(i);
            if(i<200) pessoas += ',';


        }
        arff.append("@attribute class {" + pessoas + "}\n");
        arff.append("@data\n");
        return arff.toString();
	}
	
	private String getData(String path, int inicio, int fim, String tipo, int pessoa){
        System.out.println("Recolhendo dados da pessoa " + pessoa +", tipo:" + tipo);
		StringBuilder arff = new StringBuilder();
		ImagePlus imagePlus = null;
        Image image = null;
        ColorProcessor cprocessador = null;
        ByteProcessor processador = null;
        ImageStatistics dados = null;		
		
		for(int i=inicio; i<=fim; i++){
            String caminho;
		    if(i < 10) caminho = path + pessoa + "-0" + i + ".jpg";
		    else caminho = path + pessoa + "-" + i + ".jpg";
            imagePlus = new ImagePlus(caminho);
            image = imagePlus.getImage();
            cprocessador = new ColorProcessor(image);
            processador = new ByteProcessor(cprocessador.convertToByte(true).createImage());
            processador.setThreshold(160, 255, 1);
            dados = processador.getStatistics();
            arff.append(dados.histogram[0]+","+dados.stdDev+","+dados.mean+","+tipo+"\n");
        }
		
		this.pessoa++;
        return arff.toString();
	}
	
}
