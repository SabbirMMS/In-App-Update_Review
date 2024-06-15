package com.mrmms.inappupdate_review;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.SharedPreferences;

import com.google.android.gms.tasks.Task;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

public class InAppUpdateHelper {

    private static final int REQUEST_CODE = 999;

    private final Activity activity;
    private final AppUpdateManager appUpdateManager;

    public InAppUpdateHelper(Activity activity) {
        this.activity = activity;
        appUpdateManager = AppUpdateManagerFactory.create(activity);
    }

    public void launchInAppUpdate(boolean isImmediate) {
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                if (isImmediate) {
                    startImmediateUpdate(appUpdateInfo);
                } else {
                    startFlexibleUpdate(appUpdateInfo);
                }
            }
        });

        appUpdateManager.registerListener(installState -> {
            if (installState.installStatus() == InstallStatus.DOWNLOADED) {
                appUpdateManager.completeUpdate();
            }
        });
    }

    private void startImmediateUpdate(AppUpdateInfo appUpdateInfo) {
        try {
            appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.IMMEDIATE,
                    activity,
                    REQUEST_CODE
            );
        } catch (IntentSender.SendIntentException ignored) {
        }
    }

    private void startFlexibleUpdate(AppUpdateInfo appUpdateInfo) {
        try {
            appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.FLEXIBLE,
                    activity,
                    REQUEST_CODE
            );
        } catch (IntentSender.SendIntentException ignored) {
        }
    }

    //In AppReView
    public void requestInAppReview(long maxOpen) {

        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String key = "InAppUpdateBy@SabbirMMS";
        long count = preferences.getLong(key, 0);


        if (count > maxOpen) {
            ReviewManager manager = ReviewManagerFactory.create(activity);
            Task<ReviewInfo> managerInfoTask = manager.requestReviewFlow();
            managerInfoTask.addOnCompleteListener((task) -> {
                if (task.isSuccessful()) {
                    ReviewInfo reviewInfo = task.getResult();
                    manager.launchReviewFlow(activity, reviewInfo);
                }
            });
        }
        editor.putLong(key, ++count).apply();
    }//InAppReview

}
