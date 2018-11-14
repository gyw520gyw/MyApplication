package demo.gyw.com.myapplication;

import android.app.Activity;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class ReactiveNetworkActivity extends Activity {

    private TextView tvConnectivityStatus;
    private ListView lvAccessPoints;
    private ReactiveNetwork reactiveNetwork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reactive_network);

        tvConnectivityStatus = (TextView) findViewById(R.id.connectivity_status);
        lvAccessPoints = (ListView) findViewById(R.id.access_points);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reactiveNetwork = new ReactiveNetwork();

        reactiveNetwork.observeConnectivity(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<ConnectivityStatus>() {
                    @Override
                    public void call(ConnectivityStatus connectivityStatus) {
                        tvConnectivityStatus.setText(connectivityStatus.toString());
                    }
                });

        reactiveNetwork.observeWifiAccessPoints(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<List<ScanResult>>() {
                    @Override
                    public void call(List<ScanResult> scanResults) {
                        displayAccessPoints(scanResults);
                    }
                });
    }


    private void displayAccessPoints(List<ScanResult> scanResults) {
        List<String> wifiScanResults = new ArrayList<>();

        for (ScanResult scanResult : scanResults) {
            wifiScanResults.add(scanResult.SSID);
        }

        lvAccessPoints.setAdapter(
                new ArrayAdapter<>(ReactiveNetworkActivity.this, android.R.layout.simple_list_item_1,
                        wifiScanResults));

        String message = getString(R.string.wifi_signal_strength_changed);
        Toast.makeText(ReactiveNetworkActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
