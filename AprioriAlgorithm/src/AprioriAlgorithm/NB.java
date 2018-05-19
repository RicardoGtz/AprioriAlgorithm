package AprioriAlgorithm;

import java.util.ArrayList;

public class NB {
	ArrayList<Attribute> attributes;
	ArrayList<ArrayList<String>> instances;
	int classifierAttribute;
	ArrayList<double[][]>attributesFrequency;
	double[] classesFrequency;
	
	NB(ArrayList<Attribute> attributes,ArrayList<ArrayList<String>> instances,int classifierAttribute){
		this.attributes=attributes;
		this.instances=instances;
		this.classifierAttribute=classifierAttribute;		
		this.buildModel();
	}
	public String getDecision(ArrayList<String> instance){
		double max=Double.MIN_VALUE;
		String decision="";
		for(int i=0;i<classesFrequency.length;i++) {
			double aux=classesFrequency[i];
			for(int j=0;j<attributesFrequency.size();j++)
				if(attributesFrequency.get(j)[attributes.get(j).range.indexOf(instance.get(j))][i]!=0)
					aux*=attributesFrequency.get(j)[attributes.get(j).range.indexOf(instance.get(j))][i];
			if(aux>max) {
				max=aux;decision=attributes.get(classifierAttribute).range.get(i);
			}
		}
		return decision;
	}
	private void buildModel() {
		classesFrequency=getTableFrequency(attributes.get(classifierAttribute), instances);		
		attributesFrequency=new ArrayList<double[][]>();
		for(int i=0;i<attributes.size();i++)
			if(i!=classifierAttribute)
				attributesFrequency.add(this.getFrequency(this.attributes.get(i),
														  this.attributes.get(this.classifierAttribute),
														  this.instances));		
	}
	private double[] getTableFrequency(Attribute classAttribute, ArrayList<ArrayList<String>> currentData) {
		double frequency[]=new double[classAttribute.range.size()];
		for(int i=0;i<currentData.size();i++){
			String cls=currentData.get(i).get(classAttribute.index);
			if(classAttribute.range.indexOf(cls)!=-1)
				frequency[classAttribute.range.indexOf(cls)]+=1;
			else
				System.out.println("Value not found");
		}
		for(int i=0;i<frequency.length;i++)
			frequency[i]=frequency[i]/currentData.size();
		return frequency;
	}
	private double[][] getFrequency(Attribute attribute,Attribute classAttribute, ArrayList<ArrayList<String>> currentData) {
		double frequency[][]=new double[attribute.range.size()][classAttribute.range.size()];
		int classes[]=new int[classAttribute.range.size()];
		for(int i=0;i<currentData.size();i++){
			String val=currentData.get(i).get(attribute.index);
			String cls=currentData.get(i).get(classAttribute.index);
			if(attribute.range.indexOf(val)!=-1&&classAttribute.range.indexOf(cls)!=-1){
				frequency[attribute.range.indexOf(val)][classAttribute.range.indexOf(cls)]+=1;
				classes[classAttribute.range.indexOf(cls)]+=1;
			}else
				System.out.println("Value not found");
		}
		for(int i=0;i<frequency.length;i++)
			for (int j=0;j<frequency[0].length;j++)
				frequency[i][j]=frequency[i][j]/classes[j];		
		return frequency;
	}
}
