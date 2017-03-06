package erickmorales.firebasetwo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NombreUser extends AppCompatActivity {

    private EditText editTextName,editTexUserFrends;
    private Button buttonIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre_user);

        editTextName = (EditText)findViewById(R.id.editText2);
        buttonIngresar = (Button)findViewById(R.id.button2);
        editTexUserFrends = (EditText)findViewById(R.id.editText3);

        final Toast toast = Toast.makeText(this,"Ingresaste",Toast.LENGTH_LONG);
        final Toast toastError = Toast.makeText(this,"Ingrese su nombre",Toast.LENGTH_LONG);
        final Toast toastErrorFrend = Toast.makeText(this,"Ingresa el nombre de un amigo",toast.LENGTH_LONG);

        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextName.getText().toString().equals("")){

                    if(!editTexUserFrends.getText().toString().equals("")) {
                        Intent intent = new Intent(NombreUser.this, MainActivity.class);
                        intent.putExtra("Nombre", editTextName.getText().toString());
                        intent.putExtra("Amigo", editTexUserFrends.getText().toString());
                        startActivity(intent);
                        toast.show();
                    }else{
                        toastErrorFrend.show();
                    }
                }else {
                    toastError.show();
                }

            }
        });

    }
}
