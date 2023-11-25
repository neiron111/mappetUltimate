## Версия 0.0.1

Дополнение к Mappet "MappetUltimate" добавляет клиентские методы
по типу saveScreenshot, и облегчения работы со скриптами.


### Полный список изменений:

* Новые возможности:
    * Добавлен конфиг для мода по нажатию клавиши 0 (там где все конфиги от модов McHorse).
    * Добавлен конфиг для удаления автодобавления кода при создании скрипта.
* Документация:
    * Добавлены `IScriptUltimate`, `IUltimateEvent`, `IUltimatePlayer`
* API:
   
    * Добавлено API для получения и установки клиентских данных:
      * Для `IUltimatePlayer` добавлены
      методы `gameStop`, `getDate`, `keyRealise`, `saveScreenshot`
    * Также методы для `IUltimatePlayer`:
      * `getShaderPack`, `setLocalMorph`
