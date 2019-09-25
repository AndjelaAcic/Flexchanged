package com.example.bottomnavigationview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowUploadProjectsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ProjectAdapter mProjectAdapter;
    private ProgressBar mProgressCircle;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private List<UploadProject> mProjectUploads;
    private ValueEventListener mDBListener;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_projects,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProjectUploads=new ArrayList<>();
        mProgressCircle=view.findViewById(R.id.progress_circle);
        mStorage=FirebaseStorage.getInstance();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("uploads");


        mRecyclerView=view.findViewById(R.id.recycler_view_projects);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mProjectAdapter=new ProjectAdapter(getContext(),mProjectUploads);
        mRecyclerView.setAdapter(mProjectAdapter);

        loadProjects();



    }

    private void loadProjects() {
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mProjectUploads.clear();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    UploadProject upload=postSnapshot.getValue(UploadProject.class);
                    upload.setmKey(postSnapshot.getKey());
                    mProjectUploads.add(upload);
                }
                Collections.reverse(mProjectUploads);
                mProjectAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

        /*{
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                // Declare adapter and set here

                // OR... adapter.notifyDataSetChanged();

            }

                @Override
                public void onCancelled(DatabaseError databaseError) {

            }
            }
        };*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }
}
