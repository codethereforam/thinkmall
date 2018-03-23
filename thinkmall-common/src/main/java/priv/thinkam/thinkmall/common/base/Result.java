package priv.thinkam.thinkmall.common.base;

/**
 * 结果
 *
 * @author thinkam
 * @date 2018/03/20
 */
public class Result {
    private boolean success;
    private Object data;

    private Result(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public static Result create(boolean success, Object data) {
        return new Result(success, data);
    }

    public static Result createWithoutData(boolean success) {
        return new Result(success, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
