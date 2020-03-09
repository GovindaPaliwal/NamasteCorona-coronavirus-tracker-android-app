package com.gp.namastecorona.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gp.namastecorona.R;
import com.gp.namastecorona.model.ConfirmModel;

import java.util.ArrayList;
import java.util.List;

public class CommonListAdapter extends RecyclerView.Adapter<CommonListAdapter.ViewHolder> {
    private List<ConfirmModel.Location> listdata, listFiltered;

    public CommonListAdapter(List<ConfirmModel.Location> listdata) {
        this.listdata = listdata;
        this.listFiltered = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.row_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ConfirmModel.Location myListData = listFiltered.get(position);
        holder.txtName.setText("" + myListData.getCountry());
        holder.txtCount.setText("" + myListData.getLatest());
        holder.txtTime.setText("" + myListData.getProvince());
    }


    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName, txtCount, txtTime;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_contry_name);
            txtCount = (TextView) itemView.findViewById(R.id.txt_count);
            txtTime = (TextView) itemView.findViewById(R.id.txt_contry_time);
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listFiltered = listdata;
                } else {
                    List<ConfirmModel.Location> filteredList = new ArrayList<>();
                    for (ConfirmModel.Location row : listdata) {
                        if (row.getCountry().toLowerCase().contains(charString.toLowerCase()) || row.getProvince().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    listFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listFiltered = (ArrayList<ConfirmModel.Location>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }
}