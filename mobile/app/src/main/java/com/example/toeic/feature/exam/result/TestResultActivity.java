package com.example.toeic.feature.exam.result;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.config.ApplicationConfig;
import com.example.toeic.data.model.Part;
import com.example.toeic.data.model.Result;
import com.example.toeic.ultis.chart.RadarMarkerView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.toeic.ultis.Constraints.EMPTY_STRING;
import static com.example.toeic.ultis.Constraints.RESULT;

public class TestResultActivity extends AppCompatActivity {

    @BindView(R2.id.chart1)
    BarChart chart;

    @BindView(R2.id.radarChart)
    RadarChart radarChart;

    Result currentResult;
    Result oldResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_test_result);
        ButterKnife.bind(this);
        getData();
        getOldResult();
        saveNewResult();
        setUpChart();
        setUpRadarChart();
    }

    private void getOldResult() {
        oldResult = ApplicationConfig.getInstance().getOldResult();
    }

    private void saveNewResult() {
        ApplicationConfig.getInstance().saveResult(currentResult);
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            currentResult = (Result) intent.getSerializableExtra(RESULT);
        }
    }

    private void setUpChart() {

        chart = findViewById(R.id.chart1);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        chart.setDrawValueAboveBar(false);
        chart.setHighlightFullBarEnabled(false);

        // change the position of the y-labels
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        chart.getAxisRight().setEnabled(false);

        XAxis xLabels = chart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] parts = new String[]{
                getString(R.string.part_one), getString(R.string.part_two),
                getString(R.string.part_three), getString(R.string.part_four),
                getString(R.string.part_five), getString(R.string.part_six),
                getString(R.string.part_seven)
        };
        xLabels.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return parts[(int) value % parts.length];
            }
        });

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(15f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

        ArrayList<BarEntry> values = new ArrayList<>();


        int process = Part.partQuestionSize.size();
        for (int partNumber = 1; partNumber <= process; partNumber++) {
            float val1 = currentResult.getCorrectQuestionByPart(partNumber);
            float val2 = Part.partQuestionSize.get(partNumber - 1) - currentResult.getCorrectQuestionByPart(partNumber);
            values.add(new BarEntry(partNumber - 1, new float[]{val1, val2}));
        }

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, EMPTY_STRING);
            set1.setDrawIcons(false);
            set1.setColors(getColors());
            set1.setStackLabels(new String[]{getString(R.string.correct), getString(R.string.wrong)});

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueFormatter(new StackedValueFormatter(false, "", 1));
            data.setValueTextColor(Color.WHITE);

            chart.setData(data);
        }

        chart.invalidate();
    }

    private void setUpRadarChart() {
        final String[] parts = new String[]{
                getString(R.string.part_one),
                getString(R.string.part_two),
                getString(R.string.part_three),
                getString(R.string.part_four),
                getString(R.string.part_five),
                getString(R.string.part_six),
                getString(R.string.part_seven)};

        int size = parts.length;

        radarChart.setBackgroundColor(Color.rgb(60, 65, 82));

        radarChart.getDescription().setEnabled(false);

        radarChart.setWebLineWidth(1f);
        radarChart.setWebColor(Color.LTGRAY);
        radarChart.setWebLineWidthInner(1f);
        radarChart.setWebColorInner(Color.LTGRAY);
        radarChart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(this, R.layout.radar_markerview);
        mv.setChartView(radarChart); // For bounds control
        radarChart.setMarker(mv); // Set the marker to the radarChart

        setData(parts);

        radarChart.animateXY(1400, 1400, Easing.EaseInOutQuad);

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return parts[(int) value % parts.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setLabelCount(size, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = radarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.WHITE);
    }

    private void setData(String[] parts) {

        int size = parts.length;
        ArrayList<RadarEntry> entries1 = new ArrayList<>();
        ArrayList<RadarEntry> entries2 = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int partNumber = 1; partNumber <= size; partNumber++) {
            entries1.add(new RadarEntry(oldResult.getCorrectQuestionByPart(partNumber) * 100f / Part.partQuestionSize.get(partNumber - 1)));
            entries2.add(new RadarEntry(currentResult.getCorrectQuestionByPart(partNumber) * 100f / Part.partQuestionSize.get(partNumber - 1)));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, getString(R.string.old_result));
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, getString(R.string.current_result));
        set2.setColor(Color.rgb(121, 162, 175));
        set2.setFillColor(Color.rgb(121, 162, 175));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        radarChart.setData(data);
        radarChart.invalidate();
    }

    private int[] getColors() {

        // have as many colors as stack-values per entry
        int[] colors = new int[2];
        colors[0] = ColorTemplate.rgb("9AFF8E");
        colors[1] = ColorTemplate.rgb("FF8C8C");
        return colors;
    }
}
