package demo.gyw.com.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.taobao.weex.IWXRenderListener
import com.taobao.weex.WXSDKInstance
import com.taobao.weex.common.WXRenderStrategy
import com.taobao.weex.WXEnvironment.getApplication
import com.taobao.weex.WXSDKEngine
import com.taobao.weex.WXEnvironment




class CommonWeexActivity : AppCompatActivity(), IWXRenderListener {

    private lateinit var mWXSDKInstance: WXSDKInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_weex)

        initWeexData()
    }

    private fun initWeexData() {
        mWXSDKInstance = WXSDKInstance(this)
        mWXSDKInstance.registerRenderListener(this)
        /**
         * bundleUrl source http://dotwe.org/vue/38e202c16bdfefbdb88a8754f975454c
         */
        var pageName = "WXSample"
        var bundleUrl = "http://dotwe.org/raw/dist/38e202c16bdfefbdb88a8754f975454c.bundle.wx"
//        var bundleUrl = "http://192.168.3.248/HLOHbgj/HLOneAPI-Weex/raw/master/TestDemo/dist/hloneapi-test.weex.js?showtype=weex"
        mWXSDKInstance.renderByUrl(pageName, bundleUrl, null, null, WXRenderStrategy.APPEND_ASYNC)


    }


    override fun onViewCreated(instance: WXSDKInstance?, view: View?) {
        setContentView(view)
    }

    override fun onRefreshSuccess(instance: WXSDKInstance?, width: Int, height: Int) {
    }

    override fun onException(instance: WXSDKInstance?, errCode: String?, msg: String?) {
    }

    override fun onRenderSuccess(instance: WXSDKInstance?, width: Int, height: Int) {
    }

    override fun onResume() {
        super.onResume()
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityResume()
        }
    }

    override fun onPause() {
        super.onPause()
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityPause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy()
        }
    }

}
