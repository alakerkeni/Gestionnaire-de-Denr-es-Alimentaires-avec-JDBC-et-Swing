package Ex1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.IntStream;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

public class MaFenetre {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu rangementMenu;
    private JMenu recetteMenu;
    private JMenu typeIngredientMenu;
    private JMenu ingredientMenu;
    private JMenu compositionMenu;
    private JMenu produitMenu;
	private boolean add;
    
    public MaFenetre(Connection connect) {
        frame = new JFrame("Gestion des denrées alimentaires");
        ImageIcon imageIcon = new ImageIcon("DALL·E 2023-04-29 22.11.532.png");
        JLabel imageLabel = new JLabel(new ImageIcon("DALL·E 2023-04-29 22.11.53.png"));
        frame.add(imageLabel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        menuBar = new JMenuBar();
        rangementMenu = new JMenu("Rangement");
        recetteMenu = new JMenu("Recette");
        typeIngredientMenu = new JMenu("Type d'Ingredient");
        ingredientMenu = new JMenu("Ingredient");
        compositionMenu = new JMenu("Composition");
        produitMenu = new JMenu("Produit");
        
        JMenuItem rangement1 = new JMenuItem("All");
        rangementMenu.add(rangement1);

        JMenuItem rangement2 = new JMenuItem("Rechercher");
        rangementMenu.add(rangement2);

        JMenuItem rangement3 = new JMenuItem("Ajouter");
        rangementMenu.add(rangement3);

        JMenuItem rangement4 = new JMenuItem("Mise à jour");
        rangementMenu.add(rangement4);

        JMenuItem rangement5 = new JMenuItem("Effacer");
        rangementMenu.add(rangement5);


        JMenuItem recette1 = new JMenuItem("All");
        recetteMenu.add(recette1);

        JMenuItem recette2 = new JMenuItem("Rechercher");
        recetteMenu.add(recette2);

        JMenuItem recette3 = new JMenuItem("Ajouter");
        recetteMenu.add(recette3);

        JMenuItem recette4 = new JMenuItem("Mise à jour");
        recetteMenu.add(recette4);

        JMenuItem recette5 = new JMenuItem("Effacer");
        recetteMenu.add(recette5);


        // For TypeIngredient submenu
        JMenuItem typeIngredient1 = new JMenuItem("All");
        typeIngredientMenu.add(typeIngredient1);

        JMenuItem typeIngredient2 = new JMenuItem("Rechercher");
        typeIngredientMenu.add(typeIngredient2);

        JMenuItem typeIngredient3 = new JMenuItem("Ajouter");
        typeIngredientMenu.add(typeIngredient3);

        JMenuItem typeIngredient4 = new JMenuItem("Mise à jour");
        typeIngredientMenu.add(typeIngredient4);

        JMenuItem typeIngredient5 = new JMenuItem("Effacer");
        typeIngredientMenu.add(typeIngredient5);

        //Ingredient submenu
        JMenuItem ingredient1 = new JMenuItem("All");
        ingredientMenu.add(ingredient1);

        JMenuItem ingredient2 = new JMenuItem("Rechercher");
        ingredientMenu.add(ingredient2);

        JMenuItem ingredient3 = new JMenuItem("Ajouter");
        ingredientMenu.add(ingredient3);

        JMenuItem ingredient4 = new JMenuItem("Mise à jour");
        ingredientMenu.add(ingredient4);

        JMenuItem ingredient5 = new JMenuItem("Effacer");
        ingredientMenu.add(ingredient5);

        //Composition submenu
        JMenuItem composition1 = new JMenuItem("All");
        compositionMenu.add(composition1);

        JMenuItem composition2 = new JMenuItem("Rechercher");
        compositionMenu.add(composition2);

        JMenuItem composition3 = new JMenuItem("Ajouter");
        compositionMenu.add(composition3);

        JMenuItem composition4 = new JMenuItem("Mise à jour");
        compositionMenu.add(composition4);

        JMenuItem composition5 = new JMenuItem("Effacer");
        compositionMenu.add(composition5);

        //Produit submenu
        JMenuItem produit1 = new JMenuItem("All");
        produitMenu.add(produit1);

        JMenuItem produit2 = new JMenuItem("Rechercher");
        produitMenu.add(produit2);

        JMenuItem produit3 = new JMenuItem("Ajouter");
        produitMenu.add(produit3);

        JMenuItem produit4 = new JMenuItem("Mise à jour");
        produitMenu.add(produit4);

        JMenuItem produit5 = new JMenuItem("Effacer");
        produitMenu.add(produit5);

        //Action listeners
        rangement1.addActionListener(e -> {
            JLabel panel = new JLabel(imageIcon);
            panel.setLayout(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            RangementDAO dao= new RangementDAO(connect);
            // Create table model and set data from findAll() method
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Référence");
            tableModel.addColumn("Nom");
            ArrayList<Rangement> list = dao.findAll();
            for (Rangement r : list) {
                Object[] rowData = {r.getRefRangement(), r.getNomRangement()};
                tableModel.addRow(rowData);
            }

            // Create JTable and set the table model
            JTable table = new JTable(tableModel);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setResizingAllowed(false);
            table.setRowHeight(30);

            // Add table to scroll pane and add to panel
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.CENTER);
            
            // Set background color
            table.setBackground(new Color(230, 204, 255));

            // Add panel to JFrame
            frame.getContentPane().removeAll();
            frame.setContentPane(panel);
            frame.revalidate();
        });



        	rangement2.addActionListener(e -> {
        		JLabel panel = new JLabel(imageIcon);
        		panel.setLayout(new GridBagLayout());
        		//JPanel panel = new JPanel(new GridBagLayout());
        		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        		panel.setBackground(Color.WHITE);
        		RangementDAO daoR = new RangementDAO(connect);
    			ArrayList<Rangement> list2= daoR.findAll();

        		GridBagConstraints c = new GridBagConstraints();
        		c.gridx = 0;
        		c.gridy = 0;
        		c.anchor = GridBagConstraints.LINE_START;
        		c.insets = new Insets(0, 0, 10, 0);

        		JLabel refLabel = new JLabel("Référence du rangement à rechercher:");
        		panel.add(refLabel, c);
        		c.gridy++;
        		
        		ArrayList<String> refRang = new ArrayList<String>();
        		for (Rangement r : list2) {
        			refRang.add(r.getRefRangement());
        		}
        		
        		JComboBox<String> refRangComboBox = new JComboBox<String>(refRang.toArray(new String[0]));
        		refRangComboBox.setSelectedIndex(-1);
        		panel.add(refRangComboBox, c);
        		c.gridy++;
        		c.anchor = GridBagConstraints.CENTER;
        		c.fill = GridBagConstraints.HORIZONTAL;
        		c.insets = new Insets(20, 0, 0, 0);

        		c.gridy++;
        		JButton submitButton = new JButton("Rechercher");
        		submitButton.setBackground(Color.decode("#A7C68C"));
        		panel.add(submitButton, c);
        		submitButton.addActionListener(new ActionListener() {
        		    public void actionPerformed(ActionEvent a) {
        		        Rangement result = daoR.find((String)refRangComboBox.getSelectedItem());
        		        if (result != null) {
        		            JDialog dialog = new JDialog(frame, "Rangement Information");
        		            dialog.setSize(100, 100);
        		            //.setLayout(new GridLayout(2, 1));

        		            DefaultTableModel model = new DefaultTableModel();
        		            model.addColumn("Properiete");
        		            model.addColumn("Valeur");

        		            model.addRow(new Object[]{"Ref Rangement", result.getRefRangement()});
        		            model.addRow(new Object[]{"Nom Rangement", result.getNomRangement()});

        		            JTable table = new JTable(model);

        		            JScrollPane scrollPane = new JScrollPane(table);

        		            JPanel panel = new JPanel(new BorderLayout());
        		            panel.add(scrollPane, BorderLayout.CENTER);
       		           
        		            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        		            dialog.setContentPane(panel);
        		            dialog.pack();
        		            dialog.setVisible(true);

        		        } else {
        		            JOptionPane.showMessageDialog(null, "Rangement non trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
        		        }
        		    }
        		});

        		panel.add(submitButton, c);        		     
        		
        		frame.getContentPane().removeAll();
        		frame.setContentPane(panel);
        		frame.revalidate();
        	/*panel.setBackground(Color.GREEN);
        	frame.setContentPane(panel);
        	frame.revalidate();*/
        	});

        	rangement3.addActionListener(e -> {
        		
        		JLabel panel = new JLabel(imageIcon);
        		panel.setLayout(new GridBagLayout());
        		//JPanel panel = new JPanel(new GridBagLayout());
        		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        		panel.setBackground(Color.WHITE);
        		

        		GridBagConstraints c = new GridBagConstraints();
        		c.gridx = 0;
        		c.gridy = 0;
        		c.anchor = GridBagConstraints.LINE_START;
        		c.insets = new Insets(0, 0, 10, 0);

        		JLabel refLabel = new JLabel("Référence:");
        		panel.add(refLabel, c);

        		c.gridy++;
        		
        		ArrayList<String> references = new ArrayList<String>();
        		for (int i = 1001; i <= 2000; i++) {
        		    references.add("R" + i);
        		}
        		JComboBox<String> refComboBox = new JComboBox<String>(references.toArray(new String[0]));
        		refComboBox.setSelectedIndex(-1);
        		panel.add(refComboBox, c);
        		c.gridy++;
        		
        		JLabel nomLabel = new JLabel("Nom:");
        		panel.add(nomLabel, c);

        		c.gridy++;
        		JTextField nomField = new JTextField(20);
        		panel.add(nomField, c);

        		c.gridy++;
        		c.anchor = GridBagConstraints.CENTER;
        		c.fill = GridBagConstraints.HORIZONTAL;
        		c.insets = new Insets(20, 0, 0, 0);

        		JButton submitButton = new JButton("Soumettre");
        		submitButton.setBackground(Color.decode("#A7C68C"));
        		panel.add(submitButton, c);

        		c.gridy++;
        		JButton resetButton = new JButton("Réinitialiser");
        		resetButton.setBackground(Color.decode("#C98080"));
        		resetButton.addActionListener(new ActionListener() {
        		    public void actionPerformed(ActionEvent a) {
        		        //refField.setText("");
        		        nomField.setText("");
        		    }
        		});
        		panel.add(resetButton, c);
        		
        		submitButton.addActionListener(new ActionListener() {
        		    public void actionPerformed(ActionEvent e) {
        		        String nom = nomField.getText();
        		        Rangement result=null;
        		        RangementDAO dao= new RangementDAO(connect);
        		        if (nom.length() > 100) {
        		            JOptionPane.showMessageDialog(null, "Le nom de la recette doit être de 50 caractères maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
        		            return;
        		        }
        		        if (refComboBox.getSelectedItem()==null) {
        		            JOptionPane.showMessageDialog(null, "Le choix d'une référence est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
        		            return;
        		        }
        		        if (dao.create(result =  new Rangement((String) refComboBox.getSelectedItem(), nom))) {
        		        	int i =1000;
        		        	JOptionPane.showMessageDialog(null, "ajouté à la base de données avec succés", "ajouté", JOptionPane.INFORMATION_MESSAGE);
        		            references.add("R" + (references.size()+1000+i++));
        		            refComboBox.addItem("R" + references.size());
        		            
        		            references.remove((String) refComboBox.getSelectedItem());
        		            refComboBox.removeItem(refComboBox.getSelectedItem());
        		            
        		            return;
        		        }
        		        else {
        		            JOptionPane.showMessageDialog(null, "violation de contrainte unique", "Erreur", JOptionPane.ERROR_MESSAGE);       		    	
        		        }
        		        System.out.println(result);
        		    }
        		});

        		
        		frame.getContentPane().removeAll();
        		frame.setContentPane(panel);
        		frame.revalidate();


        	/*panel.setBackground(Color.BLUE);
        	frame.setContentPane(panel);
        	frame.revalidate();*/
        	});

        	rangement4.addActionListener(e -> {
        		JLabel panel = new JLabel(imageIcon);
        		panel.setLayout(new GridBagLayout());
        		//JPanel panel = new JPanel(new GridBagLayout());
        		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        		panel.setBackground(Color.WHITE);
        		RangementDAO daoR = new RangementDAO(connect);
        		ArrayList<Rangement> list2= daoR.findAll();

        		GridBagConstraints c = new GridBagConstraints();
        		c.gridx = 0;
        		c.gridy = 0;
        		c.anchor = GridBagConstraints.LINE_START;
        		c.insets = new Insets(0, 0, 10, 0);

        		JLabel refLabel = new JLabel("Référence du Rangement à mettre à jour:");
        		panel.add(refLabel, c);

        		c.gridy++;
        		
        		ArrayList<String> refRang = new ArrayList<String>();
        		for (Rangement r : list2) {
        			refRang.add(r.getRefRangement());
        		}
        		
        		JComboBox<String> refRangComboBox = new JComboBox<String>(refRang.toArray(new String[0]));
        		refRangComboBox.setSelectedIndex(-1);
        		panel.add(refRangComboBox, c);
        		c.gridy++;
        		
        		JLabel nomLabel = new JLabel("Nouveau Nom:");
        		panel.add(nomLabel, c);

        		c.gridy++;
        		JTextField nomField = new JTextField(20);
        		panel.add(nomField, c);

        		c.gridy++;
        		c.anchor = GridBagConstraints.CENTER;
        		c.fill = GridBagConstraints.HORIZONTAL;
        		c.insets = new Insets(20, 0, 0, 0);

        		JButton submitButton = new JButton("mettre à jour");
        		submitButton.setBackground(Color.decode("#A7C68C"));
        		panel.add(submitButton, c);

        		c.gridy++;
        		JButton resetButton = new JButton("Réinitialiser");
        		resetButton.setBackground(Color.decode("#C98080"));
        		resetButton.addActionListener(new ActionListener() {
        		    public void actionPerformed(ActionEvent a) {       		        
        		        nomField.setText("");
        		    }
        		});
        		panel.add(resetButton, c);
        		
        		submitButton.addActionListener(new ActionListener() {
        		    public void actionPerformed(ActionEvent e) {
        		        String nom = nomField.getText();
        		        Rangement result=null;
        		        RangementDAO dao= new RangementDAO(connect);
        		        if (nom.length() > 100) {
        		            JOptionPane.showMessageDialog(null, "Le nom de la recette doit être de 50 caractères maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
        		            return;
        		        }
        		        if (refRangComboBox.getSelectedItem()==null) {
        		            JOptionPane.showMessageDialog(null, "Le choix d'une référence est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
        		            return;
        		        }
        		        if (dao.update(result =  new Rangement((String) refRangComboBox.getSelectedItem(), nom))) {
        		        	int i =1000;
        		        	JOptionPane.showMessageDialog(null, "Mis à jour avec succés", "Mis à jour", JOptionPane.INFORMATION_MESSAGE);        	            
        		            return;
        		        }
        		        else {
        		            JOptionPane.showMessageDialog(null, "violation de contrainte unique", "Erreur", JOptionPane.ERROR_MESSAGE);       		    	
        		        }
        		        System.out.println(result);
        		    }
        		});

        		
        		frame.getContentPane().removeAll();
        		frame.setContentPane(panel);
        		frame.revalidate();

        	/*JPanel panel = new JPanel();
        	panel.setBackground(Color.YELLOW);
        	frame.setContentPane(panel);
        	frame.revalidate();*/
        	});

        	rangement5.addActionListener(e -> {
        		JLabel panel = new JLabel(imageIcon);
        		panel.setLayout(new GridBagLayout());
        		//JPanel panel = new JPanel(new GridBagLayout());
        		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        		panel.setBackground(Color.WHITE);
        		RangementDAO daoR = new RangementDAO(connect);
    			ArrayList<Rangement> list2= daoR.findAll();

        		GridBagConstraints c = new GridBagConstraints();
        		c.gridx = 0;
        		c.gridy = 0;
        		c.anchor = GridBagConstraints.LINE_START;
        		c.insets = new Insets(0, 0, 10, 0);

        		JLabel refLabel = new JLabel("Référence du rangement à effacer:");
        		panel.add(refLabel, c);
        		c.gridy++;
        		
        		ArrayList<String> refRang = new ArrayList<String>();
        		for (Rangement r : list2) {
        			refRang.add(r.getRefRangement());
        		}
        		
        		JComboBox<String> refRangComboBox = new JComboBox<String>(refRang.toArray(new String[0]));
        		refRangComboBox.setSelectedIndex(-1);
        		panel.add(refRangComboBox, c);
        		c.gridy++;
        		c.anchor = GridBagConstraints.CENTER;
        		c.fill = GridBagConstraints.HORIZONTAL;
        		c.insets = new Insets(20, 0, 0, 0);

        		c.gridy++;
        		JButton resetButton = new JButton("Supprimer");
        		resetButton.setBackground(Color.decode("#C98080"));
        		resetButton.addActionListener(new ActionListener() {
        		    public void actionPerformed(ActionEvent a) {
        		        if (daoR.delete(daoR.find((String)refRangComboBox.getSelectedItem()))) {
        		        	JOptionPane.showMessageDialog(null, "supprimé de la base de données avec succés", "supprimé", JOptionPane.INFORMATION_MESSAGE);
        		        }
        		    }
        		});
        		panel.add(resetButton, c);        		     
        		
        		frame.getContentPane().removeAll();
        		frame.setContentPane(panel);
        		frame.revalidate();

        	/*panel.setBackground(Color.PINK);
        	frame.setContentPane(panel);
        	frame.revalidate();*/
        	});
        	
        	recette1.addActionListener(e -> {
        	    JLabel panel = new JLabel(imageIcon);
        	    panel.setLayout(new BorderLayout());
        	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        	    RecetteDAO dao = new RecetteDAO(connect);
        	    
        	    // Create table model and set data from findAll() method
        	    DefaultTableModel tableModel = new DefaultTableModel();
        	    tableModel.addColumn("Référence");
        	    tableModel.addColumn("Nom");
        	    tableModel.addColumn("Description");
        	    tableModel.addColumn("Calories");
        	    tableModel.addColumn("Difficulté");
        	    tableModel.addColumn("Temps de préparation");
        	    tableModel.addColumn("Temps de cuisson");
        	    tableModel.addColumn("Nombre de personnes");
        	    ArrayList<Recette> list = dao.findAll();
        	    for (Recette r : list) {
        	        Object[] rowData = {r.getRefRecette(), r.getNomRecette(), r.getDescriptifRecette(), r.getCaloriesRecette(),
        	                            r.getDifficulte(), r.getTempsPreparation(), r.getTempsCuisson(), r.getNbPersonnes()};
        	        tableModel.addRow(rowData);
        	    }

        	    // Create JTable and set the table model
        	    JTable table = new JTable(tableModel);
        	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        	    table.getTableHeader().setReorderingAllowed(false);
        	    table.getTableHeader().setResizingAllowed(false);
        	    table.setRowHeight(30);

        	    // Add table to scroll pane and add to panel
        	    JScrollPane scrollPane = new JScrollPane(table);
        	    panel.add(scrollPane, BorderLayout.CENTER);
        	            
        	    // Set background color
        	    table.setBackground(new Color(230, 204, 255));

        	    // Add panel to JFrame
        	    frame.getContentPane().removeAll();
        	    frame.setContentPane(panel);
        	    frame.revalidate();
        	});


        		recette2.addActionListener(e -> {
        		    JLabel panel = new JLabel(imageIcon);
        		    panel.setLayout(new GridBagLayout());
        		    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        		    panel.setBackground(Color.WHITE);
        		    RecetteDAO daoR = new RecetteDAO(connect);
        		    ArrayList<Recette> list2 = daoR.findAll();

        		    GridBagConstraints c = new GridBagConstraints();
        		    c.gridx = 0;
        		    c.gridy = 0;
        		    c.anchor = GridBagConstraints.LINE_START;
        		    c.insets = new Insets(0, 0, 10, 0);

        		    JLabel refLabel = new JLabel("Référence de la recette à rechercher:");
        		    panel.add(refLabel, c);
        		    c.gridy++;

        		    ArrayList<String> refRec = new ArrayList<String>();
        		    for (Recette r : list2) {
        		        refRec.add(r.getRefRecette());
        		    }

        		    JComboBox<String> refRecComboBox = new JComboBox<String>(refRec.toArray(new String[0]));
        		    refRecComboBox.setSelectedIndex(-1);
        		    panel.add(refRecComboBox, c);
        		    c.gridy++;
        		    c.anchor = GridBagConstraints.CENTER;
        		    c.fill = GridBagConstraints.HORIZONTAL;
        		    c.insets = new Insets(20, 0, 0, 0);

        		    c.gridy++;
        		    JButton submitButton = new JButton("Rechercher");
        		    submitButton.setBackground(Color.decode("#A7C68C"));
        		    panel.add(submitButton, c);
        		    submitButton.addActionListener(new ActionListener() {
        		        public void actionPerformed(ActionEvent a) {
        		            Recette result = daoR.find((String)refRecComboBox.getSelectedItem());
        		            if (result != null) {
        		                JDialog dialog = new JDialog(frame, "Recette Information");
        		                dialog.setSize(100, 100);

        		                DefaultTableModel model = new DefaultTableModel();
        		                model.addColumn("Propriété");
        		                model.addColumn("Valeur");

        		                model.addRow(new Object[]{"Ref Recette", result.getRefRecette()});
        		                model.addRow(new Object[]{"Nom Recette", result.getNomRecette()});
        		                model.addRow(new Object[]{"Descriptif Recette", result.getDescriptifRecette()});
        		                model.addRow(new Object[]{"Calories Recette", result.getCaloriesRecette()});
        		                model.addRow(new Object[]{"Difficulté Recette", result.getDifficulte()});
        		                model.addRow(new Object[]{"Temps Préparation", result.getTempsPreparation()});
        		                model.addRow(new Object[]{"Temps Cuisson", result.getTempsCuisson()});
        		                model.addRow(new Object[]{"Nombre Personnes", result.getNbPersonnes()});

        		                JTable table = new JTable(model);

        		                JScrollPane scrollPane = new JScrollPane(table);

        		                JPanel panel = new JPanel(new BorderLayout());
        		                panel.add(scrollPane, BorderLayout.CENTER);

        		                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        		                dialog.setContentPane(panel);
        		                dialog.pack();
        		                dialog.setVisible(true);

        		            } else {
        		                JOptionPane.showMessageDialog(null, "Recette non trouvée", "Erreur", JOptionPane.ERROR_MESSAGE);
        		            }
        		        }
        		    });

        		    panel.add(submitButton, c);

        		    frame.getContentPane().removeAll();
        		    frame.setContentPane(panel);
        		    frame.revalidate();
        		});


        		recette3.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
            		panel.setLayout(new GridBagLayout());
        			//JPanel panel = new JPanel(new GridBagLayout());
        	        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        	        panel.setBackground(Color.WHITE);

        	        GridBagConstraints c = new GridBagConstraints();
        	        c.gridx = 0;
        	        c.gridy = 0;
        	        c.anchor = GridBagConstraints.LINE_START;
        	        c.insets = new Insets(0, 0, 10, 0);

        	        JLabel refLabel = new JLabel("Référence:");
        	        panel.add(refLabel, c);

        	        c.gridy++;
        	        ArrayList<String> references = new ArrayList<String>();
            		for (int i = 1001; i <= 2000; i++) {
            		    references.add("re" + i);
            		}
            		JComboBox<String> refComboBox = new JComboBox<String>(references.toArray(new String[0]));
            		refComboBox.setSelectedIndex(-1);
            		panel.add(refComboBox, c);

        	        c.gridy++;
        	        JLabel nomLabel = new JLabel("Nom:");
        	        panel.add(nomLabel, c);

        	        c.gridy++;
        	        JTextField nomField = new JTextField(20);
        	        panel.add(nomField, c);

        	        c.gridy++;
        	        JLabel descriptifLabel = new JLabel("Descriptif:");
        	        panel.add(descriptifLabel, c);

        	        c.gridy++;
        	        JTextField descriptifField = new JTextField(20);
        	        panel.add(descriptifField, c);

        	        c.gridy++;
        	        JLabel caloriesLabel = new JLabel("Calories:");
        	        panel.add(caloriesLabel, c);

        	        c.gridy++;
        	        JComboBox<Integer> calComboBox = new JComboBox<Integer>(IntStream.rangeClosed(100, 3000).boxed().toArray(Integer[]::new));
            		panel.add(calComboBox, c);

        	        c.gridy++;
        	        JLabel difficulteLabel = new JLabel("Difficulté:");
        	        panel.add(difficulteLabel, c);

        	        c.gridy++;
        	        JTextField difficulteField = new JTextField(20);
        	        panel.add(difficulteField, c);

        	        c.gridy++;
        	        JLabel tempsPreparationLabel = new JLabel("Temps de préparation:");
        	        panel.add(tempsPreparationLabel, c);

        	        c.gridy++;
        	        JComboBox<Integer> tempsPreparationComboBox = new JComboBox<Integer>(IntStream.rangeClosed(20, 300).boxed().toArray(Integer[]::new));
        	        panel.add(tempsPreparationComboBox, c);

        	        c.gridy++;
        	        JLabel tempsCuissonLabel = new JLabel("Temps de cuisson:");
        	        panel.add(tempsCuissonLabel, c);

        	        c.gridy++;
        	        JComboBox<Integer> tempsCuissonComboBox = new JComboBox<Integer>(IntStream.rangeClosed(20, 300).boxed().toArray(Integer[]::new));
        	        panel.add(tempsCuissonComboBox, c);

        	        c.gridy++;
        	        JLabel nbPersonnesLabel = new JLabel("Nombre de personnes:");
        	        panel.add(nbPersonnesLabel, c);

        	        c.gridy++;
        	        JComboBox<Integer> nbPersonnesComboBox = new JComboBox<Integer>(IntStream.rangeClosed(20, 300).boxed().toArray(Integer[]::new));
        	        panel.add(nbPersonnesComboBox, c);

        	        c.gridy++;
        	        c.anchor = GridBagConstraints.CENTER;
        	        c.fill = GridBagConstraints.HORIZONTAL;
        	        c.insets = new Insets(20, 0, 0, 0);

        	        JButton submitButton = new JButton("Soumettre");
            		submitButton.setBackground(Color.decode("#A7C68C"));
            		panel.add(submitButton, c);

            		c.gridy++;
            		JButton resetButton = new JButton("Réinitialiser");
            		panel.add(resetButton, c);
            		resetButton.setBackground(Color.decode("#C98080"));
            		resetButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent a) {
            		        //refField.setText("");
            		        nomField.setText("");
            		        descriptifField.setText("");
            		        //caloriesField.setText("");
            		        difficulteField.setText("");
            		        //tempsPreparationField.setText("");
            		        //tempsCuissonField.setText("");
            		        //nbPersonnesField.setText("");
            		    }
            		});
            		
            		submitButton.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
            				String nom = nomField.getText();
            		        String diff = difficulteField.getText();
            		        String desc = descriptifField.getText();
            		        Recette result=null;
            		        RecetteDAO dao= new RecetteDAO(connect);
            		        if (nom.length() > 50) {
            		            JOptionPane.showMessageDialog(null, "Le nom de la recette doit être de 50 caractères maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (diff.length() >= 9) {
            		            JOptionPane.showMessageDialog(null, "La difficulté doit être de 9 caractères maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (descriptifField.getText().length() >= 500) {
            		            JOptionPane.showMessageDialog(null, "La description doit être de 500 caractères maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (refComboBox.getSelectedItem()==null) {
            		            JOptionPane.showMessageDialog(null, "Le choix d'une référence est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		    if(dao.create(result =  new Recette((String) refComboBox.getSelectedItem(), nom, desc, (int) calComboBox.getSelectedItem(), diff, (int) tempsPreparationComboBox.getSelectedItem(), (int) tempsCuissonComboBox.getSelectedItem(), (int) nbPersonnesComboBox.getSelectedItem())))
            		    {
            		    	JOptionPane.showMessageDialog(null, "ajouté à la base de données avec succés", "ajouté", JOptionPane.INFORMATION_MESSAGE);
            		    	references.add("rec" + references.size());
            		    	refComboBox.addItem("rec" + references.size()+1000);
            		    	
            		    	references.remove((String) refComboBox.getSelectedItem());
            		    	refComboBox.removeItem(refComboBox.getSelectedItem());
            		    	
            		    	return;
            		    }
            		    else {
            		    	JOptionPane.showMessageDialog(null, "violation de contrainte unique", "Erreur", JOptionPane.ERROR_MESSAGE);
            		    }
            		    System.out.println(result);
            			}
            		}
            		);
        	        frame.setContentPane(panel);
            		frame.revalidate();

        		/*panel.setBackground(Color.BLUE);
        		frame.setContentPane(panel);
        		frame.revalidate();*/
        		});

        		recette4.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
            		panel.setLayout(new GridBagLayout());
        			//JPanel panel = new JPanel(new GridBagLayout());
        	        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        	        panel.setBackground(Color.WHITE);
        	        RecetteDAO daoR = new RecetteDAO(connect);
        			ArrayList<Recette> list2= daoR.findAll();

        	        GridBagConstraints c = new GridBagConstraints();
        	        c.gridx = 0;
        	        c.gridy = 0;
        	        c.anchor = GridBagConstraints.LINE_START;
        	        c.insets = new Insets(0, 0, 10, 0);

        	        JLabel refLabel = new JLabel("Référence de la recette à mettre à jour:");
        	        panel.add(refLabel, c);

        	        c.gridy++;
        	        
        	        ArrayList<String> refRecette = new ArrayList<String>();
            		for (Recette r : list2) {
            			refRecette.add(r.getRefRecette());
            		}
            		
            		JComboBox<String> refRecetteComboBox = new JComboBox<String>(refRecette.toArray(new String[0]));
            		refRecetteComboBox.setSelectedIndex(-1);
            		panel.add(refRecetteComboBox, c);

        	        c.gridy++;
        	        JLabel nomLabel = new JLabel("Nouveau Nom:");
        	        panel.add(nomLabel, c);

        	        c.gridy++;
        	        JTextField nomField = new JTextField(20);
        	        panel.add(nomField, c);

        	        c.gridy++;
        	        JLabel descriptifLabel = new JLabel("Descriptif (Nouveau):");
        	        panel.add(descriptifLabel, c);

        	        c.gridy++;
        	        JTextField descriptifField = new JTextField(20);
        	        panel.add(descriptifField, c);

        	        c.gridy++;
        	        JLabel caloriesLabel = new JLabel("Calories (Nouveau):");
        	        panel.add(caloriesLabel, c);

        	        c.gridy++;
        	        JComboBox<Integer> calComboBox = new JComboBox<Integer>(IntStream.rangeClosed(100, 3000).boxed().toArray(Integer[]::new));
            		panel.add(calComboBox, c);

        	        c.gridy++;
        	        JLabel difficulteLabel = new JLabel("Difficulté (Nouveau):");
        	        panel.add(difficulteLabel, c);

        	        c.gridy++;
        	        JTextField difficulteField = new JTextField(20);
        	        panel.add(difficulteField, c);

        	        c.gridy++;
        	        JLabel tempsPreparationLabel = new JLabel("Temps de préparation (Nouveau):");
        	        panel.add(tempsPreparationLabel, c);

        	        c.gridy++;
        	        JComboBox<Integer> tempsPreparationComboBox = new JComboBox<Integer>(IntStream.rangeClosed(20, 300).boxed().toArray(Integer[]::new));
        	        panel.add(tempsPreparationComboBox, c);

        	        c.gridy++;
        	        JLabel tempsCuissonLabel = new JLabel("Temps de cuisson (Nouveau):");
        	        panel.add(tempsCuissonLabel, c);

        	        c.gridy++;
        	        JComboBox<Integer> tempsCuissonComboBox = new JComboBox<Integer>(IntStream.rangeClosed(20, 300).boxed().toArray(Integer[]::new));
        	        panel.add(tempsCuissonComboBox, c);

        	        c.gridy++;
        	        JLabel nbPersonnesLabel = new JLabel("Nombre de personnes (Nouveau):");
        	        panel.add(nbPersonnesLabel, c);

        	        c.gridy++;
        	        JComboBox<Integer> nbPersonnesComboBox = new JComboBox<Integer>(IntStream.rangeClosed(20, 300).boxed().toArray(Integer[]::new));
        	        panel.add(nbPersonnesComboBox, c);

        	        c.gridy++;
        	        c.anchor = GridBagConstraints.CENTER;
        	        c.fill = GridBagConstraints.HORIZONTAL;
        	        c.insets = new Insets(20, 0, 0, 0);

        	        JButton submitButton = new JButton("mettre à jour");
            		submitButton.setBackground(Color.decode("#A7C68C"));
            		panel.add(submitButton, c);

            		c.gridy++;
            		JButton resetButton = new JButton("Réinitialiser");
            		panel.add(resetButton, c);
            		resetButton.setBackground(Color.decode("#C98080"));
            		resetButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent a) {
            		        //refField.setText("");
            		        nomField.setText("");
            		        descriptifField.setText("");
            		        //caloriesField.setText("");
            		        difficulteField.setText("");
            		        //tempsPreparationField.setText("");
            		        //tempsCuissonField.setText("");
            		        //nbPersonnesField.setText("");
            		    }
            		});
            		
            		submitButton.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
            				String nom = nomField.getText();
            		        String diff = difficulteField.getText();
            		        String desc = descriptifField.getText();
            		        Recette result=null;
            		        RecetteDAO dao= new RecetteDAO(connect);
            		        if (nom.length() > 50) {
            		            JOptionPane.showMessageDialog(null, "Le nom de la recette doit être de 50 caractères maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (diff.length() >= 9) {
            		            JOptionPane.showMessageDialog(null, "La difficulté doit être de 9 caractères maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (descriptifField.getText().length() >= 500) {
            		            JOptionPane.showMessageDialog(null, "La description doit être de 500 caractères maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (refRecetteComboBox.getSelectedItem()==null) {
            		            JOptionPane.showMessageDialog(null, "Le choix d'une référence est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		    if(dao.update(result =  new Recette((String) refRecetteComboBox.getSelectedItem(), nom, desc, (int) calComboBox.getSelectedItem(), diff, (int) tempsPreparationComboBox.getSelectedItem(), (int) tempsCuissonComboBox.getSelectedItem(), (int) nbPersonnesComboBox.getSelectedItem())))
            		    {
            		    	JOptionPane.showMessageDialog(null, "mise à jour avec succés", "ajouté", JOptionPane.INFORMATION_MESSAGE);           		               		    	
            		    	return;
            		    }
            		    else {
            		    	JOptionPane.showMessageDialog(null, "violation de contrainte unique", "Erreur", JOptionPane.ERROR_MESSAGE);
            		    }
            		    System.out.println(result);
            			}
            		}
            		);
        	        frame.setContentPane(panel);
            		frame.revalidate();

        		/*JPanel panel = new JPanel();
        		panel.setBackground(Color.YELLOW);
        		frame.setContentPane(panel);
        		frame.revalidate();*/
        		});

        		recette5.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
            		panel.setLayout(new GridBagLayout());
            		//JPanel panel = new JPanel(new GridBagLayout());
            		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            		panel.setBackground(Color.WHITE);
            		RecetteDAO daoR = new RecetteDAO(connect);
        			ArrayList<Recette> list2= daoR.findAll();

            		GridBagConstraints c = new GridBagConstraints();
            		c.gridx = 0;
            		c.gridy = 0;
            		c.anchor = GridBagConstraints.LINE_START;
            		c.insets = new Insets(0, 0, 10, 0);

            		JLabel refLabel = new JLabel("Référence de la Recette à effacer:");
            		panel.add(refLabel, c);
            		c.gridy++;
            		
            		ArrayList<String> refRecette = new ArrayList<String>();
            		for (Recette r : list2) {
            			refRecette.add(r.getRefRecette());
            		}
            		
            		JComboBox<String> refRecetteComboBox = new JComboBox<String>(refRecette.toArray(new String[0]));
            		refRecetteComboBox.setSelectedIndex(-1);
            		panel.add(refRecetteComboBox, c);
            		c.gridy++;
            		c.anchor = GridBagConstraints.CENTER;
            		c.fill = GridBagConstraints.HORIZONTAL;
            		c.insets = new Insets(20, 0, 0, 0);

            		c.gridy++;
            		JButton resetButton = new JButton("Supprimer");
            		resetButton.setBackground(Color.decode("#C98080"));
            		resetButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent a) {
            		        if (daoR.delete(daoR.find((String)refRecetteComboBox.getSelectedItem()))) {
            		        	JOptionPane.showMessageDialog(null, "supprimé de la base de données avec succés", "supprimé", JOptionPane.INFORMATION_MESSAGE);
            		        }
            		    }
            		});
            		panel.add(resetButton, c);        		     
            		
            		frame.getContentPane().removeAll();
            		frame.setContentPane(panel);
            		frame.revalidate();
        		/*panel.setBackground(Color.PINK);
        		frame.setContentPane(panel);
        		frame.revalidate();*/
        		});

        		typeIngredient1.addActionListener(e -> {
        		    JLabel panel = new JLabel(imageIcon);
        		    panel.setLayout(new BorderLayout());
        		    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        		    TypeIngredientDAO dao = new TypeIngredientDAO(connect);

        		    // Create table model and set data from findAll() method
        		    DefaultTableModel tableModel = new DefaultTableModel();
        		    tableModel.addColumn("Référence");
        		    tableModel.addColumn("Nom Type");
        		    ArrayList<TypeIngredient> list = dao.findAll();
        		    for (TypeIngredient t : list) {
        		        Object[] rowData = {t.getRefType(), t.getNomType()};
        		        tableModel.addRow(rowData);
        		    }

        		    // Create JTable and set the table model
        		    JTable table = new JTable(tableModel);
        		    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		    table.getTableHeader().setReorderingAllowed(false);
        		    table.getTableHeader().setResizingAllowed(false);
        		    table.setRowHeight(30);

        		    // Add table to scroll pane and add to panel
        		    JScrollPane scrollPane = new JScrollPane(table);
        		    panel.add(scrollPane, BorderLayout.CENTER);

        		    // Set background color
        		    table.setBackground(new Color(230, 204, 255));

        		    // Add panel to JFrame
        		    frame.getContentPane().removeAll();
        		    frame.setContentPane(panel);
        		    frame.revalidate();
        		});


        		typeIngredient2.addActionListener(e -> {
        		    JLabel panel = new JLabel(imageIcon);
        		    panel.setLayout(new GridBagLayout());
        		    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        		    panel.setBackground(Color.WHITE);
        		    TypeIngredientDAO daoTI = new TypeIngredientDAO(connect);
        		    ArrayList<TypeIngredient> list3 = daoTI.findAll();

        		    GridBagConstraints c = new GridBagConstraints();
        		    c.gridx = 0;
        		    c.gridy = 0;
        		    c.anchor = GridBagConstraints.LINE_START;
        		    c.insets = new Insets(0, 0, 10, 0);

        		    JLabel refLabel = new JLabel("Référence du type d'ingrédient à rechercher:");
        		    panel.add(refLabel, c);
        		    c.gridy++;

        		    ArrayList<String> refTypes = new ArrayList<String>();
        		    for (TypeIngredient ti : list3) {
        		        refTypes.add(ti.getRefType());
        		    }

        		    JComboBox<String> refTypeComboBox = new JComboBox<String>(refTypes.toArray(new String[0]));
        		    refTypeComboBox.setSelectedIndex(-1);
        		    panel.add(refTypeComboBox, c);
        		    c.gridy++;
        		    c.anchor = GridBagConstraints.CENTER;
        		    c.fill = GridBagConstraints.HORIZONTAL;
        		    c.insets = new Insets(20, 0, 0, 0);

        		    c.gridy++;
        		    JButton submitButton = new JButton("Rechercher");
        		    submitButton.setBackground(Color.decode("#A7C68C"));
        		    panel.add(submitButton, c);
        		    submitButton.addActionListener(new ActionListener() {
        		        public void actionPerformed(ActionEvent a) {
        		            TypeIngredient result = daoTI.find((String)refTypeComboBox.getSelectedItem());
        		            if (result != null) {
        		                JDialog dialog = new JDialog(frame, "Type d'Ingrédient Information");
        		                dialog.setSize(100, 100);

        		                DefaultTableModel model = new DefaultTableModel();
        		                model.addColumn("Propriété");
        		                model.addColumn("Valeur");

        		                model.addRow(new Object[]{"Ref Type", result.getRefType()});
        		                model.addRow(new Object[]{"Nom Type", result.getNomType()});

        		                JTable table = new JTable(model);

        		                JScrollPane scrollPane = new JScrollPane(table);

        		                JPanel panel = new JPanel(new BorderLayout());
        		                panel.add(scrollPane, BorderLayout.CENTER);

        		                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        		                dialog.setContentPane(panel);
        		                dialog.pack();
        		                dialog.setVisible(true);

        		            } else {
        		                JOptionPane.showMessageDialog(null, "Type d'ingrédient non trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
        		            }
        		        }
        		    });

        		    panel.add(submitButton, c);

        		    frame.getContentPane().removeAll();
        		    frame.setContentPane(panel);
        		    frame.revalidate();
        		});


        		typeIngredient3.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
        		    panel.setLayout(new GridBagLayout());
            		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            		panel.setBackground(Color.WHITE);
            		

            		GridBagConstraints c = new GridBagConstraints();
            		c.gridx = 0;
            		c.gridy = 0;
            		c.anchor = GridBagConstraints.LINE_START;
            		c.insets = new Insets(0, 0, 10, 0);

            		JLabel refLabel = new JLabel("Référence:");
            		panel.add(refLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> references = new ArrayList<String>();
            		for (int i = 1001; i <= 2000; i++) {
            		    references.add("T" + i);
            		}
            		JComboBox<String> refComboBox = new JComboBox<String>(references.toArray(new String[0]));
            		refComboBox.setSelectedIndex(-1);
            		panel.add(refComboBox, c);
            		c.gridy++;
            		
            		JLabel nomLabel = new JLabel("Nom:");
            		panel.add(nomLabel, c);

            		c.gridy++;
            		JTextField nomField = new JTextField(20);
            		panel.add(nomField, c);
            		
            		c.gridy++;
            		c.anchor = GridBagConstraints.CENTER;
            		c.fill = GridBagConstraints.HORIZONTAL;
            		c.insets = new Insets(20, 0, 0, 0);

            		JButton submitButton = new JButton("Soumettre");
            		submitButton.setBackground(Color.decode("#A7C68C"));
            		panel.add(submitButton, c);

            		c.gridy++;
            		JButton resetButton = new JButton("Réinitialiser");
            		resetButton.setBackground(Color.decode("#C98080"));
            		resetButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent a) {
            		        //refField.setText("");
            		        nomField.setText("");
            		    }
            		});
            		panel.add(resetButton, c);
            		
            		submitButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent e) {
            		        String nom = nomField.getText();
            		        TypeIngredient result=null;
            		        TypeIngredientDAO dao= new TypeIngredientDAO(connect);
            		        if (nom.length() > 50) {
            		            JOptionPane.showMessageDialog(null, "Le nom doit être de 50 caractères maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (refComboBox.getSelectedItem()==null) {
            		            JOptionPane.showMessageDialog(null, "Le choix d'une référence est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (dao.create(result =  new TypeIngredient((String) refComboBox.getSelectedItem(), nom))) {
            		        	int i =1000;
            		        	JOptionPane.showMessageDialog(null, "ajouté à la base de données avec succés", "ajouté", JOptionPane.INFORMATION_MESSAGE);
            		            references.add("T" + (references.size()+1000+i++));
            		            refComboBox.addItem("T" + references.size());
            		            
            		            references.remove((String) refComboBox.getSelectedItem());
            		            refComboBox.removeItem(refComboBox.getSelectedItem());
            		            
            		            return;
            		        }
            		        else {
            		            JOptionPane.showMessageDialog(null, "violation de contrainte unique", "Erreur", JOptionPane.ERROR_MESSAGE);       		    	
            		        }
            		        //System.out.println(result);
            		    }
            		});

            		
            		frame.getContentPane().removeAll();
            		frame.setContentPane(panel);
            		frame.revalidate();

        		/*panel.setBackground(Color.BLUE);
        		frame.setContentPane(panel);
        		frame.revalidate();*/
        		});

        		typeIngredient4.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
        		    panel.setLayout(new GridBagLayout());
            		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            		panel.setBackground(Color.WHITE);
            		TypeIngredientDAO daoR = new TypeIngredientDAO(connect);
        			ArrayList<TypeIngredient> list2= daoR.findAll();

            		GridBagConstraints c = new GridBagConstraints();
            		c.gridx = 0;
            		c.gridy = 0;
            		c.anchor = GridBagConstraints.LINE_START;
            		c.insets = new Insets(0, 0, 10, 0);

            		JLabel refLabel = new JLabel("Référence du type d'ingredinet à mettre à jour:");
            		panel.add(refLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> refRecette = new ArrayList<String>();
            		for (TypeIngredient r : list2) {
            			refRecette.add(r.getRefType());
            		}
            		
            		JComboBox<String> refRecetteComboBox = new JComboBox<String>(refRecette.toArray(new String[0]));
            		refRecetteComboBox.setSelectedIndex(-1);
            		panel.add(refRecetteComboBox, c);
            		c.gridy++;
            		
            		JLabel nomLabel = new JLabel("Nom:");
            		panel.add(nomLabel, c);

            		c.gridy++;
            		JTextField nomField = new JTextField(20);
            		panel.add(nomField, c);
            		
            		c.gridy++;
            		c.anchor = GridBagConstraints.CENTER;
            		c.fill = GridBagConstraints.HORIZONTAL;
            		c.insets = new Insets(20, 0, 0, 0);

            		JButton submitButton = new JButton("mettre à jour");
            		submitButton.setBackground(Color.decode("#A7C68C"));
            		panel.add(submitButton, c);

            		c.gridy++;
            		JButton resetButton = new JButton("Réinitialiser");
            		resetButton.setBackground(Color.decode("#C98080"));
            		resetButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent a) {
            		        //refField.setText("");
            		        nomField.setText("");
            		    }
            		});
            		panel.add(resetButton, c);
            		
            		submitButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent e) {
            		        String nom = nomField.getText();
            		        TypeIngredient result=null;
            		        TypeIngredientDAO dao= new TypeIngredientDAO(connect);
            		        if (nom.length() > 50) {
            		            JOptionPane.showMessageDialog(null, "Le nom doit être de 50 caractères maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (refRecetteComboBox.getSelectedItem()==null) {
            		            JOptionPane.showMessageDialog(null, "Le choix d'une référence est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (dao.update(result =  new TypeIngredient((String) refRecetteComboBox.getSelectedItem(), nom))) {
            		        	JOptionPane.showMessageDialog(null, "mis à jour avec succés", "mis à jour", JOptionPane.INFORMATION_MESSAGE);            		           
            		            
            		            return;
            		        }
            		        else {
            		            JOptionPane.showMessageDialog(null, "violation de contrainte unique", "Erreur", JOptionPane.ERROR_MESSAGE);       		    	
            		        }
            		        //System.out.println(result);
            		    }
            		});

            		
            		frame.getContentPane().removeAll();
            		frame.setContentPane(panel);
            		frame.revalidate();
        		/*JPanel panel = new JPanel();
        		panel.setBackground(Color.YELLOW);
        		frame.setContentPane(panel);
        		frame.revalidate();*/
        		});

        		typeIngredient5.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
            		panel.setLayout(new GridBagLayout());
            		//JPanel panel = new JPanel(new GridBagLayout());
            		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            		panel.setBackground(Color.WHITE);
            		TypeIngredientDAO daoR = new TypeIngredientDAO(connect);
        			ArrayList<TypeIngredient> list2= daoR.findAll();

            		GridBagConstraints c = new GridBagConstraints();
            		c.gridx = 0;
            		c.gridy = 0;
            		c.anchor = GridBagConstraints.LINE_START;
            		c.insets = new Insets(0, 0, 10, 0);

            		JLabel refLabel = new JLabel("Référence du TypeIngredient à effacer:");
            		panel.add(refLabel, c);
            		c.gridy++;
            		
            		ArrayList<String> refRecette = new ArrayList<String>();
            		for (TypeIngredient r : list2) {
            			refRecette.add(r.getRefType());
            		}
            		
            		JComboBox<String> refRecetteComboBox = new JComboBox<String>(refRecette.toArray(new String[0]));
            		refRecetteComboBox.setSelectedIndex(-1);
            		panel.add(refRecetteComboBox, c);
            		c.gridy++;
            		c.anchor = GridBagConstraints.CENTER;
            		c.fill = GridBagConstraints.HORIZONTAL;
            		c.insets = new Insets(20, 0, 0, 0);

            		c.gridy++;
            		JButton resetButton = new JButton("Supprimer");
            		resetButton.setBackground(Color.decode("#C98080"));
            		resetButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent a) {
            		        if (daoR.delete(daoR.find((String)refRecetteComboBox.getSelectedItem()))) {
            		        	JOptionPane.showMessageDialog(null, "supprimé de la base de données avec succés", "supprimé", JOptionPane.INFORMATION_MESSAGE);
            		        }
            		    }
            		});
            		panel.add(resetButton, c);        		     
            		
            		frame.getContentPane().removeAll();
            		frame.setContentPane(panel);
            		frame.revalidate();
        		/*panel.setBackground(Color.PINK);
        		frame.setContentPane(panel);
        		frame.revalidate();*/
        		});

        		ingredient1.addActionListener(e -> {
        		    JLabel panel = new JLabel(imageIcon);
        		    panel.setLayout(new BorderLayout());
        		    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        		    IngredientDAO dao = new IngredientDAO(connect);

        		    // Create table model and set data from findAll() method
        		    DefaultTableModel tableModel = new DefaultTableModel();
        		    tableModel.addColumn("Référence");
        		    tableModel.addColumn("Nom");
        		    tableModel.addColumn("Type");
        		    ArrayList<Ingredient> list = dao.findAll();
        		    for (Ingredient i : list) {
        		        Object[] rowData = {i.getRefIngredient(), i.getNomIngredient(), i.getRefType().getRefType()};
        		        tableModel.addRow(rowData);
        		    }

        		    // Create JTable and set the table model
        		    JTable table = new JTable(tableModel);
        		    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		    table.getTableHeader().setReorderingAllowed(false);
        		    table.getTableHeader().setResizingAllowed(false);
        		    table.setRowHeight(30);

        		    // Add table to scroll pane and add to panel
        		    JScrollPane scrollPane = new JScrollPane(table);
        		    panel.add(scrollPane, BorderLayout.CENTER);

        		    // Set background color
        		    table.setBackground(new Color(230, 204, 255));

        		    // Add panel to JFrame
        		    frame.getContentPane().removeAll();
        		    frame.setContentPane(panel);
        		    frame.revalidate();
        		});


        		ingredient2.addActionListener(e -> {
        		    JLabel panel = new JLabel(imageIcon);
        		    panel.setLayout(new GridBagLayout());
        		    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        		    panel.setBackground(Color.WHITE);
        		    IngredientDAO daoI = new IngredientDAO(connect);
        		    ArrayList<Ingredient> list3 = daoI.findAll();

        		    GridBagConstraints c = new GridBagConstraints();
        		    c.gridx = 0;
        		    c.gridy = 0;
        		    c.anchor = GridBagConstraints.LINE_START;
        		    c.insets = new Insets(0, 0, 10, 0);

        		    JLabel refLabel = new JLabel("Référence de l'ingrédient à rechercher:");
        		    panel.add(refLabel, c);
        		    c.gridy++;

        		    ArrayList<String> refIng = new ArrayList<String>();
        		    for (Ingredient i : list3) {
        		        refIng.add(i.getRefIngredient());
        		    }

        		    JComboBox<String> refIngComboBox = new JComboBox<String>(refIng.toArray(new String[0]));
        		    refIngComboBox.setSelectedIndex(-1);
        		    panel.add(refIngComboBox, c);
        		    c.gridy++;
        		    c.anchor = GridBagConstraints.CENTER;
        		    c.fill = GridBagConstraints.HORIZONTAL;
        		    c.insets = new Insets(20, 0, 0, 0);

        		    c.gridy++;
        		    JButton submitButton = new JButton("Rechercher");
        		    submitButton.setBackground(Color.decode("#A7C68C"));
        		    panel.add(submitButton, c);
        		    submitButton.addActionListener(new ActionListener() {
        		        public void actionPerformed(ActionEvent a) {
        		            Ingredient result = daoI.find((String)refIngComboBox.getSelectedItem());
        		            if (result != null) {
        		                JDialog dialog = new JDialog(frame, "Ingredient Information");
        		                dialog.setSize(100, 100);

        		                DefaultTableModel model = new DefaultTableModel();
        		                model.addColumn("Propriété");
        		                model.addColumn("Valeur");

        		                model.addRow(new Object[]{"Ref Ingredient", result.getRefIngredient()});
        		                model.addRow(new Object[]{"Nom Ingredient", result.getNomIngredient()});
        		                model.addRow(new Object[]{"Type Ingredient", result.getRefType().getRefType()});

        		                JTable table = new JTable(model);

        		                JScrollPane scrollPane = new JScrollPane(table);

        		                JPanel panel = new JPanel(new BorderLayout());
        		                panel.add(scrollPane, BorderLayout.CENTER);

        		                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        		                dialog.setContentPane(panel);
        		                dialog.pack();
        		                dialog.setVisible(true);

        		            } else {
        		                JOptionPane.showMessageDialog(null, "Ingredient non trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
        		            }
        		        }
        		    });

        		    panel.add(submitButton, c);

        		    frame.getContentPane().removeAll();
        		    frame.setContentPane(panel);
        		    frame.revalidate();
        		});


        		ingredient3.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
        		    panel.setLayout(new GridBagLayout());
        			TypeIngredientDAO daot = new TypeIngredientDAO(connect);
        			ArrayList<TypeIngredient> list= daot.findAll();
            		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            		panel.setBackground(Color.WHITE);
            		

            		GridBagConstraints c = new GridBagConstraints();
            		c.gridx = 0;
            		c.gridy = 0;
            		c.anchor = GridBagConstraints.LINE_START;
            		c.insets = new Insets(0, 0, 10, 0);

            		JLabel refLabel = new JLabel("Référence:");
            		panel.add(refLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> references = new ArrayList<String>();
            		for (int i = 1001; i <= 2000; i++) {
            		    references.add("I" + i);
            		}
            		
            		JComboBox<String> refComboBox = new JComboBox<String>(references.toArray(new String[0]));
            		refComboBox.setSelectedIndex(-1);
            		panel.add(refComboBox, c);
            		c.gridy++;
            		
            		JLabel refTypeLabel = new JLabel("réfrence Type Ingredient:");
            		panel.add(refTypeLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> refType = new ArrayList<String>();
            		for (TypeIngredient typeIngredient : list) {
            		    refType.add(typeIngredient.getRefType());
            		}
            		
            		JComboBox<String> refTypeComboBox = new JComboBox<String>(refType.toArray(new String[0]));
            		refTypeComboBox.setSelectedIndex(-1);
            		panel.add(refTypeComboBox, c);
            		c.gridy++;
            		
            		JLabel nomLabel = new JLabel("nom:");
            		panel.add(nomLabel, c);

            		c.gridy++;
            		JTextField nomField = new JTextField(20);
            		panel.add(nomField, c);
            		
            		c.gridy++;
            		c.anchor = GridBagConstraints.CENTER;
            		c.fill = GridBagConstraints.HORIZONTAL;
            		c.insets = new Insets(20, 0, 0, 0);

            		JButton submitButton = new JButton("Soumettre");
            		submitButton.setBackground(Color.decode("#A7C68C"));
            		panel.add(submitButton, c);

            		c.gridy++;
            		JButton resetButton = new JButton("Réinitialiser");
            		resetButton.setBackground(Color.decode("#C98080"));
            		resetButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent a) {
            		        //refField.setText("");
            		        nomField.setText("");
            		    }
            		});
            		panel.add(resetButton, c);
            		
            		submitButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent e) {
            		        String nom = nomField.getText();
            		        Ingredient result=null;
            		        IngredientDAO dao= new IngredientDAO(connect);
            		        TypeIngredientDAO daot = new TypeIngredientDAO(connect);
            		        String type = (String) refTypeComboBox.getSelectedItem();
            		        if (nom.length() > 50) {
            		            JOptionPane.showMessageDialog(null, "Le nom doit être de 50 caractères maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (refComboBox.getSelectedItem()==null) {
            		            JOptionPane.showMessageDialog(null, "Le choix d'une référence est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (dao.create(result =  new Ingredient((String) refComboBox.getSelectedItem(),nom,daot.find(type)))) {
            		        	int i =1000;
            		        	JOptionPane.showMessageDialog(null, "ajouté à la base de données avec succés", "ajouté", JOptionPane.INFORMATION_MESSAGE);
            		            references.add("C" + (references.size()+1000+i++));
            		            refComboBox.addItem("C" + references.size());
            		            
            		            references.remove((String) refComboBox.getSelectedItem());
            		            refComboBox.removeItem(refComboBox.getSelectedItem());
            		            
            		            return;
            		        }
            		        else {
            		            JOptionPane.showMessageDialog(null, "violation de contrainte unique : clé déjà utilisée", "Erreur", JOptionPane.ERROR_MESSAGE);       		    	
            		        }
            		        //System.out.println(result);
            		    }
            		});

            		
            		frame.getContentPane().removeAll();
            		frame.setContentPane(panel);
            		frame.revalidate();

        		/*panel.setBackground(Color.BLUE);
        		frame.setContentPane(panel);
        		frame.revalidate();*/
        		});

        		ingredient4.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
        		    panel.setLayout(new GridBagLayout());
        			TypeIngredientDAO daot = new TypeIngredientDAO(connect);
        			ArrayList<TypeIngredient> list= daot.findAll();
            		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            		panel.setBackground(Color.WHITE);
            		IngredientDAO daoR = new IngredientDAO(connect);
        			ArrayList<Ingredient> list2= daoR.findAll();


            		GridBagConstraints c = new GridBagConstraints();
            		c.gridx = 0;
            		c.gridy = 0;
            		c.anchor = GridBagConstraints.LINE_START;
            		c.insets = new Insets(0, 0, 10, 0);

            		JLabel refLabel = new JLabel("Référence:");
            		panel.add(refLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> refRecette = new ArrayList<String>();
            		for (Ingredient r : list2) {
            			refRecette.add(r.getRefIngredient());
            		}
            		
            		JComboBox<String> refRecetteComboBox = new JComboBox<String>(refRecette.toArray(new String[0]));
            		refRecetteComboBox.setSelectedIndex(-1);
            		panel.add(refRecetteComboBox, c);
            		c.gridy++;
            		
            		JLabel refTypeLabel = new JLabel("réfrence Type Ingredient:");
            		panel.add(refTypeLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> refType = new ArrayList<String>();
            		for (TypeIngredient typeIngredient : list) {
            		    refType.add(typeIngredient.getRefType());
            		}
            		
            		JComboBox<String> refTypeComboBox = new JComboBox<String>(refType.toArray(new String[0]));
            		refTypeComboBox.setSelectedIndex(-1);
            		panel.add(refTypeComboBox, c);
            		c.gridy++;
            		
            		JLabel nomLabel = new JLabel("nom:");
            		panel.add(nomLabel, c);

            		c.gridy++;
            		JTextField nomField = new JTextField(20);
            		panel.add(nomField, c);
            		
            		c.gridy++;
            		c.anchor = GridBagConstraints.CENTER;
            		c.fill = GridBagConstraints.HORIZONTAL;
            		c.insets = new Insets(20, 0, 0, 0);

            		JButton submitButton = new JButton("mettre à jour");
            		submitButton.setBackground(Color.decode("#A7C68C"));
            		panel.add(submitButton, c);

            		c.gridy++;
            		JButton resetButton = new JButton("Réinitialiser");
            		resetButton.setBackground(Color.decode("#C98080"));
            		resetButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent a) {
            		        //refField.setText("");
            		        nomField.setText("");
            		    }
            		});
            		panel.add(resetButton, c);
            		
            		submitButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent e) {
            		        String nom = nomField.getText();
            		        Ingredient result=null;
            		        IngredientDAO dao= new IngredientDAO(connect);
            		        TypeIngredientDAO daot = new TypeIngredientDAO(connect);
            		        String type = (String) refTypeComboBox.getSelectedItem();
            		        if (nom.length() > 50) {
            		            JOptionPane.showMessageDialog(null, "Le nom doit être de 50 caractères maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (refRecetteComboBox.getSelectedItem()==null) {
            		            JOptionPane.showMessageDialog(null, "Le choix d'une référence est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (dao.update(result =  new Ingredient((String) refRecetteComboBox.getSelectedItem(),nom,daot.find(type)))) {
            		        	int i =1000;
            		        	JOptionPane.showMessageDialog(null, "mis à jour avec succés", "mis à jour", JOptionPane.INFORMATION_MESSAGE);           		                 		            
            		            return;
            		        }
            		        else {
            		            JOptionPane.showMessageDialog(null, "violation de contrainte unique : clé déjà utilisée", "Erreur", JOptionPane.ERROR_MESSAGE);       		    	
            		        }
            		        //System.out.println(result);
            		    }
            		});

            		
            		frame.getContentPane().removeAll();
            		frame.setContentPane(panel);
            		frame.revalidate();

        		/*JPanel panel = new JPanel();
        		panel.setBackground(Color.YELLOW);
        		frame.setContentPane(panel);
        		frame.revalidate();*/
        		});

        		ingredient5.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
            		panel.setLayout(new GridBagLayout());
            		//JPanel panel = new JPanel(new GridBagLayout());
            		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            		panel.setBackground(Color.WHITE);
            		IngredientDAO daoR = new IngredientDAO(connect);
        			ArrayList<Ingredient> list2= daoR.findAll();

            		GridBagConstraints c = new GridBagConstraints();
            		c.gridx = 0;
            		c.gridy = 0;
            		c.anchor = GridBagConstraints.LINE_START;
            		c.insets = new Insets(0, 0, 10, 0);

            		JLabel refLabel = new JLabel("Référence de l'Ingredient à effacer:");
            		panel.add(refLabel, c);
            		c.gridy++;
            		
            		ArrayList<String> refRecette = new ArrayList<String>();
            		for (Ingredient r : list2) {
            			refRecette.add(r.getRefIngredient());
            		}
            		
            		JComboBox<String> refRecetteComboBox = new JComboBox<String>(refRecette.toArray(new String[0]));
            		refRecetteComboBox.setSelectedIndex(-1);
            		panel.add(refRecetteComboBox, c);
            		c.gridy++;
            		c.anchor = GridBagConstraints.CENTER;
            		c.fill = GridBagConstraints.HORIZONTAL;
            		c.insets = new Insets(20, 0, 0, 0);

            		c.gridy++;
            		JButton resetButton = new JButton("Supprimer");
            		resetButton.setBackground(Color.decode("#C98080"));
            		resetButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent a) {
            		        if (daoR.delete(daoR.find((String)refRecetteComboBox.getSelectedItem()))) {
            		        	JOptionPane.showMessageDialog(null, "supprimé de la base de données avec succés", "supprimé", JOptionPane.INFORMATION_MESSAGE);
            		        }
            		    }
            		});
            		panel.add(resetButton, c);        		     
            		
            		frame.getContentPane().removeAll();
            		frame.setContentPane(panel);
            		frame.revalidate();
        		/*panel.setBackground(Color.PINK);
        		frame.setContentPane(panel);
        		frame.revalidate();*/
        		});

        		composition1.addActionListener(e -> {
        		    JLabel panel = new JLabel(imageIcon);
        		    panel.setLayout(new BorderLayout());
        		    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        		    CompositionDAO dao = new CompositionDAO(connect);
        		    
        		    // Create table model and set data from findAll() method
        		    DefaultTableModel tableModel = new DefaultTableModel();
        		    tableModel.addColumn("Référence");
        		    tableModel.addColumn("Quantité");
        		    tableModel.addColumn("Recette");
        		    tableModel.addColumn("Ingrédient");
        		    ArrayList<Composition> list = dao.findAll();
        		    for (Composition c : list) {
        		        Object[] rowData = {c.getRefComposition(), c.getQuantiteComposition(), c.getRefRecette().getRefRecette(),
        		                            c.getRefIngredient().getRefIngredient()};
        		        tableModel.addRow(rowData);
        		    }

        		    // Create JTable and set the table model
        		    JTable table = new JTable(tableModel);
        		    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		    table.getTableHeader().setReorderingAllowed(false);
        		    table.getTableHeader().setResizingAllowed(false);
        		    table.setRowHeight(30);

        		    // Add table to scroll pane and add to panel
        		    JScrollPane scrollPane = new JScrollPane(table);
        		    panel.add(scrollPane, BorderLayout.CENTER);
        		            
        		    // Set background color
        		    table.setBackground(new Color(230, 204, 255));

        		    // Add panel to JFrame
        		    frame.getContentPane().removeAll();
        		    frame.setContentPane(panel);
        		    frame.revalidate();
        		});


        		composition2.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
        			panel.setLayout(new GridBagLayout());
        			panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        			panel.setBackground(Color.WHITE);
        			CompositionDAO daoC = new CompositionDAO(connect);
        			ArrayList<Composition> list3 = daoC.findAll();


        				    GridBagConstraints c = new GridBagConstraints();
        				    c.gridx = 0;
        				    c.gridy = 0;
        				    c.anchor = GridBagConstraints.LINE_START;
        				    c.insets = new Insets(0, 0, 10, 0);

        				    JLabel refLabel = new JLabel("Référence de la composition à rechercher:");
        				    panel.add(refLabel, c);
        				    c.gridy++;

        				    ArrayList<String> refComp = new ArrayList<String>();
        				    for (Composition comp : list3) {
        				        refComp.add(Integer.toString(comp.getRefComposition()));
        				    }

        				    JComboBox<String> refCompComboBox = new JComboBox<String>(refComp.toArray(new String[0]));
        				    refCompComboBox.setSelectedIndex(-1);
        				    panel.add(refCompComboBox, c);
        				    c.gridy++;
        				    c.anchor = GridBagConstraints.CENTER;
        				    c.fill = GridBagConstraints.HORIZONTAL;
        				    c.insets = new Insets(20, 0, 0, 0);

        				    c.gridy++;
        				    JButton submitButton = new JButton("Rechercher");
        				    submitButton.setBackground(Color.decode("#A7C68C"));
        				    panel.add(submitButton, c);
        				    submitButton.addActionListener(new ActionListener() {
        				        public void actionPerformed(ActionEvent a) {
        				            Composition result = daoC.find((String)refCompComboBox.getSelectedItem());
        				            if (result != null) {
        				                JDialog dialog = new JDialog(frame, "Composition Information");
        				                dialog.setSize(100, 100);

        				                DefaultTableModel model = new DefaultTableModel();
        				                model.addColumn("Propriété");
        				                model.addColumn("Valeur");

        				                model.addRow(new Object[]{"Ref Composition", result.getRefComposition()});
        				                model.addRow(new Object[]{"Quantité Composition", result.getQuantiteComposition()});
        				                model.addRow(new Object[]{"Recette", result.getRefRecette().getNomRecette()});
        				                model.addRow(new Object[]{"Ingrédient", result.getRefIngredient().getNomIngredient()});

        				                JTable table = new JTable(model);

        				                JScrollPane scrollPane = new JScrollPane(table);

        				                JPanel panel = new JPanel(new BorderLayout());
        				                panel.add(scrollPane, BorderLayout.CENTER);

        				                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        				                dialog.setContentPane(panel);
        				                dialog.pack();
        				                dialog.setVisible(true);

        				            } else {
        				                JOptionPane.showMessageDialog(null, "Composition non trouvée", "Erreur", JOptionPane.ERROR_MESSAGE);
        				            }
        				        }
        				    });

        				    panel.add(submitButton, c);

        				    frame.getContentPane().removeAll();
        				    frame.setContentPane(panel);
        				    frame.revalidate();
        				}); 

        		composition3.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
        			panel.setLayout(new GridBagLayout());
        			IngredientDAO daot = new IngredientDAO(connect);
        			ArrayList<Ingredient> list= daot.findAll();
        			RecetteDAO daoR = new RecetteDAO(connect);
        			ArrayList<Recette> list2= daoR.findAll();
            		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            		panel.setBackground(Color.WHITE);
            		

            		GridBagConstraints c = new GridBagConstraints();
            		c.gridx = 0;
            		c.gridy = 0;
            		c.anchor = GridBagConstraints.LINE_START;
            		c.insets = new Insets(0, 0, 10, 0);

            		JLabel refLabel = new JLabel("Référence:");
            		panel.add(refLabel, c);

            		c.gridy++;
            		
            		ArrayList<Integer> references = new ArrayList<Integer>();
            		for (int i = 1001; i <= 2000; i++) {
            		    references.add(i);
            		}
            		
            		JComboBox<Integer> refComboBox = new JComboBox<Integer>(references.toArray(new Integer[references.size()]));
            		refComboBox.setSelectedIndex(-1);
            		panel.add(refComboBox, c);
            		c.gridy++;
            		
            		JLabel refTypeLabel = new JLabel("réfrence Recette:");
            		panel.add(refTypeLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> refRecette = new ArrayList<String>();
            		for (Recette r : list2) {
            			refRecette.add(r.getRefRecette());
            		}
            		
            		
            		JComboBox<String> refRecetteComboBox = new JComboBox<String>(refRecette.toArray(new String[0]));
            		refRecetteComboBox.setSelectedIndex(-1);
            		panel.add(refRecetteComboBox, c);
            		c.gridy++;
            		
            		JLabel refIngredientLabel = new JLabel("réfrence Ingredient:");
            		panel.add(refIngredientLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> refIngredient = new ArrayList<String>();
            		for (Ingredient r : list) {
            			refIngredient.add(r.getRefIngredient());
            		}
            		
            		JComboBox<String> refIngredientComboBox = new JComboBox<String>(refIngredient.toArray(new String[0]));
            		refIngredientComboBox.setSelectedIndex(-1);
            		panel.add(refIngredientComboBox, c);
            		c.gridy++;
            		
            		JLabel nomLabel = new JLabel("quantité:");
            		panel.add(nomLabel, c);

            		c.gridy++;
            		
            		ArrayList<Double> qte = new ArrayList<Double>();
            		for (double i = 10.5; i <= 200; i+=0.5) {
            		    qte.add(i);
            		}
            		JComboBox<Double> qteComboBox = new JComboBox<Double>(qte.toArray(new Double[qte.size()]));
            		qteComboBox.setSelectedIndex(-1);
            		panel.add(qteComboBox, c);
            		
            		c.gridy++;
            		c.anchor = GridBagConstraints.CENTER;
            		c.fill = GridBagConstraints.HORIZONTAL;
            		c.insets = new Insets(20, 0, 0, 0);

            		JButton submitButton = new JButton("Soumettre");
            		submitButton.setBackground(Color.decode("#A7C68C"));
            		panel.add(submitButton, c);

            		c.gridy++;
            		JButton resetButton = new JButton("Réinitialiser");
            		resetButton.setBackground(Color.decode("#C98080"));
            		resetButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent a) {
            		        //refField.setText("");
            		        //nomField.setText("");
            		    }
            		});
            		panel.add(resetButton, c);
            		
            		submitButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent e) {
            		        //String nom = nomField.getText();
            		        Composition result=null;
            		        RecetteDAO daoR= new RecetteDAO(connect);
            		        IngredientDAO dao= new IngredientDAO(connect);
            		        CompositionDAO daoc = new CompositionDAO(connect);
            		        int ref =  (int)refComboBox.getSelectedItem();
            		        String recette = (String) refRecetteComboBox.getSelectedItem();
            		        String ing = (String) refIngredientComboBox.getSelectedItem();
            		        double qte =  (double)qteComboBox.getSelectedItem();
            		        
            		        if (refComboBox.getSelectedItem()==null || refRecetteComboBox.getSelectedItem()==null || refIngredientComboBox.getSelectedItem()==null ) {
            		            JOptionPane.showMessageDialog(null, "Le choix d'une référence est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (daoc.create(result =  new Composition(ref,qte,daoR.find(recette),dao.find(ing)))) {
            		        	int i =1000;
            		        	JOptionPane.showMessageDialog(null, "ajouté à la base de données avec succés", "ajouté", JOptionPane.INFORMATION_MESSAGE);
            		            references.add(references.get(references.size()-1)+1000+i++);
            		            refComboBox.addItem(references.size());
            		            
            		            references.remove((Integer) refComboBox.getSelectedItem());
            		            refComboBox.removeItem(refComboBox.getSelectedItem());
            		            
            		            return;
            		        }
            		        else {
            		            JOptionPane.showMessageDialog(null, "violation de contrainte unique", "Erreur", JOptionPane.ERROR_MESSAGE);       		    	
            		        }
            		        //System.out.println(result);
            		    }
            		});

            		
            		frame.getContentPane().removeAll();
            		frame.setContentPane(panel);
            		frame.revalidate();

        		/*panel.setBackground(Color.BLUE);
        		frame.setContentPane(panel);
        		frame.revalidate();*/
        		});
       		
        		composition4.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
        			panel.setLayout(new GridBagLayout());
        			IngredientDAO daot = new IngredientDAO(connect);
        			ArrayList<Ingredient> list= daot.findAll();
        			RecetteDAO daoR = new RecetteDAO(connect);
        			ArrayList<Recette> list2= daoR.findAll();
            		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            		panel.setBackground(Color.WHITE);
            		

            		GridBagConstraints c = new GridBagConstraints();
            		c.gridx = 0;
            		c.gridy = 0;
            		c.anchor = GridBagConstraints.LINE_START;
            		c.insets = new Insets(0, 0, 10, 0);

            		JLabel refLabel = new JLabel("Référence de la composition à mettre à jour:");
            		panel.add(refLabel, c);

            		c.gridy++;
            		
            		CompositionDAO daoC = new CompositionDAO(connect);
        			ArrayList<Composition> list3 = daoC.findAll();
        			ArrayList<Integer> refComposition = new ArrayList<Integer>();
        			for (Composition r : list3) {
        			    refComposition.add(r.getRefComposition());
        			}

        			JComboBox<Integer> refCompositionComboBox = new JComboBox<Integer>(refComposition.toArray(new Integer[0]));
        			refCompositionComboBox.setSelectedIndex(-1);
        			panel.add(refCompositionComboBox, c);
            		c.gridy++;
            		
            		JLabel refTypeLabel = new JLabel("réfrence Recette:");
            		panel.add(refTypeLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> refRecette = new ArrayList<String>();
            		for (Recette r : list2) {
            			refRecette.add(r.getRefRecette());
            		}
            		
            		
            		JComboBox<String> refRecetteComboBox = new JComboBox<String>(refRecette.toArray(new String[0]));
            		refRecetteComboBox.setSelectedIndex(-1);
            		panel.add(refRecetteComboBox, c);
            		c.gridy++;
            		
            		JLabel refIngredientLabel = new JLabel("réfrence Ingredient:");
            		panel.add(refIngredientLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> refIngredient = new ArrayList<String>();
            		for (Ingredient r : list) {
            			refIngredient.add(r.getRefIngredient());
            		}
            		
            		JComboBox<String> refIngredientComboBox = new JComboBox<String>(refIngredient.toArray(new String[0]));
            		refIngredientComboBox.setSelectedIndex(-1);
            		panel.add(refIngredientComboBox, c);
            		c.gridy++;
            		
            		JLabel nomLabel = new JLabel("quantité:");
            		panel.add(nomLabel, c);

            		c.gridy++;
            		
            		ArrayList<Double> qte = new ArrayList<Double>();
            		for (double i = 10.5; i <= 200; i+=0.5) {
            		    qte.add(i);
            		}
            		JComboBox<Double> qteComboBox = new JComboBox<Double>(qte.toArray(new Double[qte.size()]));
            		qteComboBox.setSelectedIndex(-1);
            		panel.add(qteComboBox, c);
            		
            		c.gridy++;
            		c.anchor = GridBagConstraints.CENTER;
            		c.fill = GridBagConstraints.HORIZONTAL;
            		c.insets = new Insets(20, 0, 0, 0);

            		JButton submitButton = new JButton("mettre à jour");
            		submitButton.setBackground(Color.decode("#A7C68C"));
            		panel.add(submitButton, c);

            		c.gridy++;
            		JButton resetButton = new JButton("Réinitialiser");
            		resetButton.setBackground(Color.decode("#C98080"));
            		resetButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent a) {
            		        //refField.setText("");
            		        //nomField.setText("");
            		    }
            		});
            		panel.add(resetButton, c);
            		
            		submitButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent e) {
            		        //String nom = nomField.getText();
            		        Composition result=null;
            		        RecetteDAO daoR= new RecetteDAO(connect);
            		        IngredientDAO dao= new IngredientDAO(connect);
            		        CompositionDAO daoc = new CompositionDAO(connect);
            		        int ref =  (int)refCompositionComboBox.getSelectedItem();
            		        String recette = (String) refRecetteComboBox.getSelectedItem();
            		        String ing = (String) refIngredientComboBox.getSelectedItem();
            		        double qte =  (double)qteComboBox.getSelectedItem();
            		        
            		        if (refCompositionComboBox.getSelectedItem()==null || refRecetteComboBox.getSelectedItem()==null || refIngredientComboBox.getSelectedItem()==null ) {
            		            JOptionPane.showMessageDialog(null, "Le choix d'une référence est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (daoc.update(result =  new Composition(ref,qte,daoR.find(recette),dao.find(ing)))) {
            		        	JOptionPane.showMessageDialog(null, "mis à jour avec succés", "mis à jour", JOptionPane.INFORMATION_MESSAGE);           		                     		            
            		            return;
            		        }
            		        else {
            		            JOptionPane.showMessageDialog(null, "violation de contrainte unique", "Erreur", JOptionPane.ERROR_MESSAGE);       		    	
            		        }
            		        //System.out.println(result);
            		    }
            		});

            		
            		frame.getContentPane().removeAll();
            		frame.setContentPane(panel);
            		frame.revalidate();

        		/*JPanel panel = new JPanel();
        		panel.setBackground(Color.YELLOW);
        		frame.setContentPane(panel);
        		frame.revalidate();*/
        		});

        		composition5.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
        			panel.setLayout(new GridBagLayout());
        			panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        			panel.setBackground(Color.WHITE);
        			CompositionDAO daoC = new CompositionDAO(connect);
        			ArrayList<Composition> list2 = daoC.findAll();

        			GridBagConstraints c = new GridBagConstraints();
        			c.gridx = 0;
        			c.gridy = 0;
        			c.anchor = GridBagConstraints.LINE_START;
        			c.insets = new Insets(0, 0, 10, 0);

        			JLabel refLabel = new JLabel("Référence de la Composition à effacer:");
        			panel.add(refLabel, c);
        			c.gridy++;
        			
        			ArrayList<Integer> refComposition = new ArrayList<Integer>();
        			for (Composition r : list2) {
        			    refComposition.add(r.getRefComposition());
        			}

        			JComboBox<Integer> refCompositionComboBox = new JComboBox<Integer>(refComposition.toArray(new Integer[0]));
        			refCompositionComboBox.setSelectedIndex(-1);
        			panel.add(refCompositionComboBox, c);
        			c.gridy++;
        			c.anchor = GridBagConstraints.CENTER;
        			c.fill = GridBagConstraints.HORIZONTAL;
        			c.insets = new Insets(20, 0, 0, 0);

        			c.gridy++;
        			JButton resetButton = new JButton("Supprimer");
        			resetButton.setBackground(Color.decode("#C98080"));
        			resetButton.addActionListener(new ActionListener() {
        			    public void actionPerformed(ActionEvent a) {
        			        if (daoC.delete(daoC.find((String)refCompositionComboBox.getSelectedItem()))) {
        			            JOptionPane.showMessageDialog(null, "Supprimé de la base de données avec succès", "Supprimé", JOptionPane.INFORMATION_MESSAGE);
        			        }
        			    }
        			});
        			panel.add(resetButton, c);

        			frame.getContentPane().removeAll();
        			frame.setContentPane(panel);
        			frame.revalidate();

        		});

        		produit1.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
        			panel.setLayout(new BorderLayout());
        			panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        			ProduitDAO dao = new ProduitDAO(connect);       		
        			// Create table model and set data from findAll() method
        			DefaultTableModel tableModel = new DefaultTableModel();
        			tableModel.addColumn("Référence");
        			tableModel.addColumn("Descriptif");
        			tableModel.addColumn("Date de péremption");
        			tableModel.addColumn("Quantité");
        			tableModel.addColumn("Prix");
        			tableModel.addColumn("Rangement");
        			tableModel.addColumn("Ingrédient");
        			ArrayList<Produit> list = dao.findAll();
        			for (Produit p : list) {
        			    Object[] rowData = {p.getRefProduit(), p.getDescriptifProduit(), p.getDatePeremption(),
        			                        p.getQuantiteProduit(), p.getPrixProduit(), p.getRefRangement().getRefRangement(),
        			                        p.getRefIngredient().getRefIngredient()};
        			    tableModel.addRow(rowData);
        			}

        			// Create JTable and set the table model
        			JTable table = new JTable(tableModel);
        			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        			table.getTableHeader().setReorderingAllowed(false);
        			table.getTableHeader().setResizingAllowed(false);
        			table.setRowHeight(30);

        			// Add table to scroll pane and add to panel
        			JScrollPane scrollPane = new JScrollPane(table);
        			panel.add(scrollPane, BorderLayout.CENTER);
        			        
        			// Set background color
        			table.setBackground(new Color(230, 204, 255));

        			// Add panel to JFrame
        			frame.getContentPane().removeAll();
        			frame.setContentPane(panel);
        			frame.revalidate();

        			});

        		produit2.addActionListener(e -> {
        		    JLabel panel = new JLabel(imageIcon);
        		    panel.setLayout(new GridBagLayout());
        		    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        		    panel.setBackground(Color.WHITE);
        		    ProduitDAO daoTI = new ProduitDAO(connect);
        		    ArrayList<Produit> list3 = daoTI.findAll();

        		    GridBagConstraints c = new GridBagConstraints();
        		    c.gridx = 0;
        		    c.gridy = 0;
        		    c.anchor = GridBagConstraints.LINE_START;
        		    c.insets = new Insets(0, 0, 10, 0);

        		    JLabel refLabel = new JLabel("Référence du produit à rechercher:");
        		    panel.add(refLabel, c);
        		    c.gridy++;

        		    ArrayList<String> refProds = new ArrayList<String>();
        		    for (Produit prod : list3) {
        		        refProds.add(prod.getRefProduit());
        		    }

        		    JComboBox<String> refProdComboBox = new JComboBox<String>(refProds.toArray(new String[0]));
        		    refProdComboBox.setSelectedIndex(-1);
        		    panel.add(refProdComboBox, c);
        		    c.gridy++;

        		    JButton submitButton = new JButton("Rechercher");
        		    submitButton.setBackground(Color.decode("#A7C68C"));
        		    panel.add(submitButton, c);

        		    submitButton.addActionListener(new ActionListener() {
        		        public void actionPerformed(ActionEvent a) {
        		            Produit result = daoTI.find((String)refProdComboBox.getSelectedItem());
        		            if (result != null) {
        		                JDialog dialog = new JDialog(frame, "Produit Information");
        		                dialog.setSize(100, 100);

        		                DefaultTableModel model = new DefaultTableModel();
        		                model.addColumn("Propriété");
        		                model.addColumn("Valeur");

        		                model.addRow(new Object[]{"Référence Produit", result.getRefProduit()});
        		                model.addRow(new Object[]{"Descriptif Produit", result.getDescriptifProduit()});
        		                model.addRow(new Object[]{"Date Péremption", result.getDatePeremption()});
        		                model.addRow(new Object[]{"Quantité Produit", result.getQuantiteProduit()});
        		                model.addRow(new Object[]{"Prix Produit", result.getPrixProduit()});
        		                model.addRow(new Object[]{"Référence Rangement", result.getRefRangement().getRefRangement()});
        		                model.addRow(new Object[]{"Référence Ingredient", result.getRefIngredient().getRefIngredient()});

        		                JTable table = new JTable(model);
        		                JScrollPane scrollPane = new JScrollPane(table);

        		                JPanel panel = new JPanel(new BorderLayout());
        		                panel.add(scrollPane, BorderLayout.CENTER);

        		                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        		                dialog.setContentPane(panel);
        		                dialog.pack();
        		                dialog.setVisible(true);
        		            } else {
        		                JOptionPane.showMessageDialog(null, "Produit non trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
        		            }
        		        }
        		    });

        		    frame.getContentPane().removeAll();
            		frame.setContentPane(panel);
            		frame.revalidate();
        		});



        		produit3.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
         		    panel.setLayout(new GridBagLayout());
        			IngredientDAO daot = new IngredientDAO(connect);
        			ArrayList<Ingredient> list= daot.findAll();
        			RangementDAO daoR = new RangementDAO(connect);
        			ArrayList<Rangement> list2= daoR.findAll();
            		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            		panel.setBackground(Color.WHITE);
            		

            		GridBagConstraints c = new GridBagConstraints();
            		c.gridx = 0;
            		c.gridy = 0;
            		c.anchor = GridBagConstraints.LINE_START;
            		c.insets = new Insets(0, 0, 10, 0);

            		JLabel refLabel = new JLabel("Référence:");
            		panel.add(refLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> references = new ArrayList<String>();
            		for (int i = 1001; i <= 2000; i++) {
            		    references.add("P"+i);
            		}
            		
            		JComboBox<String> refComboBox = new JComboBox<String>(references.toArray(new String[0]));
            		refComboBox.setSelectedIndex(-1);
            		panel.add(refComboBox, c);
            		c.gridy++;
            		
            		JLabel descriptifLabel = new JLabel("Descriptif:");
        	        panel.add(descriptifLabel, c);

        	        c.gridy++;
        	        JTextField descriptifField = new JTextField(20);
        	        panel.add(descriptifField, c);
        	        
        	        c.gridy++;
        	        JLabel dateLabel = new JLabel("Date:");
        	        panel.add(dateLabel, c);

        	        c.gridy++;
        	        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        	        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        	        JFormattedTextField dateField = new JFormattedTextField(dateFormatter);
        	        dateField.setColumns(20);
        	        panel.add(dateField, c);
        	        
        	        c.gridy++;
        	        //date value
        	       // Date date = (Date) dateField.getValue();
        	        
        	        JLabel qteLabel = new JLabel("quantité:");
            		panel.add(qteLabel, c);

            		c.gridy++;
            		
            		ArrayList<Integer> qte = new ArrayList<Integer>();
            		for (Integer i = 10; i <= 200; i++) {
            		    qte.add(i);
            		}
            		JComboBox<Integer> qteComboBox = new JComboBox<Integer>(qte.toArray(new Integer[qte.size()]));
            		//qteComboBox.setSelectedIndex(-1);
            		panel.add(qteComboBox, c);
            		
            		
            		c.gridy++;
            		
            		JLabel prixLabel = new JLabel("prix:");
            		panel.add(prixLabel, c);

            		c.gridy++;
            		
            		ArrayList<Double> prix = new ArrayList<Double>();
            		for (double i = 10.5; i <= 200; i+=0.5) {
            			prix.add(i);
            		}
            		JComboBox<Double> prixComboBox = new JComboBox<Double>(prix.toArray(new Double[prix.size()]));
            		//prixComboBox.setSelectedIndex(-1);
            		panel.add(prixComboBox, c);
            		
            		c.gridy++;
            		
            		JLabel refRangLabel = new JLabel("réfrence Rangement:");
            		panel.add(refRangLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> refRang = new ArrayList<String>();
            		for (Rangement r : list2) {
            			refRang.add(r.getRefRangement());
            		}
            		
            		JComboBox<String> refRangComboBox = new JComboBox<String>(refRang.toArray(new String[0]));
            		refRangComboBox.setSelectedIndex(-1);
            		panel.add(refRangComboBox, c);
            		c.gridy++;
            		
            		JLabel refIngredientLabel = new JLabel("réfrence Ingredient:");
            		panel.add(refIngredientLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> refIngredient = new ArrayList<String>();
            		for (Ingredient r : list) {
            			refIngredient.add(r.getRefIngredient());
            		}
            		
            		JComboBox<String> refIngredientComboBox = new JComboBox<String>(refIngredient.toArray(new String[0]));
            		refIngredientComboBox.setSelectedIndex(-1);
            		panel.add(refIngredientComboBox, c);
            		c.gridy++;
            		
            		JButton submitButton = new JButton("Soumettre");
            		submitButton.setBackground(Color.decode("#A7C68C"));
            		panel.add(submitButton, c);
            		
            		c.gridy++;
            		
            		JButton resetButton = new JButton("Réinitialiser");
            		resetButton.setBackground(Color.decode("#C98080"));
            		resetButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent a) {
            		        //refField.setText("");
            		        //nomField.setText("");
            		    }
            		});
            		panel.add(resetButton, c);
            		
            		submitButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent e) { 
            		    	System.out.println(dateField.getValue());
            		        Produit result=null;           		      
            		        ProduitDAO daop = new ProduitDAO(connect);
            		        String selectedRef = (String) refComboBox.getSelectedItem();
            		        String descriptif = descriptifField.getText();
            		        java.util.Date utilDate = (java.util.Date) dateField.getValue();
            		        java.sql.Date date = new java.sql.Date(utilDate.getTime());
            		        int qteValue = (int) qteComboBox.getSelectedItem();
            		        Double prixValue = (Double) prixComboBox.getSelectedItem();
            		        String selectedRefRang = (String) refRangComboBox.getSelectedItem();
            		        String selectedRefIngredient = (String) refIngredientComboBox.getSelectedItem();
            		        
            		        if (refComboBox.getSelectedItem()==null || refRangComboBox.getSelectedItem()==null || refIngredientComboBox.getSelectedItem()==null ) {
            		            JOptionPane.showMessageDialog(null, "Le choix d'une référence est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (daop.create(result =  new Produit(selectedRef,descriptif,date,qteValue,prixValue,daoR.find(selectedRefRang),daot.find(selectedRefIngredient)))) {
            		        	int i =1000;
            		        	JOptionPane.showMessageDialog(null, "ajouté à la base de données avec succés", "ajouté", JOptionPane.INFORMATION_MESSAGE);
            		            references.add("P"+references.get(references.size()-1)+1000+i++);
            		            refComboBox.addItem("P"+references.get(references.size()-1)+1000+i++);
            		            
            		            references.remove((String) refComboBox.getSelectedItem());
            		            refComboBox.removeItem(refComboBox.getSelectedItem());
            		            
            		            return;
            		        }
            		        else {
            		            JOptionPane.showMessageDialog(null, "contraintes non respectées: clé déjà utilisée", "Erreur", JOptionPane.ERROR_MESSAGE);       		    	
            		        }
            		        //System.out.println(result);
            		    }
            		});

            		
            		frame.getContentPane().removeAll();
            		frame.setContentPane(panel);
            		frame.revalidate();
            		
            		
        		/*panel.setBackground(Color.BLUE);
        		frame.setContentPane(panel);
        		frame.revalidate();*/
        		});

        		produit4.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
         		    panel.setLayout(new GridBagLayout());
        			IngredientDAO daot = new IngredientDAO(connect);
        			ArrayList<Ingredient> list= daot.findAll();
        			RangementDAO daoR = new RangementDAO(connect);
        			ArrayList<Rangement> list2= daoR.findAll();
            		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            		panel.setBackground(Color.WHITE);
            		

            		GridBagConstraints c = new GridBagConstraints();
            		c.gridx = 0;
            		c.gridy = 0;
            		c.anchor = GridBagConstraints.LINE_START;
            		c.insets = new Insets(0, 0, 10, 0);

            		JLabel refLabel = new JLabel("Référence produit à mettre à jour:");
            		panel.add(refLabel, c);

            		c.gridy++;
            		
            		ProduitDAO daoP = new ProduitDAO(connect);
        			ArrayList<Produit> list3 = daoP.findAll();
        			ArrayList<String> refProduit = new ArrayList<String>();
        			for (Produit r : list3) {
        			refProduit.add(r.getRefProduit());
        			}

        			JComboBox<String> refComboBox = new JComboBox<String>(refProduit.toArray(new String[0]));
        			refComboBox.setSelectedIndex(-1);
        			panel.add(refComboBox, c);
            		c.gridy++;
            		
            		JLabel descriptifLabel = new JLabel("Descriptif:");
        	        panel.add(descriptifLabel, c);

        	        c.gridy++;
        	        JTextField descriptifField = new JTextField(20);
        	        panel.add(descriptifField, c);
        	        
        	        c.gridy++;
        	        JLabel dateLabel = new JLabel("Date:");
        	        panel.add(dateLabel, c);

        	        c.gridy++;
        	        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        	        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        	        JFormattedTextField dateField = new JFormattedTextField(dateFormatter);
        	        dateField.setColumns(20);
        	        panel.add(dateField, c);
        	        
        	        c.gridy++;
        	        //date value
        	        //Date date = (Date) dateField.getValue();
        	        
        	        JLabel qteLabel = new JLabel("quantité:");
            		panel.add(qteLabel, c);

            		c.gridy++;
            		
            		ArrayList<Integer> qte = new ArrayList<Integer>();
            		for (Integer i = 10; i <= 200; i++) {
            		    qte.add(i);
            		}
            		JComboBox<Integer> qteComboBox = new JComboBox<Integer>(qte.toArray(new Integer[qte.size()]));
            		//qteComboBox.setSelectedIndex(-1);
            		panel.add(qteComboBox, c);
            		
            		
            		c.gridy++;
            		
            		JLabel prixLabel = new JLabel("prix:");
            		panel.add(prixLabel, c);

            		c.gridy++;
            		
            		ArrayList<Double> prix = new ArrayList<Double>();
            		for (double i = 10.5; i <= 200; i+=0.5) {
            			prix.add(i);
            		}
            		JComboBox<Double> prixComboBox = new JComboBox<Double>(prix.toArray(new Double[prix.size()]));
            		//prixComboBox.setSelectedIndex(-1);
            		panel.add(prixComboBox, c);
            		
            		c.gridy++;
            		
            		JLabel refRangLabel = new JLabel("réfrence Rangement:");
            		panel.add(refRangLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> refRang = new ArrayList<String>();
            		for (Rangement r : list2) {
            			refRang.add(r.getRefRangement());
            		}
            		
            		JComboBox<String> refRangComboBox = new JComboBox<String>(refRang.toArray(new String[0]));
            		refRangComboBox.setSelectedIndex(-1);
            		panel.add(refRangComboBox, c);
            		c.gridy++;
            		
            		JLabel refIngredientLabel = new JLabel("réfrence Ingredient:");
            		panel.add(refIngredientLabel, c);

            		c.gridy++;
            		
            		ArrayList<String> refIngredient = new ArrayList<String>();
            		for (Ingredient r : list) {
            			refIngredient.add(r.getRefIngredient());
            		}
            		
            		JComboBox<String> refIngredientComboBox = new JComboBox<String>(refIngredient.toArray(new String[0]));
            		refIngredientComboBox.setSelectedIndex(-1);
            		panel.add(refIngredientComboBox, c);
            		c.gridy++;
            		
            		JButton submitButton = new JButton("mettre à jour");
            		submitButton.setBackground(Color.decode("#A7C68C"));
            		panel.add(submitButton, c);
            		
            		c.gridy++;
            		
            		JButton resetButton = new JButton("Réinitialiser");
            		resetButton.setBackground(Color.decode("#C98080"));
            		resetButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent a) {
            		        //refField.setText("");
            		        //nomField.setText("");
            		    }
            		});
            		panel.add(resetButton, c);
            		
            		submitButton.addActionListener(new ActionListener() {
            		    public void actionPerformed(ActionEvent e) {           		      
            		        Produit result=null;           		      
            		        ProduitDAO daop = new ProduitDAO(connect);
            		        String selectedRef = (String) refComboBox.getSelectedItem();
            		        String descriptif = descriptifField.getText();
            		        java.util.Date utilDate = (java.util.Date) dateField.getValue();
            		        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            		        int qteValue = (int) qteComboBox.getSelectedItem();
            		        Double prixValue = (Double) prixComboBox.getSelectedItem();
            		        String selectedRefRang = (String) refRangComboBox.getSelectedItem();
            		        String selectedRefIngredient = (String) refIngredientComboBox.getSelectedItem();
            		        
            		        if (refComboBox.getSelectedItem()==null || refRangComboBox.getSelectedItem()==null || refIngredientComboBox.getSelectedItem()==null ) {
            		            JOptionPane.showMessageDialog(null, "Le choix d'une référence est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
            		            return;
            		        }
            		        if (daop.update(result =  new Produit(selectedRef,descriptif,sqlDate,qteValue,prixValue,daoR.find(selectedRefRang),daot.find(selectedRefIngredient)))) {
            		        	int i =1000;
            		        	JOptionPane.showMessageDialog(null, "mis à jour avec succés", "mis à jour", JOptionPane.INFORMATION_MESSAGE);            		                       		            
            		            return;
            		        }
            		        else {
            		            JOptionPane.showMessageDialog(null, "contraintes non respectées: clé déjà utilisée", "Erreur", JOptionPane.ERROR_MESSAGE);       		    	
            		        }
            		        //System.out.println(result);
            		    }
            		});

            		
            		frame.getContentPane().removeAll();
            		frame.setContentPane(panel);
            		frame.revalidate();
        		/*JPanel panel = new JPanel();
        		panel.setBackground(Color.YELLOW);
        		frame.setContentPane(panel);
        		frame.revalidate();*/
        		});
        		
        		produit5.addActionListener(e -> {
        			JLabel panel = new JLabel(imageIcon);
        			panel.setLayout(new GridBagLayout());
        			panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        			panel.setBackground(Color.WHITE);
        			ProduitDAO daoP = new ProduitDAO(connect);
        			ArrayList<Produit> list2 = daoP.findAll();

        			GridBagConstraints c = new GridBagConstraints();
        			c.gridx = 0;
        			c.gridy = 0;
        			c.anchor = GridBagConstraints.LINE_START;
        			c.insets = new Insets(0, 0, 10, 0);

        			JLabel refLabel = new JLabel("Référence du Produit à effacer:");
        			panel.add(refLabel, c);
        			c.gridy++;
        			
        			ArrayList<String> refProduit = new ArrayList<String>();
        			for (Produit r : list2) {
        			refProduit.add(r.getRefProduit());
        			}

        			JComboBox<String> refProduitComboBox = new JComboBox<String>(refProduit.toArray(new String[0]));
        			refProduitComboBox.setSelectedIndex(-1);
        			panel.add(refProduitComboBox, c);
        			c.gridy++;
        			c.anchor = GridBagConstraints.CENTER;
        			c.fill = GridBagConstraints.HORIZONTAL;
        			c.insets = new Insets(20, 0, 0, 0);

        			c.gridy++;
        			JButton resetButton = new JButton("Supprimer");
        			resetButton.setBackground(Color.decode("#C98080"));
        			resetButton.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent a) {
        			if (daoP.delete(daoP.find((String)refProduitComboBox.getSelectedItem()))) {
        			JOptionPane.showMessageDialog(null, "Supprimé de la base de données avec succès", "Supprimé", JOptionPane.INFORMATION_MESSAGE);
        			}
        			}
        			});
        			panel.add(resetButton, c);

        			frame.getContentPane().removeAll();
        			frame.setContentPane(panel);
        			frame.revalidate();
        		/*JPanel panel = new JPanel();
            	panel.setBackground(Color.PINK);
            	frame.setContentPane(panel);
            	frame.revalidate();*/
            	});	

        menuBar.add(rangementMenu);
        menuBar.add(recetteMenu);
        menuBar.add(typeIngredientMenu);
        menuBar.add(ingredientMenu);
        menuBar.add(compositionMenu);
        menuBar.add(produitMenu);
             
        menuBar.setBackground(new Color(0xB7A5CE));
        rangementMenu.setBackground(Color.decode("#967CB4"));
        recetteMenu.setBackground(Color.decode("#967CB4"));        
        typeIngredientMenu.setBackground(Color.decode("#967CB4"));        
        ingredientMenu.setBackground(Color.decode("#967CB4"));        
        compositionMenu.setBackground(Color.decode("#967CB4"));
        produitMenu.setBackground(Color.decode("#967CB4"));

        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                item.setBackground(Color.decode("#967CB4"));
                item.setForeground(Color.WHITE);

         }
        }

        frame.setJMenuBar(menuBar);
        frame.setSize(1024,1024);
        frame.setVisible(true);
    }

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
