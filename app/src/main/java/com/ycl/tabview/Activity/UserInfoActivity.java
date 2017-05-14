package com.ycl.tabview.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
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
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ycl.tabview.R;
import com.ycl.tabview.View.SelectPicPopupWindow;
import com.ycl.tabview.application.Myapplication;

import java.io.File;

import static com.ycl.tabview.Activity.RegisterActivity.PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;
import static com.ycl.tabview.Activity.RegisterActivity.PHOTO_REQUEST_CUT;
import static com.ycl.tabview.Activity.RegisterActivity.PHOTO_REQUEST_GALLERY;
import static com.ycl.tabview.Activity.RegisterActivity.PHOTO_REQUEST_TAKEPHOTO;

/**
 * Created by Administrator on 2017/3/23.
 */

public class UserInfoActivity extends Activity {
    private RelativeLayout re_avatar;
    private RelativeLayout re_name;
    private RelativeLayout re_tel;
    private RelativeLayout re_sex;
    private RelativeLayout re_address;
    private RelativeLayout re_no;
    private RelativeLayout re_sign;
    private SelectPicPopupWindow menuWindow;
    private ImageView iv_avatar;
    private TextView tv_name;
    private TextView tv_tel;
    private TextView tv_sex;
    private TextView tv_no;
    private TextView tv_sign;
    private TextView tv_address;
    private String imageName;
    private Myapplication mMyapplication;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        mMyapplication = (Myapplication) getApplication();
        initView();
        imageName = mMyapplication.users.getUser_tel().toString()+".png";
        String path = "/storage/emulated/0/DCIM/zy/"+imageName;
        File file = new File(path);
        if(file.exists()){
            //Toast.makeText(getActivity(),"找到图片",Toast.LENGTH_LONG).show();
            Bitmap bm = BitmapFactory.decodeFile(path);
            iv_avatar.setImageBitmap(bm);
        }
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        re_avatar = (RelativeLayout) this.findViewById(R.id.re_avatar);
        re_name = (RelativeLayout) this.findViewById(R.id.re_name);
        re_tel = (RelativeLayout) this.findViewById(R.id.re_tel);
        re_sex = (RelativeLayout) this.findViewById(R.id.re_sex);
        re_address = (RelativeLayout) this.findViewById(R.id.re_address);
        re_sign = (RelativeLayout) this.findViewById(R.id.re_sign);
        re_no = (RelativeLayout) this.findViewById(R.id.re_no);
        re_sign = (RelativeLayout) this.findViewById(R.id.re_sign);

        iv_avatar = (ImageView) this.findViewById(R.id.iv_avatar);//照片
        tv_name = (TextView) this.findViewById(R.id.tv_name);//昵称
        tv_tel = (TextView) this.findViewById(R.id.tv_tel);//电话
        tv_sex = (TextView) this.findViewById(R.id.tv_sex);//性别
        tv_sign = (TextView) this.findViewById(R.id.tv_sign);//签名
        tv_no = (TextView) this.findViewById(R.id.tv_no);//职工号
        tv_address = (TextView) this.findViewById(R.id.tv_address);//所在分院
        re_sign.setOnClickListener(new SignClickListener());
        re_avatar.setOnClickListener(new PhotoButtonClickListener());
    }

    private void initData() {
        tv_name.setText(mMyapplication.users.getUser_name());
        tv_tel.setText(mMyapplication.users.getUser_tel());
        tv_sex.setText(mMyapplication.users.getUser_sex());
        tv_no.setText(mMyapplication.users.getUser_zgid());
        tv_address.setText(mMyapplication.users.getUser_school());
        tv_sign.setText(mMyapplication.users.getUser_sign());

    }

    public void back(View view) {
        this.finish();
    }


    private final class SignClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UserInfoActivity.this, AddSignActivity.class);
            intent.putExtra("title", "签名");
            startActivity(intent);

        }
    }

    // 设置图片点击监听
    private final class PhotoButtonClickListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {

            if (ContextCompat.checkSelfPermission(v.getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(UserInfoActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                return;

            }
            //实例化SelectPicPopupWindow
            menuWindow = new SelectPicPopupWindow(UserInfoActivity.this, itemsOnClick);
            //显示窗口
            menuWindow.showAtLocation(UserInfoActivity.this.findViewById(R.id.iv_avatar), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            //设置layout在PopupWindow中显示的位置
        }


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
                    //imageName = re_tel+".png";
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // 指定调用相机拍照后照片的储存路径
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File("/storage/emulated/0/DCIM/zy/", imageName)));
                    startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
                    break;
                case R.id.picture_selector_pick_picture_btn:
                   // imageName = re_tel+".png";
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
                    iv_avatar.setImageBitmap(bitmap);

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
                    menuWindow = new SelectPicPopupWindow(UserInfoActivity.this, itemsOnClick);
                    //显示窗口
                    menuWindow.showAtLocation(UserInfoActivity.this.findViewById(R.id.btn_register), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    //设置layout在PopupWindow中显示的位置
                }
            }
        }
    }
}
