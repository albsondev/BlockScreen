package com.example.blockscreen

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var devicePolicyManager: DevicePolicyManager
    private lateinit var compName: ComponentName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        compName = ComponentName(this, MyDeviceAdminReceiver::class.java)

        if (!devicePolicyManager.isAdminActive(compName)) {
            // Se não tem permissão de Administrador, abre a tela de Acesso Negado
            startActivity(Intent(this, AccessDeniedActivity::class.java))
            finish()
            return
        }

        // Se já tem permissão, BLOQUEIA a tela imediatamente
        devicePolicyManager.lockNow()

        // Após bloquear, fecha a MainActivity
        finish()
    }
}
