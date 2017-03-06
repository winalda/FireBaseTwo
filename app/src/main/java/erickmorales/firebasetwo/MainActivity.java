package erickmorales.firebasetwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.core.view.DataEvent;

public class MainActivity extends AppCompatActivity {

    private static String FIREBASE_URL = "https://intentone-25180.firebaseio.com/";

    private Button buttonEnviar;
    private EditText editTextName;
    private TextView textViewR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonEnviar = (Button)findViewById(R.id.button);
        editTextName = (EditText)findViewById(R.id.editText);
        textViewR = (TextView)findViewById(R.id.textViewR);

        final Toast toastEnviado = Toast.makeText(this,"Enviado",Toast.LENGTH_LONG);
        final Toast toastError = Toast.makeText(this,"Ingrese un nombre",Toast.LENGTH_LONG);

        Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("Nombre");
        final String friend = bundle.getString("Amigo");

        Firebase.setAndroidContext(this);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!editTextName.getText().toString().equals("")) {

                    Firebase aux = new Firebase(FIREBASE_URL);
                    aux.child(name).setValue(editTextName.getText().toString());
                    editTextName.setText("");
                    toastEnviado.show();

                    aux.child(friend).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            System.out.println("User [" + friend + "] = " + dataSnapshot.getValue());
                            textViewR.setText("User [" + friend + "] = " + dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });

                } else
                    toastError.show();
            }
        });

    }
}
