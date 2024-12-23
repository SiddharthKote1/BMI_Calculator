import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(modifier: Modifier = Modifier) {
    // Height and weight inputs
    var heightInput by remember { mutableStateOf("") }
    var weightInput by remember { mutableStateOf("") }

    // Dropdown menu state for height and weight units
    var heightDropdownMenuExpanded by remember { mutableStateOf(false) }
    var weightDropdownMenuExpanded by remember { mutableStateOf(false) }

    // Units for height and weight
    var heightUnit by remember { mutableStateOf("Unit") }
    var weightUnit by remember { mutableStateOf("Unit") }

    // Feet and inches for height
    var feet by remember { mutableStateOf("") }
    var inches by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "BMI Calculator") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.DarkGray,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Height Input
            Row {
                if (heightUnit == "feet") {
                    OutlinedTextField(
                        value = feet,
                        onValueChange = { feet = it },
                        label = { Text(text = "Feet") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = inches,
                        onValueChange = { inches = it },
                        label = { Text(text = "Inches") },
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    OutlinedTextField(
                        value = heightInput,
                        onValueChange = { heightInput = it },
                        label = { Text(text = "Height") },
                        modifier = Modifier.weight(1f)
                    )
                }
                Box {
                    TextButton(onClick = { heightDropdownMenuExpanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Select Unit")
                        Text(text = heightUnit)
                    }
                    DropdownMenu(
                        expanded = heightDropdownMenuExpanded,
                        onDismissRequest = { heightDropdownMenuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                heightDropdownMenuExpanded = false
                                heightUnit = "feet"
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {
                                heightDropdownMenuExpanded = false
                                heightUnit = "meters"
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Weight Input
            Row {
                OutlinedTextField(
                    value = weightInput,
                    onValueChange = { weightInput = it },
                    label = { Text(text = "Weight") },
                    modifier = Modifier.weight(1f)
                )
                Box {
                    TextButton(onClick = { weightDropdownMenuExpanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Select Unit")
                        Text(text = weightUnit)
                    }
                    DropdownMenu(
                        expanded = weightDropdownMenuExpanded,
                        onDismissRequest = { weightDropdownMenuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Kg") },
                            onClick = {
                                weightDropdownMenuExpanded = false
                                weightUnit = "kg"
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Lbs") },
                            onClick = {
                                weightDropdownMenuExpanded = false
                                weightUnit = "lbs"
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier=Modifier.align(Alignment.CenterHorizontally)){
                Button(
                    onClick = {

                    },shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFADD8E6),
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "Calculate BMI")
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomeViewPreview() {
    HomeView()
}
