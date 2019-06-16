package sy.project2019.itshow.a2019record.Server;

import com.google.gson.annotations.SerializedName;

public class RecordModel {
    @SerializedName("content")
    private String content;

    @SerializedName("imgPath")
    private String imgPath;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
