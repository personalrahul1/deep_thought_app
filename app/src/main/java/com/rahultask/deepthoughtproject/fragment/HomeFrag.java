package com.rahultask.deepthoughtproject.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahultask.deepthoughtproject.CustomRecViewAdapter;
import com.rahultask.deepthoughtproject.JsonResPojoObj;
import com.rahultask.deepthoughtproject.NetworkRequest;
import com.rahultask.deepthoughtproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFrag extends Fragment {

    public static AlertDialog fLoadingDialog;
    View homeFragV;
    ArrayList<JsonResPojoObj> jsonResPojoObjList = new ArrayList<>();
    RecyclerView recyclerView;
    EditText searchEt;
    CardView searchCv;
    CustomRecViewAdapter recViewAdapter;
    TextView dialogText;

    String jsonResponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for homeFragV.getContext() fragment
        homeFragV = inflater.inflate(R.layout.fragment_home, container, false);

        searchCv = homeFragV.findViewById(R.id.searchCv);
        searchEt = homeFragV.findViewById(R.id.searchEt);
        searchOperation();

        AlertDialog.Builder loadingDialog = new AlertDialog.Builder(homeFragV.getContext());
        View dialogView = LayoutInflater.from(homeFragV.getContext()).inflate(R.layout.loading_dialog, null);
        loadingDialog.setView(dialogView);
        dialogText = dialogView.findViewById(R.id.loadingDialogTv);

        fLoadingDialog = loadingDialog.create();
        fLoadingDialog.setCancelable(false);
        fLoadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        fLoadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        fLoadingDialog.show();
        makeCancellable();

        jsonResPojoObjList.clear();
        new NetworkRequestTask().execute("https://dev.deepthought.education/assets/uploads/files/others/project.json");

        homeFragV.findViewById(R.id.mainRefreshIcon).setOnClickListener(view -> {
            fLoadingDialog.setCancelable(false);
            dialogText.setText("Loading...");
            fLoadingDialog.show();
            makeCancellable();
            jsonResPojoObjList.clear();
            new NetworkRequestTask().execute("https://dev.deepthought.education/assets/uploads/files/others/project.json");
        });

        recyclerView = homeFragV.findViewById(R.id.mainPageRecView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(homeFragV.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        return homeFragV;
    }

    // Search Operation On Title
    private void searchOperation() {
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ArrayList<JsonResPojoObj> filteredList = new ArrayList<>();
                for (JsonResPojoObj item : jsonResPojoObjList) {
                    String toLookInto = item.getTaskTitle() + item.getShortDescription();
                    if (toLookInto.toLowerCase().contains(editable.toString().toLowerCase())) {
                        filteredList.add(item);
                    }
                    recViewAdapter.filteredLIst(filteredList);
                }
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void renderValues(String jsonData) throws JSONException {
        JSONObject dataObject = new JSONObject(jsonData);
        JSONArray dataArray = dataObject.getJSONObject("response").getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject item = dataArray.getJSONObject(i);

            // Extract values of specific fields
            String dataId = item.getString("_id");
            String fullName = item.getJSONObject("recruiter").getString("fullname");
            String category = item.getString("category");
            String picture = item.getString("project_image");
            String taskTitle = item.getJSONArray("tasks").getJSONObject(0).getString("task_title");
            String taskDescription = item.getJSONArray("tasks").getJSONObject(0).getString("task_description");
            String shortDescription = item.getString("short_description");
            JSONArray learningOutcomes = item.getJSONArray("learning_outcomes");
            JSONArray preRequisites = item.getJSONArray("pre_requisites");

            jsonResPojoObjList.add(new JsonResPojoObj(dataId, shortDescription, taskDescription, taskTitle, picture,
                    category, fullName, preRequisites, learningOutcomes));

            for (int j = 0; j < learningOutcomes.length(); j++) {
                System.out.println("- " + learningOutcomes.getString(j));
            }
            System.out.println("Prerequisites:");
            for (int j = 0; j < preRequisites.length(); j++) {
                System.out.println("- " + preRequisites.getString(j));
            }

            // Print out the extracted values
            /*System.out.println("Full name: " + fullName);
            System.out.println("Category: " + category);
            System.out.println("Picture: " + picture);
            System.out.println("Task title: " + taskTitle);
            System.out.println("Task description: " + taskDescription);
            System.out.println("Short description: " + shortDescription);
            System.out.println("Learning outcomes:");*/
        }
        if (fLoadingDialog != null && fLoadingDialog.isShowing()) {
            fLoadingDialog.dismiss();
        }
        recViewAdapter = new CustomRecViewAdapter(homeFragV.getContext(), jsonResPojoObjList, jsonData);
        recyclerView.setAdapter(recViewAdapter);
        recViewAdapter.notifyDataSetChanged();
    }

    public void makeCancellable() {
        new Handler().postDelayed(() -> {
            if (fLoadingDialog.isShowing()) {
                fLoadingDialog.setCancelable(true);
                dialogText.setText("It's taking longer than usual\nPlease check internet connection");
            }
        }, 8000);
    }

    private class NetworkRequestTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            //Make the network request
            return NetworkRequest.makeNetworkRequest(params[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            //Parse the response as JSON
            try {
                JSONObject jsonObject = new JSONObject(response);
                System.out.println(jsonObject + "");
                renderValues(response);
                jsonResponse = response;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}