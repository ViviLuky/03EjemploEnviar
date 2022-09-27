package com.vivianafemenia.a03_enviarydevolverinformacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vivianafemenia.a03_enviarydevolverinformacion.modelos.Dirección;

public class CreateDirActivity extends AppCompatActivity {
    private EditText txtCalle;
    private EditText txtNumero;
    private EditText txtCiudad;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dir);

        inicializarVariable();
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dirección dirección=new Dirección(txtCalle.getText().toString(),Integer.parseInt(txtNumero.getText().toString()),txtCiudad.getText().toString());
                Bundle bundle= new Bundle();
                bundle.putSerializable("DIR",dirección);
                Intent intent= new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void inicializarVariable() {
        txtCalle=findViewById(R.id.txtCalleCreateDir);
        txtNumero=findViewById(R.id.txtNumeroCreateDir);
        txtCiudad=findViewById(R.id.txtCiudadCreateDir);
        btnCrear=findViewById(R.id.btnCrearCreateDir);
    }
}