package com.example.sneakersapp

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

class MockInterceptor(private val context: Context) : Interceptor {
    val responseString = "{\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"id\": \"fffff949-cad1-4755-a630-63384e4d4296\",\n" +
            "      \"brand\": \"Saucony\",\n" +
            "      \"name\": \"Freedom 5 'Alloy Topaz'\",\n" +
            "      \"colorway\": \"Alloy/Topaz\",\n" +
            "      \"gender\": \"men\",\n" +
            "      \"releaseDate\": \"2022-05-16\",\n" +
            "      \"retailPrice\": 150,\n" +
            "      \"media\": {\n" +
            "        \"original\": \"https://image.goat.com/attachments/product_template_pictures/images/072/407/107/original/S20726_15.png.png\",\n" +
            "        \"small\": \"https://image.goat.com/750/attachments/product_template_pictures/images/072/407/107/original/S20726_15.png.png\",\n" +
            "        \"thumbnail\": \"https://image.goat.com/375/attachments/product_template_pictures/images/072/407/107/original/S20726_15.png.png\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"ffffd3db-b0bc-4b09-8fc0-66b340be7ddd\",\n" +
            "      \"brand\": \"Puma\",\n" +
            "      \"name\": \"UEG x Court Play Slip-On 'Gravity Resistance - White'\",\n" +
            "      \"colorway\": \"White/Black\",\n" +
            "      \"gender\": \"men\",\n" +
            "      \"releaseDate\": \"\",\n" +
            "      \"retailPrice\": 100,\n" +
            "      \"media\": {\n" +
            "        \"original\": \"https://image.goat.com/attachments/product_template_pictures/images/043/133/040/original/361637_02.png.png\",\n" +
            "        \"small\": \"https://image.goat.com/750/attachments/product_template_pictures/images/043/133/040/original/361637_02.png.png\",\n" +
            "        \"thumbnail\": \"https://image.goat.com/375/attachments/product_template_pictures/images/043/133/040/original/361637_02.png.png\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"ffff4e68-c0b3-40cb-a2a0-cb634c03e6bc\",\n" +
            "      \"brand\": \"Nike\",\n" +
            "      \"name\": \"NFL x OffCourt Slide 'Tampa Bay Buccaneers'\",\n" +
            "      \"colorway\": \"Black/Gym Red/White/Anthracite\",\n" +
            "      \"gender\": \"men\",\n" +
            "      \"releaseDate\": \"2022-02-21\",\n" +
            "      \"retailPrice\": 135,\n" +
            "      \"media\": {\n" +
            "        \"original\": \"https://image.goat.com/attachments/product_template_pictures/images/068/374/783/original/DD0513_001.png.png\",\n" +
            "        \"small\": \"https://image.goat.com/750/attachments/product_template_pictures/images/068/374/783/original/DD0513_001.png.png\",\n" +
            "        \"thumbnail\": \"https://image.goat.com/375/attachments/product_template_pictures/images/068/374/783/original/DD0513_001.png.png\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"ffff317b-a854-4aa0-bec1-498708ccf948\",\n" +
            "      \"brand\": \"New Balance\",\n" +
            "      \"name\": \"Wmns Powher Run 'Munsell White Cloud Pink'\",\n" +
            "      \"colorway\": \"Munsell White/Cloud Pink\",\n" +
            "      \"gender\": \"women\",\n" +
            "      \"releaseDate\": \"2021-03-29\",\n" +
            "      \"retailPrice\": 70,\n" +
            "      \"media\": {\n" +
            "        \"original\": \"https://image.goat.com/attachments/product_template_pictures/images/052/646/095/original/WPHERSR1.png.png\",\n" +
            "        \"small\": \"https://image.goat.com/750/attachments/product_template_pictures/images/052/646/095/original/WPHERSR1.png.png\",\n" +
            "        \"thumbnail\": \"https://image.goat.com/375/attachments/product_template_pictures/images/052/646/095/original/WPHERSR1.png.png\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"fffef2e6-b274-4ab7-a6da-b997db8c0bc5\",\n" +
            "      \"brand\": \"Puma\",\n" +
            "      \"name\": \"Turin 3 'White Black'\",\n" +
            "      \"colorway\": \"White/Black\",\n" +
            "      \"gender\": \"men\",\n" +
            "      \"releaseDate\": \"\",\n" +
            "      \"retailPrice\": 110,\n" +
            "      \"media\": {\n" +
            "        \"original\": \"https://image.goat.com/attachments/product_template_pictures/images/067/070/876/original/383037_06.png.png\",\n" +
            "        \"small\": \"https://image.goat.com/750/attachments/product_template_pictures/images/067/070/876/original/383037_06.png.png\",\n" +
            "        \"thumbnail\": \"https://image.goat.com/375/attachments/product_template_pictures/images/067/070/876/original/383037_06.png.png\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"fffec6f5-f0fe-4393-ac61-5a3c0213e9ad\",\n" +
            "      \"brand\": \"Puma\",\n" +
            "      \"name\": \"evoPower 1.2 POP FG 'Multi-Color'\",\n" +
            "      \"colorway\": \"White/Orange Clown Fish/Electric Blue Lemonade\",\n" +
            "      \"gender\": \"men\",\n" +
            "      \"releaseDate\": \"\",\n" +
            "      \"retailPrice\": 100,\n" +
            "      \"media\": {\n" +
            "        \"original\": \"https://image.goat.com/attachments/product_template_pictures/images/046/946/145/original/103468_01.png.png\",\n" +
            "        \"small\": \"https://image.goat.com/750/attachments/product_template_pictures/images/046/946/145/original/103468_01.png.png\",\n" +
            "        \"thumbnail\": \"https://image.goat.com/375/attachments/product_template_pictures/images/046/946/145/original/103468_01.png.png\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"fffea7a9-c072-4096-aba5-58f4a535e50e\",\n" +
            "      \"brand\": \"Nike\",\n" +
            "      \"name\": \"Nike SB Dunk Low Shanghai (2004)\",\n" +
            "      \"colorway\": \"White/Metallic Gold-Redwood\",\n" +
            "      \"gender\": \"men\",\n" +
            "      \"releaseDate\": \"2004-01-01\",\n" +
            "      \"retailPrice\": 65,\n" +
            "      \"media\": {\n" +
            "        \"original\": \"https://image.goat.com/attachments/product_template_pictures/images/052/787/516/original/23459_00.png.png\",\n" +
            "        \"small\": \"https://image.goat.com/750/attachments/product_template_pictures/images/052/787/516/original/23459_00.png.png\",\n" +
            "        \"thumbnail\": \"https://image.goat.com/375/attachments/product_template_pictures/images/052/787/516/original/23459_00.png.png\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"fffe8ea0-e71a-429d-adfc-b0b04120df8d\",\n" +
            "      \"brand\": \"adidas\",\n" +
            "      \"name\": \"ADIDAS JS Wings Opart\",\n" +
            "      \"colorway\": \"Run White/Metal Gold/Black\",\n" +
            "      \"gender\": \"men\",\n" +
            "      \"releaseDate\": \"\",\n" +
            "      \"retailPrice\": 200,\n" +
            "      \"media\": {\n" +
            "        \"original\": \"https://image.goat.com/attachments/product_template_pictures/images/000/020/412/original/G95768.png.png\",\n" +
            "        \"small\": \"https://image.goat.com/750/attachments/product_template_pictures/images/000/020/412/original/G95768.png.png\",\n" +
            "        \"thumbnail\": \"https://image.goat.com/375/attachments/product_template_pictures/images/000/020/412/original/G95768.png.png\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"fffe7944-e64f-49cb-ac13-a36bdf95d00f\",\n" +
            "      \"brand\": \"Vans\",\n" +
            "      \"name\": \"Style 36 Decon SF 'Black'\",\n" +
            "      \"colorway\": \"Black/White\",\n" +
            "      \"gender\": \"men\",\n" +
            "      \"releaseDate\": \"\",\n" +
            "      \"retailPrice\": 57,\n" +
            "      \"media\": {\n" +
            "        \"original\": \"https://image.goat.com/attachments/product_template_pictures/images/037/155/265/original/VN0A3MVLY28.png.png\",\n" +
            "        \"small\": \"https://image.goat.com/750/attachments/product_template_pictures/images/037/155/265/original/VN0A3MVLY28.png.png\",\n" +
            "        \"thumbnail\": \"https://image.goat.com/375/attachments/product_template_pictures/images/037/155/265/original/VN0A3MVLY28.png.png\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return Response.Builder()
            .code(200)
            .message(responseString)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .addHeader("Content-Type", "application/json")
            .body(ResponseBody.create("application/json".toMediaTypeOrNull(), responseString))
            .build()
    }

}
