package br.com.AM.prova02augustomateus.model

class Inquilino (CPF_inq : String, nome_inq : String, valor_cau : Double)
{
    var CPF_inq : String
    var nome_inq : String
    var valor_cau : Double

    init {
        this.CPF_inq = CPF_inq
        this.nome_inq = nome_inq
        this.valor_cau = valor_cau

    }

    override fun toString(): String {
        return ("  Inquilino\n   CPF: "+this.CPF_inq.toString() + "\n  Nome: "+ this.nome_inq.toString() + "\n  Valor de Caução: " + this.valor_cau.toString() )
    }
}