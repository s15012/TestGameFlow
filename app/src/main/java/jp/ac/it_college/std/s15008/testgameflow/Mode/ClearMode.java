package jp.ac.it_college.std.s15008.testgameflow.Mode;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.util.Log;

import java.nio.FloatBuffer;

import jp.ac.it_college.std.s15008.testgameflow.GameView;

/**
 * Created by s15012 on 17/01/31.
 */

public class ClearMode {
    private static String TAG = "ClearMode";

    public GameView.Mode mCurrentMode;
    public GameView.Mode mNextMode;

    Paint paintLiner;
    Paint paintScore;
    private int textSize = 75;

    private int topLineX;
    private int topLineY;
    private int bottomLineX;
    private int bottomLineY;

    public ClearMode() {
        mCurrentMode = GameView.Mode.CLEAR;
        mNextMode = mCurrentMode;
    }

    public void draw(Canvas canvas) {
        setDrawScore(canvas);
        setDrawLiner(canvas);
    }

    private Paint setDrawScore(Canvas score) {
        paintScore = new Paint();
        paintScore.setColor(Color.BLUE);
        paintScore.setAntiAlias(true);   //文字をなめらかにする処理
        paintScore.setTextSize(textSize);
        paintScore.setTextAlign(Paint.Align.CENTER);
        paintScore.setTextSkewX((float) -0.5f); // 斜め文字

        int height = 300;
        int width = score.getWidth() / 2;
        String Score = "Score:  ";
        for (int i = 0; i < 5; i++) {
            score.drawText(Score + i, width, height, paintScore);
            height = height + textSize;
        }

        return paintScore;
    }

    private Paint setDrawLiner(Canvas liner) {
        paintLiner = new Paint();
        paintLiner.setColor(Color.BLACK);
        paintLiner.setAntiAlias(true);   //文字をなめらかにする処理

        topLineX = liner.getWidth();
        topLineY = 200;
        bottomLineX = 0;
        bottomLineY = 800;


        while (topLineX > 0 || bottomLineX < liner.getWidth()) {
            liner.drawLine(topLineX, topLineY, topLineX - 1, topLineY, paintLiner);
            topLineX--;
            Log.d("lineXXXXXXXXX", String.valueOf(topLineX));
            liner.drawLine(bottomLineX, bottomLineY, bottomLineX + 1, bottomLineY, paintLiner);
            bottomLineX++;
            Log.d("bottomlineXXXXXXXXXXXXX", String.valueOf(bottomLineX));
        }
//        paintScore.setTextSkewX((float) -0.5f);

        return paintLiner;
    }

    public void update() {
        if (mCurrentMode == GameView.Mode.CLEAR) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mNextMode = GameView.Mode.INTRO;
                }
            }, 3000);
        }
    }
}
