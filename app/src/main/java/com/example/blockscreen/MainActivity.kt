package com.example.blockscreen

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.blockscreen.ui.theme.BlockScreenTheme

class MainActivity : ComponentActivity() {

    private lateinit var devicePolicyManager: DevicePolicyManager
    private lateinit var componentName: ComponentName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        componentName = ComponentName(this, MyDeviceAdminReceiver::class.java)

        if (!devicePolicyManager.isAdminActive(componentName)) {
            // Se não é Admin, abre a tela de acesso negado
            startActivity(Intent(this, AccessDeniedActivity::class.java))
            finish()
            return
        }

        // Se for Admin, pode travar a tela
        devicePolicyManager.lockNow()

        // E fecha o app
        finish()
    }
}
