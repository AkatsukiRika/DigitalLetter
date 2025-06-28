package com.drm.to.ssy.digitalletter.engine

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Engine(private val cmdList: List<IEngineCmd>) {
    private val _currentIndex = MutableStateFlow(-1)
    val currentIndex: StateFlow<Int> = _currentIndex

    private val _currentTitle = MutableStateFlow("")
    val currentTitle: StateFlow<String> = _currentTitle

    private val _currentMovieRes = MutableStateFlow(0)
    val currentMovieRes: StateFlow<Int> = _currentMovieRes

    private val _currentMusicRes = MutableStateFlow(0)
    val currentMusicRes: StateFlow<Int> = _currentMusicRes

    private val _currentSpeaker = MutableStateFlow<String?>(null)
    val currentSpeaker: StateFlow<String?> = _currentSpeaker

    private val _currentText = MutableStateFlow("")
    val currentText: StateFlow<String> = _currentText

    private var savedSpeaker: String? = null

    fun goNext() {
        while (currentIndex.value + 1 < cmdList.size) {
            _currentIndex.value = currentIndex.value + 1
            when (val nextCmd = cmdList[currentIndex.value]) {
                is SceneInitCmd -> {
                    Log.i("Engine", "nextCmd is SceneInitCmd")
                    if (nextCmd.title.isNotEmpty()) {
                        _currentTitle.value = nextCmd.title
                        Log.d("Engine", "currentTitle: ${currentTitle.value}")
                    }
                    if (nextCmd.movieRes != 0) {
                        _currentMovieRes.value = nextCmd.movieRes
                        Log.d("Engine", "currentMovieRes: ${currentMovieRes.value}")
                    }
                    if (nextCmd.musicRes != 0) {
                        _currentMusicRes.value = nextCmd.musicRes
                        Log.d("Engine", "currentMusicRes: ${currentMusicRes.value}")
                    }
                }

                is MusicChangeCmd -> {
                    Log.i("Engine", "nextCmd is MusicChangeCmd")
                    if (nextCmd.musicRes != 0) {
                        _currentMusicRes.value = nextCmd.musicRes
                        Log.d("Engine", "currentMusicRes: ${currentMusicRes.value}")
                    }
                }

                is MovieChangeCmd -> {
                    Log.i("Engine", "nextCmd is MovieChangeCmd")
                    if (nextCmd.movieRes != 0) {
                        _currentMovieRes.value = nextCmd.movieRes
                        Log.d("Engine", "currentMovieRes: ${currentMovieRes.value}")
                    }
                }

                is DialogCmd -> {
                    Log.i("Engine", "nextCmd is DialogCmd")
                    if (nextCmd.speaker.isNotEmpty()) {
                        _currentSpeaker.value = nextCmd.speaker
                        savedSpeaker = null
                    } else if (savedSpeaker.isNullOrEmpty().not()) {
                        _currentSpeaker.value = savedSpeaker
                        savedSpeaker = null
                    }
                    Log.d("Engine", "currentSpeaker: ${currentSpeaker.value}")
                    _currentText.value = nextCmd.text
                    Log.d("Engine", "currentText: ${currentText.value}")
                    break
                }

                is MonologueCmd -> {
                    Log.i("Engine", "nextCmd is MonologueCmd")
                    _currentSpeaker.value = null
                    Log.d("Engine", "currentSpeaker: ${currentSpeaker.value}")
                    _currentText.value = nextCmd.text
                    Log.d("Engine", "currentText: ${currentText.value}")
                    break
                }

                is EllipsisCmd -> {
                    Log.i("Engine", "nextCmd is EllipsisCmd")
                    savedSpeaker = currentSpeaker.value
                    _currentSpeaker.value = null
                    Log.d("Engine", "currentSpeaker: ${currentSpeaker.value}")
                    _currentText.value = ".".repeat(nextCmd.count)
                    Log.d("Engine", "currentText: ${currentText.value}")
                    break
                }
            }
        }
    }
}