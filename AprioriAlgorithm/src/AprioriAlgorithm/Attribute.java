package AprioriAlgorithm;

import java.util.ArrayList;

public class Attribute {
	//Almacena el nombre del atributo
	public String name;
	//Almacena el tipo de atributo
	public String type;
	//Alamacena los posibles valores para el atributo
	public ArrayList<String> range;
	//Almacena el indece de atributo 
	public int index;
	
	//Constructores de la clase
	Attribute(){}
	Attribute(Attribute e){
		this.name=e.name;
		this.range=new ArrayList<String>(e.range);
		this.index=e.index;
		this.type=e.type;
	}
	Attribute(String n, ArrayList<String> r,String type,int i){
		this.name=n;
		this.range=r;
		this.index=i;
		this.type=type;
	}
	
	@Override
	public String toString(){ 
		String resp=name;
		if(!this.range.isEmpty()) {
			resp+=" {";
			for(int i=0;i<range.size()-1;i++)
				resp+=range.get(i)+",";
			resp+=range.get(range.size()-1)+"}";
		}
		resp+="\nType: "+this.type;
		return resp;		
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Override
	public boolean equals (Object o) {
        if (o instanceof Attribute) 
        	if(this.name.equals(((Attribute) o).name))
        		return true;
        	else
        		return false;
        else
        	return false;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getRange() {
		return range;
	}

	public void setRange(ArrayList<String> range) {
		this.range = range;
	}
	
	
}
