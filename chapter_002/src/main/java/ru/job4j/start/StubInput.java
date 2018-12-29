package ru.job4j.start;

/**
 * StubInput.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class StubInput implements Input {
    /**
     * массив для хранения данных полученных от пользователя.
     */
    private String[] answers;
    /**
     * индекс массива для хранения данных полученных от пользователя.
     */
    private int position = 0;

    /**
     * конструктор.
     *
     * @param answers ответы пользователя.
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * получение полей заявки по запросу.
     *
     * @param question запрос для пользователя.
     * @return возвращает последнее введённое поле заявки по запросу.
     */
    @Override
    public String ask(String question) {
        return answers[position++];
    }
}
