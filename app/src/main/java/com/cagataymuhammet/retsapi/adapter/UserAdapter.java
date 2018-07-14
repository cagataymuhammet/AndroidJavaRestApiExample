package com.cagataymuhammet.retsapi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onurbarman.webservice.R;
import com.cagataymuhammet.retsapi.model.UserModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<UserModel> dataList;


    public UserAdapter(List<UserModel> dataList) {
        this.dataList = dataList;
    }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_user, parent, false);
        return new UserViewHolder(view);
    }


    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        holder.txtUserId.setText(dataList.get(position).getId().toString());

        holder.txtTitle.setText(dataList.get(position).getTitle());

        holder.txtBody.setText(dataList.get(position).getBody());
    }


    @Override

    public int getItemCount() {

        return dataList.size();

    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserId, txtId, txtTitle, txtBody;
        UserViewHolder(View itemView) {

            super(itemView);

            txtUserId = (TextView) itemView.findViewById(R.id.txt_user_id);

            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);

            txtBody = (TextView) itemView.findViewById(R.id.txt_body);

        }

    }
}