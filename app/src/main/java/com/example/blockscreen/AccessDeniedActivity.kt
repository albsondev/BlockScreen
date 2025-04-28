package com.example.blockscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.blockscreen.ui.theme.BlockScreenTheme

class AccessDeniedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlockScreenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Permissão de Administrador não concedida.",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "O app precisa dessa permissão para funcionar corretamente.",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Button(onClick = {
                            // Voltar para MainActivity para tentar novamente
                            val intent = Intent(this@AccessDeniedActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }) {
                            Text(text = "Tentar Novamente")
                        }
                    }
                }
            }
        }
    }
}
