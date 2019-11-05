package com.blackice.business.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blackice.business.view.activity.article.ArticleListActivity
import com.blackice.business.view.activity.article.ArticleListViewmodel
import com.blackice.business.view.activity.article_details.ArticleDetailsViewmodel
import com.blackice.business.view.activity.login.LoginViewModel
import com.blackice.business.view.activity.main.MainViewModel
import com.blackice.business.view.base.BaseViewmodelFactory
import com.blackice.business.view.fragment.login.LoginFragment
import com.blackice.business.view.fragment.login.LoginFragmentViewModel
import com.blackice.business.view.fragment.register.RegisterViewmodel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: BaseViewmodelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun postLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun postMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentViewModel::class)
    internal abstract fun postLoginFragmentViewModel(viewModel: LoginFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewmodel::class)
    internal abstract fun postRegisterViewmodel(viewModel: RegisterViewmodel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleListViewmodel::class)
    internal abstract fun postArticleListViewmodel(viewModel: ArticleListViewmodel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ArticleDetailsViewmodel::class)
    internal abstract fun postArticleDetailsViewmodel(viewModel: ArticleDetailsViewmodel): ViewModel

}