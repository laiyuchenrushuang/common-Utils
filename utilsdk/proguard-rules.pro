
-optimizationpasses 5
-useuniqueclassmembernames
-allowaccessmodification
-keepattributes InnerClasses,Signature,EnclosingMethod
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
-ignorewarnings
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

-keep class android.support.** {*;}
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep public class * extends android.app.Fragment

#entity
-keep public class  com.example.romance.entity.** {*;}
-keep public class  com.google.code.gson.** {*;}
-keep public class  android.device.** {*;}

-dontwarn android.support.annotation.Keep
# 保留类内部使用@keep注解的成员变量
-keep @android.support.annotation.Keep class **{
@android.support.annotation.Keep <fields>;
}

# natvie 方法不混淆
-keepclasseswithmembernames class * {
 native <methods>;
 }

# 海康威视视频取流播放相关库的混淆配置
-keep class org.MediaPlayer.PlayM4.** {*;}
-keep class com.hikvision.netsdk.** {*;}
-keep class com.hikvision.audio.** {*;}
-keep class hik.common.isms.hpsclient.** {*;}
-keep class com.hikvision.open.hikvideoplayer.** {*;}

#greendao
-keep class com.greendao.gen.**{*;}

#Androidx
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**
