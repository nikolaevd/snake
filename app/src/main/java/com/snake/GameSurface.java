package com.snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView {

    Game game;
    Bitmap head, till, body, bg, fruit;
    String someText = "123";
    float x, y;

    public void setXY(float x, float y){
        this.x = x;
        this.y = y;
    }

    public GameSurface(Context context){
        super(context);

        game = new Game();
        head = BitmapFactory.decodeResource(context.getResources(), R.drawable.head);
        till = BitmapFactory.decodeResource(context.getResources(), R.drawable.till);
        body = BitmapFactory.decodeResource(context.getResources(), R.drawable.body);
        bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg);
        fruit = BitmapFactory.decodeResource(context.getResources(), R.drawable.fruit);
    }

    public void setSomeText(String someText) {
        this.someText = someText;
    }

    public void drawSnake(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int mx = width / Game.sizeByX;
        int my = height / Game.sizeByY;

        Bitmap bitmapHead = Bitmap.createScaledBitmap(head, mx, my, true);
        Bitmap bitmapBody = Bitmap.createScaledBitmap(body, mx, my, true);
        Bitmap bitmapTill = Bitmap.createScaledBitmap(till, mx, my, true);
        Bitmap bitmapBg = Bitmap.createScaledBitmap(bg, mx, my, true);

        Paint paint = new Paint();
        paint.setColor(Color.CYAN);

        canvas.drawCircle(width / 2, height / 2, width / 4, paint);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(width / 2 - x * 5, height / 2 + y * 5, width / 10, paint);
        paint.setColor(Color.BLACK);
        paint.setAlpha(128);

        Bitmap bitmapFruit = Bitmap.createScaledBitmap(fruit, mx, my, true);

        for (int i = 0; i < Game.sizeByX; i++) {
            for (int j = 0; j < Game.sizeByY; j++) {
                canvas.drawBitmap(bg, mx * i, my * j, paint);
                if (game.getField()[i][j] > 1) {
                    canvas.drawBitmap(fruit, mx * i, my * j, paint);
                }
            }
        }
        paint.setAlpha(0);

        for (int i = 0; i < game.getSnakeLength(); i++) {
            canvas.drawBitmap(body, game.getSnake().get(i).getPosByX() * mx, game.getSnake().get(i).getPosByX() * my, new Paint());
            if (i == 0) {
                canvas.drawBitmap(till, game.getSnake().get(i).getPosByX() * mx, game.getSnake().get(i).getPosByY() * my, new Paint());
            }
            if (i == game.getSnakeLength() - 1) {
                canvas.drawBitmap(head, game.getSnake().get(i).getPosByX() * mx, game.getSnake().get(i).getPosByY() * my, new Paint());
            }
        }
        paint.setColor(Color.WHITE);
        paint.setAlpha(255);
        paint.setTextSize(15);
        canvas.drawText(someText, 50, 50, paint);
    }

}
