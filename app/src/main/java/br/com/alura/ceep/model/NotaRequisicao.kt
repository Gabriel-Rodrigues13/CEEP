package br.com.alura.ceep.model

import java.util.UUID

data class NotaRequisicao(
    val titulo: String,
    val descricao: String,
    val imagem: String? = null
)