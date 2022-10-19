package data.structures;

public interface Sortable<T extends Comparable<T>> {

    T[] sort(T[] elements);
}
