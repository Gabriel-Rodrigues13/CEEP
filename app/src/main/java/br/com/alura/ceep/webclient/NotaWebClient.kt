package br.com.alura.ceep.webclient

import android.util.Log
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.model.NotaRequisicao
import br.com.alura.ceep.webclient.model.NotaResposta
import br.com.alura.ceep.webclient.services.NotaService
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path
import java.lang.Exception

const val TAG = "NotaWebClient"

class NotaWebClient {
    private val notaService: NotaService = RetrofitInicializador().notaService
    suspend fun buscaTodas(): List<Nota>? {

        return try {
            val notasReposta = notaService.buscaTodas()
            notasReposta.map { notaResposta ->
                notaResposta.nota
            }
        } catch (e: Exception) {
            Log.e(TAG, "buscaTodas: ", e)
            null

        }
    }

    suspend fun salva(nota: Nota) : Boolean {
        try {
            val resposta = notaService.salva(
                nota.id,
                NotaRequisicao(titulo = nota.titulo,
                    descricao = nota.descricao,
                    imagem = nota.imagem)
            )
            return resposta.isSuccessful
        } catch (e: Exception) {
            Log.e(TAG, "salva: falha ao tentar salvar", e )
        }
        return false
    }

    suspend fun remove(id: String) : Boolean {
        try {
            notaService.remove(id)
            return true
        }catch (e : Exception){
            Log.e(TAG, "remove: Falha ao remover nota", e )
        }
        return false
    }


}