package sy.project2019.itshow.a2019record.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import sy.project2019.itshow.a2019record.R;

public class WriteRecordActivity extends AppCompatActivity {
    private static final int PICK_FROM_ALBUM = 1;
    private File tempFile;
    Button postingBtn, getImageBtn;
    Uri photoUri;
    ImageView PostImageView, Backbtn;
    TextView toolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_record);

        PostImageView = findViewById(R.id.PostImageView);
        postingBtn = findViewById(R.id.postingBtn);
        getImageBtn = findViewById(R.id.getImageBtn);

        //액션바 설정 코드
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activities_actionbar_layout);
        getSupportActionBar().setTitle(R.string.writeRecordTitle); // 임시로 설정
        toolbarTitle = findViewById(R.id.toolbarTitle);
        Backbtn = findViewById(R.id.backBtn);

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) { // 갤러리 내부 처리

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(getApplicationContext(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();

            if(tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e("", tempFile.getAbsolutePath() + " 삭제 성공");
                        tempFile = null;
                    }
                }
            }
            return;
        }

        if (requestCode == PICK_FROM_ALBUM) {

            photoUri = data.getData();
            Bitmap image_bitmap;

            try {
                image_bitmap 	= MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                setImage(image_bitmap);

            }catch(Exception e){
                e.getStackTrace();
            }
            finally {
            }

        }
    } // onActivityResult


    private void setImage(Bitmap img) { // 레이아웃에 갤러리 이미지 표시

        ImageView imageView = findViewById(R.id.PostImageView);

        imageView.setImageBitmap(img);

    }
    //갤러리 관련 코드 끝
}
