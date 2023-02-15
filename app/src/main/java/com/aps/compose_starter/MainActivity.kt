package com.aps.compose_starter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val painter = painterResource(id = R.drawable.kermit)
            val description = "Kermit playing in the snow"
            val title= "Kermit is playing in the snow"
            ImageCard(painter = painter, contentDescription = description , title = title)
        }
    }
}

@Preview(name = "imageCard")
@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(painter = painter, contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ){
                Text(text = title, style = TextStyle(Color.White, fontSize = 16.sp))
            }
        }

    }
}

//@Composable
//fun MessageCard(msg:Message){
//     Row(modifier = Modifier.padding(all = 8.dp)){
//                Image(
//                    painter = painterResource(id = R.drawable.ic_launcher_background),
//                    contentDescription = "content profile picture",
//                    modifier = Modifier
//                        .size(40.dp)
//                        .clip(CircleShape)
//                        .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Column {
//                    Text(text = "Hello ${msg.author}",
//                        color = MaterialTheme.colors.secondaryVariant,
//                        style = MaterialTheme.typography.subtitle1
//                    )
//                    Spacer(modifier = Modifier.height(4.dp))
//                    androidx.compose.material.Surface(shape = MaterialTheme.shapes.large, elevation = 2.dp,) {
//                        Text(text = msg.body,
//                            style = MaterialTheme.typography.body1
//                        )
//                    }
//                }
//
//            }
//}
//
//@Preview(name = "Light mode")
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode"
//)
//
//@Composable
//fun PreviewMessageCard(){
//    Compose_starterTheme() {
//        androidx.compose.material.Surface() {
//            MessageCard(
//                msg = Message("Android", "Jepack Compose")
//            )
//        }
//}}
//
data class Message(val author:String,val body: String)
