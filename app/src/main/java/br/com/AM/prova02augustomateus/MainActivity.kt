package br.com.AM.prova02augustomateus

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.AM.prova02augustomateus.model.Arquivo
import br.com.AM.prova02augustomateus.model.Banco_imoveis
import br.com.AM.prova02augustomateus.model.DAO_Imovel
import br.com.AM.prova02augustomateus.model.DAO_Proprietario
import br.com.AM.prova02augustomateus.model.Proprietario
import br.com.AM.prova02augustomateus.model.Transition
import br.com.AM.prova02augustomateus.ui.theme.Prova02AugustoMateusTheme
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {

    lateinit var navController : NavHostController
    lateinit var banco:Banco_imoveis

    private var arquivoExterno: File?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Prova02AugustoMateusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    banco = Banco_imoveis(applicationContext)

                    var daoImovel = DAO_Imovel(banco)

                    arquivoExterno = File(getExternalFilesDir("ArquivoGerencial"), "DadosLocacoes")
                    try {
                        val fileOutPutStream = FileOutputStream(arquivoExterno)
                        for(linha in daoImovel.mostrarImoveis()){
                            fileOutPutStream.write(linha.toString().toByteArray())
                        }
                        fileOutPutStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    Toast.makeText(applicationContext,"Texto salvo com sucesso!",Toast.LENGTH_SHORT).show()

                    arquivoExterno = File(getExternalFilesDir("ArquivoGerencial"),"DadosLocacoes")
                    if("DadosEmpresas".trim()!=""){
                        val fileInputStream = FileInputStream(arquivoExterno)
                        val inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                        val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                        val stringBuilder: StringBuilder = StringBuilder()
                        var text: String? = null

                        //while ((linha = br.readLine()) != null) - Java
                        while ((bufferedReader.readLine().also { text = it }) != null)
                        {
                            stringBuilder.append(text)
                        }
                        fileInputStream.close()
                        Toast.makeText(applicationContext,"O resultado é apresentado no log",Toast.LENGTH_SHORT).show()
                        Log.i("Dados empresariais", stringBuilder.toString())
                    }


                    navController = rememberNavController()
                    SetupNavGraph(navController, banco)
                }
            }
        }
    }



}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}