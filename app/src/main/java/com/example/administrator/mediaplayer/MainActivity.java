package com.example.administrator.mediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

	private MediaPlayer player;
	int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurfaceView sv = (SurfaceView) findViewById(R.id.sv);
        //拿到surfaceview的控制器
        final SurfaceHolder sh = sv.getHolder();

		/*Thread t = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					sleep(200);
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				runOnUiThread(new Runnable() {
				@Override
				public void run() {
						MediaPlayer player = new MediaPlayer();
						player.reset();
						try {
							player.setDataSource("/storage/sdcard0/DCIM/Camera/11.mp4");
							player.setDisplay(sh);
							player.prepare();
							player.start();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});

			}
		};
		t.start();*/

		sh.addCallback(new SurfaceHolder.Callback() {
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				//每次surfaceView创建时才去播放视频
				if (player==null) {
					player = new MediaPlayer();
					player.reset();
					try {
						player.setDataSource("/storage/sdcard0/DCIM/Camera/11.mp4");
						player.setDisplay(sh);
						player.prepare();
						player.start();
						player.seekTo(currentPosition);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				if (player!=null) {
					currentPosition=player.getCurrentPosition();
					player.stop();
					player.release();
					player=null;
				}
			}
		});
    }
}
