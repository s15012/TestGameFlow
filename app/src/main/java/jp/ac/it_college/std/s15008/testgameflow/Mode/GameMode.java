package jp.ac.it_college.std.s15008.testgameflow.Mode;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import jp.ac.it_college.std.s15008.testgameflow.GameView;

/**
 * Created by s15008 on 17/01/30.
 */

public class GameMode {
    private static final String TAG = "GameMode";

    public GameView.Mode mCurrentMode;
    public GameView.Mode mNextMode;

    private float mTouchX;
    private float mTouchY;

    Paint paintText;

    class MyButton {
        private final Paint mRectPaint;
        private Rect mRect;
        private int mWidth;
        private int mHeight;

        public MyButton(int x, int y) {
            mRectPaint = new Paint(Color.BLUE);
            mWidth = 300;
            mHeight = 250;
            this.mRect = new Rect(x, y, x+mWidth, y+mHeight);
        }

        public void draw(Canvas canvas) {
            canvas.drawRect(mRect, mRectPaint);
        }
    }
    GameMode.MyButton mMyButton;


    public GameMode() {
        mCurrentMode = GameView.Mode.GAME;
        mNextMode = mCurrentMode;
        paintText = new Paint();
        paintText.setColor(Color.RED);
        paintText.setTextSize(25);
        paintText.setTextAlign(Paint.Align.CENTER);

    }

    // 更新処理
    public void update(Canvas canvas, MotionEvent motionEvent) {

        if (mMyButton == null) {
            int x = (canvas.getWidth() / 2) - (canvas.getWidth() / 2);
            int y = (canvas.getHeight() / 2) - (canvas.getHeight() / 2);
            mMyButton = new GameMode.MyButton(x, y - (canvas.getHeight() / 4));
        }

        // タッチ処理
        if (motionEvent != null) {
            float touchX = motionEvent.getX();
            float touchY = motionEvent.getY();
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                mTouchX = touchX;
                mTouchY = touchY;
                if (mMyButton.mRect.contains((int) mTouchX, (int) mTouchY)) {
                    mNextMode = GameView.Mode.INTRO;
                }
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                mTouchX = touchX;
                mTouchY = touchY;
            } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                mTouchX = touchX;
                mTouchY = touchY;
            }
        }
//        mNextMode = GameView.Mode.INTRO;
    }

    // 描画処理
    public void draw(Canvas canvas) {
        //Log.d(TAG, "IntroMode.draw()実行中");
        canvas.drawText("Intro Mode", canvas.getWidth()/2, canvas.getHeight()/2, paintText);
        canvas.drawText(
                String.format("TouchX : %f\nTouchY : %f", mTouchX, mTouchY),
                canvas.getWidth()/2, canvas.getHeight()/2 + 100, paintText);

        mMyButton.draw(canvas);
    }

}
