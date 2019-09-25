package com.example.bottomnavigationview;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private Context mContext;
    private List<UploadProject> mProjectUploads;

    public ProjectAdapter(Context mContext, List<UploadProject> mUploads) {
        this.mContext = mContext;
        this.mProjectUploads = mUploads;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.project_item,parent,false);
        return new ProjectViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        UploadProject uploadCurrent=mProjectUploads.get(position);
        holder.textViewProjectName.setText(uploadCurrent.getmProjectName());
        holder.textViewProjectDescription.setText(uploadCurrent.getmProjectDescription());
        holder.textViewInvolvedAlumni.setText(uploadCurrent.getmInvolvedAlumni());
        Picasso.with(mContext)
                .load(uploadCurrent.getmImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mProjectUploads.size();
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewProjectName;
      //  public TextView textViewImageName;
        public TextView textViewProjectDescription;
        public TextView textViewInvolvedAlumni;
        public ImageView imageView;
        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProjectName=itemView.findViewById(R.id.text_view_card_project_name);
            imageView=itemView.findViewById(R.id.image_view_card_upload);
            textViewProjectDescription=itemView.findViewById(R.id.text_view_card_project_description);
            textViewInvolvedAlumni=itemView.findViewById(R.id.text_view_card_involved_alumni);


        }


    }

}
