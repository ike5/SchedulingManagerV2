package model;

import javafx.collections.ObservableList;

public final class CountryListSingleton {
    private ObservableList<Country> countryObservableList;

    private final static CountryListSingleton INSTANCE = new CountryListSingleton();

    private CountryListSingleton(){}

    public static CountryListSingleton getInstance(){
        return INSTANCE;
    }

    public void setCountryObservableList(ObservableList<Country> countryObservableList){
        this.countryObservableList = countryObservableList;
    }

    public ObservableList<Country> getCountryObservableList(){
        return this.countryObservableList;
    }
}
