## Version 0.0.2

### Full list of changes:

* Documentation:
* Removed `IScriptUltimate`, `IUltimateEvent', `IUltimatePlayer`
* Now everything is in its place
* Added script examples
* API:
* Added methods for IScriptPlayer:
  `setLocalMorph`, `pressKey`, `realiseKey`, `getGameProfile`
* Added method for IScriptFactory:
  `getGlobalTrigger`
* Added a method for IScriptServer:
  `getMaxPlayers`

## Version 0.0.1

The addition to Mappet "MappetUltimate" adds client methods
like saveScreenshot, and makes it easier to work with scripts.


### Full list of changes:

* New features:
* Added a config for the mod by pressing the 0 key (where all the configs from the McHorse mods are).
* Added a config to remove auto-add code when creating a script.
* Documentation:
* Added `IScriptUltimate`, `IUltimateEvent', `IUltimatePlayer`
* API:

* Added API for receiving and installing client data:
* Added for `IUltimatePlayer`
  methods `GameStop`, `getDate`, `keyRealise`, `saveScreenshot`
* Also methods for `IUltimatePlayer`:
* `getShaderPack`, `setLocalMorph`
* And flyers and tests for `IScriptUltimate`, `IUltimateEvent`