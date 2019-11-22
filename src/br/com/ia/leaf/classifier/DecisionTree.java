package br.com.ia.leaf.classifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class DecisionTree {
	
	final static String CLASSIFIER_PATH = "model/j48.model";
	final static String DATASET_PATH = "model/dataset.arff";
	private J48 j48;
	private Instances data;
	
	public DecisionTree() throws Exception{
		j48 = (J48)weka.core.SerializationHelper.read(CLASSIFIER_PATH);
		BufferedReader reader = new BufferedReader(new FileReader(DATASET_PATH));
		data = new Instances(reader);
	}
	
	
	public String classifyLeaf(double histogram,  double stdDev, double mean){
		int result;
		String predClass;
		
		try {
			result = (int) j48.classifyInstance(getInstance(histogram,  stdDev, mean));
			Attribute attr = data.classAttribute();
			predClass = attr.value((int) result);

			
		} catch (Exception e) {
			return null;
		}
		return predClass;
	}
	
	private Instance getInstance(double histogram,  double stdDev, double mean) throws IOException{
		Instance instance = new DenseInstance(3);		
		data.setClassIndex(data.numAttributes()-1);
		instance.setDataset(data);
		instance.setValue(data.attribute(0), histogram);
		instance.setValue(data.attribute(1), stdDev);
		instance.setValue(data.attribute(2), mean);

		return instance;

	}

}
