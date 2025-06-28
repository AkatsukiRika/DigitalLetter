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

/**
 * 用省略号做文本转场
 */
class EllipsisCmd : IEngineCmd {
    var count: Int = 0
}

class MusicChangeCmd : IEngineCmd {
    var musicRes: Int = 0
}

class MovieChangeCmd : IEngineCmd {
    var movieRes: Int = 0
}