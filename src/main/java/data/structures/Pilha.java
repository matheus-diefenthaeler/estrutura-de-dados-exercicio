package data.structures;

public interface Pilha<T> {

    void push(T data);

    T pop();

    T top();

    int size();

    boolean isEmpty();

    boolean isFull();
}
