package demo.gyw.com.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/11/25.
 */
public class Test {

    /**
     * status : 0
     * res : {"typelist":[{"en_code":"mall","name":"商城","typeid":"1"},{"en_code":"health","name":"健康","typeid":"2"},{"en_code":"nutrition","name":"营养","typeid":"3"},{"en_code":"software","name":"软件","typeid":"4"},{"en_code":"video","name":"视频","typeid":"5"}]}
     */

    private int status;
    private ResEntity res;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setRes(ResEntity res) {
        this.res = res;
    }

    public int getStatus() {
        return status;
    }

    public ResEntity getRes() {
        return res;
    }

    public static class ResEntity {
        /**
         * en_code : mall
         * name : 商城
         * typeid : 1
         */

        private List<TypelistEntity> typelist;

        public void setTypelist(List<TypelistEntity> typelist) {
            this.typelist = typelist;
        }

        public List<TypelistEntity> getTypelist() {
            return typelist;
        }

        public static class TypelistEntity {
            private String en_code;
            private String name;
            private String typeid;

            public void setEn_code(String en_code) {
                this.en_code = en_code;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setTypeid(String typeid) {
                this.typeid = typeid;
            }

            public String getEn_code() {
                return en_code;
            }

            public String getName() {
                return name;
            }

            public String getTypeid() {
                return typeid;
            }
        }
    }
}