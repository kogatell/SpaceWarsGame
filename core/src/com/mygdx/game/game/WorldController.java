package com.mygdx.game.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.Random;

public class WorldController {

    ArrayList<Bullet> bullets;
    Player player;
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    ScrollingBackground background;
    public int health;
    boolean dropPowerUp;
    BitmapFont font =new BitmapFont();
    public boolean doubleShoot;
    boolean canShoot = true;
    ArrayList<GameObject> goToDestroy = new ArrayList<GameObject>();
    float asteroidSpawnTimer;
    float shootingEnemySpawnTimer;
    float chaseEnemySpawnTimeTimer;
    float x, y;

    float score;
    public static final float MIN_ASTEROID_SPAWN_TIME = 1f;
    public static final float MAX_ASTEROID_SPAWN_TIME = 0.5f;
    public static final float MAX_SHOOTING_ENEMY_SHOOT_TIME = 5f;
    public static final float MIN_SHOOTING_ENEMY_SHOOT_TIME = 0.3f;
    public static final float MAX_SHOOTING_ENEMY_SPAWN_TIME = 5.0f;
    public static final float MIN_SHOOTING_ENEMY_SPAWN_TIME = 1.0f;
    public static final float MIN_CHASE_ENEMY_SPAWN_TIME = 1f;
    public static final float  MAX_CHASE_ENEMY_SPAWN_TIME = 3f;
    public boolean gameOver;
    public boolean musicOn;
    boolean hardcore;
    Random random;
    private Music music;

