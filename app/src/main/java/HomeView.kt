import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(){
    var heightInput by remember {mutableStateOf("")}
    var weightInput by remember {mutableStateOf("")}

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "BMI Calculator")
                    //BMI Calculater text at the top of the scaffold or the screen
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray,
                    titleContentColor = Color.Black
                )
                //titleContentColor is color of the text in the top Appbar Scaffold
                //container color is color of the app bar
            )
        }
    ){paddingValues ->
        //Always whenever we use Scaffold use paddingvalues so that the content of the Scaffold
        //and the content of the other does not overlap with each other
        Column(
            modifier = Modifier.
            fillMaxSize().
            padding(paddingValues).
            padding(16.dp)
            //Adds padding 16 from all sides
        ){
            Row(
                modifier=Modifier.fillMaxSize()
            ){
                OutlinedTextField(
                    value=heightInput,
                    onValueChange = {
                        heightInput = it
                    }
                    label = {
                        Text(text="Enter Height")
                    }
                )
            }

        }
    }
}

@Composable
@Preview(showBackground=true)
fun HomeViewPreview(){
    HomeView()
}

