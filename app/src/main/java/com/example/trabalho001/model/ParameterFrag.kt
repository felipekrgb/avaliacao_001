package com.example.trabalho001.model

import com.example.trabalho001.TypeList
import java.io.Serializable

data class ParameterFrag<T>(
    val list: MutableList<T>,
    val typeList: TypeList,
) : Serializable