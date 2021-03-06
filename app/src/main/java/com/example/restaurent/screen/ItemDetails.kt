package com.example.restaurent.screen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.restaurent.model.Cart

import com.example.restaurent.ui.theme.RestaurentTheme
import com.example.restaurent.ui.theme.orange
import com.example.restaurent.ui.theme.white
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ItemDetails : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurentTheme {

                val context = LocalContext.current
                val intent = (context as ItemDetails).intent
                val name = intent.getStringExtra("Name")
                val Des = intent.getStringExtra("Des")
                val Price = intent.getStringExtra("Price")
                val ImageUrl = intent.getStringExtra("ImageUrl")





                Column(modifier = Modifier.fillMaxSize()) {
                    TopBar1(name = "Product Details",modifier = Modifier.padding(16.dp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Column(Modifier.fillMaxWidth()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            AsyncImage(
                                model = "$ImageUrl",
                                contentDescription = "img",

                                modifier = Modifier
                                    .size(200.dp)
                                    .weight(3f)
                            )

                        }
                        Spacer(modifier = Modifier.height(10.dp))

                    }
                    Spacer(modifier = Modifier.height(25.dp))

                    Column(
                        // val letterSpacing = 0.5.sp
                        //    val lineHeight = 20.sp
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        if (name != null) {
                            Text(
                                text = name,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp,
                                lineHeight = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Description : ",
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp,
                            lineHeight = 20.sp
                        )
                        if (Des != null) {
                            Text(
                                text = Des,
                                letterSpacing = 0.5.sp,
                                lineHeight = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))

                        if (Price != null) {
                            Text(
                                text = "Price :$Price",
                                letterSpacing = 0.5.sp,
                                lineHeight = 20.sp
                            )
                        }


                    }
                    Spacer(Modifier.size(16.dp))

                    Spacer(Modifier.size(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ){

//                        ActionButton(
//                            text = "Add to Cart",
//
//
//                            modifier = Modifier
//                                .defaultMinSize(55.dp)
//                                .height(55.dp)
//                                .width(180.dp)
//                                .background(color = Color.Magenta)
//
//
//                        )

                        Button(
                            onClick = {
                                val cart = Cart(
                                    Firebase.auth.uid.toString(),
                                    name.toString(),
                                    ImageUrl.toString(),
                                    Price.toString()

                                )

                                val firestore = FirebaseFirestore.getInstance()
                                val collection = firestore.collection("cart").document(Firebase.auth.uid.toString()).collection("cart")
                                collection.add(cart)
                                Toast
                                    .makeText(
                                        context,
                                        "Added This Product",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = orange),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    end  = 30.dp,
                                    start = 30.dp,

                                ),
                                //.align(Alignment.CenterHorizontally),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text(
                                text = "Add to Cart",
                                color = white,
                                style = MaterialTheme.typography.button,
                                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                            )
                        }


                    }
                    Spacer(Modifier.size(16.dp))




                }





            }
        }
    }
}

@Composable
fun TopBar1(
    name: String,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {

        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )


    }
}

