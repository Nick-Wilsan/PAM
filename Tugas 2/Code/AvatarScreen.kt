package com.example.tugas02

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tugas02.ui.theme.Tugas02Theme

private const val HEAD_W = 911
private const val HEAD_H = 1293

private val BROW = intArrayOf(R.drawable.face_0001, 166, 518, 597, 59)
private val EYE = intArrayOf(R.drawable.face_0003, 162, 548, 601, 174)
private val NOSE = intArrayOf(R.drawable.face_0002, 374, 712, 181, 126)
private val MOUTH = intArrayOf(R.drawable.face_0000, 346, 838, 237, 131)

@Composable
fun AvatarFace(
    scale: Float,
    showBrow: Boolean = true,
    showEye: Boolean = true,
    showNose: Boolean = true,
    showMouth: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.requiredSize((HEAD_W * scale).dp, (HEAD_H * scale).dp)
    ) {
        Image(
            painter = painterResource(R.drawable.face_0004),
            contentDescription = "Kepala",
            modifier = Modifier.fillMaxSize()
        )

        if (showBrow) FacePart(BROW, scale, "Alis")
        if (showEye) FacePart(EYE, scale, "Mata")
        if (showNose) FacePart(NOSE, scale, "Hidung")
        if (showMouth) FacePart(MOUTH, scale, "Mulut")
    }
}

@Composable
private fun FacePart(part: IntArray, scale: Float, label: String) {
    Image(
        painter = painterResource(part[0]),
        contentDescription = label,
        modifier = Modifier
            .offset(x = (part[1] * scale).dp, y = (part[2] * scale).dp)
            .requiredSize((part[3] * scale).dp, (part[4] * scale).dp)
    )
}

private const val FACE_TOP = 300
private const val FACE_BOTTOM = 1030

@Composable
fun AvatarCircle(
    size: Dp,
    modifier: Modifier = Modifier,
    background: Color = Color.Transparent
) {
    val scale = size.value / HEAD_W
    val faceMidY = (FACE_TOP + FACE_BOTTOM) / 2f
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(background),
        contentAlignment = Alignment.TopStart
    ) {
        AvatarFace(
            scale = scale,
            modifier = Modifier.offset(
                x = 0.dp,
                y = (size.value / 2 - faceMidY * scale).dp
            )
        )
    }
}

@Composable
fun AvatarScreen(onBack: () -> Unit) {
    // State untuk tiap checkbox
    var brow by remember { mutableStateOf(true) }
    var eye by remember { mutableStateOf(true) }
    var nose by remember { mutableStateOf(true) }
    var mouth by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Avatar",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Centang untuk menampilkan komponen wajah",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            AvatarFace(
                scale = 0.25f,
                showBrow = brow,
                showEye = eye,
                showNose = nose,
                showMouth = mouth
            )
        }

        Spacer(Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CheckItem("Brow", brow) { brow = it }
                CheckItem("Eye", eye) { eye = it }
                CheckItem("Nose", nose) { nose = it }
                CheckItem("Mouth", mouth) { mouth = it }
            }
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Kembali ke Profil")
        }
    }
}

@Composable
private fun CheckItem(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(label, fontSize = 13.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun AvatarScreenPreview() {
    Tugas02Theme {
        AvatarScreen(onBack = {})
    }
}
