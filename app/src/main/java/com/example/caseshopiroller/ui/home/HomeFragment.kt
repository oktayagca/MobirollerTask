package com.example.caseshopiroller.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caseshopiroller.R
import com.example.caseshopiroller.data.entity.DataForCategory
import com.example.caseshopiroller.data.entity.DataProductsForAnCategory
import com.example.caseshopiroller.databinding.FragmentHomeBinding
import com.example.caseshopiroller.ui.MainActivity
import com.example.caseshopiroller.ui.adapter.DrawableMenuRecyclerViewAdapter
import com.example.caseshopiroller.ui.adapter.IDrawableMenuItemClickListener
import com.example.caseshopiroller.ui.adapter.IProductsItemClickListener
import com.example.caseshopiroller.ui.adapter.ProductsRecyclerViewAdapter
import com.example.caseshopiroller.ui.base.BaseFragment
import com.example.caseshopiroller.ui.model.SortParameterEnum
import com.example.caseshopiroller.utils.Resource
import com.example.caseshopiroller.utils.navigateFragment
import com.example.caseshopiroller.utils.showToastMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), IDrawableMenuItemClickListener,
    IProductsItemClickListener {

    private val viewModel: HomeViewModel by viewModels()
    private var drawerMenuRecyclerViewAdapter = DrawableMenuRecyclerViewAdapter()
    private var productsRecyclerViewAdapter = ProductsRecyclerViewAdapter()
    var mDrawerToggle: ActionBarDrawerToggle? = null
    private var selectedCategoryId = ""
    private var selectedCategoryName = ""
    private var sortvalue= SortParameterEnum.PublishmentDate.name

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onFragmentCreate() {
        super.onFragmentCreate()
        setupToolbar()
        observeCategories()
        setupDrawerToggle()
    }

    override fun onResume() {
        super.onResume()
        initSortView()
    }
    private fun observeCategories() {
        viewModel.observeCategories().observe(this) { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    (requireActivity() as MainActivity).showLoadingProgressBar(true)
                }
                Resource.Status.SUCCESS -> {
                    initCategoriesRecyclerView(response.data!!.data!!)
                    response.data.data!![0].let {
                        observeProductsForAnCategory(
                            it.categoryId!!,
                            it.name!!,
                            sortvalue
                        )
                    }
                }
                Resource.Status.ERROR -> {
                    (requireActivity() as MainActivity).showLoadingProgressBar(false)
                    showToastMessage(response.message!!)
                }
            }
        }
    }

    private fun observeProductsForAnCategory(
        categoryId: String,
        categoryName: String,
        sort: String
    ) {
        val query = mapOf("categoryId" to categoryId, "sort" to sort)
        selectedCategoryId = categoryId
        selectedCategoryName = selectedCategoryName
        viewModel.observeProductsForAnCategory(query).observe(this) { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    (requireActivity() as MainActivity).showLoadingProgressBar(true)
                }
                Resource.Status.SUCCESS -> {
                    (requireActivity() as MainActivity).showLoadingProgressBar(false)
                    (requireActivity() as MainActivity).setToolbarTitle(categoryName)
                    response.data?.data?.let { initProductsRecyclerView(it) }
                }
                Resource.Status.ERROR -> {
                    (requireActivity() as MainActivity).showLoadingProgressBar(false)
                    showToastMessage(response.message!!)
                }
            }
        }
    }

    private fun initCategoriesRecyclerView(data: List<DataForCategory>) {
        binding.apply {
            leftDrawerRecyclerview.adapter = drawerMenuRecyclerViewAdapter
            leftDrawerRecyclerview.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
        }
        drawerMenuRecyclerViewAdapter.setData(data, this)

    }

    private fun initProductsRecyclerView(data: List<DataProductsForAnCategory>) {
        binding.apply {
            contentRecyclerView.adapter = productsRecyclerViewAdapter
            val layoutManager = GridLayoutManager(context, 3)
            contentRecyclerView.layoutManager = layoutManager
        }

        productsRecyclerViewAdapter.setData(data, this)
    }

    private fun setupToolbar() {
        (requireActivity() as MainActivity).setupToolbar(binding.toolbar.toolbar)
    }

    private fun setupDrawerToggle() {
        mDrawerToggle = ActionBarDrawerToggle(
            requireActivity(),
            binding.drawerLayout,
            binding.toolbar.toolbar,
            R.string.app_name,
            R.string.app_name
        )
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle!!.syncState()
    }

    private fun initSortView() {
        val options = resources.getStringArray(R.array.sortingOptions)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, options)
        binding.apply {
            autoCompleteTextView.setAdapter(arrayAdapter)
            autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
                when (position) {
                    0 -> {
                        sortvalue=SortParameterEnum.PublishmentDate.name
                        val query =
                            mapOf("categoryId" to selectedCategoryId, "sort" to "PublishmentDate")
                        viewModel.getProductsForAnCategory(query)
                        observeProductsForAnCategory(
                            selectedCategoryId,
                            selectedCategoryName,
                            "PublishmentDate"
                        )
                    }
                    1 -> {
                        sortvalue=SortParameterEnum.Title.name
                        val query = mapOf("categoryId" to selectedCategoryId, "sort" to "Title")
                        viewModel.getProductsForAnCategory(query)
                        observeProductsForAnCategory(
                            selectedCategoryId,
                            selectedCategoryName,
                            "Title"
                        )
                    }
                    2 -> {
                        sortvalue=SortParameterEnum.Price.name
                        val query = mapOf("categoryId" to selectedCategoryId, "sort" to "Price")
                        viewModel.getProductsForAnCategory(query)
                        observeProductsForAnCategory(
                            selectedCategoryId,
                            selectedCategoryName,
                            "Price"
                        )
                    }
                }
            }
        }
    }


    override fun onClick(item: DataForCategory) {
        val query = mapOf("categoryId" to item.categoryId!!, "sort" to sortvalue)
        viewModel.getProductsForAnCategory(query)
        observeProductsForAnCategory(
            item.categoryId,
            item.name!!,
            SortParameterEnum.PublishmentDate.name
        )
    }

    override fun onClick(item: DataProductsForAnCategory) {
        val navDirection =
            HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(
                item.id.toString()
            )
        navigateFragment(navDirection = navDirection)
    }


}