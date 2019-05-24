package sy.project2019.itshow.a2019record.Activity;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
}
