package AprioriAlgorithm;

import java.util.ArrayList;

public class Measure {
	int confusionMatrix[][];
	ArrayList<String>classes;
	public Measure(Attribute classifer) {
		this.classes=new ArrayList<String>(classifer.range);
		this.confusionMatrix=new int[classes.size()][classes.size()];
	}
	public void addMeasure(String actual,String predicted) {
		int actualIndex=classes.indexOf(actual);
		int predictedIndex=classes.indexOf(predicted);
		confusionMatrix[actualIndex][predictedIndex]+=1;
	}
	public String getMatrix(){
		String str="\tConfusion Matrix\n";
		for(int i=0;i<confusionMatrix.length;i++)
			str+="\t"+classes.get(i);
		str+="\n";
		for(int i=0;i<confusionMatrix.length;i++) {
			for(int j=0;j<confusionMatrix[0].length;j++) {
				str+="\t"+confusionMatrix[i][j];
			}
			str+="   |"+classes.get(i)+"\n";
		}
		return str;
	}
	public double getTPRate(String className) {
		int classIndex=classes.indexOf(className);
		if(classIndex!=-1) {
			return (confusionMatrix[classIndex][classIndex]*1.0)/getRowSum(classIndex);
		}
		return -1;
	}
	public double getFPRate(String className) {
		int classIndex=classes.indexOf(className);
		if(classIndex!=-1) {
			int FP=0,TN=0;
			for(int i=0;i<confusionMatrix.length;i++)
				if(i!=classIndex) {
					FP+=confusionMatrix[i][classIndex];
					TN+=getRowSum(i);
				}
			return (FP*1.0)/TN;
		}
		return -1;
	}
	public double getPrecision(String className) {
		int classIndex=classes.indexOf(className);
		if(classIndex!=-1) {
			int TP=confusionMatrix[classIndex][classIndex];
			int FP=getColumnSum(classIndex);
			return (TP*1.0)/FP;
		}
		return -1;
	}
	public double getFMeasure(String className) {
		int classIndex=classes.indexOf(className);
		if(classIndex!=-1) {
			double precision=getPrecision(className);
			double TPR=getTPRate(className);
			return (2*precision*TPR)/(precision+TPR);
		}
		return -1;
	}
	public double getKappa() {
		int sum=getSum();
		double p0=(getDiagonalSum()*1.0)/sum;		
		double pe=getRowXColumnSum()/Math.pow(sum, 2);
		return (p0-pe)/(1-pe);
	}
	public double getMAE() {
		int sum=0;
		for(int i=0;i<confusionMatrix.length;i++)
			sum+=Math.abs(getRowSum(i)-confusionMatrix[i][i]);
		return (sum*1.0)/getSum();
	}
	private int getRowXColumnSum() {
		int sum=0;
		for(int i=0;i<confusionMatrix.length;i++)
			sum+=getColumnSum(i)*getRowSum(i);
		return sum;
	}
	private int getSum() {
		int sum=0;
		for(int i=0;i<confusionMatrix.length;i++)
			for(int j=0;j<confusionMatrix.length;j++)
				sum+=confusionMatrix[i][j];
		return sum;
	}
	private int getDiagonalSum() {
		int sum=0;
		for(int i=0;i<confusionMatrix.length;i++)
			sum+=confusionMatrix[i][i];
		return sum;
	}
	private int getColumnSum(int classIndex) {
		int sum=0;
		for(int i=0;i<confusionMatrix.length;i++)
			sum+=confusionMatrix[i][classIndex];
		return sum;
	}
	private int getRowSum(int classIndex) {
		int sum=0;
		for(int i=0;i<confusionMatrix.length;i++)
			sum+=confusionMatrix[classIndex][i];
		return sum;
	}
}
