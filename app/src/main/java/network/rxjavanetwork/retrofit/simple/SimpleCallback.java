package network.rxjavanetwork.retrofit.simple;

public interface SimpleCallback<T> {
    void onStart();
    void onNext(T t);
    void onComplete();
}
