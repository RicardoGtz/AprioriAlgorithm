package AprioriAlgorithm;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChooseHandler implements ActionListener {
	GUI ref;
	//Constructor de la Clase
	ChooseHandler(GUI x){
		super();
		//Guarda la referencia al frame principal
		ref=x;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//Crea un objeto tipo FileChooser
		JFileChooser chooser = new JFileChooser();
		//Crea un filtro de archivos, tipo txt y arff
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text", "txt", "arff");
		chooser.setFileFilter(filter);
		//Llama a l fileChooser, si el archivo seleccionado existe procede
	    int returnVal = chooser.showOpenDialog(ref);	    
	    if(returnVal == JFileChooser.APPROVE_OPTION) {	    	
	    	if(chooser.getSelectedFile().exists()){
	    		//Imprime la direccion del archivo en el textfFile
	    		ref.txtfFile.setText(chooser.getSelectedFile().toString());
	    		//Establecemos el archivo seleccionado como Data de nuestro objeto dat
	    		ref.dat.setData(chooser.getSelectedFile());
	    		//Establecemos el atributo perteneciente a la clase
	    		ref.dat.setClassifierAttribite(Integer.parseInt(ref.txtfClassifierAttribute.getText()));
	    	}else
	    		JOptionPane.showMessageDialog(ref,"ERROR: File doesn't exist!");    	
	    }	   
	   ref.txtaConsole.setText(ref.txtaConsole.getText()+ref.dat.name+"\n");
	   ref.txtaConsole.setText(ref.txtaConsole.getText()+"#############################\n");
	   ref.txtaConsole.setText(ref.txtaConsole.getText()+"\tAtributos\n#############################\n");
	   for(int i=0;i<ref.dat.attributes.size();i++)
		   ref.txtaConsole.setText(ref.txtaConsole.getText()+ref.dat.attributes.get(i)+"\n");
	   ref.txtaConsole.setText(ref.txtaConsole.getText()+"#############################\n");
	   ref.txtaConsole.setText(ref.txtaConsole.getText()+"\tInstancias\n#############################\n");
	  if(ref.dat.instances.size()<1000) 
		  for(int i=0;i<ref.dat.instances.size();i++)
			  ref.txtaConsole.setText(ref.txtaConsole.getText()+ref.dat.instances.get(i)+"\n");
	  else
		  ref.txtaConsole.setText(ref.txtaConsole.getText()+"There are more than 1000 instances to show\n");
	   ref.txtaConsole.setText(ref.txtaConsole.getText()+"\n#############################\n");
	   
	}

}
