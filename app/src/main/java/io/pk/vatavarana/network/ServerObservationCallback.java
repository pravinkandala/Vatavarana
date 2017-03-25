package io.pk.vatavarana.network;

import io.pk.vatavarana.model.CurrentObservation;

public interface ServerObservationCallback {
    void onSuccess(CurrentObservation weathers);
}
