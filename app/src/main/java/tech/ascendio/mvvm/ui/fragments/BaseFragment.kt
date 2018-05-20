package tech.ascendio.mvvm.ui.fragments

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.ascendio.mvvm.ui.activities.MainActivity
import tech.ascendio.mvvm.util.AutoClearedValue
import tech.ascendio.mvvm.util.binding.FragmentDataBindingComponent

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    @get:LayoutRes
    internal abstract val layoutResource: Int

    // tag is not a companion object because it should be used only from a BaseFragment reference
    internal abstract val tag: String

    lateinit var viewDataBinding: T
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    @VisibleForTesting
    var binding: AutoClearedValue<T>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResource, container, false, dataBindingComponent)
        binding = AutoClearedValue(this)
        cancelFragmentClickEvents(viewDataBinding.root)
        return viewDataBinding.root
    }

    /**
     * When two fragments are overlapping into a container, the one that's behind can still receive
     * click events if the one that's on front does not become clickable.
     */
    private fun cancelFragmentClickEvents(view: View) {
        view.isClickable = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            view.focusable = View.FOCUSABLE
        }
    }

    /**
     * Using Kotlin Extensions we can achieve View Binding after view is created. Make sure that
     * every view method call is done after this method is called.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBoundViews()
    }


    protected fun onBackPressed() {
        activity?.onBackPressed()
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }

    internal abstract fun onBoundViews()
}