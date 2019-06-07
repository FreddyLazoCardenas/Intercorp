package com.papps.freddy_lazo.intercorp.presenter;

import com.papps.freddy_lazo.intercorp.view.interfaces.BaseView;

interface Presenter<T extends BaseView> {

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();

    /**
     * Setter of the view.
     */
    void setView(T view);
}
