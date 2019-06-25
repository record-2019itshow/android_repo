package sy.project2019.itshow.a2019record.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sy.project2019.itshow.a2019record.Decorator.OneDayDecorator;
import sy.project2019.itshow.a2019record.Decorator.RecordDayDecorator;
import sy.project2019.itshow.a2019record.Model.getRecordClass;
import sy.project2019.itshow.a2019record.R;
import sy.project2019.itshow.a2019record.Decorator.SaturdayDecorator;
import sy.project2019.itshow.a2019record.Decorator.SundayDecorator;
import sy.project2019.itshow.a2019record.Server.Server;
import sy.project2019.itshow.a2019record.Server.ServerService;

public class MonthlyViewActivity extends AppCompatActivity implements OnDateSelectedListener {
    ImageView Backbtn;
    TextView toolbarTitle;
    MaterialCalendarView calendarView;
    CalendarDay clickDate;
    ArrayList<CalendarDay> recordDays;

    SharedPreferences pref;

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
        pref = getSharedPreferences("pref", MODE_PRIVATE);

        toolbarTitle.setText(R.string.monthlyTitle);
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //액션바 설정 코드 끝
        recordDays = new ArrayList<>();

        getRecordDays();

        calendarView = findViewById(R.id.monthly_calendarView);
        calendarView.setOnDateChangedListener(this);

    }

    public void getRecordDays(){
        String id = pref.getString("currentID", null);
       ServerService service = Server.getRetrofitInstance().create(ServerService.class);
        Call<List<getRecordClass>> call = service.getAllRecordTask(id);

        call.enqueue(new Callback<List<getRecordClass>>() {
            @Override
            public void onResponse(Call<List<getRecordClass>> call, Response<List<getRecordClass>> response) {

                if(response.code() != 200 || response.body() == null) {
                    Toast.makeText(getApplicationContext(), "레코드가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
                    calendarView.addDecorators(new SundayDecorator(), new SaturdayDecorator(), new OneDayDecorator(getApplicationContext()));
                    return;
                }
                for(int i= 0; i < response.body().size(); i++){
                    int year = Integer.parseInt(response.body().get(i).getTime().substring(0,4));
                    int month = Integer.parseInt(response.body().get(i).getTime().substring(5,7));
                    int day = Integer.parseInt(response.body().get(i).getTime().substring(8,10));
                    Log.e("year",year + "" );
                    Log.e("month",month + "" );
                    Log.e("day",day + "" );
                    CalendarDay c = CalendarDay.from(year, month-1, day);
                    recordDays.add(c);
                }
                Log.e("size", recordDays.size() + " " +recordDays.get(0));
                calendarView.addDecorators(new SundayDecorator(), new SaturdayDecorator(), new OneDayDecorator(getApplicationContext()),
                        new RecordDayDecorator(recordDays, MonthlyViewActivity.this));

            }

            @Override
            public void onFailure(Call<List<getRecordClass>> call, Throwable t) {
                calendarView.addDecorators(new SundayDecorator(), new SaturdayDecorator(), new OneDayDecorator(getApplicationContext()));
            }
        });

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
