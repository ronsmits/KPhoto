package nl.codetribe.controller

import nl.codetribe.model.DeliciousBookmark
import javafx.collections.ObservableList
import tornadofx.*

class DeliciousController: Controller() {
    val api: Rest by inject()

    init {
        api.baseURI = "http://feeds.del.icio.us/v2/json"
    }

    fun recentBookmarks(): ObservableList<DeliciousBookmark> =
            api.get("recent").list().toModel()
}

