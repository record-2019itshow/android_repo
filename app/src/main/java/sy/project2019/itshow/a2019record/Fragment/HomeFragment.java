package sy.project2019.itshow.a2019record.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
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


       grid = view.findViewById(R.id.main_grid_layout);
       grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//               if(position == 0) return;
               Intent intent = new Intent(getActivity(), ViewRecordActivity.class);
               Log.e(recordArr.get(position-1).getContent(), "아아아");
               Log.e(recordArr.get(position-1).getRecord_key(), "아아아");
               intent.putExtra("recoToken", recordArr.get(position-1).getRecord_key());
               // add 더미데이터 때문에 position 값보다 -1이어야 정상적으로 호출 가능

                startActivity(intent);
           }
       });//grid.setOnclick



        return view;
    }//onCreateView


    @Override
    public void onResume() {
        showItemList();
        super.onResume();
    }


    public void showItemList(){
        pref = this.getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        String currentId = pref.getString("currentID", null);

        Log.e("showItemList", "호출");

        recordArr = new ArrayList<>();
        arr = new ArrayList<>();

        adapter = new gridAdapter();
        grid.setAdapter(adapter);

        ServerService service = Server.getRetrofitInstance().create(ServerService.class);
        Call<List<getRecordClass>> call = service.getAllRecordTask(currentId);

        call.enqueue(new Callback<List<getRecordClass>>() {
            @Override
            public void onResponse(Call<List<getRecordClass>> call, Response<List<getRecordClass>> response) {
                List<getRecordClass> list = response.body();
                Log.e("onResponse", "호출" + list.get(0).getImg());

                for(int i = 0; i < list.size(); i ++){
                    recordArr.add(list.get(i));
                }

                arr.add(new gridItem("addRecord")); // addRecord 추가

                for(int i = 0; i < recordArr.size(); i++){
                    arr.add(new gridItem(recordArr.get(i).getImg()));
                    adapter.notifyDataSetChanged();
                }

                adapter.setArr(arr);
            }

            @Override
            public void onFailure(Call<List<getRecordClass>> call, Throwable t) {
                Log.e("onFailure", "호출");
            }
        });



    } //showItemList




}//class
