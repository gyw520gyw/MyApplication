package demo.gyw.com.myapplication;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.txusballesteros.widgets.FitChart;
import com.txusballesteros.widgets.FitChartValue;

import java.util.ArrayList;
import java.util.Collection;

import demo.gyw.com.myapplication.R;

public class FitChartActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_chart);


        final FitChart fitChart = (FitChart) findViewById(R.id.fitChart);

        TextView mNumTv = (TextView)findViewById(R.id.tv_total_num);

        fitChart.setMinValue(0f);
        fitChart.setMaxValue(100f);

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources resources = getResources();
                Collection<FitChartValue> values = new ArrayList<>();
                values.add(new FitChartValue(30f, resources.getColor(R.color.chart_value_1)));
                values.add(new FitChartValue(20f, resources.getColor(R.color.chart_value_2)));
                values.add(new FitChartValue(15f, resources.getColor(R.color.chart_value_3)));
                values.add(new FitChartValue(20f, resources.getColor(R.color.chart_value_4)));
                values.add(new FitChartValue(10f, resources.getColor(R.color.chart_value_5)));
                fitChart.setValues(values);
            }
        });
    }

}
