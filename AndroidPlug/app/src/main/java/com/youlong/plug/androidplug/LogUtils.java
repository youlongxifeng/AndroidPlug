package com.youlong.plug.androidplug;

import android.util.Log;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.utils
 * @class describe
 * @time 2018/2/8 17:07
 * @change
 * @class describe
 */

public class LogUtils {

    private static String sTag = "LogUtils";
    private static boolean sIsDebug = true;
    private static boolean sIsTrace = true;

    /**
     * 配置 LogUtil
     *
     * @param tag     默认 tag
     * @param isDebug 是否显示
     * @param isTrace 是否显示详细
     */
    public static void init(String tag, boolean isDebug, boolean isTrace) {
        if (tag != null) {
            sTag = tag;
        }
        sIsDebug = isDebug;
        sIsTrace = isTrace;

    }

    /**
     * @param msg
     */
    public static void i(String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            if (sIsTrace) {
                sb.append("[");
                if (list.length > 3) {
                    sb.append(list[3].getFileName() + "_" + list[3].getMethodName() + "_" + list[3].getLineNumber() + "_");
                }
                if (list.length > 4) {
                    sb.append(list[4].getFileName() + "_" + list[4].getMethodName() + "_" + list[4].getLineNumber() + "_");
                }
                if (list.length > 5) {
                    sb.append(list[5].getFileName() + "_" + list[5].getMethodName() + "_" + list[5].getLineNumber());
                }
                sb.append("] ");
            }

            Log.i(sTag, sb.toString() + msg);
        }
    }

    public static void i(String tag, String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (list.length > 3) {
                sb.append(list[3].getFileName() + "#" + list[3].getMethodName() + "(" + list[3].getLineNumber() + ")_");
            }
            if (list.length > 4) {
                sb.append(list[4].getFileName() + "#" + list[4].getMethodName() + "(" + list[4].getLineNumber() + ")_");
            }
            if (list.length > 5) {
                sb.append(list[5].getFileName() + "#" + list[5].getMethodName() + "(" + list[5].getLineNumber() + ")");
            }
            sb.append("]");
            Log.i(sTag + "_" + tag, sb.toString() + msg);
        }
    }

    public static void w(String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            if (sIsTrace) {
                sb.append("[");
                if (list.length > 3) {
                    sb.append(list[3].getFileName() + "_" + list[3].getMethodName() + "_" + list[3].getLineNumber() + "_");
                }
                if (list.length > 4) {
                    sb.append(list[4].getFileName() + "_" + list[4].getMethodName() + "_" + list[4].getLineNumber() + "_");
                }
                if (list.length > 5) {
                    sb.append(list[5].getFileName() + "_" + list[5].getMethodName() + "_" + list[5].getLineNumber());
                }
                sb.append("] ");
            }

            Log.w(sTag, sb.toString() + msg);
        }
    }

    public static void w(String tag, String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (list.length > 3) {
                sb.append(list[3].getFileName() + "#" + list[3].getMethodName() + "(" + list[3].getLineNumber() + ")_");
            }
            if (list.length > 4) {
                sb.append(list[4].getFileName() + "#" + list[4].getMethodName() + "(" + list[4].getLineNumber() + ")_");
            }
            if (list.length > 5) {
                sb.append(list[5].getFileName() + "#" + list[5].getMethodName() + "(" + list[5].getLineNumber() + ")");
            }
            sb.append("]");
            Log.w(sTag + "_" + tag, sb.toString() + msg);
        }
    }

    public static void e(String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            if (sIsTrace) {
                sb.append("[");
                if (list.length > 3) {
                    sb.append(list[3].getFileName() + "_" + list[3].getMethodName() + "_" + list[3].getLineNumber() + "_");
                }
                if (list.length > 4) {
                    sb.append(list[4].getFileName() + "_" + list[4].getMethodName() + "_" + list[4].getLineNumber() + "_");
                }
                if (list.length > 5) {
                    sb.append(list[5].getFileName() + "_" + list[5].getMethodName() + "_" + list[5].getLineNumber());
                }
                sb.append("] ");
            }
            Log.e(sTag, sb.toString() + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (list.length > 3) {
                sb.append(list[3].getFileName() + "#" + list[3].getMethodName() + "(" + list[3].getLineNumber() + ")_");
            }
            if (list.length > 4) {
                sb.append(list[4].getFileName() + "#" + list[4].getMethodName() + "(" + list[4].getLineNumber() + ")_");
            }
            if (list.length > 5) {
                sb.append(list[5].getFileName() + "#" + list[5].getMethodName() + "(" + list[5].getLineNumber() + ")");
            }
            sb.append("]");
            Log.e(sTag + "_" + tag, sb.toString() + msg);
        }
    }


    public static void print(String msg) {
        StackTraceElement[] list = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();
        if (sIsTrace) {
            sb.append("[");
            if (list.length > 3) {
                sb.append(list[3].getFileName() + "#" + list[3].getMethodName() + "(" + list[3].getLineNumber() + ")");
            }
            if (list.length > 4) {
                sb.append(list[4].getFileName() + "#" + list[4].getMethodName() + "(" + list[4].getLineNumber() + ")");
            }
            if (list.length > 5) {
                sb.append(list[5].getFileName() + "#" + list[5].getMethodName() + "(" + list[5].getLineNumber() + ")");
            }
            sb.append("] ");
        }

        Log.w(sTag + "_print", sb.toString() + msg);
    }

    public static void i(String tag, String msg, boolean isDebug) {
        if (isDebug && sIsDebug) {
            Log.i(tag, msg);
        }
    }

    public static void saveLog(String tag, String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (list.length > 3) {
                sb.append(list[3].getFileName() + "#" + list[3].getMethodName() + "(" + list[3].getLineNumber() + ")_");
            }
            if (list.length > 4) {
                sb.append(list[4].getFileName() + "#" + list[4].getMethodName() + "(" + list[4].getLineNumber() + ")_");
            }
            if (list.length > 5) {
                sb.append(list[5].getFileName() + "#" + list[5].getMethodName() + "(" + list[5].getLineNumber() + ")");
            }
            sb.append("]");
            String line = sTag + "_" + tag + "/t" + sb.toString() + msg;
            //   createFolderIfNotExist("log");
            //   writeFileToSD(Environment.getExternalStorageDirectory().getPath() + "/doorduad/log/", line);
        }
    }
}
