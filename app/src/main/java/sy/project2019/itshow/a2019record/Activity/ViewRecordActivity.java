package sy.project2019.itshow.a2019record.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sy.project2019.itshow.a2019record.Model.getRecordClass;
import sy.project2019.itshow.a2019record.R;
import sy.project2019.itshow.a2019record.Server.Server;
import sy.project2019.itshow.a2019record.Server.ServerService;

public class ViewRecordActivity extends AppCompatActivity {
    SharedPreferences pref;
    String recoToken, currentId;
    TextView content, hashTag, Date;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        Intent i =getIntent();


        pref = getSharedPreferences("pref", MODE_PRIVATE);
        recoToken = i.getStringExtra("recoToken");
        currentId = pref.getString("currentID", null);

        if(recoToken == null){
            Toast.makeText(getApplicationContext(), "오류가 발생하였습니다", Toast.LENGTH_SHORT).show();
        }

        init();

        ServerService service = Server.getRetrofitInstance().create(ServerService.class);
        Call<getRecordClass> call = service.getSingleRecordTask(currentId, recoToken);

        call.enqueue(new Callback<getRecordClass>() {
            @Override
            public void onResponse(Call<getRecordClass> call, Response<getRecordClass> response) {
                getRecordClass record = response.body();

                String hashStr="";

                for(int i = 0; i < record.getHashtags().size(); i++){
                    hashStr += (record.getHashtags().get(i) + " ");
                }

                content.setText(record.getContent());
                Log.e(record.getContent(), "헐헐");
                hashTag.setText(hashStr);
                Date.setText(record.getTime().substring(0,10));
                Picasso.get().load("http://3.17.203.21:3000/" + record.getImg()).into(img);

            }

            @Override
            public void onFailure(Call<getRecordClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "이러지마 우리 행복했잖아", Toast.LENGTH_SHORT).show();
                Log.e("무슨 오류일까용ㅎ", t.toString());
            }
        });

    }

    public void init(){
        content = findViewById(R.id.viewreco_record_content);
        hashTag = findViewById(R.id.viewreco_record_hashtag);
        Date = findViewById(R.id.viewreco_record_writeTime);

        img = findViewById(R.id.viewreco_record_img);

    }
}
