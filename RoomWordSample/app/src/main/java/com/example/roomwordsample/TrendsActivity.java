package com.example.roomwordsample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class TrendsActivity extends AppCompatActivity {

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);

        lineChart = (LineChart) findViewById(R.id.line_chart);

        ArrayList<String> x_axis = new ArrayList<>();
        ArrayList<Entry> y_axis_sin = new ArrayList<>();
        ArrayList<Entry> y_axis_cos = new ArrayList<>();

        double x = 0;
        int numDataPoints = 100;

        for (int i = 0; i < numDataPoints; i++) {
            float sinFunction = Float.parseFloat(String.valueOf(Math.sin(x)));
            float cosFunction = Float.parseFloat(String.valueOf(Math.cos(x)));
            x += 0.1;

            x_axis.add(i, String.valueOf(x));
            y_axis_sin.add(new Entry(sinFunction, i));
            y_axis_cos.add(new Entry(cosFunction, i));
        }

        String[] x_axis_cast = new String[x_axis.size()];
        for (int i = 0; i < x_axis.size(); i++) {
            x_axis_cast[i] = x_axis.get(i).toString();
        }

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineData x_final = new LineData(lineDataSets);

        LineDataSet lineDataSet1 = new LineDataSet(y_axis_cos, "SpO2");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);

        LineDataSet lineDataSet2 = new LineDataSet(y_axis_sin, "HR");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.RED);

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);

        lineChart.setData(new LineData(lineDataSets));
        lineChart.invalidate();
        lineChart.setVisibleXRangeMaximum(65f);
        lineChart.getAxisLeft().setInverted(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
    }

}