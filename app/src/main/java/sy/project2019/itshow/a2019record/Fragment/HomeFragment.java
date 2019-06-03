package sy.project2019.itshow.a2019record.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import sy.project2019.itshow.a2019record.Activity.WriteRecordActivity;
import sy.project2019.itshow.a2019record.Adapter.gridAdapter;
import sy.project2019.itshow.a2019record.Model.RecordModel;
import sy.project2019.itshow.a2019record.R;
import sy.project2019.itshow.a2019record.gridItem;

public class HomeFragment extends Fragment {
    public HomeFragment() { }

    LinearLayout addRecord;
    GridView grid;
    ArrayList<RecordModel> recordArr;
    ArrayList<gridItem> arr;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        recordArr = new ArrayList<>();
        arr = new ArrayList<>();

        grid = view.findViewById(R.id.main_grid_layout);

        gridAdapter adapter = new gridAdapter();
        grid.setAdapter(adapter);
        arr.add(new gridItem("더미데이터")); // 맨 처음에 addRecord를 추가하기 위해서 더미 테이터를 넣음

        arr.add(new gridItem("엉덩이")); // 실제로 넣고 싶은 데이터 추가
        arr.add(new gridItem("엉덩이???")); // 실제로 넣고 싶은 데이터 추가
        adapter.setArr(arr);

//        addRecord = view.findViewById(R.id.empty_grid_add);
//        addRecord.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), WriteRecordActivity.class);
//                startActivity(intent);
//            }
//        });


       grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           }
       });//grid.setOnclick



        return view;
    }//onCreateView
}//class
