package sy.project2019.itshow.a2019record.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sy.project2019.itshow.a2019record.R;
import sy.project2019.itshow.a2019record.Model.LoginUser;
import sy.project2019.itshow.a2019record.Server.Server;
import sy.project2019.itshow.a2019record.Server.ServerService;


public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton, signupButton;
    SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        preferences = getSharedPreferences("pref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //서버와의 연결 시직
                ServerService service = Server.getRetrofitInstance().create(ServerService.class);
                Call<LoginUser> call = service.sigininTask(new LoginUser(usernameEditText.getText().toString(), passwordEditText.getText().toString()));

                call.enqueue(new Callback<LoginUser>() {
                    @Override
                    public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                        if(response.code() == 200){ // 로그인 성공

                            editor.putString("currentID", usernameEditText.getText().toString());
                            editor.commit();
                            //SharedPreferences에 아이디를 저장해서 자동로그인 및 세션 없이 로그인 유지(개꼼수 ㅋ)

                            Toast.makeText(getApplicationContext(), "로그인 되셨습니다", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{ // 로그인 실패
                            Toast.makeText(getApplicationContext(), "아이디나 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginUser> call, Throwable t) {
                        //로그인 실패2
                        Toast.makeText(getApplicationContext(), "로그인에 실패하셨습니다", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        signupButton = findViewById(R.id.goto_signUp);
    }

}
