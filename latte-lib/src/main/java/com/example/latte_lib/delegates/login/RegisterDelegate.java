package com.example.latte_lib.delegates.login;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;

import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_lib.R;

public class RegisterDelegate extends LatteDelegate implements View.OnClickListener {

    private AppCompatEditText tv_name, tv_pwd, tv_re_pwd;
    private AppCompatTextView tv_to_login;
    private AppCompatButton btn_register;
    @Override
    protected void onBinderView(Bundle savedInstanceState, View rootView) {
        initView(rootView);
        initEvents();
    }

    private void initEvents() {
        tv_to_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    private void initView(View rootView) {
        tv_name = rootView.findViewById(R.id.tv_name);
        tv_re_pwd = rootView.findViewById(R.id.tv_re_pwd);
        tv_pwd = rootView.findViewById(R.id.tv_pwd);
        tv_to_login = rootView.findViewById(R.id.tv_to_login);
        btn_register = rootView.findViewById(R.id.btn_register);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_register;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.tv_to_login){
            // 跳转到登录页面
            startWithPop(new LoginDelegate());
        }else if (id == R.id.btn_register){
            if(checkParams()){
                //Todo 执行注册操作
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

        if(TextUtils.isEmpty(tv_re_pwd.getText().toString().trim())){
            tv_re_pwd.setError("密码为空");
            isFalse = false;
        }else{
            tv_re_pwd.setError(null);
        }

        if(!tv_re_pwd.getText().toString().trim().equals(tv_pwd.getText().toString().trim())){
            tv_re_pwd.setError("两次输入的密码不一致");
            isFalse = false;
        }else{
            tv_re_pwd.setError(null);
        }
        return isFalse;
    }
}
