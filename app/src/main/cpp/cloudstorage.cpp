#include <jni.h>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("cloudstorage");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("cloudstorage")
//      }
//    }
extern "C"
JNIEXPORT jstring JNICALL
Java_com_sk_cloudstorage_constants_Constant_SERVER_1URL(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("输入服务器地址");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_sk_cloudstorage_constants_Constant_EncryptKey(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("输入密钥");
}