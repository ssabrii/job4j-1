package school;

import java.util.StringJoiner;

/**
 * Student.
 *
 * @author Maxim Vanny
 * @version 4.0
 * @since 0.1
 */
public class Student {
    /**
     * User's score.
     */
    private final int score;

    /**
     * Constructor.
     *
     * @param aScore user's score
     */
    public Student(final int aScore) {
        this.score = aScore;
    }

    /**
     * Method get user's score.
     *
     * @return score
     */
    public final int getScore() {
        return this.score;
    }

    @Override
    public final String toString() {
        return new StringJoiner(
                ", ", Student.class.getSimpleName()
                + "[", "]")
                .add("score=" + this.score)
                .toString();
    }
}
