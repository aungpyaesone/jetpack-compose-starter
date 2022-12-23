package com.aps.compose_starter

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aps.compose_starter.ui.theme.Compose_starterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_starterTheme() {
                androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCard(
                        msg = Message("Android", "Jepack Compose")
                    )
                }
            }
        }
    }
}

@Composable
fun MessageCard(msg:Message){
     Row(modifier = Modifier.padding(all = 8.dp)){
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "content profile picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = "Hello ${msg.author}",
                        color = MaterialTheme.colors.secondaryVariant,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    androidx.compose.material.Surface(shape = MaterialTheme.shapes.large, elevation = 2.dp,) {
                        Text(text = msg.body,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }

            }
}

@Preview(name = "Light mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)

@Composable
fun PreviewMessageCard(){
    Compose_starterTheme() {
        androidx.compose.material.Surface() {
            MessageCard(
                msg = Message("Android", "Jepack Compose")
            )
        }
}}

data class Message(val author:String,val body: String)
