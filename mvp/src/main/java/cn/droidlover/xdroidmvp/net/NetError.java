package cn.droidlover.xdroidmvp.net;

/**
 * Created by wanglei on 2016/12/24.
 */

public class NetError extends Exception {
    private Throwable exception;
    private int type = NoConnectError;
    private String ErrorMsg = "";

    public static final int ParseError = 0;   //数据解析异常
    public static final int NoConnectError = 1;   //无连接异常
    public static final int AuthError = 2;   //用户验证异常
    public static final int NoDataError = 3;   //无数据返回异常
    public static final int BusinessError = 4;   //业务异常
    public static final int OtherError = 5;   //其他异常

    public NetError(Throwable exception, int type) {
        this.exception = exception;
        this.type = type;
    }

    public NetError(String detailMessage, int type) {
        super(detailMessage);
        this.ErrorMsg = detailMessage;
        this.type = type;
    }

    @Override
    public String getMessage() {
        if (exception != null) {
            String errorMsg = "";
            switch (type) {
                case ParseError:
                    errorMsg = "数据解析异常";
                    break;

                case NoConnectError:
                    errorMsg = "网络无连接";
                    break;

                case AuthError:
                    errorMsg = ErrorMsg;
                    break;

                case NoDataError:
                    errorMsg = ErrorMsg;
                    break;

                case BusinessError:
                    errorMsg = "业务数据异常";
                    break;

                case OtherError:
                    errorMsg = exception.getMessage();
                    break;
            }
            return errorMsg;
        }
        return super.getMessage();
    }

    public int getType() {
        return type;
    }
}
