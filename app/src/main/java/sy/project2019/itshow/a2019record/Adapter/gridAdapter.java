package sy.project2019.itshow.a2019record.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sy.project2019.itshow.a2019record.Model.RecordModel;
import sy.project2019.itshow.a2019record.R;
import sy.project2019.itshow.a2019record.gridItem;

public class gridAdapter extends BaseAdapter {

    private Context context;
    ArrayList<gridItem> item = new ArrayList<gridItem>(); // 현재 임시로넣어놓은 ARR
    ArrayList<RecordModel> arr = new ArrayList<>(); // 실제로 사용할 어레이 리스트
    View convertview;
    public gridAdapter(){}

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        convertview = convertView;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.grid_item, null);
            }

            TextView textView = (TextView) convertView
                    .findViewById(R.id.item_str);

            textView.setText(item.get(position).getStr());
            return convertView;

    }

    public void setArr(ArrayList<gridItem> arr){
        this.item = arr;
    }
    public String get(int position){
        return item.get(position).getStr();
    }

}

