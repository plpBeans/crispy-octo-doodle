package vip.hengnai.wine.ui.mine;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.HomeDateEntity;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.ui.main.MainActivity;
import vip.hengnai.wine.util.glide.MyGlideModule;

/***
 * 个人资料
 * @author Hugh
 */
public class MyInfoActivity extends BaseMvpAppCompatActivity<IMineView, MinePresenter> implements IMineView {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_title_right)
    TextView textTitleRight;
    @BindView(R.id.img_arrow)
    ImageView imgArrow;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.img_header)
    ImageView imgHeader;
    @BindView(R.id.tx_phone)
    TextView txPhone;
    @BindView(R.id.img_modifyPhone)
    ImageView imgModifyPhone;
    @BindView(R.id.tx_sex)
    TextView txSex;
    @BindView(R.id.img_sex)
    ImageView imgSex;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.tx_getPhone)
    TextView txGetPhone;
    @BindView(R.id.img_phone)
    ImageView imgPhone;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.tx_WX)
    TextView txWX;
    @BindView(R.id.img_WX)
    ImageView imgWX;
    @BindView(R.id.rl_WX)
    RelativeLayout rlWX;
    @BindView(R.id.tx_address)
    TextView txAddress;
    @BindView(R.id.img_address)
    ImageView imgAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.tx_push)
    TextView txPush;
    @BindView(R.id.img_push)
    ImageView imgPush;
    @BindView(R.id.rl_push)
    RelativeLayout rlPush;
    private MinePresenter minePresenter;
    /**
     *图片地址
     **/
    private String mPhotoPath;
    /**
     * 图片压缩地址
     */
    private String pressPhotoPath;
    /**
     *  6.不再提示权限时的展示对话框
     */
    android.support.v7.app.AlertDialog mPermissionDialog;
    /**
     * 、首先声明一个数组permissions，将所有需要申请的权限都放在里面
     */
    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,};
    List<String> mPermissionList = new ArrayList<>();
    /**
     *  权限请求码
     */
    private final int mRequestCode = 100;
    /**
     *   如果勾选了不再询问
     */
    private static final int NOT_NOTICE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        ButterKnife.bind(this);
        iniView();
    }

    @Override
    protected MinePresenter initPresenter() {
        return minePresenter = new MinePresenter();
    }

    private void iniView() {
        /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        imgBack.setVisibility(View.VISIBLE);
        imgBack.setImageResource(R.mipmap.ic_back);
        textTitle.setText("编辑个人信息");
        textTitleRight.setText("保存");
        textTitleRight.setTextColor(ContextCompat.getColor(this, R.color.text_gray6));
        textTitleRight.setVisibility(View.VISIBLE);
        MyGlideModule.loadCircleImage(this,imgHeader,"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png",R.mipmap.ic_touxiang);

    }

    @Override
    public void showDatas(List<HomeDateEntity> datas) {

    }

    @Override
    public void appendDatas(List<HomeDateEntity> datas) {

    }

    @Override
    public void showLoadingMore() {

    }

    @Override
    public void noMoreData() {

    }


    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showLongToast(message);
    }

    @Override
    public void forceToReLogin(String message) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick({R.id.img_header,R.id.img_back, R.id.rl_sex, R.id.rl_phone, R.id.rl_WX, R.id.rl_address, R.id.rl_push,R.id.text_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_header:
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    initPermission();
                }else{
                    showDialog();
                }
                break;
            case R.id.rl_sex:
                break;
            case R.id.rl_phone:
                break;
            case R.id.rl_WX:
                break;
            case R.id.rl_address:
                break;
            case R.id.rl_push:
                break;
            case R.id.text_title_right:
                //保存
                break;
        }
    }
    /**
     *   4、权限判断和申请
     */
    private void initPermission() {
        mPermissionList.clear();//清空已经允许的没有通过的权限
        //逐个判断是否还有未通过的权限
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) !=
                    PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限到mPermissionList中
            }
        }
        //申请权限
        if (mPermissionList.size() > 0) {
            //有权限没有通过，需要申请
            ActivityCompat.requestPermissions(this, permissions, mRequestCode);
        } else {
            showDialog();
        }
    }
    /**
     * 权限申请返回结果
     *
     * @param requestCode  请求码
     * @param permissions  权限数组
     * @param grantResults 申请结果数组，里面都是int类型的数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //有权限没有通过
        boolean hasPermissionDismiss = false;
        if (mRequestCode==requestCode){
            for (int i=0;i<grantResults.length;i++){
                if (grantResults[i]==-1){
                    hasPermissionDismiss=true;
                    break;
                }
            }
        }
        //如果有没有被允许的权限
        if (hasPermissionDismiss){
            showPermissionDialog();
        }else {
            showDialog();
        }
    }

    private void showPermissionDialog() {
        if (mPermissionDialog == null) {
            mPermissionDialog = new android.support.v7.app.AlertDialog.Builder(this)
                    .setMessage("已禁用权限，请手动授予权限,才能继续使用哦")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();

                            Uri packageURI = Uri.parse("package:" + getPackageName());
                            Intent intent = new Intent(Settings.
                                    ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            startActivityForResult(intent,NOT_NOTICE);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //关闭页面或者做其他操作
                            cancelPermissionDialog();
                            MyInfoActivity.this.finish();
                        }
                    })
                    .create();
        }
        mPermissionDialog.show();
    }
    private void cancelPermissionDialog() {
        mPermissionDialog.cancel();
    }

    /**
     * 相机相册弹窗
     */
    public void showDialog() {
        final AlertDialog dlg = new AlertDialog.Builder(this, R.style.ActionSheetDialogStyle).create();
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.alert_picture);
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        Button btn_camera=window.findViewById(R.id.btn_camera);
        Button btn_picture=window.findViewById(R.id.btn_picture);
        Button btn_cancel=window.findViewById(R.id.btn_cancel);


        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
                dlg.dismiss();
            }
        });
        btn_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPhoto();
                dlg.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
            }
        });
    }

    /**
     * 打开相册
     */
    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 11);
    }
    /**
     * 打开相机
     */
    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 文件存放位置
        File front_dir = new File(Constants.CAMERA_PATH);
        if (!front_dir.exists()) {
            front_dir.mkdirs();
        }
        // 正面图片路径
        mPhotoPath = Constants.CAMERA_PATH +"/"+ UUID.randomUUID().toString() + ".jpg";
        File front_file = new File(mPhotoPath);
        if (!front_file.exists()) {
            try {
                front_file.createNewFile();
            } catch (IOException e) {

            }
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(front_file));
        } else {
            //兼容android7.0 使用共享文件的形式
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, mPhotoPath);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, this.getApplication().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues));
        }

        startActivityForResult(intent, 10);
    }
    /** 删除目录及目录下的文件
     * @param filePath 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    private void deleteDirectory(String filePath) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)){
            filePath = filePath + File.separator;
        }

        File dirFile = new File(filePath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
//            Toast.makeText(getApplicationContext(), "删除目录失败：" + filePath + "不存在！", Toast.LENGTH_SHORT).show();
            return;
        }
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (File file : files) {
            // 删除子文件
            if (file.isFile()) {
               deleteSingleFile(file.getAbsolutePath());

            } else if (file.isDirectory()) {
                // 删除子目录
                 deleteDirectory(file.getAbsolutePath());

            }
        }
    }
    /** 删除单个文件
     * @param filePath$Name 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private void deleteSingleFile(String filePath$Name) {
        File file = new File(filePath$Name);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case NOT_NOTICE:
                initPermission();
                break;
            /**
             * 裁剪修改的头像
             */
            case 9:
                if (data == null) {
                    showLongToast("取消上传");
                    return;
                } else {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            Bitmap bitmap = extras.getParcelable("data");
                            compressImage(bitmap);
                            MyGlideModule.loadCircleImage(MyInfoActivity.this, imgHeader, pressPhotoPath, 0);
//                            deleteDirectory(Constants.CAMERA_PATH);
                            //网络请求上传图片
//                            deleteSingleFile(mPhotoPath);
//                            deleteSingleFile(pressPhotoPath);

                    }
                }
                break;
            // 拍照
            case 10:
                if (resultCode == RESULT_OK) {
                    if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        showLongToast("SD卡不可用");
                        return;
                    }
                    File file = new File(mPhotoPath);
                    //裁剪
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                        startPhotoZoom(Uri.fromFile(file));
                    } else {
                        startPhotoZoom(FileProvider.getUriForFile(MyInfoActivity.this,getPackageName()+".provider",file));
                    }

                }
                break;
            // 相册
            case 11:

                Uri uri_front = null;
                if (data == null) {
                    return;
                }
                if (resultCode == RESULT_OK) {
                    if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        showLongToast("SD卡不可用");
                        return;
                    }
                    uri_front = data.getData();
                    startPhotoZoom(uri_front);
                } else {
                    showLongToast("照片获取失败");
                }
                break;

        }

    }
    /**
     * 系统裁剪照片
     *
     * @param uri
     */
    private void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //需要加上这两句话 ： uri 权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("scale", true);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 9);
    }
    /**
     * 压缩图片
     * @param image
     */
    private void compressImage(Bitmap image) {
        ByteArrayOutputStream baos = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ByteArrayInputStream isBm = null;
        try {
            // compressImage
            baos = new ByteArrayOutputStream();
            // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            int options = 100;
            int KSize = baos.toByteArray().length / 1024;
            while ( KSize> 300) {
                // 重置baos即清空baos
                baos.reset();
                // 每次都减少10
                options -= 10;
                // 这里压缩options%，把压缩后的数据存放到baos中
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);

            }
            // 字节数组输出流转换成字节数组
            byte[] byteArray = baos.toByteArray();
            // 把压缩后的数据baos存放到ByteArrayInputStream中
            isBm = new ByteArrayInputStream(byteArray);
            // save image to local
            File dir = new File(Constants.CAMERA_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String filename = UUID.randomUUID().toString();
            pressPhotoPath = Constants.CAMERA_PATH +"/"+ filename + ".jpg";
            System.out.println("save  pic ===========:" + pressPhotoPath);
            File file = new File(pressPhotoPath);

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {

                }
            }
            // 将字节数组写入到刚创建的图片文件中
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(byteArray);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (isBm != null) {
                try {
                    isBm.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        deleteDirectory(Constants.CAMERA_PATH);
    }
}
