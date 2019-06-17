package sy.project2019.itshow.a2019record.Server;

public class RecordModel {
    private String WriteDate;
    private String content;
    private String imgPath;
    private String[] HashTags;

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
