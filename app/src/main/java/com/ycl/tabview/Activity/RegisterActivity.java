package com.ycl.tabview.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.Bean.Users;
import com.ycl.tabview.R;
import com.ycl.tabview.View.SelectPicPopupWindow;
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.httpBean.LoginBeanTest;
import com.ycl.tabview.retrofitUtil.Retrofitutil;

import java.io.File;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

/**
 * Created by Administrator on 2017/3/18.
 */

public class RegisterActivity extends Activity {
    public static final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private Button button;
    private TextView sex;
    private TextView school;
    private EditText et_username;
    private EditText zgid;
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
    public static final String[] school_name = {"计算","传媒","商学院","信电","工程","医学院","法学院","外国语","创意"};
    private String imageName;
    public static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    public static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    public static final int PHOTO_REQUEST_CUT = 3;// 结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initView();
        mPref =  getSharedPreferences("register", Activity.MODE_PRIVATE);
        mEditor = mPref.edit();
    }


    private void initView(){
        et_username = (EditText) findViewById(R.id.et_usernick);
        et_usertel = (EditText) findViewById(R.id.et_usertel);
        zgid = (EditText) findViewById(R.id.et_userzgid);
        et_password = (EditText) findViewById(R.id.et_password);
        iv_hide = (ImageView) findViewById(R.id.iv_hide);
        iv_show = (ImageView) findViewById(R.id.iv_show);
        et_password2 = (EditText) findViewById(R.id.et_password2);
        iv_hide2 = (ImageView) findViewById(R.id.iv_hide2);
        iv_show2 = (ImageView) findViewById(R.id.iv_show2);
        iv_photo = (ImageView) findViewById(R.id.iv_photo);
        button = (Button) findViewById(R.id.btn_register);
        sex = (TextView) findViewById(R.id.et_usersex);
        school = (TextView) findViewById(R.id.et_userschool);


        // 监听多个输入框
        et_username.addTextChangedListener(new TextChange());
        et_usertel.addTextChangedListener(new TextChange());
        zgid.addTextChangedListener(new TextChange());
        et_password.addTextChangedListener(new TextChange());
        et_password2.addTextChangedListener(new TextChange());

        button.setOnClickListener(new RegisterButtonClickListener());
        iv_hide.setOnClickListener(new HideButtonClickListener());
        iv_show.setOnClickListener(new ShowButtonClickListener());
        iv_hide2.setOnClickListener(new Hide2ButtonClickListener());
        iv_show2.setOnClickListener(new Show2ButtonClickListener());
        iv_photo.setOnClickListener(new PhotoButtonClickListener());
        sex.setOnClickListener(new SexButtonClickListener());
        school.setOnClickListener(new SchoolButtonClickListener());
        imageName = "user.png";
    }


    private final class RegisterButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Users user = new Users();
            user.setUser_name(et_username.getText().toString());
            user.setUser_pwd(et_password.getText().toString());
            user.setUser_school(school.getText().toString());
            user.setUser_sex(sex.getText().toString());
            user.setUser_tel(et_usertel.getText().toString());
            user.setUser_zgid(zgid.getText().toString());
            Map<String, String> map = user.createCommitParams();

            if(!(et_password.getText().toString()).equals(et_password2.getText().toString())){
                Toast.makeText(RegisterActivity.this,"两次密码不匹配",Toast.LENGTH_SHORT).show();
            }
            else {
                Retrofitutil.getmRetrofit().create(LoginHttps.class).getJson(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subject<LoginBeanTest>() {
                            @Override
                            public boolean hasObservers() {
                                return false;
                            }

                            @Override
                            public boolean hasThrowable() {
                                return false;
                            }

                            @Override
                            public boolean hasComplete() {
                                return false;
                            }

                            @Override
                            public Throwable getThrowable() {
                                return null;
                            }

                            @Override
                            protected void subscribeActual(Observer<? super LoginBeanTest> observer) {

                            }

                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LoginBeanTest s) {
                                if(s.getUser().equals("success")){
                                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                    mEditor.putString(KEY_SEARCH_HISTORY_KEYWORD, et_usertel.getText().toString());
                                    mEditor.commit();
                                    File from =new File("/storage/emulated/0/DCIM/zy/",imageName) ;
                                    File to=new File("/storage/emulated/0/DCIM/zy/",et_usertel.getText().toString()+".png") ;
                                    from.renameTo(to) ;
                                    //Toast.makeText(RegisterActivity.this,imageName,Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                                else
                                    Toast.makeText(RegisterActivity.this,"该号码已存在",Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onComplete() {
                                // Toast.makeText(LoginActivity.this,"complete",Toast.LENGTH_LONG).show();
                            }
                        });
            }

        }
    }

    private final class SexButtonClickListener implements View.OnClickListener {
        int index = 0 ;
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setTitle("请选择性别");
            final String[] sexs = {"男", "女"};
            builder.setSingleChoiceItems(sexs,0,new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which)
                {
                    index = which;
                }
            });

            builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    sex.setText(sexs[index]);

                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

                }
            });
            builder.show();

        }
    }

    private final class SchoolButtonClickListener implements View.OnClickListener {
        int index = 0 ;
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setTitle("请选择所属分院");
            builder.setSingleChoiceItems(school_name,0,new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which)
                {
                    index = which;
                }
            });

            builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    school.setText(school_name[index]);

                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

                }
            });
            builder.show();

        }
    }


    private final class HideButtonClickListener implements View.OnClickListener {

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

    }

    private final class ShowButtonClickListener implements View.OnClickListener {

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

    }

    private final class Hide2ButtonClickListener implements View.OnClickListener {

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

    }

    private final class Show2ButtonClickListener implements View.OnClickListener {

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

    }

    // 设置图片点击监听
    private final class PhotoButtonClickListener implements View.OnClickListener {


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
                    //imageName = et_usertel.getText().toString()+".png";
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // 指定调用相机拍照后照片的储存路径
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File("/storage/emulated/0/DCIM/zy/", imageName)));
                    startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
                    break;
                case R.id.picture_selector_pick_picture_btn:
                    //imageName = et_usertel.getText().toString()+".png";
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
            boolean Sign4 = et_password2.getText().length() > 0;
            boolean Sign5 = zgid.getText().length() > 0;

            if (Sign1 & Sign2 & Sign3 & Sign4 &Sign5) {
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
