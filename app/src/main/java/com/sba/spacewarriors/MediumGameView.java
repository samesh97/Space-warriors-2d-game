package com.sba.spacewarriors;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

public class MediumGameView extends View  {


    private Bitmap player[] = new Bitmap[10];
    private Bitmap opponent[] = new Bitmap[10];
    private Bitmap joicetick[] = new Bitmap[5];
    private Bitmap playerHealth[] = new Bitmap[6];
    private Bitmap opponentHealth[] = new Bitmap[6];

    private int goLeftX,goLeftY,goTopX,goTopY,goRightX,goRightY,goDownX,goDownY,pressShootX,pressShootY;



    private int opponentX = 40;
    private  int opponentY;


    private Bitmap backhroundImage;
    Paint scorePaint = new Paint();

    private int playerX = 40;
    private  int playerY;


    private int opponentShootX,opponentShootY,opponentShootSpeed = 15;
    private Paint opponentShootPaint = new Paint();


    private int playerShootX,playerShootY,playerShootSpeed = 18;
    private Paint playerShootPaint = new Paint();

    boolean firstTime = true;



    private int playerLife = 100,opponentLife = 100;


    private boolean touch = false;


    private int canvasWidth,canvasHeight;

    long startTime;

    boolean isUserWantToShoot = false;
    boolean isShootOver = true;

    boolean isOpponentTop = false,isOpponentBottom = false;

    boolean goPlayerUp,goPlayerDown,goPlayerLeft,goPlayerRight = false;

    int backgroundSwipe = 0;
    int getBackgroundSwipemin = 0;

    public MediumGameView(Context context)
    {



        super(context);


        player[0] = BitmapFactory.decodeResource(getResources(),R.drawable.playershoot);
        opponent[0] = BitmapFactory.decodeResource(getResources(),R.drawable.opponent);

        joicetick[0] = BitmapFactory.decodeResource(getResources(),R.drawable.goleft);
        joicetick[1] = BitmapFactory.decodeResource(getResources(),R.drawable.goup);
        joicetick[2] = BitmapFactory.decodeResource(getResources(),R.drawable.goright);
        joicetick[3] = BitmapFactory.decodeResource(getResources(),R.drawable.godown);
        joicetick[4] = BitmapFactory.decodeResource(getResources(),R.drawable.shoot);



        playerHealth[0] = BitmapFactory.decodeResource(getResources(),R.drawable.playerhealth0);
        playerHealth[1] = BitmapFactory.decodeResource(getResources(),R.drawable.playerhealth1);
        playerHealth[2] = BitmapFactory.decodeResource(getResources(),R.drawable.playerhealth2);
        playerHealth[3] = BitmapFactory.decodeResource(getResources(),R.drawable.playerhealth3);
        playerHealth[4] = BitmapFactory.decodeResource(getResources(),R.drawable.playerhealth4);
        playerHealth[5] = BitmapFactory.decodeResource(getResources(),R.drawable.playerhealth5);


        opponentHealth[0] = BitmapFactory.decodeResource(getResources(),R.drawable.opponenthealth0);
        opponentHealth[1] = BitmapFactory.decodeResource(getResources(),R.drawable.opponenthealth1);
        opponentHealth[2] = BitmapFactory.decodeResource(getResources(),R.drawable.opponenthealth2);
        opponentHealth[3] = BitmapFactory.decodeResource(getResources(),R.drawable.opponenthealth3);
        opponentHealth[4] = BitmapFactory.decodeResource(getResources(),R.drawable.opponenthealth4);
        opponentHealth[5] = BitmapFactory.decodeResource(getResources(),R.drawable.opponenthealth5);





        opponentShootPaint.setColor(Color.BLACK);
        opponentShootPaint.setAntiAlias(false);

        playerShootPaint.setColor(Color.RED);
        playerShootPaint.setAntiAlias(false);




        backhroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(40);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);








        startTime  = System.currentTimeMillis();



    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        if(firstTime)
        {
            playerY = canvasHeight - (canvasHeight  - 200);
            opponentY = canvasHeight - (canvasHeight - 200);
            opponentX = canvasWidth - canvasWidth / 8;
            playerShootY = canvasHeight - (canvasHeight  - 200);
            playerShootX = 40;
        }

