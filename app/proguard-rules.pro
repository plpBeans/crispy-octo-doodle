# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Add project specific ProGuard rules here.

# By default, the flags in this file are appended to flags specified

# in /Users/hua/android-sdk-macosx/tools/proguard/proguard-android.txt

# You can edit the include path and order by changing the proguardFiles

# directive in build.gradle.

#

# For more details, see

#   http://developer.android.com/guide/developing/tools/proguard.html



# Add any project specific keep options here:



# If your project uses WebView with JS, uncomment the following

# and specify the fully qualified class title to the JavaScript interface

# class:

#-keepclassmembers class fqcn.of.javascript.interface.for.webview {

#   public *;

#}
#直接忽略警告信息
-ignorewarnings
-dontwarn

# 指定不使用大小写区分混淆后的类名

-dontusemixedcaseclassnames

# 指定混淆的时候不忽略jar中非public的类

-dontskipnonpubliclibraryclasses

# 混淆过程中输出更详细的信息

-verbose



# 混淆的时候不执行优化步骤与预验证步骤，Dex自己会做一些相对应的优化

#-dontoptimize

#-dontpreverify



# 确保在jdk5.0以及更高版本能够使用泛型

-keepattributes Signature

# 不混淆异常,让编译器知道方法会抛出哪种异常

-keepattributes Exceptions

#不混淆注解

-keepattributes *Annotation*

#-keepattributes InnerClasses

-keepattributes EnclosingMethod

-keepattributes InnerClasses



-keep class com.balysv.** { *; }

-keep class org.robovm.** { *; }

-keep class org.apache.** { *; }

-keep class com.android.** { *; }

-keep class android.** { *; }

-keep class org.json.** { *; }



# 不混淆本地方法,includedescriptorclasses选项指定不重命名方法返回类型与参数类型

-keepclasseswithmembernames,includedescriptorclasses class * {

    native <methods>;

}



# 确保views的动画能够正常工作

-keepclassmembers public class * extends android.view.View {

   void set*(***);

   *** get*();

}



# 不混淆Activity里面带view参数的方法，确保XML的onclick属性能正常使用

-keepclassmembers class * extends android.app.Activity {

   public void *(android.view.View);

}
-keepclassmembers class * extends android.support.v7.app.ActionBarActivity {

   public void *(android.view.View);

}

# 不混淆枚举类型

-keepclassmembers enum * {

    public static **[] values();

    public static ** valueOf(java.lang.String);

}





# 对实现了Parcelable接口的所有类的类名不进行混淆

-keep class * implements android.os.Parcelable {

  public static final android.os.Parcelable$Creator *;

}



#不混淆Serializable的子类

# Explicitly preserve all serialization members. The Serializable interface

# is only a marker interface, so it wouldn't save them.

-keepclassmembers class * implements java.io.Serializable {

    static final long serialVersionUID;

    private static final java.io.ObjectStreamField[] serialPersistentFields;

    private void writeObject(java.io.ObjectOutputStream);

    private void readObject(java.io.ObjectInputStream);

    java.lang.Object writeReplace();

    java.lang.Object readResolve();

}



# 不混淆R.java文件

-keepclassmembers class **.R$* {

    public static <fields>;

}



# 忽略support包新版本警告

-keep class android.support.** { *; }

-dontwarn android.support.**



# 不混淆第三方jar包

#-dontshrink

#-dontoptimize



# Keep GSON stuff

-keep class sun.misc.Unsafe { *; }

-keep class com.google.gson.** { *; }

# Gson specific classes

-keep class com.google.gson.stream.** { *; }



# 不混淆ILicensingService类，单向的进程间通讯接口，这个接口的许可检查请求来自google play客户端

#-keep public class com.google.vending.licensing.ILicensingService

#-keep public class com.android.vending.licensing.ILicensingService



#appcompat

-keep public class android.support.v7.widget.** { *; }

-keep public class android.support.v7.internal.widget.** { *; }

-keep public class android.support.v7.internal.view.menu.** { *; }



-keep public class * extends android.support.v4.view.ActionProvider {

    public <init>(android.content.Context);

}



-keep class rx.** { *; }

#realm

-keep class io.realm.annotations.RealmModule

-keep @io.realm.annotations.RealmModule class *

-keep class io.realm.internal.Keep

-keep @io.realm.internal.Keep class * { *; }

-keep class io.realm.** { *; }

-dontwarn javax.**

-dontwarn io.realm.**



-keep @android.support.annotation.Keep class * { *; }

-keep class * implements android.os.Parcelable







-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.ArrayQueueField* {
long producerIndex;
long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
rx.internal.util.atomic.LinkedQueueNode consumerNode;
}







  -keep class android.support.v4.** { *; }

  -keep interface android.support.v4.** { *; }

  -keep class android.support.v7.** { *; }



# Okio

