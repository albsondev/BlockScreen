
package com.example.blockscreen

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.blockscreen.ui.theme.BlockScreenTheme

class AccessDeniedActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlockScreenTheme {
                AccessDeniedScreen()
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun AccessDeniedScreen() {
        var visible by remember { mutableStateOf(false) }

        // Ativa a animação de entrada ao carregar a tela
        LaunchedEffect(Unit) {
            visible = true
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFCDD2)), // Fundo vermelho claro
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Ícone de Cadeado",
                        tint = Color(0xFFD32F2F),
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Permissão necessária",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color(0xFFD32F2F)
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Este aplicativo precisa da permissão de administrador do dispositivo para bloquear a tela.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFB71C1C),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
                                putExtra(
                                    DevicePolicyManager.EXTRA_DEVICE_ADMIN,
                                    ComponentName(this@AccessDeniedActivity, MyDeviceAdminReceiver::class.java)
                                )
                                putExtra(
                                    DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                                    "Permita o acesso para que o app possa bloquear a tela quando necessário."
                                )
                            }
                            startActivity(intent)
                            finish()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Permitir Acesso",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
