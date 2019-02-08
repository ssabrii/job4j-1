package school;

import java.util.Comparator;
import java.util.StringJoiner;

/**
 * Student.
 *
 * @author Maxim Vanny
 * @version 4.0
 * @since 0.1
 */
public class Student implements Comparator<Student> {
    /**
     * User's score.
     */
    private final int score;
    /**
     * User's second name.
     */
    private final String secondName;

    /**
     * Constructor.
     *
     * @param aScore      user's score
     * @param aSecondName user's second name
     */
    public Student(final int aScore, final String aSecondName) {
        this.score = aScore;
        this.secondName = aSecondName;
    }

    /**
     * Method get user's score.
     *
     * @return score
     */
    public final int getScore() {
        return this.score;
    }

    /**
     * Method get user's second name.
     *
     * @return second name
     */
    public final String getSecondName() {
        return this.secondName;
    }

    @Override
    public final String toString() {
        return new StringJoiner(
                ", ", Student.class.getSimpleName()
                + "[", "]")
                .add("score=" + this.score)
                .add("secondName=" + this.secondName)
                .add("\n")
                .toString();
    }

    @Override
    public final int compare(final Student o1, final Student o2) {
        return o1.score - o2.score;
    }
}
