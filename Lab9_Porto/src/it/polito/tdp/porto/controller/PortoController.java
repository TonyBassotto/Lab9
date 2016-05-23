package it.polito.tdp.porto.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

public class PortoController {
	model m=new model();
	LinkedList<Integer>ListA1ID=new LinkedList<Integer>();
	LinkedList<Integer>ListA2ID=new LinkedList<Integer>();
	
	LinkedList<String>ListaSTRING1=new LinkedList<String>();
	LinkedList<String>ListaSTRING2=new LinkedList<String>();

	void exe(model mer){
		this.m=mer;
		mer.starter();
		mer.starterGraph();
		listA1.getItems().addAll(mer.boxlist());
		listA2.getItems().addAll(mer.boxlist());
		ListA1ID.addAll(m.boxlistID());
		ListA2ID.addAll(m.boxlistID());
		ListaSTRING1.addAll(mer.boxlist());
		ListaSTRING2.addAll(mer.boxlist());

		
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> listA1;

    @FXML
    private ChoiceBox<String> listA2;

    @FXML
    private Button btncoAuthor;

    @FXML
    private Button btnCluster;

    @FXML
    private Button btnArticles;

    @FXML
    private ProgressBar proBar;

    @FXML
    private TextArea textResult;

    @FXML
    private Button btnReset;

    @FXML
    private Label messageWarning;

    @FXML
    void reset(ActionEvent event) {
    	listA1.getSelectionModel().clearSelection();
    	listA2.getSelectionModel().clearSelection();
    	textResult.clear();
    }

    @FXML
    void viewArticles(ActionEvent event) {
    	if((listA1.getValue()!=null && listA2.getValue()!=null && listA1.getValue()!=listA2.getValue())){
    		int index1=ListaSTRING1.indexOf(listA1.getValue());
    		int ID1=ListA1ID.get(index1);
    		int index2=ListaSTRING1.indexOf(listA2.getValue());
    		int ID2=ListA2ID.get(index2);
    		if(m.foundCollab(ID1, ID2)!="")
    		textResult.setText("Collaborazioni tra "+listA1.getValue()+" e "+listA2.getValue()+"\n"+m.foundCollab(ID1, ID2));
    		else
        		textResult.setText("Collaborazioni tra "+listA1.getValue()+" e "+listA2.getValue()+"\nNessuna Collaborazione Presente");

    	}
    		
    	
    	else
    		textResult.setText("ERRORE");
    	
    }

    @FXML
    void viewCluster(ActionEvent event) {
    	if((listA1.getValue()==null && listA2.getValue()==null)){
    		textResult.setText(m.Lista_Connessioni());
    }
    	else
    		textResult.setText("ERRORE");
    }

    @FXML
    void viewCoAuthor(ActionEvent event) {
    	if((listA1.getValue()!=null) && !(listA1.getValue()!=null && listA2.getValue()!=null)){
    		int index=ListaSTRING1.indexOf(listA1.getValue());
    		int ID=ListA1ID.get(index);
    		textResult.setText("Coautori di "+listA1.getValue()+"\n"+m.listavicini(ID));
    	}
    	else if((listA2.getValue()!=null) && !(listA1.getValue()!=null && listA2.getValue()!=null)){
    		int index=ListaSTRING1.indexOf(listA2.getValue());
    		int ID=ListA2ID.get(index);
    		textResult.setText("Coautori di "+listA2.getValue()+"\n"+m.listavicini(ID));
    	}
    	else
    		textResult.setText("ERRORE");
    }

    @FXML
    void initialize() {
        assert listA1 != null : "fx:id=\"listA1\" was not injected: check your FXML file 'Porto.fxml'.";
        assert listA2 != null : "fx:id=\"listA2\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btncoAuthor != null : "fx:id=\"btncoAuthor\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnCluster != null : "fx:id=\"btnCluster\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnArticles != null : "fx:id=\"btnArticles\" was not injected: check your FXML file 'Porto.fxml'.";
        assert proBar != null : "fx:id=\"proBar\" was not injected: check your FXML file 'Porto.fxml'.";
        assert textResult != null : "fx:id=\"textResult\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Porto.fxml'.";
        assert messageWarning != null : "fx:id=\"messageWarning\" was not injected: check your FXML file 'Porto.fxml'.";

        this.exe(m);
    }
}