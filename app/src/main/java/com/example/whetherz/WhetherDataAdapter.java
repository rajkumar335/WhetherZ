package com.example.whetherz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whetherz.databinding.WhetherDataItemBinding;

import org.jetbrains.annotations.NotNull;

import static com.example.whetherz.MainScreen.onItemClickListener;


public class WhetherDataAdapter extends ListAdapter<WhetherData, WhetherDataAdapter.ViewHolder> {

    private onItemClickListener ItemClickListener;
    private WhetherDataItemBinding binding;
    private final Context context;

    public WhetherDataAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context=context;
    }

    public static DiffUtil.ItemCallback<WhetherData> DIFF_CALLBACK= new DiffUtil.ItemCallback<WhetherData>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull WhetherData oldItem, @NonNull @NotNull WhetherData newItem) {
            return oldItem.getFormattedDate().equals(newItem.getFormattedDate());
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull WhetherData oldItem, @NonNull @NotNull WhetherData newItem) {
            return oldItem.getWhetherStateName().equals(newItem.getWhetherStateName())
                    && oldItem.getDay().equals(newItem.getDay())
                    && oldItem.getMaxTemp()==newItem.getMaxTemp()
                    && oldItem.getMinTemp()==newItem.getMinTemp();
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        binding=WhetherDataItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View v = binding.getRoot();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  WhetherDataAdapter.ViewHolder holder, int position) {
        WhetherData currentData = getItem(position);
        binding.whetherStateText.setText(currentData.getWhetherStateName());
        binding.day.setText(currentData.getDay());
        binding.MaxTemp.setText(context.getResources().getString(R.string.temperature,currentData.getMaxTemp()));
        binding.MinTemp.setText(context.getResources().getString(R.string.temperature,currentData.getMinTemp()));
        binding.MaxTempState.setText(R.string.high);
        binding.MinTempState.setText(R.string.low);
        binding.whetherStateImg.setImageResource(IconAndBackgroundColorPicker.getCurrentWhetherIconSmall(currentData.getWhetherStateAbbr()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            itemView.setOnClickListener(v-> ItemClickListener.onClick(getItem(getAdapterPosition())));
        }
    }

    public void setItemClickListener(onItemClickListener listener){
        this.ItemClickListener =listener;
    }
}
