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
fun UpdateScreen(
    navController: NavController,
    banco: Banco_imoveis
){
    var cpf by remember{ mutableStateOf("") }
    var nome by remember{ mutableStateOf("") }
    var email by remember{ mutableStateOf("") }
    var matricula by remember{ mutableStateOf("") }
    var endereco by remember{ mutableStateOf("") }
    var text by remember{ mutableStateOf("") }
    var aviso by remember { mutableStateOf("") }

    var corp: Color = Color.Transparent
    var cori: Color = Color.Transparent

    var flag = 0

    val daoProprietario : DAO_Proprietario = DAO_Proprietario(banco)
    val daoInquilino : DAO_Inquilino = DAO_Inquilino(banco)
    val daoImovel: DAO_Imovel = DAO_Imovel(banco)

    var text1 by remember{ mutableStateOf("Cadastrar\nProprietário") }
    var text2 by remember{ mutableStateOf("Cadastrar\nInquilino") }

    var tamanho by remember { mutableStateOf(0) }

    if (Transition.proprietario != null){
        corp = Color(74, 138, 212)
        text1 = "Atualizar\nProprietário"
    }

    if (Transition.inquilino != null){
        cori = Color(74, 138, 212)
        text2 = "Atualizar\nInquilino"
    }



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

                    if(Transition.proprietario != null && flag==0){
                        cpf = Transition.proprietario!!.CPF_prop
                        nome = Transition.proprietario!!.nome_prop
                        email = Transition.proprietario!!.email
                        flag = 1
                    }


                    TextField(
                        value = nome,
                        onValueChange = {nome = it},
                        placeholder = { Text(text = "Nome") }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = email,
                        onValueChange = {email = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        placeholder = { Text(text = "E-mail") }
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(onClick = {
                        if(!cpf.equals("") && !nome.equals("") && !email.equals("")){

                            daoProprietario.atualizarProprietario(Proprietario(cpf, nome,email))
                            Transition.proprietario = Proprietario(cpf, nome,email)
                            Transition.tipo = "Imóvel"
                            navController.navigate(Screen.Update.route)

                        }else{
                            tamanho = 10
                            aviso = "Preencha todos os campos!!!"
                        }

                    }) {
                        Text(text = "Atualizar")
                    }

                }

                else if(Transition.tipo=="Inquilino"){
                    if(Transition.inquilino!=null && flag == 0){
                        cpf = Transition.inquilino!!.CPF_inq
                        nome = Transition.inquilino!!.nome_inq
                        text = Transition.inquilino!!.valor_cau.toString()
                        flag=1
                    }

                    TextField(
                        value = nome,
                        onValueChange = {nome = it},
                        placeholder = { Text(text = "Nome") }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = email,
                        onValueChange = {email = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        placeholder = { Text(text = "E-mail") }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = text,
                        onValueChange = {text = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text(text = "Valor de Caução") }
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(onClick = {

                        if(!cpf.equals("") && !nome.equals("") && !text.equals("")){

                            daoInquilino.atualizarInquilino(Inquilino(cpf, nome, text.toDouble()))

                            Transition.inquilino = Inquilino(cpf, nome, text.toDouble())
                            Transition.tipo = "Imóvel"

                            navController.navigate(Screen.Update.route)



                        }else{
                            tamanho = 10
                            aviso = "Preencha todos os campos!!!"
                        }




                    }) {
                        Text(text = "Atualizar")
                    }
                }

                else{

                    if(Transition.imovel!=null && flag==0){
                        matricula = Transition.imovel!!.matricula
                        endereco = Transition.imovel!!.endereco
                        text = Transition.imovel!!.valoralug.toString()
                        flag=1
                    }


                    TextField(
                        value = endereco,
                        onValueChange = {endereco = it},
                        placeholder = { Text(text = "Endereço") }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = text,
                        onValueChange = {text = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text(text = "Valor de Aluguel") }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier
                        .background(corp, RoundedCornerShape(4.dp))
                        .height(60.dp)
                        .width(200.dp)
                        .border(2.dp, Color(74, 138, 212), RoundedCornerShape(4.dp))
                        .clickable {
                            Transition.tipo = "Proprietário"
                            navController.navigate(Screen.Update.route)
                        }
                    ){
                        Image( painterResource(id = R.drawable.inserir_imovel), modifier = Modifier
                            .width(30.dp)
                            .height(30.dp), contentDescription = null)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = text1)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier
                        .background(cori, RoundedCornerShape(4.dp))
                        .height(60.dp)
                        .width(200.dp)
                        .border(2.dp, Color(74, 138, 212), RoundedCornerShape(4.dp))
                        .clickable {
                            Transition.tipo = "Inquilino"
                            navController.navigate(Screen.Update.route)
                        }
                    ){
                        Image( painterResource(id = R.drawable.inserir_imovel), modifier = Modifier
                            .width(30.dp)
                            .height(30.dp), contentDescription = null)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = text2)
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(onClick = {

                        if(!matricula.equals("") && !endereco.equals("") && !text.equals("") && Transition.inquilino != null && Transition.proprietario != null){

                            daoImovel.atualizarImovel(Imovel(matricula, endereco, text.toDouble(), Transition.proprietario!!, Transition.inquilino!!))
                            navController.navigate(Screen.Home.route)


                        }else{
                            tamanho = 10
                            aviso = "Preencha todos os campos!!!"
                        }



                    }) {
                        Text(text = "Atualizar")
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(modifier = Modifier.padding(tamanho.dp).background(Color.Red)){

                    Text(text = aviso, color =  Color.White)

                }
            }

        }
    }


}