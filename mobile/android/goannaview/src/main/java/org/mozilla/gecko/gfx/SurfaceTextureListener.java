/* -*- Mode: Java; c-basic-offset: 4; tab-width: 4; indent-tabs-mode: nil; -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.goanna;

import org.mozilla.goanna.annotation.WrapForJNI;
import org.mozilla.goanna.mozglue.JNIObject;

import android.graphics.SurfaceTexture;

final class SurfaceTextureListener
    extends JNIObject implements SurfaceTexture.OnFrameAvailableListener
{
    @WrapForJNI(calledFrom = "goanna")
    private SurfaceTextureListener() {
    }

    @Override
    protected void disposeNative() {
        // SurfaceTextureListener is disposed inside AndroidSurfaceTexture.
        throw new IllegalStateException("unreachable code");
    }

    @WrapForJNI(stubName = "OnFrameAvailable")
    private native void nativeOnFrameAvailable();

    @Override // SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        try {
            nativeOnFrameAvailable();
        } catch (final NullPointerException e) {
            // Ignore exceptions caused by a disposed object, i.e.
            // getting a callback after this listener is no longer in use.
        }
    }
}
