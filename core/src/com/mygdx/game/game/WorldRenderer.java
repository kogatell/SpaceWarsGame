package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;

public class WorldRenderer extends WorldController {
    ScrollingBackground background;
    public static Camera camera;
    public static Camera camera2;
    public static SpriteBatch batch;
    WorldController controller;
    BackgroundImage backgroundImg;
    TextureAtlas particleAtlas;
    ParticleEffect _particleEffect = new ParticleEffect();
    Controller cont;
    public Group controlGroup;
    Player player;
    InputScreenPleaseWork inputScreenPleaseWork;

    public WorldRenderer(WorldController wc)
    {
        background = new ScrollingBackground();
        backgroundImg = new BackgroundImage(0);
        this.controller = wc;
        controlGroup = new Group();
        camera = new Camera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, wc.player);
        //camera2 = new Camera(Constants.VIEWPORT_WIDTH/5, Constants.VIEWPORT_HEIGHT/5);
        player = wc.player;
        camera.LookAt(player);
        _particleEffect.load(Gdx.files.internal("particlesgood.party"), Gdx.files.internal(""));
        _particleEffect.setPosition(5, 5);
        _particleEffect.start();
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
            bul._particleEffect.draw(batch);
            bul._particleEffect.update(Gdx.graphics.getDeltaTime());
            _particleEffect.scaleEffect(100f);
        }
        inputScreenPleaseWork = new InputScreenPleaseWork(player.position.x,player.position.y,3,3,player);
        for(GameObject go: controller.gameObjects) {
            go.getCurrentSprite().setPosition(go.position.x, go.position.y);
            go.getCurrentSprite().setSize(go.width,go.height);
            go.getCurrentSprite().draw(batch);
        }
        background.updateAndRender(Gdx.graphics.getDeltaTime(), batch);
        //o el get
        //camera.LookAt(controller.player);
        camera.update();
        _particleEffect.scaleEffect(0.5f);
        _particleEffect.update(Gdx.graphics.getDeltaTime());
        _particleEffect.draw(batch);
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
