package com.ahmetbozkan.quickfingers.data.repository.callback

import com.ahmetbozkan.quickfingers.data.model.Word

interface WordDbCallbackRepository {

    /**
     * parse words.json file to Word objects
     * @return list of words
     */
    fun parseWordFile(): List<Word>

}