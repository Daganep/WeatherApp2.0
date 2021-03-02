package com.penkin.weatherapp20.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.penkin.weatherapp20.databinding.FragmentMainBinding;
import com.penkin.weatherapp20.model.entities.CurrentResponseInfo;
import com.penkin.weatherapp20.presenter.MainPresenter;
import com.penkin.weatherapp20.utils.ImageSetter;

import java.util.Objects;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class MainFragment extends MvpAppCompatFragment implements MainView {

    @InjectPresenter
    MainPresenter presenter;
    private FragmentMainBinding mainBinding;
    private ImageSetter imageSetter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false);
        return mainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        imageSetter = new ImageSetter();
        initToolbar();
        presenter.requestData();
    }

    private void initToolbar(){
        ((AppCompatActivity) requireActivity()).setSupportActionBar(mainBinding.mainToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void renderWeather(CurrentResponseInfo response){
        mainBinding.curCityNameMF.setText(response.getCityName());
        mainBinding.curTempMF.setText(response.getCurrentTemp());
        imageSetter.setImage(response.getCurrentImagePath(), mainBinding.curTempImageMF);
        mainBinding.curTempDescriptMF.setText(response.getCurrentTempDescription());
        mainBinding.curTempSensMF.setText(response.getGetCurrentTempSens());
        mainBinding.windSpeedMeanMF.setText(response.getWindSpeed());
        mainBinding.pressureMeanMF.setText(response.getPressure());
        mainBinding.humidityMeanMF.setText(response.getHumidity());

        //later code below will be delete (view will be initialize if RecyclerView)
        mainBinding.tomorrowDateMF.setText(response.getOtherDayDate(1));
        mainBinding.tomorrowMF.setText(response.getOtherDay(1));
        imageSetter.setImage(response.getOtherDayImagePath(1), mainBinding.tomorrowTempImageMF);
        mainBinding.tomorrowDayTempMF.setText(response.getOtherDayTemp(1));
        mainBinding.tomorrowNightTempMF.setText(response.getOtherNightTemp(1));

        mainBinding.day3DateMF.setText(response.getOtherDayDate(2));
        mainBinding.day3MF.setText(response.getOtherDay(2));
        imageSetter.setImage(response.getOtherDayImagePath(2), mainBinding.day3TempImageMF);
        mainBinding.day3DayTempMF.setText(response.getOtherDayTemp(2));
        mainBinding.day3NightTempMF.setText(response.getOtherNightTemp(2));

        mainBinding.day4DateMF.setText(response.getOtherDayDate(3));
        mainBinding.day4MF.setText(response.getOtherDay(3));
        imageSetter.setImage(response.getOtherDayImagePath(3), mainBinding.day4TempImageMF);
        mainBinding.day4DayTempMF.setText(response.getOtherDayTemp(3));
        mainBinding.day4NightTempMF.setText(response.getOtherNightTemp(3));

        mainBinding.day5DateMF.setText(response.getOtherDayDate(4));
        mainBinding.day5MF.setText(response.getOtherDay(4));
        imageSetter.setImage(response.getOtherDayImagePath(4), mainBinding.day5TempImageMF);
        mainBinding.day5DayTempMF.setText(response.getOtherDayTemp(4));
        mainBinding.day5NightTempMF.setText(response.getOtherNightTemp(4));
    }

    @Override
    public void showError(int msg) {
        mainBinding.mainScreen.setVisibility(View.GONE);
        mainBinding.emptyResult.setText(msg);
        mainBinding.emptyResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainBinding = null;
    }
}
