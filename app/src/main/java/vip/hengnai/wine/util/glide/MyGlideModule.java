package vip.hengnai.wine.util.glide;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.text.DecimalFormat;

/**
 *
 * @author hua
 * @date 2019/8/13
 */
@GlideModule
public class MyGlideModule extends AppGlideModule {
    /**
     * 配置硬盘缓存
     */
    boolean externalStorageAvailable = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    String cachePath;
    /**
     * 定义GB的计算常量
     */
    private static final double GB = 1024L * 1024L * 1024L;
    /**
     * 定义MB的计算常量
     */
    private static final double MB = 1024L * 1024L;
    /**
     * 定义KB的计算常量
     */
    private static final double KB = 1024L;
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        if(externalStorageAvailable){
            cachePath=context.getExternalCacheDir().getPath();
        }else{
            cachePath=context.getCacheDir().getPath();
        }
        // 100 MB
        int diskCacheSizeBytes = 1024 * 1024 * 100;
        builder.setDiskCache(
                new DiskLruCacheFactory( cachePath+"/GlideDisk", diskCacheSizeBytes )
        );
    }
    public static void loadImage(@NonNull Context context, @NonNull ImageView target, String url, @DrawableRes int placeHolder) {
        RequestOptions options = new RequestOptions();
        options
                //skipMemoryCache设置为true为不开启缓存,默认为开启内存缓存
                .skipMemoryCache(false)
                .dontAnimate()//去除加载动画
                //自动配置硬盘缓存
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .fitCenter()
                .error(placeHolder);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(target);
    }
    public static void loadCircleImage(@NonNull Context context, @NonNull ImageView target, String url, @DrawableRes int placeHolder) {
        RequestOptions options = new RequestOptions();
        options
                //skipMemoryCache设置为true为不开启缓存,默认为开启内存缓存
                .skipMemoryCache(false)
                .dontAnimate()//去除加载动画
                //自动配置硬盘缓存
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .fitCenter()
                .circleCrop()
                .error(placeHolder);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(target);
    }
    /**
     * 清除内存缓存
     */
    public static void clearAllMemory(Context context){
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                //只能在主线程执行
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 清除硬盘存储
     */
    public static void clearAllDiskCache(Context context){
        //注释的代码为在activity中调用清除硬盘缓存
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                MyGlideModule.clearAllDiskCache(ShopAndAddressActivity.this);
//            }
//        }).start();
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取硬盘缓存大小
     * @param context
     */
    public static void getDiskCacheSize(Context context){
        //自定义目录
        File cacheDir;
        if( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            cacheDir=new File(context.getExternalCacheDir().getPath()+"/GlideDisk");
        }else{
            cacheDir=new File(context.getCacheDir().getPath()+"/GlideDisk");
        }
//        File cacheDir= Glide.getPhotoCacheDir(context);
//        File parentFile = cacheDir.getParentFile();
        long size = getDirSize(cacheDir);
        String sizeText = byteConversionGBMBKB(size);
        Log.e("glide_size", sizeText);
    }

    /**
     * 硬盘存储大小long值
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                // 递归调用继续统计
                dirSize += getDirSize(file);
            }
        }
        return dirSize;
    }

    /**
     * long值转String
     * @param kSize
     * @return
     */
    public static String byteConversionGBMBKB(double kSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        double temp = 0;
        if (kSize / GB >= 1) {
            temp = kSize / GB;
            return df.format(temp) + "GB";
        } else if (kSize / MB >= 1) {
            temp = kSize / MB;
            return df.format(temp) + "MB";
        } else if (kSize / KB >= 1) {
            temp = kSize / KB;
            return df.format(temp) + "KB";
        }
        return kSize + "B";
    }
}
