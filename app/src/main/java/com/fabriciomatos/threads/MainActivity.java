package com.fabriciomatos.threads;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button botaoIniciar;
    private Button botaoParar;
    private int contador;
    //Envia mensagens para uma thread
    private Handler handler = new Handler();
    private boolean pararExecucao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.botaoIniciar = findViewById(R.id.buttonIniciar);
        this.botaoParar = findViewById(R.id.buttonParar);
    }

    public void iniciarThread(View view){

        //MyThread thread = new MyThread();
        //thread.start();
        this.pararExecucao = false;
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();

    }

    public void pararThread(View view){
        this.pararExecucao = true;
        botaoIniciar.setText("Interrompido!");
    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {

            for(int i = 0; i <= 15; i++){
                if(pararExecucao == false){
                    contador = i;
                    Log.d("Thread", "Contador: "+i);

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        botaoIniciar.setText("Contador: "+contador);
//                    }
//                });

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            botaoIniciar.setText("Contador: "+contador);
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    return;
                }
            }
        }
    }

    class MyThread extends Thread{

        @Override
        public void run() {
            super.run();
            for(int i = 0; i <= 15; i++){

                Log.d("Thread", "Contador: "+i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
