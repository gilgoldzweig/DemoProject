package com.example.gilgoldzweig.juul.demo.activities.details

import com.example.gilgoldzweig.juul.demo.base.BasePresenter

class DetailsActivityPresenter(private val activity: DetailsActivity) :
    BasePresenter<DetailsActivityPresenterCallback> {

    override var callback: DetailsActivityPresenterCallback? = activity

}