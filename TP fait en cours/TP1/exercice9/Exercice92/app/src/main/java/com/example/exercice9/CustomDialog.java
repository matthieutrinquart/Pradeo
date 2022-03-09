package com.example.exercice9;

import android.app.Activity;
import android.app.Dialog;
import android.widget.*;

public class CustomDialog  extends Dialog {
    private EditText Description;
    private EditText Titre;
    private Button confirmer;
    private  Button annuler;
    private TimePicker picker;
    public CustomDialog(Activity activity) {
        super(activity);
        setContentView(R.layout.custom_layout_dialog);
        picker=(TimePicker)findViewById(R.id.timePicker);
        Description =(EditText)findViewById(R.id.DescriptionEdit);
        Titre=(EditText)findViewById(R.id.Titre);
        confirmer = (Button)findViewById(R.id.button);
        annuler = (Button)findViewById(R.id.button2);
        picker.setIs24HourView(true);
    }
    public Button getannulerbouton(){return annuler;}
    public Button getconfirmerrbouton(){return confirmer;}
    public EditText getDescription() {return Description;}
    public EditText getTitre() {return Titre;}
    public TimePicker getPicker() {return picker;}
}
