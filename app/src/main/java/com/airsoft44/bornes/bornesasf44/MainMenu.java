package com.airsoft44.bornes.bornesasf44;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.macroyau.blue2serial.BluetoothDeviceListDialog;
import com.macroyau.blue2serial.BluetoothSerial;
import com.macroyau.blue2serial.BluetoothSerialListener;


public class MainMenu extends AppCompatActivity implements BluetoothSerialListener, BluetoothDeviceListDialog.OnDeviceSelectedListener {

    public BluetoothSerial bluetoothSerial;
    private static final int REQUEST_ENABLE_BLUETOOTH = 1;

    String MAC_ADDRESS = "20:16:01:20:18:64";

    private ProgressBar spinnerConnexion;
    private Button boutonConnexion;

    private String commande = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerConnexion = findViewById(R.id.progressBar_connexion);
        boutonConnexion = findViewById(R.id.btn_connexion);

        boutonConnexion.setOnClickListener(clickBtnConnexion);

        bluetoothSerial = new BluetoothSerial(this, this);
        bluetoothSerial.setup();

        if (bluetoothSerial.checkBluetooth() && bluetoothSerial.isBluetoothEnabled()) {
            if (!bluetoothSerial.isConnected()) {
                bluetoothSerial.start();
            }
        }
    }

    View.OnClickListener clickBtnConnexion = new View.OnClickListener() {
        public void onClick(View v) {

            int state = bluetoothSerial.getState();

            // Display the current state on the app bar as the subtitle
            switch (state) {
                case BluetoothSerial.STATE_DISCONNECTED:
                    showDeviceListDialog();
                    break;
                case BluetoothSerial.STATE_CONNECTED:
                    stopBluetooth();
                    break;
            }
        }
    };

    public void send(String value) {
        String send = value.trim();
        if (send.length() > 0) {
            try {
                bluetoothSerial.write(send, false);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void lancerPartie(int tpsTotalCapture,int tpsChgtEquipe,int buzzer) {
        String commandeLancement = "tpsCaptureTotal=" + tpsTotalCapture + ";tpsChgtEquipe=" + tpsChgtEquipe + ";buzzer=" + buzzer;
        Log.d("LCTPARTIE",commandeLancement);

        send(commandeLancement);
    }

    public void stopBluetooth() {
        bluetoothSerial.stop();
    }

    public void showDeviceListDialog() {
        // Display dialog for selecting a remote Bluetooth device
        BluetoothDeviceListDialog dialog = new BluetoothDeviceListDialog(this);
        dialog.setOnDeviceSelectedListener(this);
        dialog.setTitle(R.string.liste_dialog);
        dialog.setDevices(bluetoothSerial.getPairedDevices());
        dialog.showAddress(true);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_ENABLE_BLUETOOTH:
                // Set up Bluetooth serial port when Bluetooth adapter is turned on
                if (resultCode == Activity.RESULT_OK) {
                    bluetoothSerial.setup();
                }
                break;
        }
    }

    public void updateBluetoothState() {
        // Get the current Bluetooth state
        final int state;
        if (bluetoothSerial != null)
            state = bluetoothSerial.getState();
        else
            state = BluetoothSerial.STATE_DISCONNECTED;

        // Display the current state on the app bar as the subtitle
        switch (state) {
            case BluetoothSerial.STATE_CONNECTING:
                spinnerConnexion.setVisibility(View.VISIBLE);
                boutonConnexion.setEnabled(false);
                boutonConnexion.setText(R.string.btn_connexion_connexion);
                break;
            case BluetoothSerial.STATE_CONNECTED:
                boutonConnexion.setText(R.string.btn_connexion_deconnecter);
                boutonConnexion.setEnabled(true);

                initialisationPostConnexion();
                break;
            default:
                spinnerConnexion.setVisibility(View.GONE);

                boutonConnexion.setText(R.string.btn_connexion_connecter);

                boutonConnexion.setEnabled(true);
                break;
        }
    }

    private void initialisationPostConnexion() {

        send("100");

    }
    /* Implementation of BluetoothSerialListener */

    @Override
    public void onBluetoothNotSupported() {
        new AlertDialog.Builder(this)
                .setMessage("Bluetooth indisponible")
                .setPositiveButton("Quitter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void onBluetoothDisabled() {
        Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBluetooth, REQUEST_ENABLE_BLUETOOTH);
    }

    @Override
    public void onBluetoothDeviceDisconnected() {
        invalidateOptionsMenu();
        updateBluetoothState();
    }

    @Override
    public void onConnectingBluetoothDevice() {
        updateBluetoothState();
    }

    @Override
    public void onBluetoothDeviceConnected(String name, String address) {
        invalidateOptionsMenu();
        updateBluetoothState();
    }

    @Override
    public void onBluetoothSerialRead(String message) {
        if(message.contains("|")) {
            commande += message;
            commande = commande.replace("|", "");
            traitementCommande(commande);
            commande = "";
        } else {
            commande += message;
        }

    }

    public void traitementCommande(String commande){
        Log.d("BLUETOOTHASF","Commande reçue: " + commande);

        if(commande.equals("100-0")) {
            send("101");
            Intent intent = new Intent(this, LaunchGame.class);
            startActivity(intent);
        }
    }
    @Override
    public void onBluetoothSerialWrite(String message) {
        // Print the outgoing message on the terminal screen
        Log.d("BLUETOOTHASF", bluetoothSerial.getLocalAdapterName() + ": " + message);
    }

    /* Implementation of BluetoothDeviceListDialog.OnDeviceSelectedListener */

    @Override
    public void onBluetoothDeviceSelected(BluetoothDevice device) {
        // Connect to the selected remote Bluetooth device
        if(device.toString().equals(MAC_ADDRESS)) {
            bluetoothSerial.connect(device);
        }
    }
}
