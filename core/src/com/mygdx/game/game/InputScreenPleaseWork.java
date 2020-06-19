package com.mygdx.game.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class InputScreenPleaseWork {
    public TextureRegionDrawable leftArrow;
    public TextureRegionDrawable rightArrow;
    public Player _player;
    public float dir = 0;

    InputScreenPleaseWork(float x1, float y1, float x2, float y2, Player player){
        leftArrow = new TextureRegionDrawable(new Texture(Gdx.files.internal("leftarrow.png")));
        _player = player;
        prepareDirectionButton(x1, y1, x2, y2, _player);
    }

    private void prepareDirectionButton (float x1, float y1, float x2, float y2, final Player _player)
    {
        ImageButton imgButton1 = new ImageButton(leftArrow);
        imgButton1.setPosition(x1, y1);
        imgButton1.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                _player.dirX = -1;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                _player.dirX = 0;
                super.touchUp(event, x, y, pointer, button);
            }
        });
        ImageButton imgButton2 = new ImageButton(rightArrow);
        imgButton1.setPosition(x2, y2);
        imgButton1.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                _player.dirX = 1;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                _player.dirX = 0;
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }
}
