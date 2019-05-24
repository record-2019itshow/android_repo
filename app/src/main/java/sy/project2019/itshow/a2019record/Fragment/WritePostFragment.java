package sy.project2019.itshow.a2019record.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import sy.project2019.itshow.a2019record.R;

public class WritePostFragment extends Fragment {

    private static final int PICK_FROM_ALBUM = 1;
    private File tempFile;
    View view;
    Button postingBtn, getImageBtn;
    Uri photoUri;
    ImageView PostImageView;

    public WritePostFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_write_post, container, false);
        PostImageView = view.findViewById(R.id.PostImageView);
        postingBtn = view.findViewById(R.id.postingBtn);
        getImageBtn = view.findViewById(R.id.getImageBtn);


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


        return view;
    }

    private void goToAlbum() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(getActivity().getApplicationContext(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();

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
                image_bitmap 	= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                setImage(image_bitmap);

            }catch(Exception e){
                e.getStackTrace();
            }
            finally {
            }

        }
    }

    private void setImage(Bitmap img) {

        ImageView imageView = view.findViewById(R.id.PostImageView);

        imageView.setImageBitmap(img);

    }

}
