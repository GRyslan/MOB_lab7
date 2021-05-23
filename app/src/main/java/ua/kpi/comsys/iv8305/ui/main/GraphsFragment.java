package ua.kpi.comsys.iv8305.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import ua.kpi.comsys.iv8305.R;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class GraphsFragment extends Fragment {

    LineChart lineChart;
    ArrayList<Entry> line_entries;
    LineDataSet line_dataSet;
    PieChart pieChart;
    ArrayList<PieEntry> pie_entries;
    PieDataSet pie_dataSet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_graphs, container, false);
        Switch switch_graph = root.findViewById(R.id.switch1);
        line_entries = new ArrayList<>();
        pie_entries = new ArrayList<>();
        lineChart = root.findViewById(R.id.line);
        pieChart = root.findViewById(R.id.pie);
        pieChart.setVisibility(INVISIBLE);
        float i = (float) -Math.PI;
        while (i < Math.PI) {
            line_entries.add(new Entry(i, (float) Math.cos(i)));
            i += 0.2;
        }
        line_dataSet = new LineDataSet(line_entries, "cos(x)");
        lineChart.setData(new LineData(line_dataSet));
        lineChart.setVisibility(VISIBLE);

        pie_entries.add(new PieEntry(45f, 0));
        pie_entries.add(new PieEntry(5f, 1));
        pie_entries.add(new PieEntry(25f, 2));
        pie_entries.add(new PieEntry(25f, 3));

        pie_dataSet = new PieDataSet(pie_entries, "Set");
        pie_dataSet.setColors(new int[] {R.color.pie1, R.color.pie2, R.color.pie3, R.color.pie4}, this.getContext());
        pieChart.setData(new PieData(pie_dataSet));

        switch_graph.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(lineChart.getVisibility() == VISIBLE) {
                    lineChart.setVisibility(INVISIBLE);
                    pieChart.setVisibility(VISIBLE);
                } else {
                    lineChart.setVisibility(VISIBLE);
                    pieChart.setVisibility(INVISIBLE);
                }
            }
        });

        return root;
    }
}
