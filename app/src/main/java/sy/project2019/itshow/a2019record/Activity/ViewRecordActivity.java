package sy.project2019.itshow.a2019record.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import sy.project2019.itshow.a2019record.R;

public class ViewRecordActivity extends AppCompatActivity {
    SharedPreferences pref;
    String recoToken;
    TextView content, hashTag, Date;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        pref = getSharedPreferences("pref", MODE_PRIVATE);
        recoToken = pref.getString("recoToken", null);

        if(recoToken == null){
            Toast.makeText(getApplicationContext(), "오류가 발생하였습니다", Toast.LENGTH_SHORT).show();
        }

        init();





    }

    public void init(){
        content = findViewById(R.id.viewreco_record_content);
        hashTag = findViewById(R.id.viewreco_record_hashtag);
        Date = findViewById(R.id.viewreco_record_writeTime);

        img = findViewById(R.id.viewreco_record_img);

    }
}
