package br.com.AM.prova02augustomateus.model

class Proprietario (CPF_prop : String, nome_prop : String, email : String)
{
    var CPF_prop : String
    var nome_prop : String
    var email : String

    init {
        this.CPF_prop = CPF_prop
        this.nome_prop = nome_prop
        this.email = email

    }

    override fun toString(): String {
        return ("  Proprietário\n  CPF: "+this.CPF_prop.toString() + "\n  Nome: "+ this.nome_prop.toString() + "\n  Email" + this.email.toString() )
    }
}