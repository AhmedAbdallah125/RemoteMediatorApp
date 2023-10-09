package com.example.remotemediatorapp.prsentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.R
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.remotemediatorapp.domain.Beverage
import com.example.remotemediatorapp.ui.theme.RemoteMediatorAppTheme

@Composable
fun BeverageItem(beverage: Beverage) {
    Card(
        modifier = Modifier.wrapContentSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
//        to center the content and make parent wrap
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)

        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(beverage.imageUrl)
                    .build(),
                contentDescription = "hello brothers",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
                    .clip(CircleShape)

            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = beverage.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = beverage.tagLine,
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = beverage.description,
                )
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.End),
                    text = beverage.firstMadeDate,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        fontSize = 8.sp
                    )
                )

            }
        }

    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
fun BeverageItemPre() {
    RemoteMediatorAppTheme {
        BeverageItem(
            Beverage(
                id = 0,
                name = "Beverage Name",
                tagLine = "you know you shouldn't",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                        "electronic typesetting, remaining essentially unchanged. " +
                        "like Aldus PageMaker including versions of Lorem Ipsum.",
                firstMadeDate = "12 mar 20123 Date",
                imageUrl = "https://picsum.photos/id/1/200/300\""
            )
        )
    }
}