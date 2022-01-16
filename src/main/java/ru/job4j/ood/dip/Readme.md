Для реализации меню будет использована колекция Map В качестве ключа - имя пункта меню В качестве значения - уровень
вложенности меню

класс Node содержит поля:
`private final String name; private static final String PREFIX = "  "; private final Level parent; private final int number; private final String fullNumber; private final String fullName; private Action action; private final List<Level> children;`

1. Интерфейс Action - шаблон действия при выборе меню 1.1 Интерфейс должен содержать метод:
   `doAction()`

2. Интерфейс MakeLevel - шаблон создания списка меню 2.1 Содержит метод:
   ` Level make(Level current, String name, String prefix);`
   На основе текущего уровня, его имени и передаваемого в метод префикса метод создает новый уровень
3. Класс UpperLevelNodeMaker - Создает старший уровень меню
4. Класс InnerLevelMaker - Создает подменю на текущем уровне меню
5. Класс NextLevelMaker - Создает следующий уровень меню
6. Класс StartAction - запускает создание меню
7. Класс Menu описывает работу меню
8. Класс Level описывает пункты меню
9. Класс LevelComparator обеспечивает правильную сортировку при построении меню


   
 