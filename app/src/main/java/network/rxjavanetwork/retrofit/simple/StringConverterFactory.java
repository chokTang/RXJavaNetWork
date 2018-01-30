package network.rxjavanetwork.retrofit.simple;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by FHMY on 2017/7/13.
 */

public final class StringConverterFactory extends Converter.Factory{

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class){
            return StringConverter.INSTANCE;
        }
        return null;
    }


}