        firstTime = false;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            //canvas.drawRoundRect(getBackgroundSwipemin, 0, getBackgroundSwipemin + canvasWidth, canvasHeight, );
            canvas.drawBitmap(backhroundImage,0,0,null);

        }
        else
        {
            RectF dst = new RectF(getBackgroundSwipemin, 0, getBackgroundSwipemin + canvasWidth,  canvasHeight);
            canvas.drawBitmap(backhroundImage, null, dst, null);


            RectF dst2 = new RectF(canvasWidth - backgroundSwipe, 0, getBackgroundSwipemin + canvasWidth * 2,  canvasHeight);
            canvas.drawBitmap(backhroundImage, null, dst2, null);
        }




        backgroundSwipe = backgroundSwipe + 2;
        getBackgroundSwipemin = getBackgroundSwipemin - 2;

        if(backgroundSwipe == canvasWidth || backgroundSwipe == canvasWidth + 1)
        {
            backgroundSwipe = 0;
            getBackgroundSwipemin = 0;
        }



        int minPlayerX = 0;
        int maxPlayerX = canvasWidth - player[0].getWidth() * 4;

        int minPlayerY = player[0].getHeight() - player[0].getWidth() * 1;
        int maxPlayerY = canvasHeight - player[0].getHeight() * 1 - 30;



        int minOpponentX = opponent[0].getWidth();
        int maxOpponentX = canvasWidth;

        int minOpponentY = opponent[0].getHeight() - opponent[0].getWidth() * 1;
        int maxOpponentY = canvasHeight - opponent[0].getHeight() * 1 - 30;


        if(playerY < minPlayerY)
        {
            playerY = minPlayerY;
        }
        if(playerY > maxPlayerY)
        {
            playerY = maxPlayerY;
        }

        if(playerX < minPlayerX)
        {
            playerX =minPlayerX;
        }
        if(playerX >maxPlayerX)
        {
            playerX = maxPlayerX;
        }



        opponentY = opponentY - 5;

        if(opponentY < minOpponentY)
        {
            isOpponentBottom = true;
            isOpponentTop = false;
            opponentY = minOpponentY;
        }
        if(opponentY > maxOpponentY)
        {
            isOpponentTop = true;
            isOpponentBottom = false;
            opponentY = maxOpponentY;
        }

        if(opponentX < minOpponentX)
        {
            opponentX = minOpponentX;
        }
        if(opponentX >maxOpponentX)
        {
            opponentX = maxOpponentX;
        }
        if(isOpponentTop)
        {
            opponentY = opponentY - 1;
        }
        if(isOpponentBottom)
        {
            opponentY = opponentY + 10;
        }

        if(goPlayerUp)
        {
            playerY = playerY - 10;

        }
        if(goPlayerDown)
        {
            playerY = playerY + 10;
        }
        if(goPlayerLeft)
        {
            playerX = playerX - 10;
        }
        if(goPlayerRight)
        {
            playerX = playerX + 10;
        }


        if(touch)
        {
            touch = false;
        }
        else
        {
            canvas.drawBitmap(player[0],playerX,playerY,null);
            canvas.drawBitmap(opponent[0],opponentX,opponentY,null);
        }

        canvas.drawBitmap(joicetick[0],canvasWidth - ((canvasWidth / 2) + 130 ),canvasHeight - (canvasHeight / 3),null);
        goLeftX = canvasWidth - ((canvasWidth / 2) + 150 );
        goLeftY = canvasHeight - (canvasHeight / 3);

        canvas.drawBitmap(joicetick[1],canvasWidth - ((canvasWidth / 2) + 30 ),canvasHeight - ((canvasHeight / 3) + 80),null);
        goTopX = canvasWidth - ((canvasWidth / 2) + 30 );
        goTopY = canvasHeight - ((canvasHeight / 3) + 90);

        canvas.drawBitmap(joicetick[2],canvasWidth - ((canvasWidth / 2) - 70 ),canvasHeight - (canvasHeight / 3),null);
        goRightX = canvasWidth - ((canvasWidth / 2) - 80 );
        goRightY = canvasHeight - (canvasHeight / 3);

        canvas.drawBitmap(joicetick[3],canvasWidth - ((canvasWidth / 2) + 30  ),canvasHeight - ((canvasHeight / 3) - 80),null);
        goDownX = canvasWidth - ((canvasWidth / 2) + 30);
        goDownY = canvasHeight - ((canvasHeight / 3) - 90);

        canvas.drawBitmap(joicetick[4],canvasWidth - ((canvasWidth / 4)),canvasHeight - ((canvasHeight / 3)),null);

        pressShootX = canvasWidth - ((canvasWidth / 4));
        pressShootY = canvasHeight - ((canvasHeight / 3));






        opponentShootX = opponentShootX - opponentShootSpeed;

        if(isPlayerHitTheBullet(opponentShootX,opponentShootY))
        {
            playerLife = playerLife - 5;
            opponentShootX = - 100;
        }

        if(opponentShootX < 0)
        {
            opponentShootX = opponentX - 2;
            opponentShootY = opponentY + opponent[0].getHeight() / 4;
                    //(int) Math.floor(Math.random() * (maxPlayerX - minPlayerX)) + minPlayerX;
        }
        canvas.drawCircle(opponentShootX,opponentShootY,15,opponentShootPaint);









        if(isUserWantToShoot)
        {
            playerShootX = playerShootX + playerShootSpeed;
            isShootOver = false;
            if(isOpponentHitTheBullet(playerShootX,playerShootY))
            {
                //Toast.makeText(getContext(), "Opponent passed", Toast.LENGTH_SHORT).show();
                opponentLife = opponentLife - 5;


                playerShootX = canvasWidth;
                isShootOver = true;
            }
            if(playerShootX >= canvasWidth)
            {
                playerShootX = playerX + 100;
                playerShootY = playerY + player[0].getHeight() / 2;

                isShootOver = true;
                isUserWantToShoot = false;
                //(int) Math.floor(Math.random() * (maxPlayerX - minPlayerX)) + minPlayerX;
            }
            if(isUserWantToShoot)
            {
                canvas.drawCircle(playerShootX,playerShootY,15,playerShootPaint);
            }



        }





        if(isOpponentShootAndPlayerShootGetHit())
        {
            //Toast.makeText(getContext(), "Get Hit Both", Toast.LENGTH_SHORT).show();
        }

        if(opponentLife == 0)
        {
            Intent intent = new Intent(getContext(),PlayerWonActivity.class);
            intent.putExtra("GameMode","Medium");
            getContext().startActivity(intent);

        }
        if(playerLife == 0)
        {
            Intent intent = new Intent(getContext(),OpponentWonTheGame.class);
            intent.putExtra("GameMode","Medium");
            getContext().startActivity(intent);
        }


















       // bombX = bombX - bombSpeed;
