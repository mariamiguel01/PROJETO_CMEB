package org.feup.biosignals.projectbiosignals.ui.stats;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.feup.biosignals.projectbiosignals.databinding.FragmentStatsBinding;

import java.util.ArrayList;

public class StatsFragment extends Fragment {

    private FragmentStatsBinding binding;
    private PieChart pieChart;
    private BarChart barChart;
    private LineChart lineChart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StatsViewModel settingsViewModel =
                new ViewModelProvider(this).get(StatsViewModel.class);

        binding = FragmentStatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        pieChart = view.findViewById(org.feup.biosignals.projectbiosignals.R.id.piechart);
        barChart = view.findViewById(org.feup.biosignals.projectbiosignals.R.id.barchart);
        lineChart = view.findViewById(org.feup.biosignals.projectbiosignals.R.id.linechart);
        setupPieChart();
        loadPieChartData();
        loadBarChartData();
        loadLineChart();

    }

    private void setupPieChart(){
        // make a hole in the middle of the PieChart
        pieChart.setDrawHoleEnabled(true);
        // tell the PieChart that they wanted percentage values
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setDrawEntryLabels(false);
        pieChart.setCenterText("Daily Posture");
        pieChart.setCenterTextSize(16);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }


    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.2f, "Bad Posture"));
        entries.add(new PieEntry(0.8f, "Good Posture"));


        PieDataSet dataSet = new PieDataSet(entries, "Posture Habits");
        dataSet.setColors(new int[] {Color.rgb(131,157,219), Color.rgb(140,191,219)});

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
    }

    private void loadBarChartData(){
        ArrayList<Double> valueList = new ArrayList<Double>();
        ArrayList<BarEntry> entries = new ArrayList<>();
        String title = "Title";

        //input data
        for(int i = 0; i < 4; i++){
            valueList.add(i * 100.1);
        }

        //fit the data into a bar
        for (int i = 0; i < valueList.size(); i++) {
            BarEntry barEntry = new BarEntry(i, valueList.get(i).floatValue());
            entries.add(barEntry);
        }

        BarDataSet barDataSet = new BarDataSet(entries, title);
        barDataSet.setColors(new int[] {Color.rgb(131,157,219), Color.rgb(140,191,219), Color.rgb(160, 219, 206)});
        BarData data = new BarData(barDataSet);
        barChart.setData(data);
        barChart.invalidate();
    }

    private void loadLineChart(){

        ArrayList<Double> valueList = new ArrayList<Double>();
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(0,60f));
        entries.add(new Entry(1,50f));
        entries.add(new Entry(2, 70f));
        entries.add(new Entry(3, 30f));
        entries.add(new Entry(4, 50f));
        entries.add(new Entry(5, 60f));
        entries.add(new Entry(6, 65f));

        LineDataSet set1 = new LineDataSet(entries, "Data Set 1");

        LineData data = new LineData(set1);

        lineChart.setData(data);
        lineChart.invalidate();
    }
}