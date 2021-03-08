package com.example.myfirstcomposetutorial

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : AppCompatActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            printHelloWorld()
//            columnDemo()
//            ScrollColumnDemo()
//            simpleTextFieldComponent()
//            simpleCardDemo()
//            simpleBoxDemo()
//            simpleButtonDemo()
//            simpleDemoOnAlertDialog()
//            simpleCheckBox()
//            simpleProgressBarDemo()
//            simpleRadioButtonDemo()

        }
    }

    /**
     * Simple demo on RB
     */
    @Composable
    private fun simpleRadioButtonDemo() {
        val radioOptions = listOf("Android", "Ios", "React")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[2]) }
        Column {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) }
                        )
                        .padding(horizontal = 16.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) }
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }

    /**
     * Simple demo on PB
     */
    @Composable
    private fun simpleProgressBarDemo() {
        CircularProgressIndicator(
            modifier = Modifier.padding(16.dp),
            progress = 1.0f,
            color = Color.Green,

        )
    }

    /**
     * Simple demo on CheckBox
     */
    @Composable
    private fun simpleCheckBox() {
        val checkedState = remember { mutableStateOf(true) }
        Row {
            // Checkbox is a Composable that is used to indicate two states. You can either
            // select or unselect the Checkbox. Checkboxes are generally used when we have
            // a number of options to choose from.

            // checked is used to identify or set if the checkbox is checked or not
            // onCheckedChange is called when there is a change in the checkbox
            Checkbox(
                checked = checkedState.value,
                modifier = Modifier.padding(16.dp),
                onCheckedChange = { checkedState.value = it },
            )
            Text(text = "Checkbox Example", modifier = Modifier.padding(16.dp))
        }
    }

    /**
     * Simple demo on AlertDialog
     */
    @Composable
    private fun simpleDemoOnAlertDialog() {
        val openDialog = remember { mutableStateOf(true) }
        if (openDialog.value) {
            // AlertDialog is a Composable that is used to show a dialog with some urgent
            // information/detail on it. For example, while logging out, you can show a dialog
            // to the user "Are you sure?". If you click outside of the dialog or the back button,
            // then dialog will disappear. To disable this feature, use empty onCloseRequest
            AlertDialog(
                // onDismissRequest will be called when back button is pressed or there is some click
                // outside the AlertDialog and NOT on pressing the dismissButton
                onDismissRequest = { openDialog.value = false },
                title = { Text(text = "Alert Dialog") },
                text = { Text("Hello! I am an Alert Dialog") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                            /* Do some other action */
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                            /* Do some other action */
                        }
                    ) {
                        Text("Dismiss")
                    }
                },
                backgroundColor = Color.Black,
                contentColor = Color.White
            )
        }
    }

    /**
     * Simple demo on imageview
     */
    @ExperimentalComposeUiApi
    @Composable
    private fun simpleButtonDemo() {
        Column(Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = ""
            )

            Button(
                onClick = {
                    Toast.makeText(this@MainActivity, "Thanks for clicking!", Toast.LENGTH_LONG)
                        .show()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Green))
            ) {
                Text("Click Me")
            }
        }
    }

    /**
     * Simple demo on Box
     */
    @Composable
    private fun simpleBoxDemo() {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                text = "I am a text over Image",
                fontSize = 16.sp,
                color = Color.Red
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                text = "I am a text over Image",
                fontSize = 16.sp,
                color = Color.Red
            )
        }
    }

    /**
     * Simple demo on card
     */
    @Composable
    private fun simpleCardDemo() {
        Column {
            for (item in 1..100) {
                Card(
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable(onClick = {
                            Toast
                                .makeText(
                                    this@MainActivity, "Author: $item", Toast.LENGTH_SHORT
                                )
                                .show()
                        }),
                    backgroundColor = Color(0xFFFFA867.toInt())
                ) {
                    Text(
                        "blog.name$item", style = TextStyle(
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        ), modifier = Modifier.padding(16.dp)
                    )
                }
            }

        }
    }

    @Composable
    private fun simpleTextFieldComponent() {
        Surface(color = Color.Gray, modifier = Modifier.padding(16.dp)) {
            var inputvalue = remember { mutableStateOf(TextFieldValue()) }
            TextField(
                value = inputvalue.value,
                onValueChange = { inputvalue.value = it },
                placeholder = { Text(text = "Enter user name")},
                keyboardOptions = KeyboardOptions(
                    // below line is use for capitalization
                    // inside our text field.
                    capitalization = KeyboardCapitalization.None,

                    // below line is to enable auto
                    // correct in our keyboard.
                    autoCorrect = true,

                    // below line is used to specify our
                    // type of keyboard such as text, number, phone.
                    keyboardType = KeyboardType.Text,
                ),
                maxLines = 1
            )

        }
    }


    /**
     * Simple demo on text
     */
    @Composable
    private fun printHelloWorld() {
        Text("Hello World", style = TextStyle(color = Color.Blue))

    }

    /**
     * Simple column demo
     */
    @Composable
    private fun columnDemo() {
        Column(
            Modifier.padding(all = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text("Hello World", style = TextStyle(color = Color.Blue))
            Text("Hello World", style = TextStyle(color = Color.Blue))
            Text("Hello World", style = TextStyle(color = Color.Blue))
        }
    }

    /**
     * Simple Scroll demo
     */
    @Composable
    private fun ScrollColumnDemo() {
        setTextStyle()
    }

    @Composable
    private fun setTextStyle() {
       Column(
           Modifier.verticalScroll(enabled = true , state = ScrollState(0))
       )  {
                for(item in 1..100)
                Text(
                    text = "Counter $item",
                    style = TextStyle(color = Color.Blue, fontSize = 16.sp),
                    fontStyle = FontStyle.Italic,
                )
            }
    }

}
