package com.example.sneakersapp.ui.theme.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.sneakersapp.model.Result
import com.example.sneakersapp.viewmodels.SneakerViewModel
import kotlinx.coroutines.launch

@Composable
fun SneakerDashboard(
    viewModel: SneakerViewModel,
    navController: NavHostController = rememberNavController()
) {
    val sneakers by viewModel._sneakers.observeAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sneakers",
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            Text(text = "Cart", modifier = Modifier.clickable { navController.navigate("Cart") })
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.Gray)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(12.dp),
            content = {
                sneakers?.size?.let {
                    items(it) { index ->
                        Sneaker(sneakers!![index]) {
                            viewModel.selectSneaker(result = it)
                            navController.navigate("SneakerDetails")
                        }
                    }
                }
            })
    }
}

@Composable
fun Sneaker(result: Result, selectSneaker: (Result) -> Unit = {}) {
    Column(modifier = Modifier.padding(5.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(5.dp))
                .clickable {
                    selectSneaker(result)
                }, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(result.media.original),
                contentDescription = "gfg image",
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
            )
            Text(
                text = result.name, modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp), fontSize = 18.sp, textAlign = TextAlign.Center, maxLines = 1
            )
            Text(
                text = "${result.retailPrice}$",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp),
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }

}

@Composable
fun SneakerDetails(viewModel: SneakerViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val isItemRemoved by viewModel._isItemRemoved.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, top = 100.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(viewModel.selectedSneaker?.media?.original),
            contentDescription = "gfg image",
            modifier = Modifier
                .height(300.dp)
                .width(300.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(android.graphics.Color.parseColor("#fafaf7")),
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                )
                .padding(
                    top = 20.dp, start = 10.dp
                )
                .weight(1f)
        ) {
            LaunchedEffect(key1 = true, block = {
                viewModel.isSneakerStoredInDb()
            })
            SneakerTitle(title = viewModel.selectedSneaker?.name, textColor = Color.Black)
            SneakerTitle(title = "Color :  ${viewModel.selectedSneaker?.colorway}")
            SneakerTitle(title = "Brand :  ${viewModel.selectedSneaker?.brand}")
            Row(modifier = Modifier.fillMaxWidth()) {
                SneakerTitle(title = "Price :  ")
                SneakerTitle(
                    title = "$${viewModel.selectedSneaker?.retailPrice}",
                    textColor = Color(0xFFFFA500)
                )
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Button(
                        onClick = {
                            viewModel.selectedSneaker?.let {
                                coroutineScope.launch {
                                    if (isItemRemoved == null || isItemRemoved == false)
                                        viewModel.addToCart(it)
                                    else
                                        viewModel.removeItemFromCart(it.id)
                                }
                            }
                        },
                        modifier = Modifier.padding(end = 10.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFFFA500))
                    ) {
                        Text(
                            text = if (isItemRemoved == null || isItemRemoved == false) "Add To Cart" else "Remove",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SneakerTitle(title: String?, textColor: Color = Color.DarkGray) {
    Text(
        text = "$title",
        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,
        color = textColor
    )
}
