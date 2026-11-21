/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.applications;

import android.content.Context;
import android.os.Bundle;

import androidx.preference.Preference;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.dashboard.profileselector.ProfileSelectFragment;

/**
 * A preference controller for the cloned apps entry.
 */
public class ClonedAppsPreferenceController extends BasePreferenceController {
    private Context mContext;

    public ClonedAppsPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
        mContext = context;
    }

    @Override
    public int getAvailabilityStatus() {
        return mContext.getResources().getBoolean(R.bool.config_cloned_apps_page_enabled)
                ? AVAILABLE : UNSUPPORTED_ON_DEVICE;
    }

    @Override
    public boolean handlePreferenceTreeClick(Preference preference) {
        // Add this extra so that work tab is not displayed on Cloned Apps page.
        if (getPreferenceKey().equals(preference.getKey())) {
            final Bundle extras = preference.getExtras();
            extras.putInt(ProfileSelectFragment.EXTRA_PROFILE,
                    ProfileSelectFragment.ProfileType.PERSONAL);
        }

        return super.handlePreferenceTreeClick(preference);
    }
}
