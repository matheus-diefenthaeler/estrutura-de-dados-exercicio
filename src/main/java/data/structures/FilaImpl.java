package data.structures;

import java.util.Arrays;

public class FilaImpl<T> implements Fila<T> {

    private final T[] elements;
    private int front;
    private int rear;
    private int currentQueueSize;

    public FilaImpl(int size) {
        if (size <= 0) throw new RuntimeException("Tamanho invalido: " + size);
        this.elements = (T[]) new Object[size];
        this.front = -1;
        this.rear = -1;
        this.currentQueueSize = 0;
    }

    @Override
    public void enqueue(T data) {
        if (!isFull()) {
            System.out.println("Adicionando " + data);
            this.rear = (this.rear + 1) % size();
            this.elements[rear] = data;
            this.currentQueueSize++;

            if (front == -1) {
                front++;
            }

        } else {
            throw new RuntimeException("Fila cheia");
        }
    }

    @Override
    public T dequeue() {
        if (!isEmpty()) {
            T data = elements[front];

            System.out.println("Removendo " + data);

            elements[front] = null;
            this.front = (this.front + 1) % size();
            currentQueueSize--;
            return data;
        } else {
            throw new RuntimeException("Fila vazia");
        }
    }

    @Override
    public T front() {
        if (!isEmpty()) {
            return elements[front];
        }
        return null;
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        return (currentQueueSize == 0);
    }

    @Override
    public boolean isFull() {
        return (currentQueueSize == elements.length);
    }

//        return front == rear;


    @Override
    public String toString() {
        return "FilaImpl{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}
