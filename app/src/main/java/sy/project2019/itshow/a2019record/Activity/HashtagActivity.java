package sy.project2019.itshow.a2019record.Activity;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import sy.project2019.itshow.a2019record.Adapter.HashTagListAdapter;
import sy.project2019.itshow.a2019record.Adapter.hashGridAdapter;
import sy.project2019.itshow.a2019record.Server.HashTagListItem;
import sy.project2019.itshow.a2019record.R;

public class HashtagActivity extends AppCompatActivity {

    ImageView Backbtn;
    TextView toolbarTitle;
    HashTagListAdapter ListAdapter;
    ListView hashRecordList;
    GridView hashTageListGrid;
    hashGridAdapter GridAdapter;
    ArrayList<String> hashItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashtag);
        //액션바 설정 코드
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activities_actionbar_layout);
        getSupportActionBar().setTitle(R.string.writeRecordTitle); // 임시로 설정
        toolbarTitle = findViewById(R.id.toolbarTitle);
        Backbtn = findViewById(R.id.backBtn);
        toolbarTitle.setText(R.string.hashtagTitle);
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //액션바 설정 코드 끝
        init();   // 각 요소들 초기화
        setHashGrid(); // 해시태그들이 담긴 그리드뷰 구성

        hashTageListGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setRecoList(GridAdapter.getItem(position)); // 해시태그들에 대응하는 레코드들로 이루어진 리스트뷰 구성
            }
        });




    }

    public void init(){
        hashRecordList = findViewById(R.id.hashTag_records_listView);
        hashTageListGrid = findViewById(R.id.hashtag_list_grid);
        ListAdapter = new HashTagListAdapter();
        GridAdapter = new hashGridAdapter();
        hashItem = new ArrayList<>();
        hashRecordList.setAdapter(ListAdapter);
    }

    public void setHashGrid(){
        hashItem.add("#원대");
        hashItem.add("#설원대..");
        hashItem.add("#문이기");
        hashItem.add("#뭘 더 써야해");

        GridAdapter.setArr(hashItem);
        hashTageListGrid.setAdapter(GridAdapter);

    }

    public void setRecoList(String hashtag){
        ListAdapter.delItem();
        Log.e("리스트 구성", "함수 안으로 들어오기는 햇는데");
        ListAdapter.addItem(new HashTagListItem("2019년 3월 20일","mm","문익아 생일 축하해",hashtag));
        ListAdapter.addItem(new HashTagListItem("2019년 4월 28일","mm","언데 생일 축하해",hashtag));

        ListAdapter.notifyDataSetChanged();
    }

}
