package com.example.example.data

data class Group (val id: String = "",
                  val pluginName: String = "",
                  val groupName: String = "",
                  var viewType: Int = 0,
                  var itemList: List<Item>? = arrayListOf()) {

    companion object {
        val VIEW_TYPE_FKTS = 0
        val VIEW_TYPE_HEADER = 1
    }
}