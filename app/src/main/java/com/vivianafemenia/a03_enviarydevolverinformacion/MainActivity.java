package com.vivianafemenia.a03_enviarydevolverinformacion;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vivianafemenia.a03_enviarydevolverinformacion.modelos.Dirección;
import com.vivianafemenia.a03_enviarydevolverinformacion.modelos.Usuario;

public class MainActivity extends AppCompatActivity {
    private Button btnDescriptor;
    private Button btnCrearDir;
    private EditText txtmail;
    private EditText txtPassword;

    //constatntes
    private final int DIRECCIONES=123;
    private final int TRACTORES=123;

    // Launchers

    private ActivityResultLauncher<Intent> launcherDirecciones;
    private ActivityResultLauncher<Intent> launcherTractores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaVariables();
        inicializaLaunchers();

        btnDescriptor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = txtPassword.getText().toString();
                String email = txtmail.getText().toString();
                Intent intent= new Intent(MainActivity.this,DesencriptarActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("USER",new Usuario(email,password));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnCrearDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,CreateDirActivity.class);
                launcherDirecciones.launch(intent);
            }
        });
    }


    private void inicializaLaunchers() {
        //Registrar una Actividad REtorno
        //1.como lanzo la actividad hija
        //2.que hago cuando mi hija termine
        launcherDirecciones = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK){
                    if(result.getData()!= null){
                        Bundle bundle= result.getData().getExtras();
                        Dirección dir = (Dirección) bundle.getSerializable("DIR");
                        Toast.makeText(MainActivity.this,dir.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void inicializaVariables() {

        txtmail = findViewById(R.id.txtmailMain);
        txtPassword = findViewById(R.id.txtPasswordMain);
        btnCrearDir= findViewById(R.id.btnCrearMain);
        btnDescriptor= findViewById(R.id.btnDesencriparMain);
    }

    /**
     * Se activa al retornar de un StarActivityForResult y dispara las acciones necesarias
     * @param requestCode> identificador de la ventana que se ha cerrado (el tipo de dato de retorno)
     *@param resultCode> el modo que se ha cerrado la ventana
     * @param data> son los datos retornados (Intent con un bundle dentro)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==DIRECCIONES){
            if(resultCode== RESULT_OK) {
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    Dirección dir = (Dirección) bundle.getSerializable("DIR");
                    Toast.makeText(this, dir.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
                }
            } else {

                        Toast.makeText(this, "Ventana Cancelada",Toast.LENGTH_SHORT).show();



            }
        }
    }
}