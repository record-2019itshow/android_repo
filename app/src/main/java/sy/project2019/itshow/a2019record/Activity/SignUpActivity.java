package sy.project2019.itshow.a2019record.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sy.project2019.itshow.a2019record.R;
import sy.project2019.itshow.a2019record.Server.Server;
import sy.project2019.itshow.a2019record.Server.ServerService;
import sy.project2019.itshow.a2019record.Model.User;

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

                // 빈칸 및 비밀번호 확인 체크
                if(isnull(name)) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(isnull(id)){
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else if(isnull(pw)){
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(isnull(pwConfirm)){
                    Toast.makeText(getApplicationContext(),"비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!(pw.getText().toString().equals(pwConfirm.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 빈칸 및 비밀번호 확인 체크


                // 서버연결 코드 시작

                ServerService service = Server.getRetrofitInstance().create(ServerService.class);
                Call<User> call = service.sigupTask(new User(name.getText().toString(),id.getText().toString(),
                        pw.getText().toString()));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.code() == 200){
                            Toast.makeText(getApplicationContext(), "회원가입에 성공하셨습니다." + response.message(), Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "회원가입에 실패하셨습니다." + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                // 서버연결 코드 끝
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

    public boolean isnull(EditText editText){
        if(editText.getText().toString().replace(" ", "").equals("")){
            return true;
        }else{
            return false;
        }

    }

}
