package com.seatrend.utilsdk.manager.ffmpeg;

import io.microshow.rxffmpeg.RxFFmpegInvoke;
import io.microshow.rxffmpeg.RxFFmpegSubscriber;

/**
 * Created by ly on 2021/1/8 13:48
 *
 * //e: Supertypes of the following classes cannot be resolved. Please make sure you have the required dependencies in the classpath:
 * 不能用  找不到 RxFFmpegSubscriber
 */
@Deprecated
public class FFMPegManager {
    private static FFMPegManager incetance;

    public static FFMPegManager getIncetance(){
        if(incetance == null){
            synchronized (FFMPegManager.class){
                if (incetance ==null){
                    incetance = new FFMPegManager();
                }
            }
        }
        return incetance;
    }

    /**
     * ffmpeg -ss 00:00:03 -t 3 -i Test.mov -s 640x360 -r  15  dongtu.gif
     *
     * 解释：
     *
     * 1、ffmpeg 是你刚才安装的程序；
     *
     * 2、-ss 00:00:03 表示从第 00 分钟 03 秒开始制作 GIF，如果你想从第 9 秒开始，则输入 -ss 00:00:09，或者 -ss 9，支持小数点，所以也可以输入 -ss 00:00:11.3，或者 -ss 34.6 之类的，如果不加该命令，则从 0 秒开始制作；
     *
     * 3、-t 3 表示把持续 3 秒的视频转换为 GIF，你可以把它改为其他数字，例如 1.5，7 等等，时间越长，GIF 体积越大，如果不加该命令，则把整个视频转为 GIF；
     *
     * 4、-i 表示 invert 的意思吧，转换；
     *
     * 5、Test.mov 就是你要转换的视频，名称最好不要有中文，不要留空格，支持多种视频格式；
     *
     * 6、-s 640x360 是 GIF 的分辨率，视频分辨率可能是 1080p，但你制作的 GIF 可以转为 720p 等，允许自定义，分辨率越高体积越大，如果不加该命令，则保持分辨率不变；
     *
     * 7、-r “15” 表示帧率，网上下载的视频帧率通常为 24，设为 15 效果挺好了，帧率越高体积越大，如果不加该命令，则保持帧率不变；
     *
     * 8、dongtu.gif：就是你要输出的文件，你也可以把它命名为 hello.gif 等等。
     *
     *图片合并为动图：
     * ffmpeg -threads 2 -r 2 -i %d.jpg 11.gif -y
     * -threads 2：2 以两个线程进行运行， 加快处理的速度。
     * -y 对输出文件进行覆盖
     * -r 2 fps设置为2帧/秒（不同位置有不同含义)
     * -i  %d.jpg 合并的图片文件，图片文件为 1.jpg 2.jpg …
     *
     * @param commandStr  执行的命令 视频转GIF
     * @param rs
     */
    public void runCommand(String commandStr, MyRxFFmpegSubscriber rs){
        String[] commands = commandStr.split(" ");
        RxFFmpegInvoke.getInstance()
                .runCommandRxJava(commands)
                .subscribe(rs);
    }
}
