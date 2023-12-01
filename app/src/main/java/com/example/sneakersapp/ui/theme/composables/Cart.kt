package com.example.sneakersapp.ui.theme.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.sneakersapp.model.Result
import com.example.sneakersapp.viewmodels.SneakerViewModel
import kotlinx.coroutines.launch

@Composable
fun Cart(viewModel: SneakerViewModel) {
    LaunchedEffect(key1 = true, block = {
        viewModel.getSneakersFromCart()
    })
    val coroutineScope = rememberCoroutineScope()
    val itemsInCart: List<Result>? by viewModel._itemsInCart.observeAsState()
    val subTotal by viewModel._subTotal.observeAsState()
    Column {
        Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Items In your Cart",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color.Gray)
            )
            LazyColumn {
                itemsInCart?.size?.let {
                    items(it) { item ->
                        CartItem(result = itemsInCart!![item]) {
                            coroutineScope.launch {
                                viewModel.removeItemFromCart(it.id)
                                viewModel.getSneakersFromCart()
                            }
                        }
                    }
                }
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(5.dp)) {
            Text(
                text = "Order Details",
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold)
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)) {
                SneakerTitle(title = "Total : ")
                SneakerTitle(title = "$$subTotal",textColor = Color(0xFFFFA500))
            }
        }
    }
}

@Composable
fun CartItem(result: Result, selectSneaker: (Result) -> Unit = {}) {
    Row(modifier = Modifier.padding(5.dp)) {
        Box(
            modifier = Modifier.border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(5.dp)
            ),
        ) {
            Text(
                text = "Remove",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        selectSneaker(result)
                    },
                textAlign = TextAlign.End,
                fontSize = 15.sp,
                color = Color.Red,
                fontWeight = FontWeight.ExtraBold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = rememberAsyncImagePainter(result.media.original),
                    contentDescription = "gfg image",
                    modifier = Modifier
                        .height(150.dp)
                        .width(200.dp)
                )
                Column(
                    modifier = Modifier
                        .height(150.dp)
                        .weight(1f)
                        .clickable {
//                        selectSneaker(result)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = result.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp),
                        fontSize = 18.sp,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${result.retailPrice}$",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                }
            }
        }
    }
}