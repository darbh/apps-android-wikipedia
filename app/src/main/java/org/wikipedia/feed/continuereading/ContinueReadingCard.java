package org.wikipedia.feed.continuereading;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.wikipedia.feed.model.Card;
import org.wikipedia.history.HistoryEntry;

import java.util.concurrent.TimeUnit;

public class ContinueReadingCard extends Card {
    @NonNull private final HistoryEntry entry;

    public ContinueReadingCard(@NonNull HistoryEntry entry) {
        this.entry = entry;
    }

    @Override @NonNull public String title() {
        return entry.getTitle().getDisplayText();
    }

    @Override @Nullable public String subtitle() {
        return entry.getTitle().getDescription();
    }

    @Override @Nullable public Uri image() {
        return TextUtils.isEmpty(entry.getTitle().getThumbUrl()) ? null : Uri.parse(entry.getTitle().getThumbUrl());
    }

    /** @return The last visit age in days. */
    public long daysOld() {
        long now = System.currentTimeMillis();
        long lastVisited = entry.getTimestamp().getTime();
        return TimeUnit.MILLISECONDS.toDays(now - lastVisited);
    }
}