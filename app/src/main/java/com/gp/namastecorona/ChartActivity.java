package com.gp.namastecorona;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.gp.namastecorona.api.APIClient;
import com.gp.namastecorona.api.ApiInterface;
import com.gp.namastecorona.model.AllModel;
import com.gp.namastecorona.model.ConfirmModel;
import com.gp.namastecorona.ui.main.WebActivity;
import com.gp.namastecorona.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartActivity extends AppCompatActivity {

    private PieChart chart;
    private TextView txtConfirm, txtRecovered, txtDeaths, txtexisting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        chart = findViewById(R.id.pieChart1);

        txtConfirm = findViewById(R.id.txt_confirm);
        txtDeaths = findViewById(R.id.txt_death);
        txtRecovered = findViewById(R.id.txt_recover);
        txtexisting = findViewById(R.id.txt_existing);

        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

        //chart.setCenterTextTypeface(tfLight);
        //chart.setCenterText(generateCenterSpannableText());

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);
        chart.setCenterTextColor(Color.BLACK);
        chart.setCenterText("COVID-19");
        chart.setDrawCenterText(true);
        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);
        chart.setEntryLabelColor(Color.WHITE);
        chart.setEntryLabelTextSize(14f);
        // add a selection listener
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                //i.putExtra("index",e.)
                startActivity(i);
            }

            @Override
            public void onNothingSelected() {

            }
        });

        findViewById(R.id.btnHelp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), WebActivity.class));
            }
        });

        findViewById(R.id.btnMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        loadLatestData();
    }

    private void setData(int confirm, int death, int recover) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.

        txtConfirm.setText("Confirmed\n" + confirm);
        confirm = (confirm - (recover + death));
        txtDeaths.setText("Deaths\n" + death);
        txtRecovered.setText("Recovered\n" + recover);
        txtexisting.setText("Existing\n" + confirm);

        entries.add(new PieEntry(confirm, "Existing"));
        entries.add(new PieEntry(death, "Deaths"));
        entries.add(new PieEntry(recover, "Recovered"));

        PieDataSet dataSet = new PieDataSet(entries, "CoronaVirus");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        dataSet.setColors(Color.parseColor("#E7673F"), Color.parseColor("#94191D"), Color.parseColor("#286D22"));
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        //data.setValueTypeface(tfLight);
        chart.setData(data);

        // undo all highlights
        //chart.highlightValues(null);
        chart.invalidate();
    }

    public void loadLatestData() {
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setMessage("Please wait !");
        progressBar.show();

        ApiInterface apiService = APIClient.getClient().create(ApiInterface.class);

        /**
         GET List Resources
         **/
        Call<AllModel> call = apiService.getAll();
        call.enqueue(new Callback<AllModel>() {
            @Override
            public void onResponse(Call<AllModel> call, Response<AllModel> response) {
                progressBar.dismiss();
                AllModel allModel = response.body();
                if (allModel != null) {
                    setData(response.body().getLatest().getConfirmed(), response.body().getLatest().getDeaths(), response.body().getLatest().getRecovered());

                    Date updateDate1 = new Date();
                    Date updateDate2 = new Date();
                    Date updateDate3 = new Date();
                    updateDate1 = DateUtil.fromISO8601UTC(allModel.getConfirmed().getLast_updated());
                    updateDate2 = DateUtil.fromISO8601UTC(allModel.getDeaths().getLast_updated());
                    updateDate3 = DateUtil.fromISO8601UTC(allModel.getRecovered().getLast_updated());

                    TextView txtDateUpdate = findViewById(R.id.txtLastUpdate);
                    if (updateDate1==null){
                        txtDateUpdate.setText("Last Update : " + allModel.getConfirmed().getLast_updated());
                        return;
                    }
                    if (updateDate1.after(updateDate2)) {
                        txtDateUpdate.setText("Last Update : " + updateDate1.toLocaleString());
                    } else if (updateDate2.after(updateDate3)) {
                        txtDateUpdate.setText("Last Update : " + updateDate2.toLocaleString());
                    } else {
                        txtDateUpdate.setText("Last Update : " + updateDate3.toLocaleString());
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Record Not Found", Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<AllModel> call, Throwable t) {
                call.cancel();

                progressBar.dismiss();

                Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG);
            }
        });
    }
}
