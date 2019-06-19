package sy.project2019.itshow.a2019record.Server;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import sy.project2019.itshow.a2019record.Model.LoginUser;
import sy.project2019.itshow.a2019record.Model.Record;
import sy.project2019.itshow.a2019record.Model.User;
import sy.project2019.itshow.a2019record.Model.getRecordClass;

public interface ServerService {
    @POST("/signin")
    Call<LoginUser> sigininTask(@Body LoginUser user);

    @POST("/signup")
    Call<User> sigupTask(@Body User user);

    @Multipart
    @POST("/addRecord")
    Call<Record> addRecordTask(@Part("id") RequestBody id, @Part("content") RequestBody content,
                               @PartMap Map<String, RequestBody> map, @Part MultipartBody.Part img, @Part("time") RequestBody time);

    @GET("/getAllRecord/{id}")
    Call<List<getRecordClass>> getAllRecordTask(@Path("id") String id);

}
