package br.com.AM.prova02augustomateus.model

import android.content.ContentValues
import android.util.Log

class DAO_Inquilino (Banco_imoveis: Banco_imoveis) {
    var Banco_imoveis : Banco_imoveis
    init {
        this.Banco_imoveis = Banco_imoveis
    }
    fun inserirInquilino(inquilino: Inquilino): Boolean {
        val db_insercao = this.Banco_imoveis.writableDatabase
        var valores = ContentValues().apply {
            put("cpfinq",inquilino.CPF_inq)
            put("nomeinq",inquilino.nome_inq)
            put("valorcaucaode",inquilino.valor_cau)
        }
        val confirmaInsercao = db_insercao?.insert("tabelaInq", null, valores)
        if (confirmaInsercao != null) {
            if(confirmaInsercao.toInt()==-1){
                return false
            }
        }

        return true
    }

    fun mostrarInquilino(){
        val listaInquilino = ArrayList<Inquilino>()
        val db_leitura = this.Banco_imoveis.readableDatabase
        var cursor = db_leitura.rawQuery("select * from tabelaInq",null)
        with(cursor) {
            while (moveToNext()){
                val CPF_inq = getString(getColumnIndexOrThrow("cpfinq"))
                val nome_inq = getString(getColumnIndexOrThrow("nomeinq"))
                val valor_cau = getDouble(getColumnIndexOrThrow("valorcaucaode"))
                Log.i("Teste","CPF_inq: $CPF_inq - Nome: $nome_inq - Valor Caução: $valor_cau")
            }


        }
        cursor.close()
    }

    fun buscarInquilinoId(cpf:String): Inquilino? {
        val db_leitura = this.Banco_imoveis.readableDatabase
        var cursor = db_leitura.rawQuery("select * from tabelaInq where cpfinq = ?", arrayOf(cpf))
        with(cursor) {
            if(moveToNext()){
                val CPF_inq = getString(getColumnIndexOrThrow("cpfinq"))
                val nome_inq = getString(getColumnIndexOrThrow("nomeinq"))
                val valor_cau = getDouble(getColumnIndexOrThrow("valorcaucaode"))
                Log.i("Teste", CPF_inq+nome_inq+valor_cau)
                return Inquilino(CPF_inq, nome_inq, valor_cau)
            }
        }
        Log.i("Teste","teste")
        cursor.close()
        return null
    }

    fun atualizarInquilino(inquilino: Inquilino){
        val db_atualização = this.Banco_imoveis.writableDatabase
        var valores = ContentValues().apply {
            put("nomeinq",inquilino.nome_inq)
            put("valorcaucaode",inquilino.valor_cau)
        }
        val condicao = "cpfinq = "+inquilino.CPF_inq
        val confirmaAtualizacao = db_atualização.update("tabelaInq", valores, condicao, null)
        Log.i("Teste","Atualização: $confirmaAtualizacao")
    }

    fun excluirInquilino(inquilino: Inquilino){
        val db_exclusao = this.Banco_imoveis.readableDatabase
        val condicao = "cpfinq = "+inquilino.CPF_inq
        val confirmaExclusao = db_exclusao.delete("tabelaInq",condicao,null)
        Log.i("Teste", "Após a exclusão: $confirmaExclusao")
    }
}