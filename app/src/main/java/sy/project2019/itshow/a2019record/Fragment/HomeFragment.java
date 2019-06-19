package sy.project2019.itshow.a2019record.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sy.project2019.itshow.a2019record.Activity.ViewRecordActivity;
import sy.project2019.itshow.a2019record.Adapter.gridAdapter;
import sy.project2019.itshow.a2019record.Model.getRecordClass;
import sy.project2019.itshow.a2019record.R;
import sy.project2019.itshow.a2019record.Model.gridItem;
import sy.project2019.itshow.a2019record.Server.Server;
import sy.project2019.itshow.a2019record.Server.ServerService;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    public HomeFragment() { }

    LinearLayout addRecord;
    GridView grid;
    ArrayList<getRecordClass> recordArr;
    ArrayList<gridItem> arr;
    View view;
    SharedPreferences pref;
    gridAdapter adapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        recordArr = new ArrayList<>();
        arr = new ArrayList<>();

        pref = this.getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        String currentId = pref.getString("currentID", null);

        grid = view.findViewById(R.id.main_grid_layout);

        adapter = new gridAdapter();
        grid.setAdapter(adapter);

        ServerService service = Server.getRetrofitInstance().create(ServerService.class);
        Call<List<getRecordClass>> call = service.getAllRecordTask(currentId);

        call.enqueue(new Callback<List<getRecordClass>>() {
            @Override
            public void onResponse(Call<List<getRecordClass>> call, Response<List<getRecordClass>> response) {
                List<getRecordClass> list = response.body();

                if(list != null){
                    for(getRecordClass reco : list){

                        recordArr.add(reco);
                        adapter.notifyDataSetChanged();

                    }
                }else{
                    Log.e("게시글이 존재하지 않습니다", "ㅐㅐ");
                }

            }

            @Override
            public void onFailure(Call<List<getRecordClass>> call, Throwable t) {

            }
        });

        arr.add(new gridItem("addRecord")); // addRecord 추가

        for(int i = 0; i < recordArr.size(); i++){
            arr.add(new gridItem(recordArr.get(i).getContent()));
        }

//        arr.add(new gridItem("엉덩이")); // 실제로 넣고 싶은 데이터 추가
//        arr.add(new gridItem("엉덩이???")); // 실제로 넣고 싶은 데이터 추가
        adapter.setArr(arr);


       grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//               if(position == 0) return;
               Intent intent = new Intent(getActivity(), ViewRecordActivity.class);
               intent.putExtra("recoToken", recordArr.get(position).getRecord_key());
                startActivity(intent);
           }
       });//grid.setOnclick



        return view;
    }//onCreateView
}//class
