package AprioriAlgorithm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import weka.associations.Apriori;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.clusterers.HierarchicalClusterer;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.Utils;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;

import javax.swing.JOptionPane;

/*//Importar las clases necesarias de la librería de Weka
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;*/ 

public class StartHandler implements ActionListener {
	public GUI ref;
	//Constructor de la clase principal
	StartHandler(GUI x){
		super();
		//Guarda la referencia al frame principal
		ref=x;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int resp=-1;
		if(Integer.parseInt(ref.txtfClassifierAttribute.getText())!=ref.dat.classifierAttribite&&
				ref.dat.classifierAttribite!=-1)
			resp=JOptionPane.showConfirmDialog(ref,"Classifier attribute selected and saved are not equal.\nDo you want to change it?", "Index error", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(resp==0)			
			ref.dat.setClassifierAttribite(Integer.parseInt(ref.txtfClassifierAttribute.getText()));
		//Valida que los campos no esten vacios y que exista un archivo seleccionado
		if(!ref.txtfFile.getText().equals("None selected file"))			
			if(!ref.txtfRules.getText().equals(""))
				//Verifica el tipo de implementaciona a utilizar
				if(ref.rdbtnOwn.isSelected())
					ownApriori();
				else
					wekaApriori();
			else
				JOptionPane.showMessageDialog(ref,"ERROR: Cross validation field is void");			
		else
			JOptionPane.showMessageDialog(ref,"ERROR: None selected file");		
	}
	//Comienza k-means
	private void ownApriori() {
		
	}

	private void wekaApriori(){		
		Instances dataSource;
		try {
			dataSource = new Instances(new BufferedReader(new FileReader(ref.dat.data)));
			//Define el actributo clasificador
			dataSource.setClassIndex(dataSource.numAttributes() - 1);
			//Crea un objeto de tipo Apriori
			Apriori mdlApriori=new Apriori();
			mdlApriori.setNumRules(Integer.parseInt(ref.txtfRules.getText()));
			mdlApriori.setMetricType( new SelectedTag(ref.cmbox_Metrictype.getSelectedIndex(), Apriori.TAGS_SELECTION));
			mdlApriori.setMinMetric(Double.parseDouble(ref.txtf_Minmetric.getText()));
			mdlApriori.buildAssociations(dataSource);
			ref.txtaConsole.setText(ref.txtaConsole.getText()+"\n"+mdlApriori);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}

