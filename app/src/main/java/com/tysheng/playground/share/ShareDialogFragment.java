package com.tysheng.playground.share;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by tysheng
 * Date: 3/10/17 10:09 AM.
 * Email: tyshengsx@gmail.com
 */

public class ShareDialogFragment extends BottomSheetDialogFragment {
    public static final String TAG = "ShareDialogFragment";
    public static final String MESSAGE = "MESSAGE";
    private BottomSheetBehavior mBehavior;
    private ShareMessage mShareMessage;

    public static ShareDialogFragment newInstance(ShareMessage message) {
        Bundle args = new Bundle();
        args.putSerializable(MESSAGE, message);
        ShareDialogFragment fragment = new ShareDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        //默认全屏展开
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void dismissDialog() {
        //点击任意布局关闭
        mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        Context context = getContext();
        mShareMessage = (ShareMessage) getArguments().getSerializable(MESSAGE);
        ShareViewPager view = new ShareViewPager(context);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.setData(ShareUtils.getShareAppList(context));

        view.setOnAppClickListener(new ShareViewPager.onAppClickListener() {
            @Override
            public void onClick(int position, ShareAppInfo shareAppInfo) {
                Log.d(TAG, "onClick: " + position + " msg: " + shareAppInfo.toString());
                Observable.just(shareAppInfo)
                        .map(new Function<ShareAppInfo, Intent>() {
                            @Override
                            public Intent apply(@io.reactivex.annotations.NonNull ShareAppInfo shareAppInfo) throws Exception {
                                return ShareUtils.handleIntentByPlatform(getContext(),shareAppInfo, mShareMessage);
                            }
                        })
                        .filter(new Predicate<Intent>() {
                            @Override
                            public boolean test(@io.reactivex.annotations.NonNull Intent intent) throws Exception {
                                return ShareUtils.checkIntentHandle(getContext(), intent);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ResourceObserver<Intent>() {
                            final ProgressDialogFragment fragment = new ProgressDialogFragment();

                            @Override
                            protected void onStart() {
                                super.onStart();
                                fragment.show(getChildFragmentManager(), null);
                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull Intent intent) {
                                fragment.dismiss();
                                startActivity(intent);
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                fragment.dismiss();
                                Log.d(TAG, "onError: " + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());

        return dialog;
    }
}
