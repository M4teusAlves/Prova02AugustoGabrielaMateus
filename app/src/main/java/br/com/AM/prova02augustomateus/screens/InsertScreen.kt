package br.com.AM.prova02augustomateus.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.AM.prova02augustomateus.R
import br.com.AM.prova02augustomateus.Screen
import br.com.AM.prova02augustomateus.model.Banco_imoveis
import br.com.AM.prova02augustomateus.model.DAO_Imovel
import br.com.AM.prova02augustomateus.model.DAO_Inquilino
import br.com.AM.prova02augustomateus.model.DAO_Proprietario
import br.com.AM.prova02augustomateus.model.Imovel
import br.com.AM.prova02augustomateus.model.Inquilino
import br.com.AM.prova02augustomateus.model.Proprietario
import br.com.AM.prova02augustomateus.model.Transition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertScreen(
    navController: NavController,
    banco:Banco_imoveis
){
    var cpf by remember{ mutableStateOf("") }
    var nome by remember{ mutableStateOf("") }
    var email by remember{ mutableStateOf("") }
    var matricula by remember{ mutableStateOf("") }
    var endereco by remember{ mutableStateOf("") }
    var text by remember{ mutableStateOf("") }
    var aviso by remember { mutableStateOf("") }


    val daoProprietario : DAO_Proprietario = DAO_Proprietario(banco)
    val daoInquilino : DAO_Inquilino = DAO_Inquilino(banco)
    val daoImovel: DAO_Imovel = DAO_Imovel(banco)

    var tamanho by remember { mutableStateOf(0) }



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ){
            Row (modifier = Modifier
                .background(Color(97, 47, 116), RectangleShape)
                .fillMaxWidth()
                .height(50.dp)
                .padding(10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically){
                Text(text = "Jeff-imóveis", color = Color.White)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){

                Text(fontSize = 30.sp,text = Transition.contexto +" "+Transition.tipo)
                Spacer(modifier = Modifier.height(30.dp))

                if(Transition.tipo == "Proprietário"){
                    TextField(
                        value = cpf,
                        onValueChange = {cpf = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text(text = "CPF")}
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = nome,
                        onValueChange = {nome = it},
                        placeholder = { Text(text = "Nome")}
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = email,
                        onValueChange = {email = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        placeholder = { Text(text = "E-mail")}
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(onClick = {
                        if(!cpf.equals("") && !nome.equals("") && !email.equals("")){
                            if(daoProprietario.buscaProprietarioId(cpf)==null){
                                Transition.proprietario = Proprietario(cpf, nome,email)
                                Transition.tipo = "Inquilino"
                                navController.navigate(Screen.Insert.route)
                            }

                            aviso = "CPF já utilizado!!!"
                            tamanho = 10
                        }else{
                            tamanho = 10
                            aviso = "Preencha todos os campos!!!"
                        }

                    }) {
                        Text(text = "Cadastrar")
                    }

                }

                else if(Transition.tipo=="Inquilino"){
                    TextField(
                        value = cpf,
                        onValueChange = {cpf = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text(text = "CPF")}
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = nome,
                        onValueChange = {nome = it},
                        placeholder = { Text(text = "Nome")}
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = text,
                        onValueChange = {text = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text(text = "Valor de Caução")}
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(onClick = {

                        if(!cpf.equals("") && !nome.equals("") && !text.equals("")){
                            if(daoInquilino.buscarInquilinoId(cpf)==null){

                                Transition.inquilino = Inquilino(cpf, nome, text.toDouble())
                                Transition.tipo = "Imóvel"

                                navController.navigate(Screen.Insert.route)
                            }

                            aviso = "CPF já utilizado!!!"
                            tamanho = 10
                        }else{
                            tamanho = 10
                            aviso = "Preencha todos os campos!!!"
                        }

                    }) {
                        Text(text = "Cadastrar")
                    }
                }

                else{
                    TextField(
                        value = matricula,
                        onValueChange = {matricula = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text(text = "Matrícula")}
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = endereco,
                        onValueChange = {endereco = it},
                        placeholder = { Text(text = "Endereço")}
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = text,
                        onValueChange = {text = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text(text = "Valor de Aluguel")}
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(onClick = {

                        if(!matricula.equals("") && !endereco.equals("") && !text.equals("") && Transition.inquilino != null && Transition.proprietario != null){
                            if(daoImovel.inserirImovel(Imovel(matricula, endereco, text.toDouble(), Transition.proprietario!!, Transition.inquilino!!))){
                                daoProprietario.inserirProprietario(Transition.proprietario!!)
                                daoInquilino.inserirInquilino(Transition.inquilino!!)
                                Transition.inquilino = null
                                Transition.proprietario = null
                                Transition.tipo=""
                                Transition.contexto=""
                                navController.navigate(Screen.Home.route)
                            }

                            aviso = "Matrícula já utilizado!!!"
                            tamanho = 10
                        }else{
                            tamanho = 10
                            aviso = "Preencha todos os campos!!!"
                        }



                    }) {
                        Text(text = "Cadastrar")
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(modifier = Modifier
                    .background(Color.Red)){

                    Text(text = aviso, color =  Color.White)

                }
            }

        }
        }


}