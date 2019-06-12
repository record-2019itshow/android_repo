package sy.project2019.itshow.a2019record.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sy.project2019.itshow.a2019record.R;

public class SignUpActivity extends AppCompatActivity {

    EditText name, id, pw, pwConfirm;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();



        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });

    }


    public void init(){
        name= findViewById(R.id.signUp_nameEdit);
        id = findViewById(R.id.signUp_idEdit);
        pw = findViewById(R.id.signUp_pwEdit);
        pwConfirm = findViewById(R.id.signUp_pwConfirm_Edit);
        signUpBtn = findViewById(R.id.signUp_btn);
    }
}
