package AprioriAlgorithm;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
//import java.util.Collections;

public class Data {
	File data;
	String name;
	ArrayList<Attribute> attributes;
	int classifierAttribite=-1;	
	ArrayList<ArrayList<String>> instances;
	
	Data(){	}
	
	Data(Data e){
		this.data=e.data;
		this.name=e.name;
		this.attributes=new ArrayList<Attribute>();
		for(Attribute value:e.attributes)
			this.attributes.add(new Attribute(value));
		this.classifierAttribite=e.classifierAttribite;
		this.instances=new ArrayList<ArrayList<String>>();
		for(ArrayList<String> value:e.instances)
			this.instances.add(new ArrayList<String>(value));
	}
	
	public void setData(File e){
		//Establece el atributo data como el archivo e
		data=e;
		//Creamos una lisa de instancias y atributos
		instances= new ArrayList<ArrayList<String>>();
		attributes=new ArrayList<Attribute>();
		try {
			//Leemos y desplegamos el contenido del archivo de texto
			FileReader fr= new FileReader(data);//aqui va el archivo
			BufferedReader br=new BufferedReader(fr);
			String flag;
			int i=0;
			//Leemos el archivo hasta encontrar la linea @DATA
			while (!(flag=(br.readLine()).toLowerCase()).matches("@data.*")) {
				//Si encontramos la linea que inicia como @RELATION
				//La almacenamos como nombre del conjunto
				if(flag.matches("@relation.*"))
					name=flag.replaceAll("@relation\\s", "");//Limpia la linea
				//Si encontramos que continen los valores de las clases
				//Limpia la linea la almacenana en el arreglo de clases
				if(flag.matches("@attribute.*")){					
					if(flag.indexOf("{")!=-1) {	
						String n=(flag.replaceAll("@attribute ", "")).replaceAll(" [{].*[}]","");
						String r[]=flag.substring(flag.indexOf('{')+1, flag.indexOf('}')).split(",[\\s]*");
						//Añade el atributo a la lista de atributos
						attributes.add(new Attribute(n,new ArrayList<String>(Arrays.asList(r)),"discrete",i));
					}else { 
						String n=(flag.replaceAll("@attribute ", "")).replaceAll("numeric|integer|real","");
						attributes.add(new Attribute(n,new ArrayList<String>(),"numeric",i));
					}					
					i++;
				}
			}
			//Continuamos leyendo el archivo hasta el final
			while((flag=br.readLine())!=null){
				//Si encontramo el simbolo % significa que termino el archivo
				if(flag.matches("%"))
					break;
				//Almacena cada instancia de datos en un arreglo
				if(!flag.matches("")) {
					String aux[]=flag.split(",");
					instances.add(new ArrayList<>(Arrays.asList(aux)));
				}
			}
			//Deosrdenamos las instancias que ingresamos
			Collections.shuffle(instances);
			br.close();
			
		} catch ( IOException e1) {
			System.out.println("ERROR: The file cannot be read!");							
		}	
		
	}
	//Establece el indice del atributo que almacena la clase
	public void setClassifierAttribite(int index){
		this.classifierAttribite=index;
	}
	public BufferedReader getBufferedReader(){
		BufferedReader inputReader = null;
		try {
			inputReader=new BufferedReader(new FileReader(data));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: The file cannot be read!");
		}
		return inputReader;
	}
	@Override
	public String toString() {
		String resp="";
		for(Attribute value:this.attributes)
			resp+=value+"\n";
		return resp;
	}

}
