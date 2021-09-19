var user1 = new User(12, "Alex");
* Место, выделяемое при создании объекта user1:
* --- Заголовок в х64 системе 16 байт
* --- поле int age 4 байт
* --- поле String name
  * Заголовок 16 байт
  * три поля int (offset, count, hash) 12 байт
  * ссылка на массив char[] 4 байт
    * итого 32 байт
* --- плюс массив char[]:
  * Заголовок 16 байт
  * элементы char 2 байт * количество символов (Alex) 4 = 8 байт
    * итого 24 байт
* итого на строку 32 + 24 = 56 байт
* всего под объект выделяется 16 + 4 + 56 = 76 байт + 4 для кратности на 8 = 80 байт



var user2 = new User(18);
* Место, выделяемое при создании объекта user2:
* --- Заголовок в х64 системе 16 байт
* --- поле int age 4 байт
* --- поле String name
  * Заголовок 16 байт
  * три поля int (offset, count, hash) 12 байт
  * ссылка на массив char[] 4 байт
    * итого 32 байт
* --- плюс массив char[]:
  * Заголовок 16 байт
  * элементы char 2 байт * количество символов (null) 0 = 0 байт
    * итого 16 байт
* итого на строку 32 + 16 = 48 байт
* всего под объект выделяется 16 + 4 + 48 = 68 байт + 4 для кратности на 8 = 72 байт

При создании пустого объекта место выделяется только под заголовок
 12 байт заголовок + 4 байт для кратности 8, итого 16 байт



* Создаем 1000 новых объектов
* если //  System.gc(); закомментирована
*-Xmx2m -Xms2m 
  * метод не запускается
*
* -Xmx4m -Xms2m
* 2021-09-19 21:51:02,755  INFO GCDemo:info:17 - === Environment state ===
* 2021-09-19 21:51:02,769  INFO GCDemo:info:18 - Free: 1
* 2021-09-19 21:51:02,769  INFO GCDemo:info:19 - Total: 4
* 2021-09-19 21:51:02,769  INFO GCDemo:info:20 - Max: 4
* 2021-09-19 21:51:02,787  INFO GCDemo:info:17 - === Environment state ===
* 2021-09-19 21:51:02,787  INFO GCDemo:info:18 - Free: 0
* 2021-09-19 21:51:02,789  INFO GCDemo:info:19 - Total: 4
* 2021-09-19 21:51:02,789  INFO GCDemo:info:20 - Max: 4
  * Не вижу работу GC
*
*
* если создается 5000 объектов 
  * Exception: java.lang.OutOfMemoryError
*
* если System.gc(); раскомментирована
  * -Xmx4m -Xms2m
    * Exception: java.lang.OutOfMemoryError
*
*
  * -Xmx4m -Xms4m
    * Exception: java.lang.OutOfMemoryError
*
* -Xmx8m -Xms4m
    * GC работает, вижу удаляемые объекты
* 2021-09-19 21:57:53,678  INFO GCDemo:info:18 - Free: 3
* 2021-09-19 21:57:53,678  INFO GCDemo:info:19 - Total: 6
* 2021-09-19 21:57:53,679  INFO GCDemo:info:20 - Max: 8
* 2021-09-19 21:57:53,689  INFO User:finalize:36 - Removed 66 null
* 2021-09-19 21:57:53,692  INFO User:finalize:36 - Removed 99 null
* 2021-09-19 21:57:53,692  INFO User:finalize:36 - Removed 98 null
* ...
* 2021-09-19 21:57:53,703  INFO GCDemo:info:18 - Free: 3
* 2021-09-19 21:57:53,703  INFO User:finalize:36 - Removed 49 null
* 2021-09-19 21:57:53,703  INFO GCDemo:info:19 - Total: 7
* 2021-09-19 21:57:53,703  INFO User:finalize:36 - Removed 48 null
* 2021-09-19 21:57:53,703  INFO GCDemo:info:20 - Max: 8
*/
         
        
