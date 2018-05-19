package AprioriAlgorithm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	//Declaramos los elementos de la interfaz grafica
	public JTextArea txtaConsole;
	public JRadioButton rdbtnWeka;
	public JRadioButton rdbtnOwn;
	public JTextField txtfClassifierAttribute;
	//Este objeto almacenara los datos del archivo a leer
	public Data dat;
	public JTextField txtfFile;
	public JTextField txtfClusters;
	private JPanel contentPane;	
	private JPanel pnlNorth;
	private JButton btnChoose;
	private JButton btnStart;
	private ButtonGroup btngOption;
	JCheckBox chckbxSelectAttributes;
	
	
	//Constructor de la clase
	public GUI() {
		//Establecemos las propiedades del frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setMinimumSize(this.getSize());		
		//Establecemos las propiedades del contenedor principal
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		//Agrega un Panel a la parte norte del Frame
		pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		//Declaran las etiquetas del frame
		JLabel lblTitle = new JLabel("Kmeans - Aglomerativo");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblK_1 = new JLabel("Clusters");
		lblK_1.setHorizontalAlignment(SwingConstants.RIGHT);
		//Declara los botones del frame
		btnChoose = new JButton("Choose File");			
		btnStart = new JButton("Start");
		//Declara el TextField para mostrar la ruta del archivo
		txtfFile = new JTextField();
		txtfFile.setText("None selected file");
		txtfFile.setEditable(false);
		txtfFile.setBackground(new Color(255,255,255));
		txtfFile.setColumns(10);		
		//Declara el TextField para ingresar la contidad de particiones a 
		//consideras en el cross validation
		txtfClusters = new JTextField();
		txtfClusters.setText("3");
		txtfClusters.setColumns(10);
		//Declara los botones para seleccionar la implementacion con la que se desea trabajar
		rdbtnWeka = new JRadioButton("Aglomerativo");		
		rdbtnOwn = new JRadioButton("Kmeans");
		rdbtnOwn.setSelected(true);		
		
		btngOption = new ButtonGroup();
	    btngOption.add(rdbtnWeka);
	    btngOption.add(rdbtnOwn);
		
		JLabel lblClasificationIndex = new JLabel("Classifier attribute");
		
		txtfClassifierAttribute = new JTextField();
		txtfClassifierAttribute.setText("4");
		txtfClassifierAttribute.setColumns(10);
		
		chckbxSelectAttributes = new JCheckBox("Select attributes");
		
	    //Configura el layout del pnlNorth
		GroupLayout gl_pnlNorth = new GroupLayout(pnlNorth);
		gl_pnlNorth.setHorizontalGroup(
			gl_pnlNorth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNorth.createSequentialGroup()
					.addGap(217)
					.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
					.addGap(222))
				.addGroup(gl_pnlNorth.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlNorth.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnChoose, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlNorth.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtfFile, GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
						.addGroup(gl_pnlNorth.createSequentialGroup()
							.addComponent(lblClasificationIndex, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtfClassifierAttribute, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
							.addComponent(chckbxSelectAttributes)
							.addGap(18)
							.addComponent(lblK_1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtfClusters, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(rdbtnWeka)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnOwn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(52)))
					.addContainerGap())
		);
		gl_pnlNorth.setVerticalGroup(
			gl_pnlNorth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNorth.createSequentialGroup()
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlNorth.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnChoose)
						.addComponent(txtfFile, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlNorth.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStart)
						.addComponent(lblClasificationIndex)
						.addComponent(chckbxSelectAttributes)
						.addComponent(txtfClassifierAttribute, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblK_1)
						.addComponent(txtfClusters, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnWeka)
						.addComponent(rdbtnOwn))
					.addContainerGap())
		);
		//Establece el layout configurado
		pnlNorth.setLayout(gl_pnlNorth);
		
		//Agrega un scrollPane al Centro del frame
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		//Agrega un textArea donde mostraremos los resultados al ScrolaPanel
		txtaConsole = new JTextArea();
		txtaConsole.setEditable(false);
		scrollPane.setViewportView(txtaConsole);
		
		//Construye un nuvo objeto de tipo Data
		dat=new Data();
		
		//Añade los acton Listener correspondientes a cada boton
		//Se les encia la referencia al frame principal
		btnChoose.addActionListener(new ChooseHandler(this)); 
		btnStart.addActionListener(new StartHandler(this));
		
	}
}
