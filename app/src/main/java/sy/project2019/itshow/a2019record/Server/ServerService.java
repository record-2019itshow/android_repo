package sy.project2019.itshow.a2019record.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServerService {
    @POST("/signin")
    Call<List<LoginUser>> sigininTask(@Body LoginUser user);

    @POST("/signup")
    Call<List<User>> siginupTask(@Body User user);


}
