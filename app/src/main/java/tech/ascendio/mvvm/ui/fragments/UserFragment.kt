package tech.ascendio.mvvm.ui.fragments

import tech.ascendio.mvvm.R
import tech.ascendio.mvvm.databinding.UserFragmentBinding
import tech.ascendio.mvvm.di.Injectable
import tech.ascendio.mvvm.testing.OpenForTesting

@OpenForTesting
class UserFragment : BaseFragment<UserFragmentBinding>(), Injectable {
    override val layoutResource: Int
        get() = R.layout.user_fragment
    override val tag: String
        get() = "UserFragment"

    override fun onBoundViews() {
    }
}