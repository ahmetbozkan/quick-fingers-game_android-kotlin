package com.ahmetbozkan.quickfingers.base

import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.ahmetbozkan.quickfingers.BR

abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel> : Fragment() {

    private var _binding: DB? = null
    val binding: DB get() = _binding!!

    protected abstract val viewModel: VM

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initialize(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataBinding()

        initialize(savedInstanceState)

       // manageBackButton()

        setHasOptionsMenu(true)
    }

    private fun setDataBinding() {
        binding.setVariable(BR.viewModel, viewModel)
    }

    /*private fun manageBackButton() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if(this@BaseFragment is StartGameFragment) {
                requireActivity().finish()
            }
            else {
                navigateBack()
            }
        }
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun navigateBack() {
        findNavController().navigateUp()
    }

    protected fun popBackStack() {
        findNavController().popBackStack()
    }

}