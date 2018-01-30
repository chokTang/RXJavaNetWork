package simple;

public interface SimpleCallback<T> {
    void onStart();

    void onNext(T t);

    void onComplete();

    void onError();
}
