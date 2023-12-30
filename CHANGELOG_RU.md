## Версия 0.0.2



### Полный список изменений:

* Документация:
  * Удалены `IScriptUltimate`, `IUltimateEvent`, `IUltimatePlayer`
  * Теперь все по своим местам
  * Добавлены примеры скриптов
* API:
  * Добавлены методы для IScriptPlayer:
    `setLocalMorph`, `pressKey`, `realiseKey`, `getGameProfile`
  * Добавлен метод для IScriptFactory:
    `getGlobalTrigger`
  * Добавлен метод для IScriptServer:
    `getMaxPlayers`

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
  * И рофлянки и тесты для `IScriptUltimate`, `IUltimateEvent`