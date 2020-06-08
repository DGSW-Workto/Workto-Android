package com.example.workto_android.di

import androidx.room.Room
import com.example.data.CommonMessageMapper
import com.example.data.SharedPreferenceStorage
import com.example.data.login.LoginApi
import com.example.data.login.LoginDataMapper
import com.example.data.login.LoginRemoteDataSource
import com.example.data.login.LoginRepositoryImpl
import com.example.domain.login.LoginRepository
import com.example.domain.TokenManager
import com.example.domain.login.JoinUseCase
import com.example.domain.login.LoginUseCase
import com.example.workto_android.ui.login.JoinViewModel
import com.example.workto_android.ui.login.LoginViewModel
import com.example.workto_android.util.BASE_URL
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofit: Retrofit = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient.Builder()
            .addInterceptor(TokenManager.getAuthenticationInterceptor())
            .build()
    )
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

private val loginApi = retrofit.create(LoginApi::class.java)

val networkModule = module {
    factory { loginApi }
}

val dataSourceModule = module {
    factory { LoginRemoteDataSource(get()) }
}

val mapperModule = module {
    factory { CommonMessageMapper() }
    factory { LoginDataMapper() }
}

val repositoryModule = module {
    factory<LoginRepository> { LoginRepositoryImpl(get(),get(), get()) }
}

val useCaseModule = module {
    factory { JoinUseCase(get()) }
    factory { LoginUseCase(get()) }
}

val viewModelModule = module {
    factory { LoginViewModel(get()) }
    factory { JoinViewModel(get()) }
}


val dbModule = module {
    single { SharedPreferenceStorage(androidContext()) }
}


val moduleList = listOf(viewModelModule, networkModule, dataSourceModule, mapperModule, repositoryModule, useCaseModule, dbModule)