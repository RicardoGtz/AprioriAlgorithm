package AprioriAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Kmeans {
	ArrayList<Attribute> attributes;
	ArrayList<ArrayList<String>> instances;
	ArrayList<Cluster> clusters;
	int classifierAttribute;
	//Constructor de la clase
	Kmeans(ArrayList<Attribute> attributes,ArrayList<ArrayList<String>> instances,int classifierAttribute){
		this.attributes=attributes;
		this.instances=instances;
		this.classifierAttribute=classifierAttribute;
		this.clusters=new ArrayList<Cluster>();
	}
	//Construye los grupos
	public void buildClusters(int numClusters) {
		clusters.clear();
		TreeSet<Integer> id=new TreeSet<Integer>();
		while(clusters.size()!=numClusters) {			
			int index=(int)(Math.random()*(this.instances.size()-1));			
			if(id.add(index)) { 				
				clusters.add(new Cluster(instances.get(index),classifierAttribute,2));
			}
		}
		id=null;
		fillClusters();
		ArrayList<Cluster> aux=new ArrayList<Cluster>();
		while(!this.clusters.equals(aux)) {			
			aux=cloneClustersList(this.clusters);
			updateClustersCentroid();
			clearClusters();
			fillClusters();
		}					
	}
	private void clearClusters() {
		for(Cluster value:this.clusters)
			value.clearInstances();		
	}
	private void updateClustersCentroid() {
		for(Cluster value:this.clusters) {
			value.updateCentroid();	
		}
	}
	public static ArrayList<Cluster> cloneClustersList(ArrayList<Cluster> list) {
		ArrayList<Cluster> clone = new ArrayList<Cluster>(list.size());
	    for (Cluster item : list) clone.add(item.clone());
	    return clone;
	}
	private void fillClusters() {
		for(ArrayList<String> value:this.instances) {
			double minDist=Double.MAX_VALUE; int minCluster=0;
			for(int i=0;i<this.clusters.size();i++) {
				double aux=clusters.get(i).getDistance(value);
				if(aux<minDist) {
					minDist=aux;
					minCluster=i;
				}
			}
			this.clusters.get(minCluster).addInstance(value);
		}		
	}
	@Override
	public String toString() {
		String str="";int i=1;
		for(Cluster value:this.clusters) {
			str+="*************************\n"
				+"Grupo "+i+" Elementos:"+value.instances.size()+"\n";
			if(this.classifierAttribute!=-1) {
				HashMap<String,Integer> auxMap=value.getClassesFrequency();
				for (Map.Entry<String, Integer> entry : auxMap.entrySet()) {
				    str+="Clase=" + entry.getKey() + ", Conteo=" + entry.getValue()+"\n";
				}
				
			}else {
				str+="Sin clases asigandas";
			}
			
															
			for(ArrayList<String> instance:value.instances)
				str+=instance+"\n";
			i++;
		}
		return str;
	}
}
