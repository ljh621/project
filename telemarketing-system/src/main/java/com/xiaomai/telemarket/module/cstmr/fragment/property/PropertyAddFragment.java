package com.xiaomai.telemarket.module.cstmr.fragment.property;

import com.jinggan.library.base.EventBusValues;
import com.jinggan.library.net.retrofit.RemetoRepoCallback;
import com.jinggan.library.ui.dialog.DialogFactory;
import com.xiaomai.telemarket.api.Responese;
import com.xiaomai.telemarket.module.cstmr.CusrometDetailsActivity;
import com.xiaomai.telemarket.module.cstmr.data.PropertyEntity;
import com.xiaomai.telemarket.module.cstmr.data.repo.CusrometRemoteRepo;

import org.greenrobot.eventbus.EventBus;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/5/20 22:12
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class PropertyAddFragment extends PropertyBaseFragment implements RemetoRepoCallback<Responese> {

    @Override
    public void onSubmit() {
        super.onSubmit();
        dialog = DialogFactory.createLoadingDialog(getActivity(), "提交...");
        PropertyEntity propertyEntity=getPropertyEntity();
        propertyEntity.setCustomerID(CusrometDetailsActivity.entity.getID());
        CusrometRemoteRepo.getInstance().addHouse(propertyEntity, this);
    }

    @Override
    public void onSuccess(Responese data) {
        EventBusValues values=new EventBusValues();
        values.setWhat(0x203);
        EventBus.getDefault().post(values);

        showToast("保存成功");
        getActivity().finish();
    }

    @Override
    public void onFailure(int code, String msg) {
        showToast(msg);
    }

    @Override
    public void onThrowable(Throwable t) {
        showToast("提交失败");
    }

    @Override
    public void onUnauthorized() {
        showToast("提交失败");
    }


    @Override
    public void onFinish() {
        DialogFactory.dimissDialog(dialog);
    }

}
