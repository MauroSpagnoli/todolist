package msh.todolist // Asegúrate que este sea el paquete correcto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme { // Reemplaza esto con el tema de tu aplicación si tienes uno definido
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme { // Reemplaza esto con el tema de tu aplicación si tienes uno definido
        Greeting("Android")
    }
}

// Puedes definir un tema básico como este si aún no tienes uno
@Composable
fun MyApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        // Puedes personalizar los colores, tipografía, etc. aquí
        content = content
    )
}
