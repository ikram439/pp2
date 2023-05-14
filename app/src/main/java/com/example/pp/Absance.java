package com.example.pp;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class Absance extends AppCompatActivity {
    EditText debut,fin,excuse;
    Button envoyer;
    MyBaseDonnee bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absance);
        debut = findViewById(R.id.debut);
        fin = findViewById(R.id.fin);
        excuse = findViewById(R.id.excuse);
        envoyer = findViewById(R.id.envoyer);
        bd = new MyBaseDonnee(this);

        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String de = debut.getText().toString();
                String fi = fin.getText().toString();
                String exc = excuse.getText().toString();

                if(de.equals("") || fi.equals("") || exc.equals("")) {
                    Toast.makeText(Absance.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                } else {
                    boolean result = bd.demandeConge(de,fi,exc);
                    if (result ==true) {
                        Toast.makeText(Absance.this, "Demande envoyée avec succès", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Absance.this, "Erreur lors de l'envoi de la demande", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
