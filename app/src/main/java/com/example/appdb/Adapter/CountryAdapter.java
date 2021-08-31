package com.example.appdb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdb.Model.Country;

import com.example.appdb.Others.ItemClickListener;
import com.example.appdb.R;

import java.util.ArrayList;


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private ArrayList<Country> alCountry;
    private Context context;
    private CountryAdapterListener countryAdapterListener;

    public CountryAdapter(ArrayList<Country> alCountry, Context context, CountryAdapterListener countryAdapterListener) {
        this.alCountry = alCountry;
        this.context = context;
        this.countryAdapterListener = countryAdapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ct_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtAdd.setText(String.valueOf(alCountry.get(position).getAdd()+"-"+alCountry.get(position).getZipcode()));
        holder.txtName.setText(String.valueOf(alCountry.get(position).getName()));
        switch(String.valueOf(alCountry.get(position).getCountry())) {
            case "VietNam": holder.imgIc.setImageResource(R.drawable.vietnam);
                break;
            case "Japan": holder.imgIc.setImageResource(R.drawable.japan);
                break;
            case "UnitedKingdom": holder.imgIc.setImageResource(R.drawable.united_kingdom);
                break;
            case "SouthKorea": holder.imgIc.setImageResource(R.drawable.south_korea);
                break;
            default:
                break;
                }
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                countryAdapterListener.click(v, position);

            }

        });



    }

    @Override
    public int getItemCount() {
        return alCountry.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtName,txtAdd;
        public ImageView imgIc;
        public ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAdd = itemView.findViewById(R.id.txtAdd);
            imgIc = itemView.findViewById(R.id.imgIc);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
        this.itemClickListener.onItemClick(view,getAdapterPosition());
        }
        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener = ic;
        }

    }
    public interface CountryAdapterListener {
        void click(View v, int position);
    }


}
