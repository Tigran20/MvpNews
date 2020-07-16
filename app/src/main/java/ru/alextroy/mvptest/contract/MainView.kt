package ru.alextroy.mvptest.contract

interface MainView {
    fun showProgress()
    fun hideProgress()
    fun setData()
    fun setDataError(strError: String)
}