package demo.gyw.com.myapplication.retrofit;

import java.io.Serializable;
import java.util.List;

public class AppDetailBean implements Serializable{


	/**
	 * status : 0
	 * res : {"versionid":1616,"packagename":"com.bai.doctor","app_id":"A000000000361","name":"易复诊","ldpi_icon_url":"http://121.40.92.223:8080/appstore/app/com.bai.doctor/2.6.4_ldpi.icon","icon_url":"http://121.40.92.223:8080/appstore/app/com.bai.doctor/com.bai.doctor.2.6.4.icon","app_size":"17588090","short_description":"最实用的医疗APP","app_detail":"向患者提供医疗咨询服务并享受其他在线医疗信息服务，帮助医生实现价值的平台。\n1.面向诊后出院的患者，建立源于信任的医患关系。\n2.沟通时可查看患者病史，使沟通有据可依。\n3.多点执业，积分系统，保障医生合理收益。\n4.提供各种专业的小工具，提高医生的管理效率。\n","author_name":"青岛百洋健康药房连锁有限公司","app_version":"2.6.4","version_code":"34","update_time":"2015-12-03","istest":"1","type_id":"T00024","type_name":"病患管理","app_label":["病患管理"],"apple_url":"","appurl":"http://121.40.92.223:8080/appstore/app/com.bai.doctor/com.bai.doctor.2.6.4.apk","downloadnum":"3026","rating":"67%","pic_list":["http://121.40.92.223:8080/appstore/app/com.bai.doctor//2015070213442740.jpg","http://121.40.92.223:8080/appstore/app/com.bai.doctor//20150702134427821.jpg","http://121.40.92.223:8080/appstore/app/com.bai.doctor//20150702134427642.jpg","http://121.40.92.223:8080/appstore/app/com.bai.doctor//20150702134427143.jpg","http://121.40.92.223:8080/appstore/app/com.bai.doctor//20150702134427234.jpg"]}
	 */

	private int status;
	/**
	 * versionid : 1616
	 * packagename : com.bai.doctor
	 * app_id : A000000000361
	 * name : 易复诊
	 * ldpi_icon_url : http://121.40.92.223:8080/appstore/app/com.bai.doctor/2.6.4_ldpi.icon
	 * icon_url : http://121.40.92.223:8080/appstore/app/com.bai.doctor/com.bai.doctor.2.6.4.icon
	 * app_size : 17588090
	 * short_description : 最实用的医疗APP
	 * app_detail : 向患者提供医疗咨询服务并享受其他在线医疗信息服务，帮助医生实现价值的平台。
	 1.面向诊后出院的患者，建立源于信任的医患关系。
	 2.沟通时可查看患者病史，使沟通有据可依。
	 3.多点执业，积分系统，保障医生合理收益。
	 4.提供各种专业的小工具，提高医生的管理效率。

	 * author_name : 青岛百洋健康药房连锁有限公司
	 * app_version : 2.6.4
	 * version_code : 34
	 * update_time : 2015-12-03
	 * istest : 1
	 * type_id : T00024
	 * type_name : 病患管理
	 * app_label : ["病患管理"]
	 * apple_url :
	 * appurl : http://121.40.92.223:8080/appstore/app/com.bai.doctor/com.bai.doctor.2.6.4.apk
	 * downloadnum : 3026
	 * rating : 67%
	 * pic_list : ["http://121.40.92.223:8080/appstore/app/com.bai.doctor//2015070213442740.jpg","http://121.40.92.223:8080/appstore/app/com.bai.doctor//20150702134427821.jpg","http://121.40.92.223:8080/appstore/app/com.bai.doctor//20150702134427642.jpg","http://121.40.92.223:8080/appstore/app/com.bai.doctor//20150702134427143.jpg","http://121.40.92.223:8080/appstore/app/com.bai.doctor//20150702134427234.jpg"]
	 */

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
		private int versionid;
		private String packagename;
		private String app_id;
		private String name;
		private String ldpi_icon_url;
		private String icon_url;
		private String app_size;
		private String short_description;
		private String app_detail;
		private String author_name;
		private String app_version;
		private String version_code;
		private String update_time;
		private String istest;
		private String type_id;
		private String type_name;
		private String apple_url;
		private String appurl;
		private String downloadnum;
		private String rating;
		private List<String> app_label;
		private List<String> pic_list;

