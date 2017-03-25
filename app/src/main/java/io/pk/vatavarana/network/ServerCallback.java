package io.pk.vatavarana.network;

import io.pk.vatavarana.model.CurrentObservation;

public interface ServerCallback {
    void onSuccess(CurrentObservation weathers);
}
