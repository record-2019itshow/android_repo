package sy.project2019.itshow.a2019record.Model;

import android.widget.ArrayAdapter;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HashtagsItem {
    @SerializedName("result")
    List<String> result;

    HashtagsItem(){}

    public HashtagsItem(List<String> result) {
        this.result = result;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

}
