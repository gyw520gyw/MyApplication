package demo.gyw.com.myapplication.retrofit;

/**
 * Created by Administrator on 2016/1/5.
 */
public class ErrorInfo {


    /**
     * status : 103
     * res : 应用app_id不能为空
     */

    private int status;
    private String res;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public int getStatus() {
        return status;
    }

    public String getRes() {
        return res;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "status=" + status +
                ", res='" + res + '\'' +
                '}';
    }
}
