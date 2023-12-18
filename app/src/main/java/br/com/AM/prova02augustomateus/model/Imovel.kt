package br.com.AM.prova02augustomateus.model

class Imovel (matricula : String, endereco : String, valoralug : Double, proprietario : Proprietario, inquilino : Inquilino){
    var matricula : String
    var endereco : String
    var valoralug : Double
    var proprietario : Proprietario
    var inquilino : Inquilino
    init {
        this.matricula = matricula
        this.endereco = endereco
        this.valoralug = valoralug
        this.proprietario = proprietario
        this.inquilino = inquilino
    }

    override fun toString(): String {
        return ("Imóvel\n"+"  Matrícula: "+this.matricula.toString() + "\n  Endereço: "+ this.endereco.toString() + "\n Valor Aluguel: " + this.valoralug.toString() + "\n\n   " + this.proprietario.toString() + "\n\n   " + this.inquilino.toString()+"\n\n------------\n\n" )
    }
}