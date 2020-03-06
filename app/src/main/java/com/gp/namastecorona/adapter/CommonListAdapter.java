package com.gp.namastecorona.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gp.namastecorona.R;
import com.gp.namastecorona.model.ConfirmModel;

import java.util.List;

public class CommonListAdapter extends RecyclerView.Adapter<CommonListAdapter.ViewHolder>{
    private List<ConfirmModel.Location> listdata;

    public CommonListAdapter(List<ConfirmModel.Location> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ConfirmModel.Location myListData = listdata.get(position);
        holder.txtName.setText(""+myListData.getCountry());
        holder.txtCount.setText(""+myListData.getLatest());
        holder.txtTime.setText(""+myListData.getProvince());
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName,txtCount,txtTime;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_contry_name);
            txtCount = (TextView) itemView.findViewById(R.id.txt_count);
            txtTime = (TextView) itemView.findViewById(R.id.txt_contry_time);
        }
    }
}