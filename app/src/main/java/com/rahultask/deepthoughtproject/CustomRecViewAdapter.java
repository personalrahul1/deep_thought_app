package com.rahultask.deepthoughtproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.rahultask.deepthoughtproject.fragment.FullDetailsFrag;

import org.json.JSONException;

import java.util.ArrayList;

public class CustomRecViewAdapter extends RecyclerView.Adapter<CustomRecViewAdapter.CustomViewHolder> {

    public static boolean toAnimate;
    Context context;
    ArrayList<JsonResPojoObj> jsonResPojoObjFull;
    String jsonData;
    boolean isClickable;

    public CustomRecViewAdapter(Context context, ArrayList<JsonResPojoObj> jsonResPojoObjFull, String jsonData) {
        this.context = context;
        this.jsonResPojoObjFull = jsonResPojoObjFull;
        this.jsonData = jsonData;
        toAnimate = true;
    }

    @NonNull
    @Override
    public CustomRecViewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.recycler_view_layout, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView.setLayoutParams(lp);
        return new CustomViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecViewAdapter.CustomViewHolder holder, int position) {
        if (toAnimate)
            new Animations().scaleAnimView(holder.itemView);

        StringBuilder learningOutcome = new StringBuilder();
        if (jsonResPojoObjFull.get(position).getLearningOutcomes().length() > 0) {
            for (int j = 0; j < jsonResPojoObjFull.get(position).getLearningOutcomes().length(); j++) {
                try {
                    learningOutcome.append(jsonResPojoObjFull.get(position).getLearningOutcomes().getString(j)).append("\n");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        StringBuilder preRequisite = new StringBuilder();
        if (jsonResPojoObjFull.get(position).getPreRequisites().length() > 0) {
            for (int j = 0; j < jsonResPojoObjFull.get(position).getPreRequisites().length(); j++) {
                try {
                    preRequisite.append(jsonResPojoObjFull.get(position).getPreRequisites().getString(j)).append("\n");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        holder.titleTv.setText(jsonResPojoObjFull.get(position).getTaskTitle());
        holder.titleDesTv.setText(jsonResPojoObjFull.get(position).getShortDescription());
        holder.learnOutDesTv.setText(learningOutcome);
        holder.preReqDesTv.setText(preRequisite);
        holder.fullNameTv.setText(jsonResPojoObjFull.get(position).getFullName());

        Glide.with(context)
                .load(jsonResPojoObjFull.get(position).getPicture())
                .placeholder(R.drawable.picturellandscape_gallery)
                .error(R.drawable.error_no_image)
                .into(holder.mainKenImg);


        System.out.println(jsonResPojoObjFull.get(position).getPicture());

     /*   holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                ft.addToBackStack(null);

                ft.replace(R.id.mainFragCv, new FullDetailsFrag("string", jsonData)).commit();
            }
        });*/
        holder.titleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClickable) {
                    isClickable = true;
                    Toast.makeText(context, "Click Again to see more details", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(() -> isClickable = false, 2500);
                } else {
                    holder.exploreBtnTv.callOnClick();
                    isClickable = false;
                }
            }
        });

        holder.exploreBtnTv.setOnClickListener(view -> {
//            Toast.makeText(context, jsonResPojoObjFull.get(position).getDataId() + "", Toast.LENGTH_SHORT).show();
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.mainFragCv, new FullDetailsFrag(jsonResPojoObjFull.get(position).getDataId(), jsonData)).commit();
        });
    }


    @Override
    public int getItemCount() {
        return jsonResPojoObjFull.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filteredLIst(ArrayList<JsonResPojoObj> filteredList) {
        jsonResPojoObjFull = filteredList;
        toAnimate = false;
        notifyDataSetChanged();
    }


    public void setData(ArrayList<JsonResPojoObj> itemList) {
        jsonResPojoObjFull.clear();
        jsonResPojoObjFull.addAll(itemList);
        notifyDataSetChanged();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv, titleDesTv, learnOutTv, learnOutDesTv, preReqTv, preReqDesTv, exploreBtnTv, fullNameTv;
        KenBurnsView mainKenImg;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.recTitleTv);
            titleDesTv = itemView.findViewById(R.id.recTitleDescTv);
            learnOutTv = itemView.findViewById(R.id.recLearningOutTv);
            learnOutDesTv = itemView.findViewById(R.id.recLearningOutDescTv);
            preReqTv = itemView.findViewById(R.id.recPreReqTv);
            preReqDesTv = itemView.findViewById(R.id.recPreReqDescTv);
            exploreBtnTv = itemView.findViewById(R.id.recExploreBtnTv);
            fullNameTv = itemView.findViewById(R.id.recFullNameTv);
            mainKenImg = itemView.findViewById(R.id.recMainKImv);
        }
    }
}
