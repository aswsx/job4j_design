package ru.job4j.template;

import java.util.Map;

/**
 * Класс генерирует фразы по шаблону template из слов, передаваемых на вход в карте args
 */
public interface Generator {
    String produce(String template, Map<String, String> args);
}