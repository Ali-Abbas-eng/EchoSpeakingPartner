package com.example.client.Logic.Accounts;

import androidx.annotation.NonNull;

public class LoginResult {
    String key;
    String non_field_errors;

    public String getNon_field_errors() {
        return non_field_errors;
    }

    public void setNon_field_errors(String non_field_errors) {
        this.non_field_errors = non_field_errors;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @NonNull
    @Override
    public String toString(){
        return (this.key != null)? this.key : this.non_field_errors;
    }
}
