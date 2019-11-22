package br.com.ia.leaf.service;

import java.awt.Image;

import br.com.ia.leaf.classifier.DecisionTree;
import ij.ImagePlus;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.ImageStatistics;

public class LeafService {

	DecisionTree cls = null;
	
	public LeafService() throws Exception{
		cls = new DecisionTree();
	}
			
	public String classify(String imagePath){
		
		ImagePlus imagePlus = null;
        Image image = null;
        ColorProcessor cprocessador = null;
        ByteProcessor processador = null;
        ImageStatistics dados = null;	
		
        imagePlus = new ImagePlus(imagePath);
        image = imagePlus.getImage();
        cprocessador = new ColorProcessor(image);
        processador = new ByteProcessor(cprocessador.convertToByte(true).createImage());
        processador.setThreshold(160, 255, 1);
        dados = processador.getStatistics();
        
        String  result = cls.classifyLeaf(dados.histogram[0], dados.stdDev, dados.mean);
        
        if(result!=null){
        	result = result.replaceAll("_", " ");
        }
        
		return result;
	}
	
}
