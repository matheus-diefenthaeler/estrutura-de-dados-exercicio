package data.structures;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FilaImplTest {

    @Test
    void initializationOk() {
        //given
        int size = random(10);

        //when
        Fila<Integer> fila = new FilaImpl<>(size);

        //then
        assertThat(fila.size()).isEqualTo(size);
        assertThat(fila.isEmpty()).isTrue();
    }

    @Test
    void initializationZeroSizeNotAllowed() {
        //given when then
        assertThatThrownBy(() -> new FilaImpl<>(0))
                .isInstanceOf(RuntimeException.class) ;
    }

    @Test
    void initializationNegativeSizeNotAllowed() {
        //given
        final int size = -random(10);

        //when then
        assertThatThrownBy(() -> new PilhaImpl<>(size))
                .isInstanceOf(RuntimeException.class) ;
    }

    @ParameterizedTest
    @MethodSource("pushArguments")
    void enqueue(int size, int[] elements, int expectedFront, boolean expectedFull ) {

        //given
        Fila<Integer> fila = new FilaImpl<>(size);

        //when
        for(var element: elements) fila.enqueue(element);

        //then
        assertThat(fila.front()).isEqualTo(expectedFront);
        assertThat(fila.isFull()).isEqualTo(expectedFull);
        assertThat(fila.isEmpty()).isFalse();

    }

    static Stream<Arguments> pushArguments() {
        int elementOne = random(10);
        int elementTwo = random(10);

        return Stream.of(
                arguments(2, new int[] {elementOne, elementTwo}, elementOne, true),
                arguments(3, new int[] {elementOne, elementTwo}, elementOne, false),
                arguments(1, new int[] {elementOne}, elementOne, true),
                arguments(2, new int[] {elementOne}, elementOne, false)
        );
    }

    @ParameterizedTest
    @MethodSource("popArguments")
    void dequeue(Fila<Integer> fila, int expectedElement, boolean isVazia) {
        var atualElement = fila.dequeue();

        assertThat(atualElement).isEqualTo(expectedElement);
        assertThat(fila.isEmpty()).isEqualTo(isVazia);
        assertThat(fila.isFull()).isFalse();
    }

    static Stream<Arguments> popArguments() {
        int elementOne = random(10);
        int elementTwo = random(10);

        Fila<Integer> filaOneElementFull = new FilaImpl<>(1);
        filaOneElementFull.enqueue(elementOne);

        Fila<Integer> filaTwoElementsFull = new FilaImpl<>(2);
        filaTwoElementsFull.enqueue(elementOne);
        filaTwoElementsFull.enqueue(elementTwo);

        Fila<Integer> filaTwoElementsNotFull = new FilaImpl<>(2);
        filaTwoElementsNotFull.enqueue(elementOne);

        return Stream.of(
                arguments(filaOneElementFull, elementOne, true),
                arguments(filaTwoElementsFull, elementOne, false),
                arguments(filaTwoElementsNotFull, elementOne, true)
        );
    }

    @Test
    void isEmpty() {
        //given
        int size = random(10);
        Fila<Integer> fila = new FilaImpl<>(size);

        //when then
        assertThatThrownBy(fila::dequeue)
                .isInstanceOf(RuntimeException.class) ;
    }

    @Test
    void verifyStack() {
        //given
        int elementOne = random(10);
        int elementTwo = random(10);
        int elementThree = random(10);

        //when then
        Fila<Integer> fila = new FilaImpl<>(3);
        assertThat(fila.isEmpty()).isTrue();
        assertThat(fila.isFull()).isFalse();
        assertThat(fila.front()).isNull();

        fila.enqueue(elementOne);
        assertThat(fila.isEmpty()).isFalse();
        assertThat(fila.isFull()).isFalse();
        assertThat(fila.front()).isEqualTo(elementOne);

        fila.enqueue(elementTwo);
        assertThat(fila.isEmpty()).isFalse();
        assertThat(fila.isFull()).isFalse();
        assertThat(fila.front()).isEqualTo(elementOne);

        fila.enqueue(elementThree);
        assertThat(fila.isEmpty()).isFalse();
        assertThat(fila.isFull()).isTrue();
        assertThat(fila.front()).isEqualTo(elementOne);

        assertThat(fila.dequeue()).isEqualTo(elementOne);
        assertThat(fila.isEmpty()).isFalse();
        assertThat(fila.isFull()).isFalse();

        assertThat(fila.dequeue()).isEqualTo(elementTwo);
        assertThat(fila.isEmpty()).isFalse();
        assertThat(fila.isFull()).isFalse();

        assertThat(fila.dequeue()).isEqualTo(elementThree);
        assertThat(fila.isEmpty()).isTrue();
        assertThat(fila.isFull()).isFalse();
    }

    private static int random() {
        return new Random().nextInt();
    }

    private static int random(int limit) {
        var number = 0;
        while (number == 0) {
            number = new Random().nextInt(limit+1);
        }
        return number;
    }
}