		public void setVersionid(int versionid) {
			this.versionid = versionid;
		}

		public void setPackagename(String packagename) {
			this.packagename = packagename;
		}

		public void setApp_id(String app_id) {
			this.app_id = app_id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setLdpi_icon_url(String ldpi_icon_url) {
			this.ldpi_icon_url = ldpi_icon_url;
		}

		public void setIcon_url(String icon_url) {
			this.icon_url = icon_url;
		}

		public void setApp_size(String app_size) {
			this.app_size = app_size;
		}

		public void setShort_description(String short_description) {
			this.short_description = short_description;
		}

		public void setApp_detail(String app_detail) {
			this.app_detail = app_detail;
		}

		public void setAuthor_name(String author_name) {
			this.author_name = author_name;
		}

		public void setApp_version(String app_version) {
			this.app_version = app_version;
		}

		public void setVersion_code(String version_code) {
			this.version_code = version_code;
		}

		public void setUpdate_time(String update_time) {
			this.update_time = update_time;
		}

		public void setIstest(String istest) {
			this.istest = istest;
		}

		public void setType_id(String type_id) {
			this.type_id = type_id;
		}

		public void setType_name(String type_name) {
			this.type_name = type_name;
		}

		public void setApple_url(String apple_url) {
			this.apple_url = apple_url;
		}

		public void setAppurl(String appurl) {
			this.appurl = appurl;
		}

		public void setDownloadnum(String downloadnum) {
			this.downloadnum = downloadnum;
		}

		public void setRating(String rating) {
			this.rating = rating;
		}

		public void setApp_label(List<String> app_label) {
			this.app_label = app_label;
		}

		public void setPic_list(List<String> pic_list) {
			this.pic_list = pic_list;
		}

		public int getVersionid() {
			return versionid;
		}

		public String getPackagename() {
			return packagename;
		}

		public String getApp_id() {
			return app_id;
		}

		public String getName() {
			return name;
		}

		public String getLdpi_icon_url() {
			return ldpi_icon_url;
		}

		public String getIcon_url() {
			return icon_url;
		}

		public String getApp_size() {
			return app_size;
		}

		public String getShort_description() {
			return short_description;
		}

		public String getApp_detail() {
			return app_detail;
		}

		public String getAuthor_name() {
			return author_name;
		}

		public String getApp_version() {
			return app_version;
		}

		public String getVersion_code() {
			return version_code;
		}

		public String getUpdate_time() {
			return update_time;
		}

		public String getIstest() {
			return istest;
		}

		public String getType_id() {
			return type_id;
		}

		public String getType_name() {
			return type_name;
		}

		public String getApple_url() {
			return apple_url;
		}

		public String getAppurl() {
			return appurl;
		}

		public String getDownloadnum() {
			return downloadnum;
		}

		public String getRating() {
			return rating;
		}

		public List<String> getApp_label() {
			return app_label;
		}

		public List<String> getPic_list() {
			return pic_list;
		}

		@Override
		public String toString() {
			return "ResEntity{" +
					"versionid=" + versionid +
					", packagename='" + packagename + '\'' +
					", app_id='" + app_id + '\'' +
					", name='" + name + '\'' +
					", ldpi_icon_url='" + ldpi_icon_url + '\'' +
					", icon_url='" + icon_url + '\'' +
					", app_size='" + app_size + '\'' +
					", short_description='" + short_description + '\'' +
					", app_detail='" + app_detail + '\'' +
					", author_name='" + author_name + '\'' +
					", app_version='" + app_version + '\'' +
					", version_code='" + version_code + '\'' +
					", update_time='" + update_time + '\'' +
					", istest='" + istest + '\'' +
					", type_id='" + type_id + '\'' +
					", type_name='" + type_name + '\'' +
					", apple_url='" + apple_url + '\'' +
					", appurl='" + appurl + '\'' +
					", downloadnum='" + downloadnum + '\'' +
					", rating='" + rating + '\'' +
					", app_label=" + app_label +
					", pic_list=" + pic_list +
					'}';
		}
	}

	@Override
	public String toString() {
		return "AppDetailBean{" +
				"status=" + status +
				", res=" + res +
				'}';
	}
}
