package com.mmreda.mohamedredatask.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withStarted
import com.developnetwork.universe.base.BaseViewModel
import com.mmreda.mohamedredatask.utils.ProgressDialog
import com.mmreda.mohamedredatask.utils.makeSnackBarShort
import com.mmreda.mohamedredatask.utils.makeToastShort
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {

    private var loadingDialog: ProgressDialog? = null


    override fun onResume() {
        super.onResume()
        loadingDialog = ProgressDialog(requireContext())
        loadingDialog?.setOnCancelListener {
            try {
                requireActivity().onBackPressed()
            } catch (e: Exception) {
                requireContext().makeToastShort(e.toString())
            }
        }
    }


    open fun showLoading() {
        loadingDialog?.show()
    }

    open fun hideLoading() {
        loadingDialog?.dismiss()
    }


    fun handleProgress(viewModel: BaseViewModel) {
        lifecycleScope.launch {
            withStarted {
                viewModel.showLoading.observe(viewLifecycleOwner) {
                    if (it)
                        showLoading()
                    else
                        hideLoading()
                }
            }
        }
    }

    fun handleError(viewModel: BaseViewModel) {
        lifecycleScope.launch {
            withStarted {
                viewModel.errorLiveData.observe(viewLifecycleOwner) {
                    if (it != null) {
                        view?.makeSnackBarShort(it)
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        loadingDialog = null
    }

}