// Generated by Dagger (https://dagger.dev).
package com.swapna.metaweather.data.network;

import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class WeatherApiService_MembersInjector implements MembersInjector<WeatherApiService> {
  private final Provider<WeatherApi> apiProvider;

  public WeatherApiService_MembersInjector(Provider<WeatherApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  public static MembersInjector<WeatherApiService> create(Provider<WeatherApi> apiProvider) {
    return new WeatherApiService_MembersInjector(apiProvider);}

  @Override
  public void injectMembers(WeatherApiService instance) {
    injectApi(instance, apiProvider.get());
  }

  @InjectedFieldSignature("com.swapna.metaweather.data.network.WeatherApiService.api")
  public static void injectApi(WeatherApiService instance, WeatherApi api) {
    instance.api = api;
  }
}
