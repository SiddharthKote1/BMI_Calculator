import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
fun HomeView() {
    //This are for the height and input values for the person
    var heightInput by remember { mutableStateOf("") }
    var weightInput by remember { mutableStateOf("") }
    //This are expand menus for the height and for the weight

    var HeightDropdownMenuExpand by remember { mutableStateOf(false) }
    var WeightDropdownMenuExpand by remember { mutableStateOf(false) }

    //Units
    var HUnit by remember { mutableStateOf("Unit") }
    var WUnit by remember {mutableStateOf("Unit")}

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "BMI Calculator")
                    //BMI Calculater text at the top of the scaffold or the screen
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.DarkGray,
                    titleContentColor = Color.Black
                )
                //titleContentColor is color of the text in the top Appbar Scaffold
                //container color is color of the app bar
            )
        }
    ) { paddingValues ->
        //Always whenever we use Scaffold use paddingvalues so that the content of the Scaffold
        //and the content of the other does not overlap with each other
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)
            //Adds padding 16 from all sides
        ) {
            Row(
            ) {
                OutlinedTextField(
                    value = heightInput,
                    onValueChange = {
                        heightInput = it
                    },
                    label = {
                        Text(text = "Enter the Height ")
                    }
                )
                Box(
                ) {
                    TextButton(onClick = {
                        HeightDropdownMenuExpand = true
                    }) {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "you are good enough to know that"
                        )
                            //  Text(HUnit="Unit")
                    }
                    DropdownMenu(
                        expanded = HeightDropdownMenuExpand,
                        onDismissRequest = {
                            HeightDropdownMenuExpand =false
                        }) {
                        DropdownMenuItem(
                            text = { Text("feet") },
                            onClick = {
                                HeightDropdownMenuExpand = false
                            }
                        )
                        DropdownMenuItem(
                            text ={Text("metres")},
                            onClick = {
                                HeightDropdownMenuExpand =false
                                text = ""
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier=Modifier.fillMaxSize()
            ){
                OutlinedTextField(
                    value=weightInput,
                    onValueChange={
                        weightInput =it
                    },
                    label = {
                        Text(text="Enter the Weight")
                    }
                )
                Box(){
                    TextButton(onClick = {
                        WeightDropdownMenuExpand = true
                    }) {
                        Text(text = "Unit")
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "you are good enough to know that"
                        )
                        Text(text = "Unit")
                    }
                    DropdownMenu(
                        expanded = WeightDropdownMenuExpand,
                        onDismissRequest = {
                            WeightDropdownMenuExpand =false
                        }) {
                        DropdownMenuItem(
                            text = { Text("kg") },
                            onClick = {
                                WeightDropdownMenuExpand =false
                            }
                        )
                        DropdownMenuItem(
                            text ={Text("lbs")},
                            onClick = {
                                WeightDropdownMenuExpand =false
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview(showBackground=true)
fun HomeViewPreview(){
    HomeView()
}