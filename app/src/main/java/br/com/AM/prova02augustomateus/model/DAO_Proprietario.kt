package br.com.AM.prova02augustomateus.model

import android.content.ContentValues
import android.util.Log

class DAO_Proprietario (Banco_imoveis: Banco_imoveis){
    var Banco_imoveis : Banco_imoveis
    init {
        this.Banco_imoveis = Banco_imoveis
    }
    fun inserirProprietario(proprietario: Proprietario): Boolean {
        val db_insercao = this.Banco_imoveis.writableDatabase
        var valores = ContentValues().apply {
            put("cpfprop",proprietario.CPF_prop)
            put("nomeprop",proprietario.nome_prop)
            put("email",proprietario.email)
        }
        val confirmaInsercao = db_insercao.insert("tabelaProp", null, valores)
        if (confirmaInsercao != null) {
            if(confirmaInsercao.toInt()==-1){
                return false
            }
        }

        return true
    }

    fun mostrarProprietarios(){
        val listaProprietario = ArrayList<Proprietario>()
        val db_leitura = this.Banco_imoveis.readableDatabase
        var cursor = db_leitura.rawQuery("select * from tabelaProp", null)
        with(cursor) {
            while (moveToNext()){
                val CPF_prop = getString(getColumnIndexOrThrow("cpfprop"))
                val nome_prop = getString(getColumnIndexOrThrow("nomeprop"))
                val email = getString(getColumnIndexOrThrow("email"))
                Log.i("Teste","CPF_prop: " + CPF_prop + " Nome: " + nome_prop +"- Email: ${email}")
            }
        }
        cursor.close()
    }

    fun buscaProprietarioId(cpf: String): Proprietario? {
        val db_leitura = this.Banco_imoveis.readableDatabase
        var cursor = db_leitura.rawQuery("select * from tabelaProp where cpfprop = ?", arrayOf(cpf))
        with(cursor) {
            if(moveToNext()){
                val CPF_prop = getString(getColumnIndexOrThrow("cpfprop"))
                val nome_prop = getString(getColumnIndexOrThrow("nomeprop"))
                val email = getString(getColumnIndexOrThrow("email"))
                Log.i("Teste", CPF_prop+nome_prop+email)
                return Proprietario(CPF_prop, nome_prop, email)
            }
        }
        cursor.close()
        return null
    }

    fun atualizarProprietario(proprietario: Proprietario){
        val db_atualização = this.Banco_imoveis.writableDatabase
        var valores = ContentValues().apply {
            put("nomeprop",proprietario.nome_prop)
            put("email",proprietario.email)
        }
        val condicao = "cpfprop = "+proprietario.CPF_prop
        val confirmaAtualizacao = db_atualização.update("tabelaProp", valores, condicao, null)
        Log.i("Teste","Atualização: $confirmaAtualizacao")
    }

    fun excluirProprietario(proprietario: Proprietario){
        val db_exclusao = this.Banco_imoveis.readableDatabase
        val condicao = "cpfprop = "+proprietario.CPF_prop
        val confirmaExclusao = db_exclusao.delete("tabelaProp",condicao,null)
        Log.i("Teste", "Após a exclusão: $confirmaExclusao")
    }
}