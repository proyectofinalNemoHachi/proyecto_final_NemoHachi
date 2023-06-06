package com.yeinerdpajaro.nemohachi.model

data class Anuncio (

    var id: String? = null,
    var name: String? = null,
    var comment: String? = null,
    var urlPicture: String? = null,
    var isCatSelected: Boolean = false,
    var isDogSelected: Boolean = false,
    var isOtherSelected: Boolean = false

)