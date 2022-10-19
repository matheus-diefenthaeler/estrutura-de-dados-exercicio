package data.structures;

public class app {
    public static void main(String[] args) {


        FilaImpl<Integer> fila = new FilaImpl(3);

//        System.out.println(fila.isEmpty());
//        System.out.println(fila.isFull());
//        System.out.println(fila.size());
//        System.out.println(fila.());

//
        fila.enqueue(10);
        fila.enqueue(20);
        fila.enqueue(30);
        System.out.println(fila.front());


        fila.dequeue();
        fila.enqueue(40);

        System.out.println("Agora o primeiro eh : " + fila.front());

        System.out.println(fila);
    }
}
