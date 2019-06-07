package sy.project2019.itshow.a2019record.Model;

public class HashTagListItem {
    private String writeDate;
    private String imgpath;
    private String content;
    private String hashTag;

    public HashTagListItem(){ }

    public HashTagListItem(String writeDate, String imgpath, String content, String hashTag) {
        this.writeDate = writeDate;
        this.imgpath = imgpath;
        this.content = content;
        this.hashTag = hashTag;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }


}
