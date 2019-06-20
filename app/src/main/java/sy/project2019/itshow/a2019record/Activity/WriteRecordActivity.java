package sy.project2019.itshow.a2019record.Activity;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sy.project2019.itshow.a2019record.Model.Record;
import sy.project2019.itshow.a2019record.R;
import sy.project2019.itshow.a2019record.Server.Server;
import sy.project2019.itshow.a2019record.Server.ServerService;

public class WriteRecordActivity extends AppCompatActivity {
    private static final int PICK_FROM_ALBUM = 1;
    private File tempFile;
    Button postingBtn;
    LinearLayout getImageBtn;

    Uri photoUri;
    ImageView PostImageView, Backbtn;
    TextView toolbarTitle;
    EditText content_edit, hashTag_edit;
    File img_file = null; // 갤러리에서 불러온 사진 저장하는 File 객체
    SharedPreferences preferences;
    private final int MY_PERMISSIONS_REQUEST=1000;
    String nowDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_record);
        checkPermission();


        init(); // xml 아이디와 연결

        //액션바 설정 코드
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activities_actionbar_layout);
        getSupportActionBar().setTitle(R.string.writeRecordTitle);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        Backbtn = findViewById(R.id.backBtn);

        preferences = getSharedPreferences("pref", MODE_PRIVATE);

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date ();
        nowDate = mSimpleDateFormat.format ( currentTime );



        toolbarTitle.setText(R.string.writeRecordTitle);
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //액션바 설정 코드 끝


        postingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db 처리 후


                if(img_file == null){
                    Toast.makeText(getApplicationContext(), "이미지를 선택해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String id = preferences.getString("currentID", null);
                String content = content_edit.getText().toString();
                String hashtagsTxt = hashTag_edit.getText().toString();


                String[] hashtags = hashtagsTxt.replaceAll(" ", "").split("#");

                Map<String, RequestBody> hashBody = new LinkedHashMap<>();
                RequestBody rb;

                for(int i = 1; i < hashtags.length; i++){
                    rb = RequestBody.create(MediaType.parse("text/plain"), "#" + hashtags[i]);
                    hashBody.put("hashtags[" + (i-1) + "]", rb);
                }

                RequestBody idBody = RequestBody.create(MediaType.parse("id"), id);
                RequestBody contentBody = RequestBody.create(MediaType.parse("content"), content);
                RequestBody timeBody = RequestBody.create(MediaType.parse("time"), nowDate);

                Log.e("time" , nowDate);

                MultipartBody.Part body =
                MultipartBody.Part.createFormData("img", img_file.getPath(), RequestBody.create(MediaType.parse("image/jpeg"), img_file));

                ServerService service = Server.getRetrofitInstance().create(ServerService.class);
                Call<Record> call = service.addRecordTask(idBody, contentBody, hashBody, body,timeBody);

                call.enqueue(new Callback<Record>() {
                    @Override
                    public void onResponse(Call<Record> call, Response<Record> response) {
                        if(response.code() == 200){
                            Toast.makeText(getApplicationContext(), "레코드가 작성되었습니다", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "레코드 작성 실패!" + response.message(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onFailure(Call<Record> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getApplicationContext(), "레코드 작성 실패!22", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
            }
        });

        getImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAlbum();
            }
        });

    }//onCreate

    //여기서 부터 갤러리 이동코드

    private void goToAlbum() { // 앨범으로 이동하는 메소드

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

        startActivityForResult(intent, PICK_FROM_ALBUM); // 갤러리에서 사진 가져옴
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { // 갤러리에서의 처리
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(getApplicationContext(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();

            if(tempFile != null) { // 갤러리에서 가져오는 이미지가 없을 때 삭제
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e("", tempFile.getAbsolutePath() + " 삭제 성공");
                        tempFile = null;
                    }
                }
            }
            return;
        }  // 갤러리에서 가져오기 실패 (취소)

        if (requestCode == PICK_FROM_ALBUM) {
            photoUri = data.getData();
            String filePath = getPath(photoUri); // 이걸로 file 처리
            String file_extn = filePath.substring(filePath.lastIndexOf(".") + 1); // 사진 타입
            Bitmap image_bitmap;

            try {
                if (file_extn.equals("img") || file_extn.equals("jpg") || file_extn.equals("jpeg") ||
                        file_extn.equals("gif") || file_extn.equals("png")) {
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    setImage(image_bitmap);
                    getImageBtn.setVisibility(View.GONE);
                    img_file = new File(filePath);

                } else {
                    Toast.makeText(getApplicationContext(), "지원하지 않는 형식의 파일입니다", Toast.LENGTH_SHORT).show();
                    return;
                }

            }catch(Exception e){
                e.getStackTrace();
            }

        }
    } // onActivityResult


    private void setImage(Bitmap img) { // 레이아웃에 갤러리 이미지 표시

        ImageView imageView = findViewById(R.id.PostImageView);
        imageView.setImageBitmap(img);

    }

    public String getPath(Uri uri) { // 이미지 파일 경로 얻는 메서드
        int column_index; // 갤러리에서 사진의 좌표
        String imagePath; // 사진의 경로

        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        column_index = cursor
                .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        imagePath = cursor.getString(column_index);

        Log.e("imagepath", imagePath);

        return imagePath;
    }



    //갤러리 관련 코드 끝

    public void init(){
        PostImageView = findViewById(R.id.PostImageView);
        postingBtn = findViewById(R.id.postingBtn);
        getImageBtn = findViewById(R.id.getImageBtn);
        content_edit = findViewById(R.id.edit_content);
        hashTag_edit = findViewById(R.id.edit_hashTag);
    }



    private void checkPermission(){

        if(android.os.Build.VERSION.SDK_INT  >= 23){
            int permissionCheck = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
            ArrayList<String>  arrayPermission = new ArrayList<>();

            if(permissionCheck != PackageManager.PERMISSION_GRANTED){
                arrayPermission.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }

            permissionCheck = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if(permissionCheck != PackageManager.PERMISSION_GRANTED){
                arrayPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if(arrayPermission.size() > 0){
                String arr[] = new String[arrayPermission.size()];
                arr = arrayPermission.toArray(arr);
                ActivityCompat.requestPermissions(this, arr, MY_PERMISSIONS_REQUEST);
            }

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if(grantResults.length < 1){
                    Toast.makeText(this, "권한이 없습니다", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }

                for(int i = 0; i <grantResults.length; i++){
                    if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "권한이 거부되었습니다", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                }
                Toast.makeText(this, "권한이 허용되었습니다", Toast.LENGTH_SHORT).show();

            }
            break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