/*
        if(hitBallChecker(bombX,bombY))
        {
            lifeCouterOfFish--;
            canvas.drawBitmap(bomb[1],bombX,bombY,null);
            bombX = - 100;
        }*/

        /*
        if(hitBallChecker(bombX + (bomb[0].getWidth()/2 ),bombY + (bomb[0].getHeight()/2)))
        {
            lifeCouterOfFish--;
            canvas.drawBitmap(bomb[1],bombX,bombY,null);
            bombX = - 100;
        }*/
/*
        if(bombX < 0)
        {
            bombX = canvasWidth + 21;
            bombY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawBitmap(bomb[0],bombX,bombY,null);*/










        if(playerLife <= 100 && playerLife > 80 )
        {
            canvas.drawBitmap(playerHealth[5],canvasWidth - (canvasWidth / 1) + playerHealth[5].getWidth() / 4,-50,null);
        }
        if(playerLife <= 80 && playerLife > 60)
        {
            canvas.drawBitmap(playerHealth[4],canvasWidth - (canvasWidth / 1) + playerHealth[5].getWidth() / 4,-50,null);
        }
        if(playerLife <= 60 && playerLife > 40)
        {
            canvas.drawBitmap(playerHealth[3],canvasWidth - (canvasWidth / 1) + playerHealth[5].getWidth() / 4,-50,null);
        }
        if(playerLife <= 40 && playerLife > 20)
        {
            canvas.drawBitmap(playerHealth[2],canvasWidth - (canvasWidth / 1) + playerHealth[5].getWidth() / 4,-50,null);
        }
        if(playerLife <= 20 && playerLife > 0)
        {
            canvas.drawBitmap(playerHealth[1],canvasWidth - (canvasWidth / 1) + playerHealth[5].getWidth() / 4,-50,null);
        }
        if(playerLife == 0)
        {
            canvas.drawBitmap(playerHealth[0],canvasWidth - (canvasWidth / 1) + playerHealth[5].getWidth() / 4,-50,null);
        }
        //canvas.drawText("Health : " + playerLife,30,70, scorePaint);
        //canvas.drawText("Health : " + opponentLife,canvasWidth -250,70, scorePaint);








        if(opponentLife <= 100 && opponentLife > 80 )
        {
            canvas.drawBitmap(opponentHealth[5],canvasWidth - (canvasWidth / 4) - (opponentHealth[5].getWidth() / 4) - 40 ,-50,null);
        }
        if(opponentLife <= 80 && opponentLife > 60 )
        {
            canvas.drawBitmap(opponentHealth[4],canvasWidth - (canvasWidth / 4) - (opponentHealth[5].getWidth() / 4) - 40,-50,null);
        }
        if(opponentLife <= 60 && opponentLife > 40 )
        {
            canvas.drawBitmap(opponentHealth[3],canvasWidth - (canvasWidth / 4) - (opponentHealth[5].getWidth() / 4) - 40,-50,null);
        }
        if(opponentLife <= 40 && opponentLife > 20 )
        {
            canvas.drawBitmap(opponentHealth[2],canvasWidth - (canvasWidth / 4) - (opponentHealth[5].getWidth() / 4) - 40,-50,null);
        }
        if(opponentLife <= 20 && opponentLife > 0 )
        {
            canvas.drawBitmap(opponentHealth[1],canvasWidth - (canvasWidth / 4) - (opponentHealth[5].getWidth() / 4) - 40,-50,null);
        }
        if(opponentLife == 0)
        {
            canvas.drawBitmap(opponentHealth[0],canvasWidth - (canvasWidth / 4) - (opponentHealth[5].getWidth() / 4) - 40,-50,null);
        }










            //Intent intent = new Intent(getContext(),GameOver.class);



           // intent.putExtra("Marks",String.valueOf(score));
            ///intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //getContext().startActivity(intent);
        }


        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;


        /*

        if(elapsedSeconds == 10)
        {
            bombSpeed++;
            greenSpeed++;
            yellowSpeed++;

            startTime = System.currentTimeMillis();
        }*/





    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                //Check if the x and y position of the touch is inside the bitmap
                if( x > goLeftX && x < goLeftX + joicetick[0].getWidth() && y > goLeftY && y < goLeftY + joicetick[0].getHeight() )
                {
                    //playerX = playerX - 20;
                    goPlayerLeft = true;
                    goPlayerRight = false;

                }
                if( x > goTopX && x < goTopX + joicetick[1].getWidth() && y > goTopY && y < goTopY + joicetick[1].getHeight() )
                {
                    //playerY = playerY - 40;
                    goPlayerUp = true;
                    goPlayerDown = false;

                }
                if( x > goRightX && x < goRightX + joicetick[2].getWidth() && y > goRightY && y < goRightY + joicetick[2].getHeight() )
                {

                    //playerX = playerX + 20;
                    goPlayerRight = true;
                    goPlayerLeft = false;
                }
                if( x > goDownX && x < goDownX + joicetick[3].getWidth() && y > goDownY && y < goDownY + joicetick[3].getHeight() )
                {
                    //playerY = playerY + 40;
                    goPlayerDown = true;
                    goPlayerUp = false;

                }
                if( x > pressShootX && x < pressShootX + joicetick[4].getWidth() && y > pressShootY && y < pressShootY + joicetick[4].getHeight() )
                {
                    if(isShootOver)
                    {
                        isUserWantToShoot = true;
                        playerShootX = playerX + 100;
                        playerShootY = playerY +player[0].getHeight() / 3;
                    }




                }
                return true;
        }
        return true;

        /*if(event.getAction() == MotionEvent.ACTION_UP)
        {
            touch = true;
            //playerX = playerX + 10;
            //fishSpeed = -22;


        }




        return true;*/
    }
    public  boolean isPlayerHitTheBullet(int opponentShootX, int opponentShootY)
    {
        if(playerX < opponentShootX && opponentShootX < (playerX + player[0].getWidth()) && playerY < opponentShootY && opponentShootY < (playerY +  player[0].getHeight()))
        {
            return true;
        }
        return false;
    }
    public  boolean isOpponentHitTheBullet(int playerShootX, int playerShootY)
    {
        if(opponentX < playerShootX && playerShootX < (opponentX + opponent[0].getWidth()) && opponentY < playerShootY && playerShootY < (opponentY +  opponent[0].getHeight()))
        {
            MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.playersuccess);
            mp.start();
            return true;
        }
        return false;
    }
    public boolean isOpponentShootAndPlayerShootGetHit()
    {
        if(playerShootX == opponentShootX && playerShootY == opponentShootY)
        {
            MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.opponentsuccess);
            mp.start();
            return true;
        }
        else
        {
            return false;
        }
    }


}
