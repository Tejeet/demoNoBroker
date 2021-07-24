package com.tejeet.nobrokerdemoapi.dataModel

import java.io.Serializable

data class UserPosts(
	val image: String? = null,
	val subTitle: String? = null,
	val title: String? = null
) : Serializable