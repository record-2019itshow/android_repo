package sy.project2019.itshow.a2019record.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import sy.project2019.itshow.a2019record.Decorator.OneDayDecorator;
import sy.project2019.itshow.a2019record.R;
import sy.project2019.itshow.a2019record.Decorator.SaturdayDecorator;
import sy.project2019.itshow.a2019record.Decorator.SundayDecorator;

public class MonthlyViewActivity extends AppCompatActivity implements OnDateSelectedListener {
    ImageView Backbtn;
    TextView toolbarTitle;
    MaterialCalendarView calendarView;
    CalendarDay clickDate;

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

        calendarView = findViewById(R.id.monthly_calendarView);
        calendarView.addDecorators(new SundayDecorator(), new SaturdayDecorator(), new OneDayDecorator(getApplicationContext()));
        calendarView.setOnDateChangedListener(this);

    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

        clickDate = new CalendarDay(date.getYear(), date.getMonth()+1, date.getDay());

        //selected is no value on logcat
        Log.d("selected", "" + selected);
        Intent intent = new Intent(MonthlyViewActivity.this, ViewMonthhashActivity.class);
        intent.putExtra("hashDate",clickDate);
        startActivity(intent);

        if (selected == true) {
            //It can't be show
            Toast.makeText(this, "onClick" + clickDate, Toast.LENGTH_SHORT).show();
        }
    } // onDateSelected


} //MonthlyViewActivity
