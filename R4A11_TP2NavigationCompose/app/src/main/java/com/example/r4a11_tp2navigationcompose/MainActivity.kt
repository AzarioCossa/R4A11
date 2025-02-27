package com.example.r4a11_tp2navigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.r4a11_tp2navigationcompose.ui.theme.R4A11_TP2NavigationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            R4A11_TP2NavigationComposeTheme {
                MaterialTheme{
                    AppNavigation()
                }
            }
        }
    }
}


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("form") {
            FormScreen(navController = navController)
        }
        composable(
            route = "display/{name}/{age}",
            arguments = listOf(navArgument("name") { defaultValue = ""},
                                navArgument("age") { defaultValue = ""})
        ){backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")?:""
            val age = backStackEntry.arguments?.getString("age")?:""
            DisplayScreen(navController=navController, name=name,age=age)
        }
    }
}

@Composable
fun DisplayScreen(navController: NavHostController, name: String, age: String) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Bienvenue")
        Text("$name")
        Text("Vous avez $age ans")
        Button(onClick = { navController.navigate("form") }) {
            Text("Rétour")
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
   Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
   {
         Text("Bienvenue dans ma première application commpose navigation")
        //style =  MaterialTheme.typography.titleMedium
         Button(onClick = { navController.navigate("form") }) {
              Text("Accéder au formulaire")
         }
   }
}

@Composable
fun FormScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Page du formulaire")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = name,
            onValueChange = { newText -> name = newText },
            label={Text("Entrez votre nom")},
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(top = 50.dp).fillMaxWidth()
        )
        TextField(
            value = age,
            onValueChange = { newAge -> age = newAge },
            label={Text("Entrez votre age")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(top = 50.dp).fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("display/$name/$age") }) {
            Text("Valider")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("home") }) {
            Text("Retour")
        }
    }
}


