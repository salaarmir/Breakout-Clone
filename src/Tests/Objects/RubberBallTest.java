package Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

class RubberBallTest {

    @Test
    void Test1() {

        RubberBall ball = new RubberBall(new Point(350, 400));
        Assertions.assertEquals(new Point(350, 400), ball.getPosition());

    }

}
