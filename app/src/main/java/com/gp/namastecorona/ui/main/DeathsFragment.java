package com.gp.namastecorona.ui.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gp.namastecorona.R;
import com.gp.namastecorona.adapter.CommonListAdapter;
import com.gp.namastecorona.api.APIClient;
import com.gp.namastecorona.api.ApiInterface;
import com.gp.namastecorona.model.ConfirmModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class DeathsFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private RecyclerView recyclerView;
    private TextView txtTotal;
    ProgressDialog progressBar;
    public static DeathsFragment newInstance(int index) {
        DeathsFragment fragment = new DeathsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_common, container, false);
        txtTotal = root.findViewById(R.id.txt_total);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
                loadDeathsData();
            }
        });
        return root;
    }

    public void loadDeathsData() {

        progressBar=new ProgressDialog(getActivity());
        progressBar.setMessage("Please wait !");
        progressBar.show();

        ApiInterface apiService = APIClient.getClient().create(ApiInterface.class);

        /**
         GET List Resources
         **/
        Call<ConfirmModel> call = apiService.getDeaths();
        call.enqueue(new Callback<ConfirmModel>() {
            @Override
            public void onResponse(Call<ConfirmModel> call, Response<ConfirmModel> response) {
                progressBar.dismiss();
                setAdapter(response.body());
            }

            @Override
            public void onFailure(Call<ConfirmModel> call, Throwable t) {
                call.cancel();

                // recyclerView.setVisibility(View.GONE);
                // emptyView.setVisibility(View.GONE);
                progressBar.dismiss();

                Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_LONG);
            }
        });
    }

    private void setAdapter(ConfirmModel confirmModel) {
        if (confirmModel.getLocations().size() == 0) {
            Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_LONG);
        } else {
            txtTotal.setText("Total : "+confirmModel.getLatest());
            CommonListAdapter adapter = new CommonListAdapter(confirmModel.getLocations());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}