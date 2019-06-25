package sy.project2019.itshow.a2019record.Model;

import java.util.Date;
import java.util.List;

public class getRecordClass {


    String img;
    String content;
    List<String> hashtags;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String time;
    String record_key;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }


    public String getRecord_key() {
        return record_key;
    }

    public void setRecord_key(String record_key) {
        this.record_key = record_key;
    }

}
