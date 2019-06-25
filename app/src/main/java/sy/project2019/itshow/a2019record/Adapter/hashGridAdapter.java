package sy.project2019.itshow.a2019record.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import sy.project2019.itshow.a2019record.R;

public class hashGridAdapter  extends BaseAdapter {
    private Context context;
    ArrayList<String> items = new ArrayList<>(); // 현재 임시로넣어놓은 ARR

    public hashGridAdapter(){}

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.hashtag_list_griditem, null);
        }
        TextView hash = convertView.findViewById(R.id.hashtag_grid_name);
        hash.setText(items.get(position));

        return convertView;

    }

    public void setArr(ArrayList<String> arr){
        this.items = arr;
    }
    public  void setItem(String item){
        items.add(item);
    }
}
