package br.com.ia.leaf;

import br.com.ia.leaf.service.LeafService;

public class MainAppDecisionTree {

	final static String PATH = "C:\\Users\\jorla\\Desktop\\face\\";
	
	public static void main(String[] args) throws Exception {
		LeafService cls = new LeafService();
		
		System.out.println("1 ==>"+cls.classify(PATH+"1-10.jpg"));
		System.out.println("2 ==> "+cls.classify(PATH+"2-01.jpg"));
		System.out.println("3 ==> "+cls.classify(PATH+"3-07.jpg"));
		System.out.println("4 ==> "+cls.classify(PATH+"4-11.jpg"));
		System.out.println("5 ==> "+cls.classify(PATH+"5-06.jpg"));
		
	}

}	
