// Generated by Dagger (https://dagger.dev).
package com.swapna.metaweather.data.repository;

import com.swapna.metaweather.data.network.WeatherApiService;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class WeatherRepository_MembersInjector implements MembersInjector<WeatherRepository> {
  private final Provider<WeatherApiService> apiServiceProvider;

  public WeatherRepository_MembersInjector(Provider<WeatherApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  public static MembersInjector<WeatherRepository> create(
      Provider<WeatherApiService> apiServiceProvider) {
    return new WeatherRepository_MembersInjector(apiServiceProvider);}

  @Override
  public void injectMembers(WeatherRepository instance) {
    injectApiService(instance, apiServiceProvider.get());
  }

  @InjectedFieldSignature("com.swapna.metaweather.data.repository.WeatherRepository.apiService")
  public static void injectApiService(WeatherRepository instance, WeatherApiService apiService) {
    instance.apiService = apiService;
  }
}