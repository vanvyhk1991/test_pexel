package com.app.testpexel.base

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Job

abstract class BaseViewModel<T : Any> : ViewModel() {
    private val lazyJobs = lazy(LazyThreadSafetyMode.NONE) { HashSet<Job>() }
    private val jobs by lazyJobs
    protected val dataI: MutableLiveData<T> = MutableLiveData()
    val dataO: LiveData<T> = dataI
    protected val loadingI: MutableLiveData<Boolean> = MutableLiveData()
    val loadingO: LiveData<Boolean> = loadingI
    protected val errorI: MutableLiveData<Throwable> = MutableLiveData()
    val errorO: LiveData<Throwable> = errorI
    protected val compositeDisposable = CompositeDisposable()

    @MainThread
    protected fun Job.addToJobDispose() = apply { jobs += this }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        if (lazyJobs.isInitialized()) {
            jobs.forEach { it.cancel() }
            jobs.clear()
        }
    }
}
