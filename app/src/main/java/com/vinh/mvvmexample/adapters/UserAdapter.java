package com.vinh.mvvmexample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vinh.mvvmexample.R;
import com.vinh.mvvmexample.databinding.RowItemBinding;
import com.vinh.mvvmexample.viewmodels.UserViewModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyAdapter> {

    List<UserViewModel> data;
    Context context;
    private LayoutInflater inflater;
    private UserAdapterListener listener;

    public UserAdapter(List<UserViewModel> data, Context context, UserAdapterListener listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        RowItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.row_item, parent, false);
        return new MyAdapter(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter holder, final int position) {
        final UserViewModel userViewModel = data.get(position);
        holder.bind(userViewModel);

        holder.binding.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onUserClicked(userViewModel, data.indexOf(userViewModel));
            }
        });
        holder.binding.rowLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (listener != null)
                    listener.onUserLongClicked(userViewModel, data.indexOf(userViewModel));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyAdapter extends RecyclerView.ViewHolder {

        RowItemBinding binding;

        public MyAdapter(RowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(UserViewModel userViewModel) {
            this.binding.setViewModel(userViewModel);
        }

    }

    public interface UserAdapterListener {
        void onUserClicked(UserViewModel user, int position);

        void onUserLongClicked(UserViewModel user, int position);
    }
}
