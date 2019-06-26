package sy.project2019.itshow.a2019record.Activity;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sy.project2019.itshow.a2019record.Adapter.HashTagListAdapter;
import sy.project2019.itshow.a2019record.Adapter.hashGridAdapter;
import sy.project2019.itshow.a2019record.Model.HashTagListItem;
import sy.project2019.itshow.a2019record.Model.HashtagsItem;
import sy.project2019.itshow.a2019record.Model.getRecordClass;
import sy.project2019.itshow.a2019record.R;
import sy.project2019.itshow.a2019record.Server.Server;
import sy.project2019.itshow.a2019record.Server.ServerService;

public class HashtagActivity extends AppCompatActivity {

    ImageView Backbtn;
    TextView toolbarTitle;
    HashTagListAdapter ListAdapter;
    ListView hashRecordList;
    GridView hashTageListGrid;
    hashGridAdapter GridAdapter;
    SharedPreferences pref;
    TextView this_hash;

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

        hashTageListGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                this_hash.setText(GridAdapter.getItem(position));
                setRecoList(GridAdapter.getItem(position).replace("#","")); // 해시태그들에 대응하는 레코드들로 이루어진 리스트뷰 구성
            }
        });



    }

    public void init(){
        this_hash = findViewById(R.id.this_hash);
        hashRecordList = findViewById(R.id.hashTag_records_listView);
        hashTageListGrid = findViewById(R.id.hashtag_list_grid);
        ListAdapter = new HashTagListAdapter();
        GridAdapter = new hashGridAdapter();
        hashRecordList.setAdapter(ListAdapter);
        setHashGrid(); // 해시태그들이 담긴 그리드뷰 구성
    }

    public void setHashGrid(){
        pref = getSharedPreferences("pref", MODE_PRIVATE);


        ServerService service = Server.getRetrofitInstance().create(ServerService.class);
        Call<HashtagsItem> call = service.getAllHash(pref.getString("currentID", null));

        call.enqueue(new Callback<HashtagsItem>() {
            @Override
            public void onResponse(Call<HashtagsItem> call, Response<HashtagsItem> response) {

                if(response.body().getResult().size()>0){
                    for(int i = 0; i < response.body().getResult().size(); i++){
                        GridAdapter.setItem(response.body().getResult().get(i));
                        GridAdapter.notifyDataSetChanged();
                    }
                    hashTageListGrid.setAdapter(GridAdapter);
                }else{
                    Toast.makeText(getApplicationContext(), "해시태그가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<HashtagsItem> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error",t.getMessage());
            }
        });

    }

    public void setRecoList(final String hashtag){
        ListAdapter.delItem();

        ServerService service = Server.getRetrofitInstance().create(ServerService.class);
        Call<List<getRecordClass>> call = service.getHashRecord(pref.getString("currentID", null), hashtag);

        call.enqueue(new Callback<List<getRecordClass>>() {
            @Override
            public void onResponse(Call<List<getRecordClass>> call, Response<List<getRecordClass>> response) {

                if(response.code() ==200){
                   if(response.body() != null) {

                       for(int i=0; i < response.body().size(); i++){
                           String hash="";
                           for(int j=0; j< response.body().get(i).getHashtags().size(); j++){
                               hash += (response.body().get(i).getHashtags().get(j) + " ");
                           }
                           ListAdapter.addItem(new HashTagListItem(response.body().get(i).getTime().substring(0,10), response.body().get(i).getImg(),
                                   response.body().get(i).getContent(), hash));
                           ListAdapter.notifyDataSetChanged();
                       }
                   }else{
                       Toast.makeText(getApplicationContext(), "레코드가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
                       return;
                   }
                }else{
                    Toast.makeText(getApplicationContext(), "에러가 발생했습니다", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<getRecordClass>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "서버와의 연결이 불안정합니다.", Toast.LENGTH_SHORT).show();
                Log.e("서버", t.toString());
            }
        });

//        Log.e("리스트 구성", "함수 안으로 들어오기는 햇는데");

//        for(int i=0; i < records.size(); i++){
//            ListAdapter.addItem(new HashTagListItem(records.get(i).getDate(), records.get(i).getImg(), records.get(i).getContent(), hashtag));
//            ListAdapter.notifyDataSetChanged();
//        }

    }


}
