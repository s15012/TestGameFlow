package jp.ac.it_college.std.s15008.testgameflow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import jp.ac.it_college.std.s15008.testgameflow.Mode.GameMode;
import jp.ac.it_college.std.s15008.testgameflow.Mode.IntroMode;

/**
 * Created by s15008 on 17/01/30.
 */

public class GameView extends View {
    private static final String TAG = "GameView";

    // ゲームモード
    public enum Mode {
        INTRO,
        GAME,
        OVER,
        CLEAR,
    }
    // 現在のゲームモード
    private Mode mMode;

    // モードのオブジェクト
    private IntroMode mIntroMode;
    private GameMode mGameMode;

    // タッチ座標
    private float mTouchX;
    private float mTouchY;
    private boolean mIsTouch;
    private MotionEvent mMotionEvent;

    // デバッグ用
    Paint paintText = new Paint();


    public GameView(Context context) {
        super(context);

        // ゲームモードオブジェクト
        mMode = Mode.INTRO;
        mGameMode = new GameMode();
        mIntroMode = new IntroMode();

        // タッチイベント関係
        mTouchX = 0f;
        mTouchY = 0f;
        mIsTouch = false;
        mMotionEvent = null;

        // 背景色変更
        setBackgroundColor(Color.WHITE);

        // デバッグ用
        paintText = new Paint();
        paintText.setColor(Color.RED);
        paintText.setTextSize(50);
        paintText.setTextAlign(Paint.Align.CENTER);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 仮のテキスト描画
        canvas.drawText("GAME VIEW", canvas.getWidth()/2, paintText.getTextSize(), paintText);

        // GameModeのupdate()とdraw()は毎フレーム処理
        // モードによってゲームの処理を変える
        if (mMode == Mode.INTRO) {
            //Log.d(TAG, "イントロモード");
            mIntroMode.update(canvas, mMotionEvent);
            mIntroMode.draw(canvas);

            // モード遷移
            if (mIntroMode.mNextMode != mIntroMode.mCurrentMode) {
                mMode = mIntroMode.mNextMode;
                invalidate();
            }
        } else if (mMode == Mode.GAME) {
            Log.d(TAG, "ゲームモード");
        } else if (mMode == Mode.OVER) {
            Log.d(TAG, "ゲームオーバーモード");
        } else if (mMode == Mode.CLEAR) {
            Log.d(TAG, "ゲームクリアーモード");
        }

        mMotionEvent = null;    // モーションイベントをクリアする
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mMotionEvent = event;

        return true;
    }
}
