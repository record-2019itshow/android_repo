package sy.project2019.itshow.a2019record.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sy.project2019.itshow.a2019record.Adapter.HashTagListAdapter;
import sy.project2019.itshow.a2019record.Model.HashTagListItem;
import sy.project2019.itshow.a2019record.Model.getRecordClass;
import sy.project2019.itshow.a2019record.R;
import sy.project2019.itshow.a2019record.Server.Server;
import sy.project2019.itshow.a2019record.Server.ServerService;

public class ViewMonthhashActivity extends AppCompatActivity {

    ListView listView;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_monthhash);
        Intent intent = getIntent();
        CalendarDay thisDay = intent.getParcelableExtra("hashDate");
        final String dateStr = thisDay.getYear() + "년 " + thisDay.getMonth() + "월 " + thisDay.getDay() + "일";

        //액션바 설정 코드
        TextView toolbarTitle;
        ImageView Backbtn;
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activities_actionbar_layout);
        getSupportActionBar().setTitle(R.string.writeRecordTitle); // 임시로 설정
        toolbarTitle = findViewById(R.id.toolbarTitle);
        Backbtn = findViewById(R.id.backBtn);

        toolbarTitle.setText(dateStr);
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //액션바 설정 코드 끝

        pref = getSharedPreferences("pref", MODE_PRIVATE);

        listView = findViewById(R.id.monthly_record_list);
        final HashTagListAdapter adapter = new HashTagListAdapter();
        listView.setAdapter(adapter);


        Log.e("date", thisDay.toString());
        String month;
        if(thisDay.getMonth() < 10){
            month = "0"+thisDay.getMonth();
        }else{
            month = String.valueOf(thisDay.getMonth());
        }

        String queryStr = thisDay.getYear() + "-" + month + "-" + thisDay.getDay();
        Log.e("date", queryStr);


        ServerService service = Server.getRetrofitInstance().create(ServerService.class);
        Call<List<getRecordClass>> call = service.getDayRecordTask(pref.getString("currentID", null), queryStr);

        call.enqueue(new Callback<List<getRecordClass>>() {
            @Override
            public void onResponse(Call<List<getRecordClass>> call, Response<List<getRecordClass>> response) {
                List<getRecordClass> reco = response.body();

                for(int i = 0; i < reco.size(); i++){
                    getRecordClass recoObj = reco.get(i);
                    String hash="";
                    for(int j = 0; j < recoObj.getHashtags().size(); j++){
                        hash += (recoObj.getHashtags().get(j) + " ");
                    }

                    adapter.addItem(new HashTagListItem(dateStr, reco.get(i).getImg(), reco.get(i).getContent(),
                            hash));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<getRecordClass>> call, Throwable t) {

            }
        });

    }

}
