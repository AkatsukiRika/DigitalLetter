package com.drm.to.ssy.digitalletter.engine

interface IEngineCmd

class SceneInitCmd : IEngineCmd {
    var title: String = ""
    var movieRes: Int = 0
    var musicRes: Int = 0
}

class DialogCmd : IEngineCmd {
    var speaker: String = ""
    var text: String = ""
}

class MonologueCmd : IEngineCmd {
    var text: String = ""
}

class MusicChangeCmd : IEngineCmd {
    var musicRes: Int = 0
}