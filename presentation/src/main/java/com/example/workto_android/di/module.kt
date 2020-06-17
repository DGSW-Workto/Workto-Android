package com.example.workto_android.di

import com.example.data.CommonMessageMapper
import com.example.data.DateTimeConverter
import com.example.data.SharedPreferenceStorage
import com.example.data.login.LoginApi
import com.example.data.login.LoginDataMapper
import com.example.data.login.LoginRemoteDataSource
import com.example.data.login.LoginRepositoryImpl
import com.example.data.token.TokenApi
import com.example.data.token.TokenDataMapper
import com.example.data.token.TokenRemoteDataSource
import com.example.data.token.TokenRepositoryImpl
import com.example.data.user.UserApi
import com.example.data.user.UserDataMapper
import com.example.data.user.UserRemoteDataSource
import com.example.data.user.UserRepositoryImpl
import com.example.domain.login.LoginRepository
import com.example.domain.TokenManager
import com.example.domain.login.JoinUseCase
import com.example.domain.login.LoginUseCase
import com.example.domain.network.GetNetworkStateUseCase
import com.example.domain.network.NetworkManager
import com.example.data.post.PostApi
import com.example.data.post.PostListMapper
import com.example.data.post.PostRemoteDataSource
import com.example.data.post.PostRepositoryImpl
import com.example.data.team.TeamApi
import com.example.data.team.TeamListMapper
import com.example.data.team.TeamRemoteDataSource
import com.example.data.team.TeamRepositoryImpl
import com.example.domain.post.GetPostListUseCase
import com.example.domain.post.PostRepository
import com.example.domain.team.GetTeamListUseCase
import com.example.domain.team.TeamRepository
import com.example.domain.token.CheckTokenUseCase
import com.example.domain.token.GetTokenUseCase
import com.example.domain.token.SaveTokenUseCase
import com.example.domain.token.TokenRepository
import com.example.domain.user.GetUserDataUseCase
import com.example.domain.user.UserRepository
import com.example.workto_android.ui.login.JoinViewModel
import com.example.workto_android.ui.login.LoginViewModel
import com.example.workto_android.ui.main.MainMenuFragment
import com.example.workto_android.ui.main.MainViewModel
import com.example.workto_android.ui.main.PostListFragment
import com.example.workto_android.ui.main.TeamListFragment
import com.example.workto_android.ui.splash.SplashViewModel
import com.example.workto_android.util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val interceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.HEADERS
}

val retrofit: Retrofit = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient.Builder()
            .addInterceptor(TokenManager.getAuthenticationInterceptor())
            .addInterceptor(interceptor)
            .build()
    )
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

private val loginApi = retrofit.create(LoginApi::class.java)
private val tokenAPi = retrofit.create(TokenApi::class.java)
private val userApi = retrofit.create(UserApi::class.java)
private val postApi = retrofit.create(PostApi::class.java)
private val teamApi = retrofit.create(TeamApi::class.java)

val networkModule = module {
    factory { loginApi }
    factory { tokenAPi }
    factory { userApi }
    factory { postApi }
    factory { teamApi }
}

val dataSourceModule = module {
    factory { LoginRemoteDataSource(get()) }
    factory { TokenRemoteDataSource(get()) }
    factory { UserRemoteDataSource(get()) }
    factory { PostRemoteDataSource(get()) }
    factory { TeamRemoteDataSource(get()) }
}

val mapperModule = module {
    factory { CommonMessageMapper() }
    factory { LoginDataMapper() }
    factory { TokenDataMapper() }
    factory { UserDataMapper() }
    factory { PostListMapper(get()) }
    factory { DateTimeConverter() }
    factory { TeamListMapper() }
}

val repositoryModule = module {
    factory<LoginRepository> { LoginRepositoryImpl(get(), get(), get()) }
    factory<TokenRepository> { TokenRepositoryImpl(get(), get(), get()) }
    factory<UserRepository> { UserRepositoryImpl(get(), get()) }
    factory<PostRepository> { PostRepositoryImpl(get(),get()) }
    factory<TeamRepository> { TeamRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    factory { JoinUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { GetTokenUseCase(get()) }
    factory { SaveTokenUseCase(get()) }
    factory { CheckTokenUseCase(get()) }
    factory { GetNetworkStateUseCase(get()) }
    factory { GetUserDataUseCase(get()) }
    factory { GetPostListUseCase(get()) }
    factory { GetTeamListUseCase(get()) }
}

val viewModelModule = module {
    factory { LoginViewModel(get(), get()) }
    factory { JoinViewModel(get()) }
    factory { SplashViewModel(get(), get(), get()) }
    factory { MainViewModel(get(), get(),get()) }
}


val dbModule = module {
    single { SharedPreferenceStorage(androidContext()) }
    single { NetworkManager(androidApplication()) }
}

val fragmentModule = module {
    factory { MainMenuFragment() }
    factory { PostListFragment() }
    factory { TeamListFragment() }
}


val moduleList = listOf(
    viewModelModule,
    networkModule,
    dataSourceModule,
    mapperModule,
    repositoryModule,
    useCaseModule,
    dbModule,
    fragmentModule
)