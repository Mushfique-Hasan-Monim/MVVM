package com.blackice.business.di

import com.blackice.business.view.activity.article.ArticleListActivity
import com.blackice.business.view.activity.article_details.ArticleDetailsActivity
import com.blackice.business.view.activity.login.LoginActivity
import com.blackice.business.view.activity.main.MainActivity
import com.blackice.business.view.fragment.login.LoginFragment
import com.blackice.business.view.fragment.register.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeArticleDetailsActivity(): ArticleDetailsActivity

    @ContributesAndroidInjector
    internal abstract fun contributeArticleListActivity(): ArticleListActivity

    @ContributesAndroidInjector
    internal abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    internal abstract fun contributeRegisterFragment(): RegisterFragment

}