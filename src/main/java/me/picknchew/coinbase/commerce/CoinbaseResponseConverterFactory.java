package me.picknchew.coinbase.commerce;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class CoinbaseResponseConverterFactory extends Converter.Factory {

    private CoinbaseResponseConverterFactory() {
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type, Annotation[] annotations, Retrofit retrofit) {
        Type wrappedType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] {type};
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public Type getRawType() {
                return CoinbaseResponse.class;
            }
        };

        Converter<ResponseBody, CoinbaseResponse> delegate = retrofit.nextResponseBodyConverter(this, wrappedType, annotations);
        return new CoinbaseResponseBodyConverter(delegate);
    }

    static class CoinbaseResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        final Converter<ResponseBody, CoinbaseResponse> delegate;

        CoinbaseResponseBodyConverter(Converter<ResponseBody, CoinbaseResponse> delegate) {
            this.delegate = delegate;
        }

        @Override
        public T convert(ResponseBody responseBody) throws IOException {
            CoinbaseResponse<T> response = delegate.convert(responseBody);
            return response.data;
        }
    }

    public static CoinbaseResponseConverterFactory create() {
        return new CoinbaseResponseConverterFactory();
    }
}
