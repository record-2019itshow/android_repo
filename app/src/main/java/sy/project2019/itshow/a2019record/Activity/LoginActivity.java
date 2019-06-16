package sy.project2019.itshow.a2019record.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sy.project2019.itshow.a2019record.R;
import sy.project2019.itshow.a2019record.Server.Server;
import sy.project2019.itshow.a2019record.Server.ServerService;
import sy.project2019.itshow.a2019record.Server.User;

public class LoginActivity extends AppCompatActivity {

    ProgressBar loadingProgressBar;
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton, signupButton;
    SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //서버와의 연결 시직
                ServerService service = Server.getRetrofitInstance().create(ServerService.class);
                Call<List<User>> call = service.sigininTask(new User(usernameEditText.toString(), passwordEditText.toString()));

                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if(response.code() == 200){

//                           여기 SharedPreferences 추가
                            Toast.makeText(getApplicationContext(), "로그인 되셨습니다", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "아이디나 비밀번호를 확인해주세요" + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "회원가입에 실패하셨습니다", Toast.LENGTH_SHORT).show();
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
        loadingProgressBar = findViewById(R.id.loading);
        signupButton = findViewById(R.id.goto_signUp);
    }

}
