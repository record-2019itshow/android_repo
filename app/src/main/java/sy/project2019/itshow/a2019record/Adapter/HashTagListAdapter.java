package sy.project2019.itshow.a2019record.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import sy.project2019.itshow.a2019record.Model.HashTagListItem;
import sy.project2019.itshow.a2019record.R;



public class HashTagListAdapter extends BaseAdapter {

    ArrayList<HashTagListItem> items = new ArrayList<>();

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public HashTagListItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("함수 호출", "getView");
        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.hashtag_records_item, null);


        }
        ImageView img = convertView.findViewById(R.id.hashtag_record_img);
        TextView writeTime = convertView.findViewById(R.id.hashtag_record_writeTime);
        TextView hashTag = convertView.findViewById(R.id.hashtag_record_hashtag);
        TextView content = convertView.findViewById(R.id.hashtag_record_content);

        Picasso.get().load("http://10.96.123.73:3000/"  + items.get(position).getImgpath()).into(img);

        writeTime.setText(items.get(position).getWriteDate());
        hashTag.setText(items.get(position).getHashTag());
        content.setText(items.get(position).getContent());

        return convertView;
    }

    public void addItem(HashTagListItem item){
        items.add(item);
    }
    public void delItem(){  items.clear();  }
    public ArrayList<HashTagListItem> getItems(){
        return items;
    }
}
