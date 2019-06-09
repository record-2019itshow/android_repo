package sy.project2019.itshow.a2019record.Activity;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import sy.project2019.itshow.a2019record.R;

public class MonthlyViewActivity extends AppCompatActivity {
    ImageView Backbtn;
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_view);

        //액션바 설정 코드
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activities_actionbar_layout);
        getSupportActionBar().setTitle(R.string.writeRecordTitle); // 임시로 설정
        toolbarTitle = findViewById(R.id.toolbarTitle);
        Backbtn = findViewById(R.id.backBtn);

        toolbarTitle.setText(R.string.monthlyTitle);
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //액션바 설정 코드 끝
    }



    // 캘린더의 날짜를 업데이트하는 함수
//    public void updateCalendar(HashSet<Date> events)
//    {
//        ArrayList<Date> cells = new ArrayList<>();
//        Calendar calendar = (Calendar)currentDate.clone();
//
//        // determine the cell for current month's beginning
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;
//
//        // move calendar backwards to the beginning of the week
//        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);
//
//        // fill cells
//        while (cells.size() < DAYS_COUNT)
//        {
//            cells.add(calendar.getTime());
//            calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//
//        // update grid
//        grid.setAdapter(new CalendarAdapter(getContext(), cells, events));
//
//        // update title
//        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
//        txtDate.setText(sdf.format(currentDate.getTime()));
//
//        // set header color according to current season
//        int month = currentDate.get(Calendar.MONTH);
//        int season = monthSeason[month];
//        int color = rainbow[season];
//
//        header.setBackgroundColor(getResources().getColor(color));
//    }
}
