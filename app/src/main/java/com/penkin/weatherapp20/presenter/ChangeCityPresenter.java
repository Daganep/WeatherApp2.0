package com.penkin.weatherapp20.presenter;

import com.penkin.weatherapp20.model.SettingsSingleton;
import com.penkin.weatherapp20.view.changecity.ChangeCityView;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class ChangeCityPresenter extends MvpPresenter<ChangeCityView> {
    public void setCurrentCity(String cityName){
        SettingsSingleton.setCurrentCity(cityName);
    }
}