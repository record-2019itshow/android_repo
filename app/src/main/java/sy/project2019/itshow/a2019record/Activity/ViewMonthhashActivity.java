package sy.project2019.itshow.a2019record.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import sy.project2019.itshow.a2019record.Adapter.HashTagListAdapter;
import sy.project2019.itshow.a2019record.Server.HashTagListItem;
import sy.project2019.itshow.a2019record.R;

public class ViewMonthhashActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_monthhash);
        Intent intent = getIntent();
        CalendarDay thisDay = intent.getParcelableExtra("hashDate");
        String dateStr = thisDay.getYear() + "년 " + thisDay.getMonth() + "월 " + thisDay.getDay() + "일";

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

        listView = findViewById(R.id.monthly_record_list);
        HashTagListAdapter adapter = new HashTagListAdapter();
        listView.setAdapter(adapter);

        adapter.addItem(new HashTagListItem(dateStr, "path", "content", "#hashtag"));
        adapter.addItem(new HashTagListItem(dateStr, "path", "content", "#hashtag"));
        adapter.addItem(new HashTagListItem(dateStr, "path", "content", "#hashtag"));



        Log.e("date", thisDay.toString());

    }
}
