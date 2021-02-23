package com.seatrend.utilsdk.utils;

import android.content.Context;
import android.graphics.*;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by seatrend on 2018/8/28.
 */

public class BitmapUtils {

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.NO_WRAP);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 10;
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }

    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 300) { //循环判断如果压缩后图片是否大于300kb,大于继续压缩
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context) {
        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        int h = (int) (newHeight * densityMultiplier);
        int w = (int) (h * photo.getWidth() / ((double) photo.getHeight()));

        photo = Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }

    /**
     * 通过bitmap 转为文件
     * @param context  上下文
     * @param bitmap bitmap对象
     * @param fileName 把bitmap转为文件的名字  123.jpg   fileName = 123
     * @param parentfilePath 在sdcard 目录下的 文件件名  parentfilePath =/123  整体的file 名 [/storage/emulated/0/Android/data/yourPackageName/]123
     * @return 绝对的文件path
     */
    public static String saveBitmap(Context context,Bitmap bitmap, String fileName, String parentfilePath) {

        File catalog = new File(context.getExternalFilesDir(null).toString()+parentfilePath);
        if (!catalog.exists()) {
            catalog.mkdirs();
        }

        File file = new File(catalog, fileName);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            return file.getPath();
        } catch (Exception e) {
            return null;
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 裁剪专用
     *
     * @param bitmap   获取的bitmap
     * @param filePath c存储的bitmap文件路径
     */
    public static void saveBitmapToFile(Bitmap bitmap, String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } catch (Exception e) {
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Bitmap getBitMap(String filePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        return bitmap;
    }

    public static Bitmap getSmallBitmap(String filePath) {
        Bitmap bitmap;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inPreferredConfig = Bitmap.Config.ARGB_8888; ///为了兼容人脸比对 这里选择用 ARGB_8888 格式
        options.inSampleSize = calculateInSampleSize(options, 1920, 1080);
        options.inJustDecodeBounds = false;

        try {
            bitmap = BitmapFactory.decodeFile(filePath, options);
        } catch (Exception e) {
            options.inSampleSize = calculateInSampleSize(options, 480, 320);
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(filePath, options);
        }

        return bitmap;
    }


    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio > widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap getRoundCornerImage(Bitmap bitmap, int roundPixels) {
        /**创建一个和原始图片一样大小位图*/
        Bitmap roundConcerImage = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        /**创建带有位图roundConcerImage的画布*/
        Canvas canvas = new Canvas(roundConcerImage);
        /**创建画笔  */
        Paint paint = new Paint();
        /**创建一个和原始图片一样大小的矩形*/
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        /**去锯齿*/
        paint.setAntiAlias(true);
        /**画一个和原始图片一样大小的圆角矩形*/
        canvas.drawRoundRect(rectF, roundPixels, roundPixels, paint);
        /**设置相交模式  */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**把图片画到矩形去  */
        canvas.drawBitmap(bitmap, rect, rectF, paint);


        /**引时圆角区域为透明，给其填充白色  */
        paint.setColor(Color.WHITE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));

        return roundConcerImage;
    }

    public static byte[] getNV21(int inputWidth, int inputHeight, Bitmap scaled) {
        if (scaled == null) {
            return new byte[0];
        }

        int[] argb = new int[inputWidth * inputHeight];

        scaled.getPixels(argb, 0, inputWidth, 0, 0, inputWidth, inputHeight);

        byte[] yuv = new byte[inputWidth * inputHeight * 3 / 2];
        encodeYUV420SP(yuv, argb, inputWidth, inputHeight);

        scaled.recycle();

        return yuv;
    }

    private static void encodeYUV420SP(byte[] yuv420sp, int[] argb, int width, int height) {
        final int frameSize = width * height;
        int yIndex = 0;
        int uvIndex = frameSize;
        int a, R, G, B, Y, U, V;
        int index = 0;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                a = (argb[index] & 0xff000000) >> 24; // a is not used obviously
                R = (argb[index] & 0xff0000) >> 16;
                G = (argb[index] & 0xff00) >> 8;
                B = (argb[index] & 0xff) >> 0;
                // well known RGB to YUV algorithm
                Y = ((66 * R + 129 * G + 25 * B + 128) >> 8) + 16;
                U = ((-38 * R - 74 * G + 112 * B + 128) >> 8) + 128;
                V = ((112 * R - 94 * G - 18 * B + 128) >> 8) + 128;

                // NV21 has a plane of Y and interleaved planes of VU each sampled by a factor of 2
                //    meaning for every 4 Y pixels there are 1 V and 1 U.  Note the sampling is every other
                //    pixel AND every other scanline.
                yuv420sp[yIndex++] = (byte) ((Y < 0) ? 0 : ((Y > 255) ? 255 : Y));
                if (j % 2 == 0 && index % 2 == 0) {
                    yuv420sp[uvIndex++] = (byte) ((V < 0) ? 0 : ((V > 255) ? 255 : V));
                    yuv420sp[uvIndex++] = (byte) ((U < 0) ? 0 : ((U > 255) ? 255 : U));
                }
                index++;
            }
        }
    }

    /**
     * 文件转base64字符串
     * @param file
     * @return
     */
    public static String fileToBase64(File file) {
        String base64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            int length = in.read(bytes);
            base64 = Base64.encodeToString(bytes, 0, length, Base64.DEFAULT);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return base64;
    }

    /**
     * base64字符串转文件
     * @param base64
     * @param fileName  文件的绝对目录
     * @return
     */
    public static File base64ToFile(String base64,String fileName ) {
        File file = null;
        FileOutputStream out = null;
        try {
            // 解码，然后将字节转换为文件
            file = new File(fileName);
            if (!file.exists())
                file.createNewFile();
            byte[] bytes = Base64.decode(base64, Base64.DEFAULT);// 将字符串转换为byte数组
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            out = new FileOutputStream(file);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); // 文件写操作
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (out!= null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }



    public static int getBitmapWithRightRotation(String path) {
//        android.hardware.Camera.CameraInfo info =
//                new android.hardware.Camera.CameraInfo();
//        android.hardware.Camera.getCameraInfo(0, info);
//        int rotation = activity.getWindowManager().getDefaultDisplay()
//                .getRotation();
//        int degrees = 0;
//        switch (rotation) {
//            case Surface.ROTATION_0: degrees = 0; break;
//            case Surface.ROTATION_90: degrees = 90; break;
//            case Surface.ROTATION_180: degrees = 180; break;
//            case Surface.ROTATION_270: degrees = 270; break;
//        }

        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }


    /**
     * 通过url 获得bitmap
     * @param url
     * @return bitmap
     */
    public static Bitmap getBitmapByUrl(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }
}
