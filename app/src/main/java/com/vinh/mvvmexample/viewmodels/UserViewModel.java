package com.vinh.mvvmexample.viewmodels;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.vinh.mvvmexample.BR;
import com.vinh.mvvmexample.models.User;

public class UserViewModel extends BaseObservable {

    private User user;

    public UserViewModel(String name, int age) {
        user = new User(name, age);
    }

    @Bindable
    public String getUserName() {
        return user.getName();
    }

    @Bindable
    public void setUserName(String name) {
        user.setName(name);
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserAge() {
        return String.valueOf(user.getAge());
    }

    @Bindable
    public void setUserAge(int age) {
        user.setAge(age);
        notifyPropertyChanged(BR.userAge);
    }

    public void onImageClicked(){
        Log.e("Event", "Image clicked");
    }

}
