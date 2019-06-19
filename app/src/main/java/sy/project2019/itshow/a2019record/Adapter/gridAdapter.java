package sy.project2019.itshow.a2019record.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import sy.project2019.itshow.a2019record.Activity.WriteRecordActivity;
import sy.project2019.itshow.a2019record.Model.RecordModel;
import sy.project2019.itshow.a2019record.R;
import sy.project2019.itshow.a2019record.Model.gridItem;

public class gridAdapter extends BaseAdapter {

    private Context context;
    ArrayList<gridItem> item = new ArrayList<gridItem>(); // 현재 임시로넣어놓은 ARR
    ArrayList<RecordModel> arr = new ArrayList<>(); // 실제로 사용할 어레이 리스트

    public gridAdapter(){}

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public gridItem getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

            if(position == 0){
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.empty_grid_layout, null);

                LinearLayout add = convertView.findViewById(R.id.empty_grid_add);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, WriteRecordActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                });

                return  convertView;
            }else{

                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.grid_item, null);

                ImageView img = convertView.findViewById(R.id.record_img);
                Picasso.with(context).load("http://10.96.123.73:3000" + "/public/" + item.get(position).getStr()).into(img);

                return convertView;
            }


    }

    public void setArr(ArrayList<gridItem> arr){
        this.item = arr;
    }
    public String get(int position){
        return item.get(position).getStr();
    }

}

