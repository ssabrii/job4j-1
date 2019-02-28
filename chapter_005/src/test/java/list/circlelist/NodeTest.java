package list.circlelist;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NodeTest {
    private final NodeCircle<Integer> circle = new NodeCircle<>();
    private final NodeCircle.Node<Integer> first = new NodeCircle.Node<>(1);
    private final NodeCircle.Node<Integer> two = new NodeCircle.Node<>(2);
    private final NodeCircle.Node<Integer> third = new NodeCircle.Node<>(3);
    private final NodeCircle.Node<Integer> four = new NodeCircle.Node<>(4);

    {
        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);
    }

    @Test
    public void whenCircleOk() {
        var result = this.circle.hasCycle(this.four);
        assertTrue(result);
    }

    @Test
    public void whenCircleFall() {
        this.four.setNext(null);
        var result = this.circle.hasCycle(this.two);
        assertFalse(result);
    }
}