package com.rahultask.deepthoughtproject.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.rahultask.deepthoughtproject.Animations;
import com.rahultask.deepthoughtproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FullDetailsFrag extends Fragment {
    View fullDetailsFragV;
    String dataId, jsonData;

    KenBurnsView kenBImv;

    TextView titleTv, typeTv, fullNameTv, mainDescTv, statusTv,
            shortDescTv, learningOutDescTv, preReqDescTv, recruiterNameTv, recruiterProTv,
            taskDescTv, asset0TitleTv, asset0TypeTv, asset0DescTv, asset1TitleTv, asset1TypeTv, asset1DescTv,
            dataIdTv, dataKeyTv, taskIdTv, assetId0Tv, assetId1Tv, tidTv, timestampTv, uidTv, publishedTimeTv,
            applicantCountTv, pendingCountTv, reassignedCountTv;

    public FullDetailsFrag(String dataId, String jsonData) {
        this.dataId = dataId;
        this.jsonData = jsonData;
    }

    private void initialisation() {
        kenBImv = fullDetailsFragV.findViewById(R.id.fullDetailKenImv);
        titleTv = fullDetailsFragV.findViewById(R.id.fullDetailTitleTv);
        typeTv = fullDetailsFragV.findViewById(R.id.fullDetailTypeTv);
        fullNameTv = fullDetailsFragV.findViewById(R.id.fullDetailFullNameTv);
        statusTv = fullDetailsFragV.findViewById(R.id.fullDetailStatusTv);
        mainDescTv = fullDetailsFragV.findViewById(R.id.fullDetailMainDescTv);
        shortDescTv = fullDetailsFragV.findViewById(R.id.fullDetailShortDescTv);
        learningOutDescTv = fullDetailsFragV.findViewById(R.id.fullDetailLearningOutDescTv);
        preReqDescTv = fullDetailsFragV.findViewById(R.id.fullDetailPreReqDescTv);
        recruiterNameTv = fullDetailsFragV.findViewById(R.id.fullDetailRecNameTv);
        recruiterProTv = fullDetailsFragV.findViewById(R.id.fullDetaiRecImvTv);
        taskDescTv = fullDetailsFragV.findViewById(R.id.fullDetailTaskDescTv);
        asset0TitleTv = fullDetailsFragV.findViewById(R.id.fullDetailAsset0TitleTv);
        asset0TypeTv = fullDetailsFragV.findViewById(R.id.fullDetailAsset0TypeTv);
        asset0DescTv = fullDetailsFragV.findViewById(R.id.fullDetailAsset0DescTv);
        asset1TitleTv = fullDetailsFragV.findViewById(R.id.fullDetailAsset1TitleTv);
        asset1TypeTv = fullDetailsFragV.findViewById(R.id.fullDetailAsset1TypeTv);
        asset1DescTv = fullDetailsFragV.findViewById(R.id.fullDetailAsset1DescTv);
        dataIdTv = fullDetailsFragV.findViewById(R.id.fullDetailDataIdTv);
        dataKeyTv = fullDetailsFragV.findViewById(R.id.fullDetailDataKeyTv);
        taskIdTv = fullDetailsFragV.findViewById(R.id.fullDetailTaskIdTv);
        assetId0Tv = fullDetailsFragV.findViewById(R.id.fullDetailAssetId0Tv);
        assetId1Tv = fullDetailsFragV.findViewById(R.id.fullDetailAssetId1Tv);
        tidTv = fullDetailsFragV.findViewById(R.id.fullDetailTidTv);
        timestampTv = fullDetailsFragV.findViewById(R.id.fullDetailTimeStampTv);
        uidTv = fullDetailsFragV.findViewById(R.id.fullDetailUidTv);
        publishedTimeTv = fullDetailsFragV.findViewById(R.id.fullDetailPublishedTimeStampTv);
        applicantCountTv = fullDetailsFragV.findViewById(R.id.fullDetailApplicantCountTv);
        pendingCountTv = fullDetailsFragV.findViewById(R.id.fullDetailPendingCountTv);
        reassignedCountTv = fullDetailsFragV.findViewById(R.id.fullDetailReassignedCountTv);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fullDetailsFragV = inflater.inflate(R.layout.fragment_full_details, container, false);
        initialisation();

        new Animations().vibrateAnimation(fullDetailsFragV.findViewById(R.id.fullDetailSlideActionImv));
        Toast.makeText(fullDetailsFragV.getContext(), "Swipe left to see Extra Data", Toast.LENGTH_SHORT).show();

        try {
            retrieveData(jsonData, dataId);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        backPressHandle();

        return fullDetailsFragV;
    }

    public void retrieveData(String jsonData, String _id) throws JSONException {

        // Load the JSON data from a file
        JSONObject json = new JSONObject(jsonData);

        // Get the data array from the response object
        JSONArray dataArray = json.getJSONObject("response").getJSONArray("data");

        // Iterate over the array and find the object with the given _id
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject dataObject = dataArray.getJSONObject(i);
            if (dataObject.getString("_id").equals(_id)) {
                String mainId = "" + dataObject.getString("_id");
                String status = "" + dataObject.getString("status");
                String mainDesc = "" + Html.fromHtml(dataObject.getString("description"), 0);
                String taskDesc = dataObject.getJSONArray("tasks").getJSONObject(0).getString("task_description");
                String taskId = dataObject.getJSONArray("tasks").getJSONObject(0).getString("task_id");
                String mainKey = "" + dataObject.getString("_key");
                String projectImage = "" + dataObject.getString("project_image");
                String shortDesc = "" + dataObject.getString("short_description");
                String tid = "" + dataObject.getInt("tid");
                String timeStamp = "" + dataObject.getLong("timestamp");
                String title = "" + dataObject.getString("title");
                String type = "" + dataObject.getString("type");
                String uid = "" + dataObject.getInt("uid");
                String publishedAt = "" + dataObject.getLong("publishedAt");
                String fullName = "" + dataObject.getJSONObject("recruiter").optString("fullname");
                String iconText = "" + dataObject.getJSONObject("recruiter").getString("icon:text");
                String iconBg = "" + dataObject.getJSONObject("recruiter").getString("icon:bgColor");
                String applicantCount = "" + dataObject.getJSONObject("macrodata").getInt("applicant_count");
                String pendingCount = "" + dataObject.getJSONObject("macrodata").getInt("pending_count");
                String reassignedCount = "" + dataObject.getJSONObject("macrodata").getInt("reAsigned_count");
                String assetTitle0 = dataObject.getJSONArray("tasks").getJSONObject(0).getJSONArray("assets").getJSONObject(0).getString("asset_title");
                String assetDesc0 = dataObject.getJSONArray("tasks").getJSONObject(0).getJSONArray("assets").getJSONObject(0).getString("asset_description");
                String assetType0 = dataObject.getJSONArray("tasks").getJSONObject(0).getJSONArray("assets").getJSONObject(0).getString("asset_type");
                String assetTitle1 = dataObject.getJSONArray("tasks").getJSONObject(0).getJSONArray("assets").getJSONObject(0).getString("asset_title");
                String assetDesc1 = dataObject.getJSONArray("tasks").getJSONObject(0).getJSONArray("assets").getJSONObject(0).getString("asset_description");
                String assetType1 = dataObject.getJSONArray("tasks").getJSONObject(0).getJSONArray("assets").getJSONObject(0).getString("asset_type");
                String assetId0 = String.valueOf(dataObject.getJSONArray("tasks").getJSONObject(0).getJSONArray("assets").getJSONObject(0).getInt("asset_id"));
                String assetId1 = String.valueOf(dataObject.getJSONArray("tasks").getJSONObject(0).getJSONArray("assets").getJSONObject(0).getInt("asset_id"));

                // Setting data to textView
                Glide.with(fullDetailsFragV.getContext())
                        .load(projectImage)
                        .placeholder(R.drawable.picturellandscape_gallery)
                        .error(R.drawable.error_no_image)
                        .into(kenBImv);

                StringBuilder learningOutcome = new StringBuilder();
                if (dataObject.getJSONArray("learning_outcomes").length() > 0) {
                    for (int j = 0; j < dataObject.getJSONArray("learning_outcomes").length(); j++) {
                        try {
                            learningOutcome.append(dataObject.getJSONArray("learning_outcomes").getString(j)).append("\n");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                StringBuilder preRequisite = new StringBuilder();
                if (dataObject.getJSONArray("pre_requisites").length() > 0) {
                    for (int j = 0; j < dataObject.getJSONArray("pre_requisites").length(); j++) {
                        try {
                            preRequisite.append(dataObject.getJSONArray("pre_requisites").getString(j)).append("\n");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                titleTv.setText(title);
                typeTv.append(" : " + type);
                fullNameTv.setText(fullName);
                statusTv.append(status);
                mainDescTv.setText(mainDesc);
                shortDescTv.setText(shortDesc);
                learningOutDescTv.setText(learningOutcome);
                preReqDescTv.setText(preRequisite);
                recruiterNameTv.setText(fullName);
                recruiterNameTv.setTextColor(Color.parseColor(iconBg));
                recruiterProTv.setText(iconText);
                recruiterProTv.setBackgroundColor(Color.parseColor(iconBg));
                taskDescTv.setText(taskDesc);
                asset0TitleTv.setText(assetTitle0);
                asset0TypeTv.append(" : " + assetType0);
                asset0DescTv.setText(assetDesc0);
                asset1TitleTv.setText(assetTitle1);
                asset1TypeTv.append(" : " + assetType1);
                asset1DescTv.setText(assetDesc1);

                dataIdTv.append(mainId);
                dataKeyTv.append(mainKey);
                taskIdTv.append(taskId);
                assetId0Tv.append(assetId0);
                assetId1Tv.append(assetId1);
                tidTv.append(tid);
                timestampTv.append(timeStamp + " - " + formatDate(Long.parseLong(timeStamp)));
                uidTv.append(uid);
                publishedTimeTv.append(publishedAt + " - " + formatDate(Long.parseLong(publishedAt)));
                applicantCountTv.append(applicantCount);
                pendingCountTv.append(pendingCount);
                reassignedCountTv.append(reassignedCount);
                break;
            }
        }
    }

    public String formatDate(long timestamp) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy 'at' hh:mm a");
        Date date = new Date(timestamp);
        return dateFormat.format(date);
    }

    private void backPressHandle() {
               /* requireActivity().getOnBackPressedDispatcher().addCallback((LifecycleOwner) fullDetailsFragV.getContext(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // in here you can do logic when backPress is clicked
                    MotionLayout parent = (MotionLayout) fullDetailsFragV.findViewById(R.id.fullDetailFrag);    //7 Parental layers
                    parent.transitionToStart();
            }
        });*/
    }

}

/*  ***   JSON DATA FOR REFERENCE
{
          "_id": "64282079a1d327cd525353a8",
          "_key": "topic:2803",
          "category": "Event",
          "cid": null,
          "commitment": "30 minutes",
          "commitment_type": "custom",
          "deadline": "",
          "description": "<p>In this event we will reflect back on our learnings and the number of reasons we came up with.&nbsp;</p>",
          "faqs": [],
          "globalTags": [],
          "isActive": true,
          "lastposttime": 0,
          "learning_outcomes": [],
          "mainPid": 0,
          "native_tid": 2754,
          "native_uid": 853,
          "postcount": 0,
          "pre_requisites": [],
          "project_image": "",
          "short_description": "Let's reflect back on our learnings and reasonings for this week.",
          "slug": "2803/social-scorecard-event-learning-and-reasoning",
          "status": "published",
          "tasks": [
            {
              "task_id": 27284,
              "task_title": "Learning & Reasoning",
              "task_description": "Let's reflect back on the week!",
              "status": "notworkyet",
              "assets": [
                {
                  "asset_id": 27285,
                  "asset_title": "Learnings",
                  "asset_description": "",
                  "asset_content": "{\"title\":\"Learnings for this week\",\"blocks\":[{\"helptext\":\"\",\"question\":\"Write down 3 or more learnings you had this week.\",\"response\":\"\",\"input_type\":\"textfield\",\"options\":[],\"child\":[]}]}",
                  "asset_type": "input_asset",
                  "asset_content_type": "form"
                },
                {
                  "asset_id": 27286,
                  "asset_title": "Reasonings",
                  "asset_description": "",
                  "asset_content": "{\"title\":\"Let's reflect back on reasonings for this week\",\"blocks\":[{\"helptext\":\"\",\"question\":\"Write down 3 or more reasons you came up with this week.\",\"response\":\"\",\"input_type\":\"textfield\",\"options\":[],\"child\":[]}]}",
                  "asset_type": "input_asset",
                  "asset_content_type": "form"
                }
              ]
            }
          ],
          "tid": 2803,
          "timestamp": 1680351353111,
          "title": "Social Scorecard Event: Learning and Reasoning",
          "type": "project",
          "uid": 853,
          "viewcount": 0,
          "publishedAt": 1680351363898,
          "scorecardAssociationTime": 1680351395710,
          "scorecardId": 2755,
          "scorecardTitle": "Social Scorecard Event: Learning and Reasoning",
          "recruiter": {
            "username": "purnimakumar",
            "fullname": "Purnima Kumar",
            "userslug": "purnimakumar",
            "picture": "https://sdlms.deepthought.education/assets/uploads/files/profile_images/default_profile-image-from-rawpixel-id-6320778-original.jpg",
            "uid": 853,
            "displayname": "purnimakumar",
            "icon:text": "P",
            "icon:bgColor": "#009688"
          },
          "macrodata": {
            "applicant_count": 0,
            "pending_count": 0,
            "reAsigned_count": 0
          },
          "evaluatedCount": 19
        }
*
*
* */