package model;

import javafx.collections.ObservableList;

public final class DivisionListSingleton {
    private ObservableList<Division> divisionObservableList;

    private final static DivisionListSingleton INSTANCE = new DivisionListSingleton();

    private DivisionListSingleton(){}

    public static DivisionListSingleton getInstance(){
        return INSTANCE;
    }

    public void setDivisionObservableList(ObservableList<Division> divisionObservableList){
        this.divisionObservableList = divisionObservableList;
    }

    public ObservableList<Division> getDivisionObservableList(){
        return this.divisionObservableList;
    }
}
