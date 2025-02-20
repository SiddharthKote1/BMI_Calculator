import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    var bmiResult by remember { mutableStateOf("") }

    var backgroundColor by remember { mutableStateOf(Color.White) }

    fun calculateBMI() {
        // Convert height to meters
        val heightInMeters: Float? = when (heightUnit) {
            "feet" -> {
                val feetValue = feet.toFloatOrNull()
                val inchesValue = inches.toFloatOrNull()
                if (feetValue != null && inchesValue != null) {
                    (feetValue * 0.3048f) + (inchesValue * 0.0254f)
                } else null
            }
            "meters" -> heightInput.toFloatOrNull()
            else -> null
        }

        // Convert weight to kilograms
        val weightInKg: Float? = when (weightUnit) {
            "kg" -> weightInput.toFloatOrNull()
            "lbs" -> weightInput.toFloatOrNull()?.times(0.453592f)
            else -> null
        }

        // Validate height and weight
        if (heightInMeters == null || heightInMeters <= 0) {
            bmiResult = "Recheck the details."
            return
        }

        if (weightInKg == null || weightInKg <= 0) {
            bmiResult = "Invalid the details."
            return
        }

        // Calculate BMI
        val bmi = weightInKg / (heightInMeters*heightInMeters)
        bmiResult = "Your BMI is: %.2f".format(bmi)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "BMI Calculator") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(backgroundColor)

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
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(
                    onClick = {
                        calculateBMI()
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFADD8E6),
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "Calculate BMI")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = bmiResult,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

           if (bmiResult.isNotEmpty()) {
                when {
                    bmiResult.startsWith("Invalid") -> {
                        // Display error message
                        Text(
                            text = "Select the Unit",
                            color = Color.Black,
                            modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally)
                        )
                    }

                    else -> {
                        // Determine BMI category
                        val bmiValue = bmiResult.substringAfter("Your BMI is: ").toFloatOrNull()
                        val bmiCategory = when {
                            bmiValue == null -> "Unknown"
                            bmiValue < 18.5 -> "Underweight"
                            bmiValue in 18.5..24.9 -> "Normal weight"
                            bmiValue in 25.0..29.9 -> "Overweight"
                            else -> "Obese"
                        }
                        // Display BMI category
                        Text(
                            text = "BMI Category: $bmiCategory",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            modifier = Modifier.padding(8.dp).align(
                                Alignment.CenterHorizontally
                            )
                        )
                        if(bmiCategory=="Underweight"){
                            backgroundColor=Color(0xFFFFFACD)
                        }
                        else if(bmiCategory=="Normal weight"){
                            backgroundColor=Color(0xFF90EE90)
                        }
                        else if(bmiCategory=="Overweight"){
                          backgroundColor=Color(0xFFFFA07A)
                        }
                        else if(bmiCategory=="Obese"){
                            backgroundColor=Color(0xFFCD5C5C)
                        }
                    }
                }
            }
        }
    }
}

