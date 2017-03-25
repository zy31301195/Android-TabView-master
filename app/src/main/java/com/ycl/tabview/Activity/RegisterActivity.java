package com.ycl.tabview.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ycl.tabview.R;
import com.ycl.tabview.View.SelectPicPopupWindow;

import java.io.File;

/**
 * Created by Administrator on 2017/3/18.
 */

public class RegisterActivity extends Activity {
    private static final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private Button button;
    private EditText et_username;
    private EditText et_usertel;
    private EditText et_password;
    private ImageView iv_hide;
    private ImageView iv_show;
    private EditText et_password2;
    private ImageView iv_hide2;
    private ImageView iv_show2;
    private ImageView iv_photo;
    private SelectPicPopupWindow menuWindow;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    public static final String KEY_SEARCH_HISTORY_KEYWORD = "Tel_keyword";

    private String imageName;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mPref =  getSharedPreferences("register", Activity.MODE_PRIVATE);
        mEditor = mPref.edit();
        et_username = (EditText) findViewById(R.id.et_usernick);
        et_usertel = (EditText) findViewById(R.id.et_usertel);
        et_password = (EditText) findViewById(R.id.et_password);
        iv_hide = (ImageView) findViewById(R.id.iv_hide);
        iv_show = (ImageView) findViewById(R.id.iv_show);
        et_password2 = (EditText) findViewById(R.id.et_password2);
        iv_hide2 = (ImageView) findViewById(R.id.iv_hide2);
        iv_show2 = (ImageView) findViewById(R.id.iv_show2);
        iv_photo = (ImageView) findViewById(R.id.iv_photo);


        // 监听多个输入框
        et_username.addTextChangedListener(new TextChange());
        et_usertel.addTextChangedListener(new TextChange());
        et_password.addTextChangedListener(new TextChange());
        et_password2.addTextChangedListener(new TextChange());

        button = (Button) findViewById(R.id.btn_register);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!(et_password.getText().toString()).equals(et_password2.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"两次密码不匹配",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    mEditor.putString(KEY_SEARCH_HISTORY_KEYWORD, et_usertel.getText().toString());
                    mEditor.commit();
                    //intent.putExtra("tel",et_usertel.getText().toString());
                    startActivity(intent);
                }

            }
        });

        iv_hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                iv_hide.setVisibility(View.GONE);
                iv_show.setVisibility(View.VISIBLE);
                et_password
                        .setTransformationMethod(HideReturnsTransformationMethod
                                .getInstance());
                // 切换后将EditText光标置于末尾
                CharSequence charSequence = et_password.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }

            }

        });

        iv_show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                iv_show.setVisibility(View.GONE);
                iv_hide.setVisibility(View.VISIBLE);
                et_password
                        .setTransformationMethod(PasswordTransformationMethod
                                .getInstance());
                // 切换后将EditText光标置于末尾
                CharSequence charSequence = et_password.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }

        });

        iv_hide2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                iv_hide2.setVisibility(View.GONE);
                iv_show2.setVisibility(View.VISIBLE);
                et_password2
                        .setTransformationMethod(HideReturnsTransformationMethod
                                .getInstance());
                // 切换后将EditText光标置于末尾
                CharSequence charSequence = et_password2.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }

            }

        });

        iv_show2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                iv_show2.setVisibility(View.GONE);
                iv_hide2.setVisibility(View.VISIBLE);
                et_password2
                        .setTransformationMethod(PasswordTransformationMethod
                                .getInstance());
                // 切换后将EditText光标置于末尾
                CharSequence charSequence = et_password2.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }

        });

        // 设置图片点击监听
        iv_photo.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(v.getContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(RegisterActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    return;

                }
                //实例化SelectPicPopupWindow
                menuWindow = new SelectPicPopupWindow(RegisterActivity.this, itemsOnClick);
                //显示窗口
                menuWindow.showAtLocation(RegisterActivity.this.findViewById(R.id.btn_register), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                //设置layout在PopupWindow中显示的位置
            }


        });
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {

            menuWindow.dismiss();
            File appDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/zy");
            if (!appDir.exists()) {
                appDir.mkdirs();
            }
            switch (v.getId()) {
                case R.id.picture_selector_take_photo_btn:
                    imageName = "takephoto.png";
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // 指定调用相机拍照后照片的储存路径
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File("/storage/emulated/0/DCIM/zy/", imageName)));
                    startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
                    break;
                case R.id.picture_selector_pick_picture_btn:
                    imageName = "user_photo.png";
                    Intent intent2 = new Intent(Intent.ACTION_PICK, null);
                    intent2.setDataAndType(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent2, PHOTO_REQUEST_GALLERY);
                    break;
                default:
                    break;

            }

        }

    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_REQUEST_TAKEPHOTO:
                    startPhotoZoom(
                            Uri.fromFile(new File("/storage/emulated/0/DCIM/zy/", imageName)),
                            480);
                    break;

                case PHOTO_REQUEST_GALLERY:
                    if (data != null)
                        startPhotoZoom(data.getData(), 480);

                    break;

                case PHOTO_REQUEST_CUT:
                    Bitmap bitmap = BitmapFactory.decodeFile("/storage/emulated/0/DCIM/zy/" + imageName);
                    iv_photo.setImageBitmap(bitmap);

                    break;

            }
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    private void startPhotoZoom(Uri uri1, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri1, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", false);

        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File("/storage/emulated/0/DCIM/zy/", imageName)));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //实例化SelectPicPopupWindow
                    menuWindow = new SelectPicPopupWindow(RegisterActivity.this, itemsOnClick);
                    //显示窗口
                    menuWindow.showAtLocation(RegisterActivity.this.findViewById(R.id.btn_register), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    //设置layout在PopupWindow中显示的位置
                }
            }
        }
    }

    // EditText监听器
    private class TextChange implements TextWatcher {

        @Override
        public void afterTextChanged(Editable arg0) {

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence cs, int start, int before,
                                  int count) {

            boolean Sign1 = et_username.getText().length() > 0;
            boolean Sign2 = et_usertel.getText().length() > 0;
            boolean Sign3 = et_password.getText().length() > 0;

            if (Sign1 & Sign2 & Sign3) {
                button.setTextColor(0xFFFFFFFF);
                button.setEnabled(true);
            }
            // 在layout文件中，对Button的text属性应预先设置默认值，否则刚打开程序的时候Button是无显示的
            else {
                button.setTextColor(0xFFD0EFC6);
                button.setEnabled(false);
            }
        }

    }
}