    public WorldController(){
        musicOn = true;
        music = Gdx.audio.newMusic((Gdx.files.internal("newnew.mp3"))); //prod by me
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        bullets = new ArrayList<Bullet>();
        gameOver = false;
        boolean doubleShoot = false;
        health = 3;
        //background = new ScrollingBackground();
        //gameObjects = new ArrayList<GameObject>();
        init();
        random = new Random();
        asteroidSpawnTimer = random.nextFloat() * (MAX_ASTEROID_SPAWN_TIME - MIN_ASTEROID_SPAWN_TIME) + MIN_ASTEROID_SPAWN_TIME;
        shootingEnemySpawnTimer = random.nextFloat() * (MAX_ASTEROID_SPAWN_TIME - MIN_ASTEROID_SPAWN_TIME) + MIN_ASTEROID_SPAWN_TIME;
    }
    public void  init(){
        player = new Player();
        gameObjects.add(player);
    }
    public void update(float deltaTime){
        if(hardcore)
        {
            health = 1;
            hardcore = false;
        }
        Controllers.addListener(new ControllerListener() {
            @Override
            public void connected(Controller controller) {

            }

            @Override
            public void disconnected(Controller controller) {

            }

            @Override
            public boolean buttonDown(Controller controller, int buttonCode) {
                if(buttonCode == 5)
                {
                    isShooting();
                }
                return false;
            }

            @Override
            public boolean buttonUp(Controller controller, int buttonCode) {
                return false;
            }

            @Override
            public boolean axisMoved(Controller controller, int axisCode, float value) {
                return false;
            }

            @Override
            public boolean povMoved(Controller controller, int povCode, PovDirection value) {
                return false;
            }

            @Override
            public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
                return false;
            }

            @Override
            public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
                return false;
            }

            @Override
            public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
                return false;
            }
        });
        Vector2 playercopy = player.position.cpy();
        if(!musicOn)
        {
            music.pause();
        }
        //s.rotate(45.0f * deltaTime);
        if (isShooting() && canShoot && !doubleShoot)
        {
            gameObjects.add(new Bullet(player.position.x + player.width / 2 -Bullet.WIDTH/2, player.position.y + player.HEIGHT - Bullet.HEIGHT));
            //bullets.add(new Bullet(player.position.x));
        }
        if(isShielding())
        {
            //Gdx.app.log("Escudo", "Creo escudo" +);
            gameObjects.add(new Shield(player.position.x, player.position.y));
            ///Shield _shield = gameObjects.get(Shi);
            for(GameObject go: gameObjects)
            {
                if(go.typeOfGO == 6)
                {
                    goToDestroy.add(go);
                }
            }
        }
        else {
            for(GameObject go: gameObjects)
            {
                if(go.typeOfGO == 6)
                {
                    goToDestroy.add(go);
                }
            }
        }
        if (isShooting() && canShoot && doubleShoot) {
            doubleShoot = false;
            gameObjects.add(new Bullet(player.position.x + player.WIDTH, player.position.y - Bullet.HEIGHT));
            gameObjects.add(new Bullet(player.position.x, player.position.y - Bullet.HEIGHT));
            gameObjects.add(new Bullet(player.position.x, player.position.y - Bullet.HEIGHT));
        }

        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        for (Bullet bullet : bullets) {
            bullet.update(deltaTime);
            if (bullet.remove)
                bulletsToRemove.add(bullet);
        }
        asteroidSpawnTimer -= deltaTime;
        shootingEnemySpawnTimer -= deltaTime;
        chaseEnemySpawnTimeTimer -= deltaTime;
        if (asteroidSpawnTimer <= 0) {
            Gdx.app.error("Hola","Creo asteroide");
            asteroidSpawnTimer = random.nextFloat() * (MAX_ASTEROID_SPAWN_TIME - MIN_ASTEROID_SPAWN_TIME) + MIN_ASTEROID_SPAWN_TIME;
            gameObjects.add(new SimpleEnemy(random.nextInt( (int) ((2*Constants.VIEWPORT_WIDTH) - SimpleEnemy.WIDTH)) - (Constants.VIEWPORT_WIDTH - SimpleEnemy.WIDTH)));
        }
        if (shootingEnemySpawnTimer <= 0) {
            Gdx.app.error("Hola","Creo ShootingEnemy");
            shootingEnemySpawnTimer = random.nextFloat() * (MAX_SHOOTING_ENEMY_SPAWN_TIME - MIN_SHOOTING_ENEMY_SPAWN_TIME) + MIN_SHOOTING_ENEMY_SPAWN_TIME;
            gameObjects.add(new ShootingEnemy(random.nextInt( (int) ((2*Constants.VIEWPORT_WIDTH) - ShootingEnemy.WIDTH)) - (Constants.VIEWPORT_WIDTH - ShootingEnemy.WIDTH)));
        }
        if(chaseEnemySpawnTimeTimer <= 0)
        {
            Gdx.app.error("Hola","Creo Chase Enemy");
            chaseEnemySpawnTimeTimer = random.nextFloat() * (MAX_CHASE_ENEMY_SPAWN_TIME - MIN_CHASE_ENEMY_SPAWN_TIME) + MIN_CHASE_ENEMY_SPAWN_TIME;
            gameObjects.add(new ChaseEnemy(random.nextInt( (int) ((2*Constants.VIEWPORT_WIDTH) - ChaseEnemy.WIDTH)) - (Constants.VIEWPORT_WIDTH - ChaseEnemy.WIDTH), playercopy));
        }
        for(GameObject go: gameObjects)
        {
            go.update(deltaTime);
        }

        ArrayList<GameObject> gameObjectsCopy = (ArrayList<GameObject>) gameObjects.clone();
        for (GameObject go: gameObjectsCopy){
            if(go.typeOfGO == 7)
            {

                go.lookAt(playercopy);

            }
            if(go.typeOfGO == 4) {
                go.shootingTimer -= deltaTime;
                if (go.shootingTimer <= 0) {
                    go.shootingTimer = random.nextFloat() * (MAX_SHOOTING_ENEMY_SHOOT_TIME - MIN_SHOOTING_ENEMY_SHOOT_TIME) + MIN_SHOOTING_ENEMY_SHOOT_TIME;
                    gameObjects.add(new BulletEnemy(go.position.x, go.position.y - go.height));
                }


            }
        }
        for(GameObject go: gameObjectsCopy)
        {
            if (go.getCollisionRect().collidesWith(player.getCollisionRect()) && go.typeOfGO == 2)
            {
                goToDestroy.add(go);
                doubleShoot = true;
                Gdx.app.error("PowerUp", "Double Shoot is ready!");
            }
           for (GameObject go2: gameObjectsCopy)
           {

               gameObjects.clone();
               if(go.position.y < -10)
               {
                   goToDestroy.add(go);
               }
               if(go.getCollisionRect().collidesWith(player.getCollisionRect()) && go.typeOfGO == 1 || go.getCollisionRect().collidesWith(player.getCollisionRect()) && go.typeOfGO == 4 || go.getCollisionRect().collidesWith(player.getCollisionRect()) && go.typeOfGO == 5)
               {
                    go.getCollisionRect().move(-1000, -1000);
                    goToDestroy.add(go);
                    //gameObjects.removeAll(goToDestroy);
                    health--;
               }
               if(go.getCollisionRect().collidesWith(player.getCollisionRect()) && go.typeOfGO == 1 || go.getCollisionRect().collidesWith(player.getCollisionRect()) && go.typeOfGO == 7 || go.getCollisionRect().collidesWith(player.getCollisionRect()) && go.typeOfGO == 7)
               {
                   go.getCollisionRect().move(-1000, -1000);
                   goToDestroy.add(go);
                   //gameObjects.removeAll(goToDestroy);
                   health--;
               }
               if (go.getCollisionRect().collidesWith(go2.getCollisionRect()) && go.typeOfGO == 0 && go2.typeOfGO == 1 || go.getCollisionRect().collidesWith(go2.getCollisionRect()) && go.typeOfGO == 0 && go2.typeOfGO == 4 || go.getCollisionRect().collidesWith(go2.getCollisionRect()) && go.typeOfGO == 0 && go2.typeOfGO == 7){
                    x = go.position.x;
                    y = go.position.y;
                    goToDestroy.add(go);
                    goToDestroy.add(go2);
                    score += 100;
                    Gdx.app.error("Destruyo","Go destruido");
                    dropPowerUp = random.nextBoolean();

                    if(dropPowerUp && !doubleShoot)
                    {
                        gameObjects.add(new DoubleShootPowerUp(x, y));
                    }
               }
               if(health <= 0)
               {
                   //gameObjects.add(new Explosion(player.position.x, player.position.y));
                   player.isDead = true;
                   goToDestroy.add(player);
                   //player.destroyCol(player.getCollisionRect());
                   //player.destroyCol(player.col);
                   canShoot = false;
                   for (GameObject go3 : gameObjectsCopy)
                   {
                       goToDestroy.add(go3);
                   }

               }

               if(go.position.y == - Constants.VIEWPORT_HEIGHT || go.position.y <= -5)
               {
                   goToDestroy.add(go);
                   Gdx.app.error("Destruyo", "GameObject destruido altura");
               }
           }
        }
        gameObjects.removeAll(goToDestroy);
    }
    private boolean isShooting(){
        if(Gdx.app.getType() == Application.ApplicationType.Desktop) return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        if(Gdx.app.getType() == Application.ApplicationType.Desktop) return Gdx.input.justTouched();

        return false;
    }
    private boolean isShielding(){
        return Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT);
    }
}
