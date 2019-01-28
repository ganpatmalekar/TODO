package com.todo.task.todo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryHolder> {

    List<InventoryModel> listItems;
    Context context;

    public InventoryAdapter(Context context, List<InventoryModel> list)
    {
        this.context = context;
        this.listItems = list;
    }

    @NonNull
    @Override
    public InventoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_layout, parent, false);
        return new InventoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryHolder holder, int position) {
        InventoryModel model = listItems.get(position);

        Picasso.with(context).load(model.getImg_url()).into(holder.name_img);
        holder.name.setText(model.getName());
        Picasso.with(context).load(model.getStatus_url()).into(holder.status_img);
        holder.status.setText(model.getStatus());
        holder.issue.setText(model.getIssue_name());
        holder.person.setText(model.getPer_name());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class InventoryHolder extends RecyclerView.ViewHolder {

        TextView name, status, issue, person;
        ImageView name_img, status_img;

        public InventoryHolder(@NonNull View itemView) {
            super(itemView);

            name_img = (ImageView)itemView.findViewById(R.id.img1);
            name = (TextView)itemView.findViewById(R.id.img_title1);
            status_img = (ImageView)itemView.findViewById(R.id.img2);
            status = (TextView)itemView.findViewById(R.id.img_title2);
            issue = (TextView)itemView.findViewById(R.id.main_title);
            person = (TextView)itemView.findViewById(R.id.person_name);
        }
    }
}
