#include <jni.h>
#include <string.h>
#include <string>
#include <android/log.h>
using namespace std;

//#define vername "(Ljava/lang/String;)V"  //using as signature of string
#define  LOG_TAG    "someTag"
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

int isOk(JNIEnv *env, jstring email, jstring password){

    string emailstring = env->GetStringUTFChars(email, nullptr);
    string passwordstring = env->GetStringUTFChars(password,nullptr);
    string constemail = "fahimfahadleon6474@gmail.com";
    string constpassword = "64742812";
    int emailverification = emailstring.compare(constemail);
    int passwordverification = passwordstring.compare(constpassword);
// use your string
    LOGE("%s",emailstring.c_str());
    LOGE("%s",passwordstring.c_str());

    return emailverification == 0 && passwordverification == 0 ? 1 : 0;
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_logintest_NativeClass_setVerificationResponseFromJNI(JNIEnv *env,jobject jobject,jstring email,jstring password){
    jclass javaclass=env->FindClass("com/example/logintest/NativeClass");
    int isValid = isOk(env,email,password);
    jmethodID classmethod = env->GetMethodID(javaclass,"setResponse","(Ljava/lang/String;)V");
    string isvalid = to_string(isValid);
    env->CallVoidMethod(jobject,classmethod,env->NewStringUTF(isvalid.c_str()));
    return 0;
}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_logintest_NativeClass_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
        string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

