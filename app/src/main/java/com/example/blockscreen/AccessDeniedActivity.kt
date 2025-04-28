package com.example.blockscreen

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blockscreen.ui.theme.BlockScreenTheme

class AccessDeniedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BlockScreenTheme {
                AccessDeniedScreen(
                    onOkClicked = {
                        // Ao clicar no botão, vai abrir as permissões de Device Admin
                        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
                        intent.putExtra(
                            DevicePolicyManager.EXTRA_DEVICE_ADMIN,
                            ComponentName(this, MyDeviceAdminReceiver::class.java)
                        )
                        intent.putExtra(
                            DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                            "Este app precisa de permissão para bloquear a tela."
                        )
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun AccessDeniedScreen(onOkClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC0392B)) // Vermelho escuro
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Ícone de bloqueio
            Image(
                painter = painterResource(id = R.drawable.ic_lock),
                contentDescription = "Access Denied Icon",
                modifier = Modifier.size(120.dp)
            )

            // Texto de acesso negado
            Text(
                text = "Permissão Necessária",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Este app precisa de acesso como administrador para que a função de bloquear a tela possa funcionar corretamente.",
                color = Color.White,
                fontSize = 22.sp,
                lineHeight = 22.sp
            )

            // Botão OK
            Button(
                onClick = onOkClicked,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Alterar permissões de administrador",
                    color = Color(0xFFB00020),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
