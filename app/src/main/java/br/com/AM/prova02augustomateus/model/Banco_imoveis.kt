package br.com.AM.prova02augustomateus.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Banco_imoveis (context: Context) : SQLiteOpenHelper(context, "BancoImoveis", null, 6)
{
    override fun onCreate(db: SQLiteDatabase)
    {


        val SQL_criacao = "CREATE TABLE tabelaProp (" +
                    "cpfprop TEXT PRIMARY KEY NOT NULL," +
                    "nomeprop TEXT NOT NULL," +
                    "email TEXT NOT NULL);"

        val SQL_Inq = "CREATE TABLE tabelaInq (" +
                    "cpfinq TEXT PRIMARY KEY NOT NULL," +
                    "nomeinq TEXT NOT NULL," +
                    "valorcaucaode REAL NOT NULL);"

        val SQL_Imo = "CREATE TABLE tabelaImovel (" +
                "matricula TEXT PRIMARY KEY NOT NULL," +
                "endereco TEXT NOT NULL," +
                "valor_alug REAL NOT NULL," +
                "proprietario TEXT NOT NULL, " +
                "inquilino TEXT NOT NULL );"

        db.execSQL(SQL_criacao)
        db.execSQL(SQL_Inq)
        db.execSQL(SQL_Imo)
    }
    override fun onUpgrade(db: SQLiteDatabase, versaoAntiga: Int, novaVersao: Int) {
        val SQL_exclusaoProp = "DROP TABLE IF EXISTS tabelaProp"
        db.execSQL(SQL_exclusaoProp)
        val SQL_exclusaoInq = "DROP TABLE IF EXISTS tabelaInq"
        db.execSQL(SQL_exclusaoInq)
        val SQL_exclusaoImo = "DROP TABLE IF EXISTS tabelaImovel"
        db.execSQL(SQL_exclusaoImo)
        onCreate(db)
    }
}