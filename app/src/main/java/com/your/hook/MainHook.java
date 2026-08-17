package com.your.hook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.vphonegaga.titan")) return;

        // Hook User.isVip()
        XposedHelpers.findAndHookMethod(
            "com.vphonegaga.titan.user.User",
            lpparam.classLoader,
            "isVip",
            new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) {
                    return true;
                }
            }
        );

        // Hook User.getUid()
        XposedHelpers.findAndHookMethod(
            "com.vphonegaga.titan.user.User",
            lpparam.classLoader,
            "getUid",
            new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) {
                    return "88888888";
                }
            }
        );

        // Hook UserInfo.isLogin()
        XposedHelpers.findAndHookMethod(
            "com.vphonegaga.titan.user.UserInfo",
            lpparam.classLoader,
            "isLogin",
            new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) {
                    return true;
                }
            }
        );

        // Hook UserMgr.isLogin()
        XposedHelpers.findAndHookMethod(
            "com.vphonegaga.titan.user.UserMgr",
            lpparam.classLoader,
            "isLogin",
            new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) {
                    return true;
                }
            }
        );

        // Hook UserMgr.getUser() - 防止空指针
        XposedHelpers.findAndHookMethod(
            "com.vphonegaga.titan.user.UserMgr",
            lpparam.classLoader,
            "getUser",
            new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) {
                    // 返回一个新建的User对象
                    Object user = XposedHelpers.newInstance(
                        "com.vphonegaga.titan.user.User"
                    );
                    // 设置Vip为true
                    XposedHelpers.setBooleanField(user, "isVip", true);
                    XposedHelpers.setObjectField(user, "uid", "88888888");
                    XposedHelpers.setObjectField(user, "nickname", "VIP_User");
                    return user;
                }
            }
        );
    }
}
