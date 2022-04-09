package com.gabrielbog.openstream.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ServerResponseViewModel extends ViewModel {
    private MutableLiveData<String> serverResponse = new MutableLiveData<>("");

    public MutableLiveData<String> getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(String value){
        serverResponse.setValue(value);
    }
}