-keep class sun.misc.Unsafe { *; }

-dontwarn java.nio.file.*

-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

-dontwarn okio.**



# OkHttp

-keep class com.squareup.okhttp.** { *; }

-keep class okhttp3.** { *; }

-keep interface com.squareup.okhttp.** { *; }

-keep interface okhttp3.** { *; }

-dontwarn com.squareup.okhttp.**



#retrofit

-dontwarn retrofit2.**

-keep class retrofit2.** { *; }



#butterknife

-keep class butterknife.** { *; }

-dontwarn butterknife.internal.**

-keep class **$$ViewBinder { *; }



-keepclasseswithmembernames class * {

    @butterknife.* <fields>;

}



-keepclasseswithmembernames class * {

    @butterknife.* <methods>;

}



-dontwarn butterknife.internal.**





#ActivityRouter

-keep class com.github.mzule.activityrouter.router.** { *; }



#Glide

-keep public class * implements com.bumptech.glide.module.GlideModule

-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {

  **[] $VALUES;

  public *;

}



# for DexGuard only

#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule



#ARouter

-keep public class com.alibaba.android.arouter.**{*;}

-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}



#蒲公英

#-libraryjars libs/pgyer_sdk_2.2.jar

#-libraryjars libs/pgyer_sdk_x.x.jar
-dontwarn com.pgyersdk.**
-keep class com.pgyersdk.** { *; }





#joda

-keep class org.joda.convert.** { *; }

-keep interface org.joda.convert.** { *; }

-dontwarn org.joda.**



-keep class com.jeek.calendar.widget.** { *; }


-keep class com.sell.wine.util.** { *; }

-keep class com.sell.wine.view.** { *; }
#entity


-keep  class com.sell.wine.SellWineApplication

-keep  class com.sell.wine.ui.** { *; }

-keep  class com.sell.wine.entity.** { *; }

-keep  class com.sell.wine.framework.** { *; }

-keep class com.youth.banner.** {
    *;
 }
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keep class com.mob.**{*;}
-keep class cn.smssdk.**{*;}

#Province、City
-keepattributes InnerClasses,Signature
-keepattributes *Annotation*

-keep class cn.qqtheme.framework.entity.** { *;}

-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn com.mob.**
-dontwarn cn.sharesdk.**
-dontwarn **.R$*

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
# support-v7-appcompat
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }
-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}
# support-design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
-keep public class com.litesuits.orm.** { *; }

-keep public class io.agora.** { *; }
#-libraryjars public class io.agora.** { *; }
-dontwarn com.wq.photo
-keep public class com.wq.photo.**{*;}
-dontwarn io.agora.**
-keep class io.agora.** { *;}

-dontwarn com.tencent.**
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep class com.tencent.beaconselfupdate.** {*;}
-keep class com.tencent.hlyyb.** {*;}
-keep class com.tencent.tmapkupdatesdk.** {*;}
-keep class com.tencent.tmassistantbase.** {*;}
-keep class com.tencent.tmdownloader.** {*;}
-keep class com.tencent.tmassistantsdk.** {*;}
-keep class com.tencent.tmselfupdatesdk.** {*;}
-keep class com.tencent.yybsdk.apkpatch.** {*;}
-keep class com.tencent.assistant.sdk.remote.**{public protected private *;}
-keep public interface com.tencent.tmassistantbase.common.download.ITMAssistantDownloadClientListener{*;}
-keep class com.qq.** {*;}


#eventBus混淆
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#3D 地图 V5.0.0之后：
-keep   class com.amap.api.maps.**{*;}
-keep   class com.autonavi.**{*;}
-keep   class com.amap.api.trace.**{*;}

#定位
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

#搜索
-keep   class com.amap.api.services.**{*;}



-keep class com.jph.takephoto.** { *; }
-dontwarn com.jph.takephoto.**

-keep class com.darsh.multipleimageselect.** { *; }
-dontwarn com.darsh.multipleimageselect.**

-keep class com.soundcloud.android.crop.** { *; }
-dontwarn com.soundcloud.android.crop.**


-dontwarn sun.misc.Unsafe

-keep class com.alibaba.sdk.android.oss.** { *; }
-dontwarn okio.**
-dontwarn org.apache.commons.codec.binary.**



#PictureSelector 2.0
-keep class com.luck.picture.lib.** { *; }

-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }

 #rxjava
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#rxandroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# FastJson 混淆代码
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }
-keepattributes Signature
-keepattributes *Annotation*
-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }
-keep  class vip.hengnai.wine.SellWineApplication
-keep class vip.hengnai.wine.util.** { *; }
-keep class vip.hengnai.wine.framework.** { *; }
-keep class vip.hengnai.wine.view.** { *; }
-keep class vip.hengnai.wine.entity.** { *; }
-keep class vip.hengnai.wine.ui.** { *; }
#entity
# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule


