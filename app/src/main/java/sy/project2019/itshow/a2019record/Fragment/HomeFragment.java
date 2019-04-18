package sy.project2019.itshow.a2019record.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import sy.project2019.itshow.a2019record.Adapter.gridAdapter;
import sy.project2019.itshow.a2019record.R;

public class HomeFragment extends Fragment {
    public HomeFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        GridView grid = view.findViewById(R.id.main_grid_layout);
        final gridAdapter adapter = new gridAdapter();

        for(int i = 0; i < 5; i++){
            adapter.addItem(Integer.toString(i));
        }

        grid.setAdapter(adapter);

       grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(view.getContext().getApplicationContext(), adapter.get(position), Toast.LENGTH_SHORT).show();
           }
       });



        return view;
    }
}
