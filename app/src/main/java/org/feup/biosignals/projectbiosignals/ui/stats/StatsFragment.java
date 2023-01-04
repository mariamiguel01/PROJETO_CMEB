package org.feup.biosignals.projectbiosignals.ui.stats;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
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
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.feup.biosignals.projectbiosignals.DBManager;
import org.feup.biosignals.projectbiosignals.databinding.FragmentStatsBinding;

import java.util.ArrayList;
import java.util.List;

public class StatsFragment extends Fragment {

    private FragmentStatsBinding binding;
    private PieChart pieChart;
    private BarChart barChart;
    private LineChart lineChart;
    int points;
    DBManager db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StatsViewModel settingsViewModel =
                new ViewModelProvider(this).get(StatsViewModel.class);

        binding = FragmentStatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        getActivity().getWindow().setBackgroundDrawable(null);

        getParentFragmentManager().setFragmentResultListener("requestPoints", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestPoints, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                points = bundle.getInt("points");
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        addPoints();
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        pieChart = view.findViewById(org.feup.biosignals.projectbiosignals.R.id.piechart);
        lineChart = view.findViewById(org.feup.biosignals.projectbiosignals.R.id.linechart);
        db = new DBManager(getContext());

        setupPieChart();
        loadPieChartData();
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
        List<Double> aux = db.getPitchByDate();

        entries.add(new PieEntry(1-db.calculatePercentage(aux), "Bad Posture"));
        entries.add(new PieEntry(db.calculatePercentage(aux), "Good Posture"));

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

    private void loadLineChart(){
        ArrayList<Double> valueList = new ArrayList<Double>();
        ArrayList<Entry> entries = new ArrayList<>();

        String month = db.getCurrentMonth();
        String year = db.getCurrentYear();
        for (int i = 1; i <= 31; i++) {
            List<Double> aux1= db.getPitchForHist(month, year, i);
            float aux2 = db.calculatePercentage(aux1);
            if(Float.isNaN(aux2)){
                entries.add(new Entry(i,0));
            }
            else{
                entries.add(new Entry(i,aux2));
            }
        }

        LineDataSet set1 = new LineDataSet(entries, "% Good Posture");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(set1);

        lineChart.setData(data);
        lineChart.invalidate();
        lineChart.getDescription().setEnabled(false);
        lineChart.getLineData().setDrawValues(false);


    }

    public void addPoints() {
        double value_good = 0.8;
        if (value_good >= 0.7) {
            Log.i("Com_piechart", Integer.toString(points));
            points = points + 5;
            Bundle result = new Bundle(); // --> Enviar a informação
            result.putInt("points", points);
            getParentFragmentManager().setFragmentResult("statsPoints", result);
        }
    }
}