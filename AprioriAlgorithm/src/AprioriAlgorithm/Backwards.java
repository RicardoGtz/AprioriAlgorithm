package AprioriAlgorithm;

import java.util.ArrayList;

public class Backwards {
	Data data;
	int flds;
	//Constructor de la clase
	public Backwards(Data e,int flds) {
		//Realiza una copia del objeto Data recivido
		this.data=new Data(e);		
		this.flds=flds;
	}
	//Metodo para seleccionar los atributos 
	public Data selectAtributes() {
		//Calcula la precisión con n atributos
		double bestAccuracy=crossValidation(this.data);
		//Genera una copia del objeto data
		Data bestData=new Data(this.data);
		//Realiza iteraciones mientras que el numero de atributos sea mayor a dos
		//(El atributo de la clase y uno mas)
		while(bestData.attributes.size()>2) {
			Data newData=null;//Objeto para guardar el conjuto que de la mayor precisión
			for(int i=0;i<bestData.attributes.size();i++){//Recorremos el numero de atributos del conjunto
				//Si el indice no corresponde al atributo de la clase
				if(bestData.classifierAttribite!=i) {
					//Guarda un sub-conjunto de n-1 atributos eliminando al atributo i
					Data auxData=deleteAttribute(bestData,i);
					//Calcula la precision que da este sub-conjunto
					double auxAccuracy=crossValidation(auxData);
					System.out.println(auxData+""+auxAccuracy+"\n");
					//Si la precición es mayor a la que habiamos calculado con n atributos 
					if(auxAccuracy>bestAccuracy){
						bestAccuracy=auxAccuracy;//Intercambia por la nueva precision mayor
						newData=auxData;//Guarda el subconjunto en el objeto newData
					}
				}
			}//Si el objeto newData no es nulo, signofica que se encontro un sub-conjunto de n-1 atributos
			if(newData!=null)// que da una mayor precision
				bestData=newData;//Se intercambia como mejor conjutnto de atributos
			else//En caso contrario, significa que no se encontro un sub-conjunto de n-1 atributos 
				break;//que proporcione una mayor precision y se termina el ciclo
		}
		return bestData;
	}
	private Data deleteAttribute(Data data, int i) {
		//Crea una copia de los datos recibidos
		Data auxData=new Data(data);
		//Ajusta el indice del atribtio clasificador
		if(auxData.classifierAttribite>i)
			auxData.classifierAttribite--;
		//Elimina el atributo i
		auxData.attributes.remove(i);
		//Reindexa los atributos
		int index=0;
		for(Attribute value:auxData.attributes) {
			value.index=index;
			index++;
		}
		//Elimina el atributo de las instancias
		for(ArrayList<String> value:auxData.instances)
			value.remove(i);		
		return auxData;
	}
	private double crossValidation(Data dat) {
		//Define las variables a utilizar:
		//Numero de partcicones a considerar para Cross Validation
		int folds=this.flds;
		//Numero total de instancias del conjunto
		int m=dat.instances.size();
		//Numero de instancias por particion
		int s=m/folds;
		//Inices que indican el inicio y fin del conjunto de prueba
		int p=0,q=s;
		//Porcentaje de aciertos
		double porcent=0;
		//Crea dos arraylist para almacenar el subconjunto de prueba y entenamiento
		ArrayList<ArrayList<String>> training=new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> test=new ArrayList<ArrayList<String>>();
		//Inicia un ciclo desde 0 hasta folds (particiones de cross validation)
		for(int i=0;i<folds;i++){
			//Se claculan los subconjuntos correspondientes al conjunto de prueba y entrenamiento
			//para cada iteracion del Cross Validation			
			//Si el puntero p es diferente de cero el conjunto de entrenamiento 
			//es un subconjunto desde 0 a p-1 del conjunto total
			if(p!=0)
				training.addAll(dat.instances.subList(0,p));		
			//El conjunto de prueba es el subconjunto desde p a q-1 del conjunto total
			test.addAll(dat.instances.subList(p,q));
			//Si q es diferente de m entonces añadir el subconjunto de q a m-1 del conjunto total
			//al subconjuto de entrenamineto
			if(q!=m)
				training.addAll(dat.instances.subList(q,m));
			//Si el modulo de divicion entre el numero total de instacias y el numero de particiones
			//es diferente de cero, la ultima iteracion tomara el resto de iteracioes no considerado 
			//como parte del conjunto de prueba.
			//En caso contrario solo se le suma un incremento de s a los punteros q y p
			if(m%folds!=0&&i==folds-2)
				q=m;
			else
				q+=s;
			p+=s;
			//Cada instancia del conjunto de pruba se envia a la funcion ownKNN junto con el conjunto 
			//de entrenamieto, para calcular su clasificacon estimada
			double aciertos=0;
			NB nb=new NB(dat.attributes,training,dat.classifierAttribite);		
			for(int j=0;j<test.size();j++){								
				String resp=nb.getDecision(test.get(j));				
				//Si la clase calculada coincide con la clase de la instancia, incrementamos los aciertos
				if(resp.equals(test.get(j).get(dat.classifierAttribite)))
					aciertos+=1;												
			}
			//Calcula el porcentaje de aciertos del conjunto de prueba
			double porc=((aciertos/test.size())*100);			
			//Se suma al porcentaje global del conjunto
			porcent+=porc;			
			//limpiamos el conjutno de prueba y entrenamiento
			training.clear();
			test.clear();
		}
		//Calculamos el promedio de los porcentajes obtenidos para obtener el porcentaje de exactitud del
		//algoritmo en este conjunto de instancias, y se imprime en el Text Area consola
		double accuracy=porcent/folds;
		return accuracy;
	}
}
