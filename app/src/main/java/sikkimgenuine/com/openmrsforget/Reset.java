package sikkimgenuine.com.openmrsforget;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset extends AppCompatActivity {
private EditText email;
private FirebaseAuth mAuth;
private Button ResetButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        email=findViewById(R.id.email);
        ResetButton=findViewById(R.id.resetbutton);
        mAuth=FirebaseAuth.getInstance();
        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emai=email.getText().toString();
                if(TextUtils.isEmpty(emai))
                {
                    Toast.makeText(Reset.this,"Please enter your email id",Toast.LENGTH_LONG).show();


                }
                else
                {
                    mAuth.sendPasswordResetEmail(emai).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Reset.this,"We have sent you link",Toast.LENGTH_LONG).show();

                            }
                            else
                            {
                                String error=task.getException().getMessage();
                                Toast.makeText(Reset.this,error,Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
