package com.example.myapplication_tp2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.myapplication_tp2.ui.theme.MyApplication_TP2Theme
import com.example.myapplication_tp2.MainActivity2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication_TP2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val textName = remember { mutableStateOf("") }
    val textAge = remember { mutableStateOf("") }
    val labelText = remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    )

    {
        Text(
            text = "Bienvenue",
            modifier = Modifier.align(Alignment.CenterHorizontally)
                                .padding(top = 100.dp),
            style = TextStyle(fontSize = 24.sp, fontWeight= FontWeight.Bold)

        )

        Text(
            text = textName.value+", "+textAge.value,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 150.dp)
        )



        TextField(
            value = textName.value,
            onValueChange = { textName.value = it },
            label = { Text("Saisir votre nom") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(top = 50.dp)
        )

        TextField(
            value = textAge.value,
            onValueChange = { textAge.value = it },
            label = { Text("Saisir votre age") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(top = 50.dp)
        )


        Button(onClick = {
            labelText.value = textName.value
            val intent = Intent(context, MainActivity2::class.java)
            intent.putExtra("name", textName.value)
            intent.putExtra("age", textAge.value)
            context.startActivity(intent)
                         },
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(top = 20.dp))
            //enabled = textState.value.isNotEmpty()
        {

            Text("Valider")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplication_TP2Theme {
        Greeting("Android")
    }
}