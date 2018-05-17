package com.example.latte_lib.login;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;

import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_lib.R;
import com.orhanobut.logger.Logger;

public class LoginDelegate extends LatteDelegate implements View.OnClickListener {
    private AppCompatEditText tv_name, tv_pwd;
    private AppCompatTextView tv_to_register;
    private AppCompatButton btn_login;
    @Override
    protected void onBinderView(Bundle savedInstanceState, View rootView) {
        initView(rootView);
        initEvents();
    }

    private void initEvents() {
        tv_to_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    private void initView(View rootView) {
        tv_name = rootView.findViewById(R.id.tv_name);
        tv_pwd = rootView.findViewById(R.id.tv_pwd);
        tv_to_register = rootView.findViewById(R.id.tv_to_register);
        btn_login = rootView.findViewById(R.id.btn_login);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_login;
    }

    @Override
    public void onClick(View v) {
        Logger.d("我草，这么牛逼！！！");
        Logger.d("fucking%s","草....");
        int id = v.getId();
        if(id == R.id.tv_to_register){
            //跳转到注册页面
            startWithPop(new RegisterDelegate());
        }else if (id == R.id.btn_login){
            if(checkParams()){
                //Todo 执行登录操作1111111

            }
        }


    }

    private boolean checkParams() {
        boolean isFalse = true;
        if(TextUtils.isEmpty(tv_name.getText().toString().trim())){
            tv_name.setError("用户名为空");
            isFalse = false;
        }else{
            tv_name.setError(null);
        }

        if(TextUtils.isEmpty(tv_pwd.getText().toString().trim())){
            tv_pwd.setError("密码为空");
            isFalse = false;
        }else{
            tv_pwd.setError(null);
        }
        return isFalse;
    }
}
