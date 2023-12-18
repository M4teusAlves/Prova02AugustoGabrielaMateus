package br.com.AM.prova02augustomateus.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.AM.prova02augustomateus.Screen
import br.com.AM.prova02augustomateus.model.Banco_imoveis
import br.com.AM.prova02augustomateus.model.DAO_Imovel
import br.com.AM.prova02augustomateus.model.DAO_Inquilino
import br.com.AM.prova02augustomateus.model.DAO_Proprietario
import br.com.AM.prova02augustomateus.model.Imovel
import br.com.AM.prova02augustomateus.model.Inquilino
import br.com.AM.prova02augustomateus.model.Proprietario
import br.com.AM.prova02augustomateus.model.Transition

@Composable
fun ListScreen(
    navController: NavController,
    banco: Banco_imoveis
){
    val daoImovel = DAO_Imovel(banco)
    val lista = daoImovel.mostrarImoveis()

    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background){

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Row(
                modifier = Modifier
                    .background(Color(97, 47, 116), RectangleShape)
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = null, tint = Color.White, modifier = Modifier.clickable {
                    navController.navigate(Screen.Home.route)
                })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Jeff-imóveis", color = Color.White)
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ){
                Text(fontSize = 30.sp,text = "Lista de Locações")
                Spacer(modifier = Modifier.height(30.dp))
                if(lista.isEmpty()){
                    Text(fontSize = 15.sp,text = "Lista vazia!!!")
                }
                LazyColumn {
                    items(lista) { i -> item(i, banco, navController)}
                }
            }

        }


    }
}


@Composable
fun item(i:Imovel, banco: Banco_imoveis, navController: NavController){
    val daoImovel = DAO_Imovel(banco)
    val daoProprietario = DAO_Proprietario(banco)
    val daoInquilino = DAO_Inquilino(banco)
    Row(modifier = Modifier
        .height(150.dp)
        .width(300.dp)
        .border(1.dp, Color.Gray, RoundedCornerShape(5)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        Column(){
            Text(text = "Matrícula:")
            Text(text = i.matricula)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = "Endereço:")
            Text(text = i.endereco)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = "Valor do Aluguel:")
            Text(text = i.valoralug.toString())

        }
        Spacer(modifier = Modifier.width(30.dp))
        Column(){
            Row {
                Icon(Icons.Rounded.Delete, contentDescription = null, modifier = Modifier.clickable {
                    daoInquilino.excluirInquilino(i.inquilino)
                    daoProprietario.excluirProprietario(i.proprietario)
                    daoImovel.excluirImovel(i)
                    navController.navigate(Screen.List.route)
                })
                Spacer(modifier = Modifier.width(30.dp))
                Icon(Icons.Rounded.Edit, contentDescription = null, modifier = Modifier.clickable {
                    Transition.contexto = "Atualizar"
                    Transition.tipo = "Imóvel"
                    Transition.imovel = i
                    Transition.inquilino = i.inquilino
                    Transition.proprietario = i.proprietario
                    navController.navigate(Screen.Update.route)
                })
            }

            Spacer(modifier = Modifier.height(7.dp))
            Text(text = "Propietário:")
            Text(text = i.proprietario.nome_prop)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Inquilino:")
            Text(text = i.inquilino.nome_inq)
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}