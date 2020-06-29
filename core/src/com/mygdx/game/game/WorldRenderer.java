package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

public class WorldRenderer extends WorldController {
    ScrollingBackground background;
    public static Camera camera;
    public static Camera camera2;
    public static SpriteBatch batch;
    ArrayList<Health> healthIcons;
    WorldController controller;
    BackgroundImage backgroundImg;
    TextureAtlas particleAtlas;
    ParticleEffect _particleEffect = new ParticleEffect();
    Controller cont;
    public Group controlGroup;
    Player player;
    InputScreenPleaseWork inputScreenPleaseWork;
    Explosion exp;
    SpecialShot specialShot;
    YouLoose youLoose;

    public WorldRenderer(WorldController wc)
    {
        healthIcons = new ArrayList<Health>();
        youLoose = new YouLoose(1000, 100);
        exp = new Explosion(500,5000);
        //exp.render(batch);
        background = new ScrollingBackground();
        backgroundImg = new BackgroundImage(0);
        this.controller = wc;
        controlGroup = new Group();
        camera = new Camera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, wc.player);
        //camera2 = new Camera(Constants.VIEWPORT_WIDTH/5, Constants.VIEWPORT_HEIGHT/5);
        player = wc.player;
        camera.LookAt(player);
        _particleEffect.load(Gdx.files.internal("particlescool.party"), Gdx.files.internal(""));
        _particleEffect.setPosition(5, 5);
        _particleEffect.flipY();
        _particleEffect.start();
        specialShot = new SpecialShot(-SpecialShot.WIDTH, 0);
        for(int i = 0; i< wc.health; i++)
        {
            healthIcons.add(new Health(0 + Health.WIDTH + Health.WIDTH * i, 0));
        }
        resize(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        //Gdx.graphics.setWindowedMode();
        batch = new SpriteBatch();
        //cont = new Controller(camera, this);
        background.setSpeedFixed(true);
    }

    public void init()
    {

    }
    /*
     * gdx texture packer
     *   atlas methods
     *
     *
     *
     * */
    public void render()
    {
        _particleEffect.setDuration(10000);
        long t1 = System.nanoTime();
        batch.setProjectionMatrix(camera.combined());
        //batch.setProjectionMatrix(camera2.combined);
        batch.begin();
        backgroundImg.yCoordBg1 += backgroundImg.BACKGROUND_MOVE_SPEED * Gdx.graphics.getDeltaTime();
        backgroundImg.yCoordBg2 = backgroundImg.yCoordBg1 + backgroundImg.yMax;

        if (backgroundImg.yCoordBg1 >= 0) {
            backgroundImg.yCoordBg1 = backgroundImg.yMax*(-1); backgroundImg.yCoordBg2 = 0;
        }
        batch.draw(backgroundImg.background1, -Constants.VIEWPORT_WIDTH*2, backgroundImg.yCoordBg1, Constants.VIEWPORT_WIDTH * 4, Constants.VIEWPORT_HEIGHT * 4);
        batch.draw(backgroundImg.background2, -Constants.VIEWPORT_WIDTH*2, backgroundImg.yCoordBg2, Constants.VIEWPORT_HEIGHT * 4, Constants.VIEWPORT_WIDTH * 4);

        for(Bullet bul: controller.bullets) {
            bul.render(batch);
            //bul._particleEffect.draw(batch);
            //bul._particleEffect.update(Gdx.graphics.getDeltaTime());
            //bul._particleEffect.scaleEffect(100f);
            //bul._particleEffect.draw(batch);
        }
        inputScreenPleaseWork = new InputScreenPleaseWork(player.position.x,player.position.y,3,3,player);
        for(GameObject go: controller.gameObjects) {
            go.getCurrentSprite().setPosition(go.position.x, go.position.y);
            go.getCurrentSprite().setSize(go.width,go.height);
            go.getCurrentSprite().draw(batch);
        }

        for(int i = 0; i<=controller.health-1; i++)
        {
            healthIcons.get(i).getCurrentSprite().setPosition(player.position.x - healthIcons.get(i).position.x, healthIcons.get(i).position.y);
            healthIcons.get(i).getCurrentSprite().setSize(healthIcons.get(i).width, healthIcons.get(i).height);
            healthIcons.get(i).getCurrentSprite().draw(batch);
        }
        if(controller.doubleShoot)
        {
            specialShot.getCurrentSprite().setPosition(player.position.x + specialShot.getCurrentSprite().getWidth(), specialShot.position.y);
            specialShot.getCurrentSprite().setSize(specialShot.width, specialShot.height);
            specialShot.getCurrentSprite().draw(batch);
        }
        background.updateAndRender(Gdx.graphics.getDeltaTime(), batch);
        if(player.isDead){
            youLoose.position.x = player.position.x;
            youLoose.position.y = player.position.y;
            youLoose.getCurrentSprite().draw(batch);
            exp.position.x = player.position.x;
            exp.position.y = player.position.y;
            exp.render(batch);
            exp.update(Gdx.graphics.getDeltaTime());
        }

        //o el get
        //camera.LookAt(controller.player);
        camera.update();
        _particleEffect.scaleEffect(0.5f);
        _particleEffect.update(Gdx.graphics.getDeltaTime());
        _particleEffect.setPosition(player.position.x, player.position.y);
        _particleEffect.draw(batch);
        exp.render(batch);
        batch.end();
        long t2 = System.nanoTime();
        long delt = t2 - t1;
        //Gdx.app.error("TIME", delt+" ns");

        //camera2.update();
        //cont.draw();
    }

    public void resize(int width, int height)
    {
        //camera.viewportWidth = (Constants.VIEWPORT_WIDTH/height)*width;
        //camera.viewportHeight= (Constants.VIEWPORT_HEIGHT/width)*height;
        //camera.update();
        //cont.resize(width, height);
    }


    public void dispose()
    {
        batch.dispose();
    }
}
