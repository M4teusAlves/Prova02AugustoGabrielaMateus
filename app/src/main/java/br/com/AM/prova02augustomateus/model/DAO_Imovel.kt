package br.com.AM.prova02augustomateus.model

import android.content.ContentValues
import android.util.Log

class DAO_Imovel (Banco_imoveis: Banco_imoveis){
    var Banco_imoveis : Banco_imoveis
    val daoProprietario = DAO_Proprietario(Banco_imoveis)
    val daoInquilino = DAO_Inquilino(Banco_imoveis)
    init {
        this.Banco_imoveis = Banco_imoveis
    }
    fun inserirImovel(imovel: Imovel): Boolean {
        val db_insercao = this.Banco_imoveis.writableDatabase
        var valores = ContentValues().apply {
            put("matricula",imovel.matricula)
            put("endereco",imovel.endereco)
            put("valor_alug",imovel.valoralug)
            put("proprietario",imovel.proprietario.CPF_prop)
            put("inquilino", imovel.inquilino.CPF_inq)
        }
        val confirmaInsercao = db_insercao?.insert("tabelaImovel", null, valores)

        if (confirmaInsercao != null) {
            if(confirmaInsercao.toInt()==-1){
                return false
            }
        }

        return true
    }

    fun mostrarImoveis(): List<Imovel>{
        val listaImovel = ArrayList<Imovel>()
        val db_leitura = this.Banco_imoveis.readableDatabase
        var cursor = db_leitura.rawQuery("select * from tabelaImovel",null)
        with(cursor) {
            while(moveToNext()){
                val matricula = getString(getColumnIndexOrThrow("matricula"))
                val endereco = getString(getColumnIndexOrThrow("endereco"))
                val valor_alug = getDouble(getColumnIndexOrThrow("valor_alug"))
                val proprietario = getString(getColumnIndexOrThrow("proprietario"))
                val inquilino = getString(getColumnIndexOrThrow("inquilino"))

                Log.i("Teste", matricula+endereco+valor_alug+proprietario+inquilino)
                listaImovel.add(Imovel(matricula,endereco,valor_alug,daoProprietario.buscaProprietarioId(proprietario)!!, daoInquilino.buscarInquilinoId(inquilino)!!))
            }
        }
        cursor.close()
        return (listaImovel)
    }

    fun atualizarImovel(imovel: Imovel){
        val db_atualização = this.Banco_imoveis.writableDatabase
        var valores = ContentValues().apply {
            put("endereco",imovel.endereco)
            put("valor_alug",imovel.valoralug)
        }
        val condicao = "matricula = "+imovel.matricula
        val confirmaAtualizacao = db_atualização.update("tabelaImovel", valores, condicao, null)
        Log.i("Teste","Atualização: $confirmaAtualizacao")
    }

    fun excluirImovel(imovel: Imovel){
        val db_exclusao = this.Banco_imoveis.readableDatabase
        val condicao = "matricula = "+imovel.matricula
        val confirmaExclusao = db_exclusao.delete("tabelaImovel",condicao,null)
        Log.i("Teste", "Após a exclusão: $confirmaExclusao")
    }

}