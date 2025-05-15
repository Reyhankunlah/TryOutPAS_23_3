package com.example.tryoutpas_23_3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPremiere extends Fragment {

    RecyclerView recyclerView;
    TeamAdapter teamAdapter;
    List<Team> teamList = new ArrayList<>();
    ProgressBar pbRV;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_premiere, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        pbRV = view.findViewById(R.id.pbRV);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        teamAdapter = new TeamAdapter(teamList);
        recyclerView.setAdapter(teamAdapter);

        getTeamData();

        return view;
    }

    private void getTeamData() {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
        Call<TeamResponse> call = apiService.getTeams("English Premier League");

        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    recyclerView.setVisibility(View.VISIBLE);
                    pbRV.setVisibility(View.GONE);

                    teamList.clear();
                    teamList.addAll(response.body().teams);
                    teamAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Toast.makeText(getContext(), "GAGAL: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
